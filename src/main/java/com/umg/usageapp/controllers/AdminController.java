package com.umg.usageapp.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umg.usageapp.models.Empresa;
import com.umg.usageapp.models.Pin;
import com.umg.usageapp.models.Puesto;
import com.umg.usageapp.models.Role;
import com.umg.usageapp.models.User;
import com.umg.usageapp.repositories.CatalogoPuestosRepository;
import com.umg.usageapp.repositories.PuestoRepository;
import com.umg.usageapp.repositories.RoleRepository;
import com.umg.usageapp.repositories.UserRepository;
import com.umg.usageapp.services.CatalogoPuestosService;
import com.umg.usageapp.services.EmpresaService;
import com.umg.usageapp.services.UserService;
import com.umg.usageapp.util.Mailer;
import com.umg.usageapp.util.PasswordGenerator;

@RestController
@RequestMapping( value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE )
public class AdminController { 
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordGenerator  passwordGenerator;
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	PuestoRepository puestoRepository;
	
	@Autowired
	CatalogoPuestosService catalogoPuestosService;
	
	@Autowired
	CatalogoPuestosRepository catalogoPuestosRepository;
	
	@Autowired
	EmpresaService empresaService;
	
	@Autowired
	Mailer mailer;
	
	@Autowired
	EntityManager em ;
	
    @SuppressWarnings("unused")
    @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<?>  createUser(@RequestBody CreateUser createUser , Principal userLog) throws IOException {
    	String response = null;
    	 User user = new User();
    	 user = userService.findByUsername(createUser.email);
    	 String pass = passwordGenerator.genrar();
         Map<String, String> result = new HashMap<>();


    	 User userAdmin = userService.findByUsername(userLog.getName());
    	 Puesto puestoAdmin = puestoRepository.getPuestoByEmpleado(userAdmin.getId());
    	 Empresa empresa = empresaService.getEmpresaById(puestoAdmin.getEmpresa().getId());


    	if (userService.findByUsername(createUser.email) == null ) {
    		user = new User();
    	 	Puesto puesto = new Puesto();
    	 	Role role = new Role();
    	 	System.out.println(createUser.email);
    		user.setEmail(createUser.email);
    		user.setUsername(createUser.email);
    		user.setPhoneNumber(createUser.phoneNumber);
    		
    		user.setEnabled(true);
    		user.setFirstName(createUser.firstName);
    		user.setLastName(createUser.lastName);
    		user.setGenero(createUser.genero);
    		user.setPassword(passwordEncoder.encode(pass));
    		userService.createUser(user);
    		System.out.println("user"+user);
    		
        	puesto.setCatalogoPuestos(catalogoPuestosRepository.getPuestoID(createUser.puesto));
        	puesto.setEmpresa(empresa);
        	puesto.setNombre(catalogoPuestosRepository.getPuestoID(createUser.puesto).getNombre());
        	puesto.setUsuarios(user);
        	puestoRepository.save(puesto);
        	role.setAuthorityId(createUser.role);
        	role.setIdUser(user.getId().intValue());
        	roleRepository.save(role);
    		response = "SUCCESS";
    		mailer.executeNewUSer(user, pass);

    	}else {
    	}

        result.put( "result",response);
       return ResponseEntity.accepted().body(result);
    }
    

    @SuppressWarnings("unused")
    @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<?>  deleteUser(@RequestParam Integer idUser) throws IOException {
    	String response = null;
         Map<String, String> result = new HashMap<>();
         String query = "DELETE FROM asistentes WHERE id_usuario= "+idUser;
         String query2 = "DELETE FROM monitoreo WHERE id ="+idUser;
         try {
        	 User user = userService.findById(new Long(idUser));
             Puesto puesto = puestoRepository.getPuestoByEmpleado(user.getId());
             puestoRepository.delete(puesto);	
             userRepository.delete(user);
             
         	Query resultado =  em.createNativeQuery(query);
         	Query resultado1 =  em.createNativeQuery(query2);

             
             
             response = "SUCCESS";
		} catch (Exception e) {
            response = "ERROR";
            System.out.println(e);
		}

         
         	
         
         

    	         result.put( "result",response);
       return ResponseEntity.accepted().body(result);
    }

    
    
    
    
    
    @RequestMapping(value = "/getRoles", method = RequestMethod.POST)
    public List<Roles>  getRoles() throws IOException {
     
    	List<Roles> roles = new ArrayList<Roles>();
    	
    	Roles roles1 = new Roles();
    	Roles roles2 = new Roles();
    	roles1.Descripcion = "ROLE_USUARIO";
    	roles2.Descripcion = "ROLE_CAPACITADOR";
    	roles1.idRole = 1;
    	roles2.idRole = 3 ;
    	roles.add(roles1);
    	roles.add(roles2);
       return roles;
    }
	
	
	
    static class CreateUser {
        public String firstName;
        public String lastName;
        public String email;
        public String genero;
        public int role;
        public String phoneNumber;
        public int puesto;
        
    }
    
    
    static class Roles {
        public int idRole;
        public String Descripcion;
      
        
    }
	
	

	
	
	
}
