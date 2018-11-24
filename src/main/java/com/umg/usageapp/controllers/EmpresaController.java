package com.umg.usageapp.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umg.usageapp.model.inputs.EmpresaCreate;
import com.umg.usageapp.models.CatalogoPuestos;
import com.umg.usageapp.models.Empresa;
import com.umg.usageapp.models.Negocios;
import com.umg.usageapp.models.Puesto;
import com.umg.usageapp.models.Role;
import com.umg.usageapp.models.SectorNegocio;
import com.umg.usageapp.models.User;
import com.umg.usageapp.repositories.EmpresaRepository;
import com.umg.usageapp.repositories.PuestoRepository;
import com.umg.usageapp.repositories.RoleRepository;
import com.umg.usageapp.services.CatalogoPuestosService;
import com.umg.usageapp.services.EmpresaService;
import com.umg.usageapp.services.NegociosService;
import com.umg.usageapp.services.SectorNegocioService;
import com.umg.usageapp.services.UserService;
import com.umg.usageapp.servicesimpl.SectorNegociosServiceImpl;
//import com.umg.usageapp.util.Mailer;
import com.umg.usageapp.util.Mailer;
import com.umg.usageapp.util.PasswordGenerator;

@RestController
@RequestMapping(value = "/registro")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private SectorNegociosServiceImpl sectorNegociosServiceImpl;
	
	@Autowired
	private NegociosService negociosService;
	
	@Autowired
	Mailer mailer; 
	
	@Autowired
	PasswordGenerator passwordGenerator;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CatalogoPuestosService catalogoPuestosService;
	
	@Autowired
	PuestoRepository puestoRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	private static final  String CREATE_REGISTER_EMPRESA = "createEmpresa";
	
    @PostMapping(value = "/crearEmpresa")
    public String create(@RequestBody createEmpresa empresaCReate){
    	
    	String response = null;
    	Empresa empresa = new Empresa();
    	User usuario = new User();
    	Puesto puesto = new Puesto();
    	Role role = new Role();
    	String empresaVerification = "nohay";
    	String usuarioVerification = "nohay";
    	

    	try {
        	 empresaVerification = empresaRepository.getEmpresaByName(empresaCReate.nombreEmpresa).getNombre();
        	 usuarioVerification = userService.findByUsername(empresaCReate.email).getEmail();
        	 
		} catch (NullPointerException e) {

		System.out.println(e);
		}
    	

    	System.out.println(empresaVerification);
    	
    	if (empresaVerification == "nohay" && usuarioVerification == "nohay" ) { 
    try {  	
    	empresa.setDescripcion(empresaCReate.descripcionEmpresa);
    	empresa.setNombre(empresaCReate.nombreEmpresa);
    	empresa.setDireccion(empresaCReate.direccionEmpresa);
    	empresa.setLogo(empresaCReate.logo);
    	empresa.setNumeroColaboradores(empresaCReate.numeroColaboradoresEmpresa);
    	empresa.setStatus(0);
    	empresa.setTelefono(empresaCReate.telefono);
    	empresa = empresaService.createEmpresa(empresa);
    	usuario.setLastName(empresaCReate.apellidosUsuario);
    	usuario.setFirstName(empresaCReate.nombreUsuario);
    	String pass = passwordGenerator.genrar();
    	usuario.setPassword(passwordEncoder.encode(empresaCReate.contrasenia));
    	usuario.setUsername(empresaCReate.email);
    	usuario.setEmail(empresaCReate.email);
    	//esta sera la validacion de 2 pasos 
    	usuario.setEnabled(true);
    	usuario = userService.createUser(usuario);

    	puesto.setCatalogoPuestos(catalogoPuestosService.findById(empresaCReate.puesto));
    	puesto.setEmpresa(empresa);
    	puesto.setNombre("Administrador");
    	puesto.setUsuarios(usuario);
    	puestoRepository.save(puesto);
    	role.setAuthorityId(2);
    	role.setIdUser(usuario.getId().intValue());
    	roleRepository.save(role);
    	

    	for(int negocio : empresaCReate.idNegocios){
        	Negocios negocios = new Negocios();
        	negocios.setEmpresa(empresa);
        	negocios.setSectornegocio(sectorNegociosServiceImpl.finById(negocio));
        	negocios.setFecha(new Date());
        	negociosService.registerNegocios(negocios);
    	}
    	mailer.executeMailCreate(usuario);
        	response = "SUCCESS";
		} catch (Exception e) {
        	response = "ERROR";
		}
    }else {
    	response = "EMPRESA YA EXISTE";
    }
    	
		return response ;

    	
    }
    
 
    @PostMapping(value = "/getPuestos")
    @PreAuthorize("permitAll")
    public List<CatalogoPuestos> getAllPuestos(){
		return catalogoPuestosService.getAllCatalogoPuestos() ;    	
    }
    
    
    @PostMapping(value = "/createPuesto")
    @PreAuthorize("permitAll")
    public String createPuesto(@RequestBody CatalogoPuestos puesto){
    	String response = null;
    	
    	try {
			catalogoPuestosService.createPuesto(puesto);
			response = "SUCCESS";
		} catch (Exception e) {
			response = "FAIL";
		}
		return response ;    	
    }
    
    @PostMapping(value = "/updatePuesto")
    @PreAuthorize("permitAll")
    public String updatePuesto(@RequestBody CatalogoPuestos puesto){
    	String response = null;
    	
    	try {
			catalogoPuestosService.updatePuesto(puesto);
			response = "SUCCESS";
		} catch (Exception e) {
			response = "FAIL";
		}
		return response ;    	
    }
  
    
    @PostMapping(value = "/getSectorNegocio")
    @PreAuthorize("permitAll")
    public List<SectorNegocio> getAllNegocios(){
		return sectorNegociosServiceImpl.findAll() ;    	
    }
    

    @PostMapping(value = "/createSectorNegocio")
    @PreAuthorize("permitAll")
    public String createSectorNegocio(@RequestBody SectorNegocio sectorNegocio){
    	String response = null;
    	
    	try {
    		sectorNegociosServiceImpl.createSector(sectorNegocio);
			response = "SUCCESS";
		} catch (Exception e) {
			response = "FAIL";
		}
		return response ;    	
    }
    
    
    @PostMapping(value = "/updateNegocio")
    @PreAuthorize("permitAll")
    public String updateNegocio(@RequestBody SectorNegocio sectorNegocio){
    	String response = null;
    	
    	try {
    		sectorNegociosServiceImpl.createSector(sectorNegocio);
			response = "SUCCESS";
		} catch (Exception e) {
			response = "FAIL";
		}
		return response ;    	
    }

    
    static class createEmpresa {
        public String nombreUsuario;
        public String apellidosUsuario;
        public String contrasenia;
        public String email;
        public String telefono;
        public List<Integer> idNegocios;
        public int puesto;
        public String nombreEmpresa;
        public String direccionEmpresa;
        public int numeroColaboradoresEmpresa;
        public String telefonoEmpresa;
        public String logo;
        public String descripcionEmpresa;
        public String genero;



    }
    

}
