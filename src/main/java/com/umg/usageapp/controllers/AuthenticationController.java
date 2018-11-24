package com.umg.usageapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umg.usageapp.auth.JwtAuthenticationRequest;
import com.umg.usageapp.common.DeviceProvider;
import com.umg.usageapp.models.Empresa;
import com.umg.usageapp.models.Pin;
import com.umg.usageapp.models.Puesto;
import com.umg.usageapp.models.User;
import com.umg.usageapp.models.UserTokenState;
import com.umg.usageapp.repositories.PinRepositorie;
import com.umg.usageapp.repositories.UserRepository;
import com.umg.usageapp.security.TokenHelper;
import com.umg.usageapp.services.EmpresaService;
import com.umg.usageapp.services.PinService;
import com.umg.usageapp.services.PuestoService;
import com.umg.usageapp.services.UserService;
import com.umg.usageapp.servicesimpl.CustomUserDetailsService;
import com.umg.usageapp.util.Mailer;
import com.umg.usageapp.util.PasswordGenerator;
import com.umg.usageapp.util.PinGenerator;

import ch.qos.logback.core.net.SyslogOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping( value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE )
public class AuthenticationController {

	
	static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
	
    @Autowired
    TokenHelper tokenHelper;
    
    @Autowired
    EmpresaService  empresaService;
    
    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    PasswordGenerator passwordGenerator;
    
    @Autowired
    Mailer mailer;
    
    @Autowired
    PinGenerator pinGenerator;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	PuestoService puestoService;
	
	@Autowired
	PinService pinService;
	
	@Autowired
	PinRepositorie pinRepositorie;
	
	@Autowired
	UserRepository userRepository ;
   

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest,
            HttpServletResponse response )
            		throws AuthenticationException, IOException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        // Inject into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // token creation
        User user = (User)authentication.getPrincipal();
        Puesto puesto = puestoService.getPuestoByEmpleado(user.getId());
        Integer idEmpresa =puesto.getEmpresa().getId();
        Empresa empresa = empresaService.getEmpresaById(idEmpresa);
        String jws = tokenHelper.generateToken( user.getUsername());
        int expiresIn = tokenHelper.getExpiredIn();
        // Return the token
        return ResponseEntity.ok(new UserTokenState(jws, Long.parseLong(expiresIn+""), user, empresa, puesto));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAuthenticationToken(
            HttpServletRequest request,
            HttpServletResponse response,
            Principal principal
            ) {

        String authToken = tokenHelper.getToken( request );


        if (authToken != null && principal != null) {

            // TODO check user password last update
            String refreshedToken = tokenHelper.refreshToken(authToken);
            int expiresIn = tokenHelper.getExpiredIn();

            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.accepted().body(userTokenState);
        }
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
        Map<String, String> result = new HashMap<>();
        result.put( "result", "success" );
        return ResponseEntity.accepted().body(result);
    }
    
    
    @RequestMapping(value = "/validar_Token", method = RequestMethod.POST)
    public ResponseEntity<?> validarToken(@RequestBody ValidateToken validarToken) {
    	Pin pin = pinService.getPinByPin(validarToken.token);
        Map<String, String> result = new HashMap<>();
 	   Calendar date = Calendar.getInstance();
 	   long t= date.getTimeInMillis();
 	   Date expiracion=new Date();
 	  User usuario = new User();

    	if (pin != null) {
			System.out.println(expiracion);
			System.out.println(pin.getEspiracion());
			
    		if (!pin.getEspiracion().before(expiracion)) { 
    			
    			usuario = userRepository.getUSerById(pin.getUsuario().getId());
    			usuario.setPassword(validarToken.newPassword);
                userDetailsService.resetPassword(usuario);
                result.put( "result", "SUCCESS" );
                pinRepositorie.delete(pin);
			}else {
	            result.put( "result", "PASO DE TIEMPO" );
                pinRepositorie.delete(pin);


			}
    		
		}else {
            result.put( "result", "ERROR" );

		}

        return ResponseEntity.accepted().body(result);
    }
    
     
    
    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public ResponseEntity<?>  resetPassword(@RequestParam String email) throws IOException {
    String response = null;
       User user =  userService.findByUsername(email);
       Map<String, String> result = new HashMap<>();

       
       if(user != null) {
    	   String pass = pinGenerator.generatePing();
    	   
    	   Calendar date = Calendar.getInstance();
    	   long t= date.getTimeInMillis();
    	   Date expiracion=new Date(t + (15 * ONE_MINUTE_IN_MILLIS));
    	   Pin pin = new Pin(Integer.parseInt(pass),expiracion, user);   
    	   pinRepositorie.save(pin);
    	   mailer.executeMailReset(user, pass);
    	   response = "SUCCESS"; 
    	   result.put( "token", pass );

       }else {
    	   response = "ERROR";
       }
       
       result.put( "result", response );
   
       return ResponseEntity.accepted().body(result);
    }
    

    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }
    
    
    static class ValidateToken {
        public int token;
        public String newPassword;
    }
    
}