package com.umg.usageapp.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umg.usageapp.model.inputs.UpdateEvento;
import com.umg.usageapp.model.inputs.UserSet;
import com.umg.usageapp.models.Accion;
import com.umg.usageapp.models.AccionEvento;
import com.umg.usageapp.models.Aplicacion;
import com.umg.usageapp.models.AplicacionEvento;
import com.umg.usageapp.models.Asistentes;
import com.umg.usageapp.models.CatalogoPuestos;
import com.umg.usageapp.models.Empresa;
import com.umg.usageapp.models.Evento;
import com.umg.usageapp.models.Monitoreo;
import com.umg.usageapp.models.MonitoreoResult;
import com.umg.usageapp.models.User;
import com.umg.usageapp.repositories.AccionEventoRepository;
import com.umg.usageapp.repositories.AccionRepository;
import com.umg.usageapp.repositories.AplicacionEventoRepository;
import com.umg.usageapp.repositories.AplicacionRepository;
import com.umg.usageapp.repositories.AsistentesRepository;
import com.umg.usageapp.repositories.EmpresaRepository;
import com.umg.usageapp.repositories.EventoRepository;
import com.umg.usageapp.repositories.MonitoreoRepository;
import com.umg.usageapp.services.EmpresaService;
import com.umg.usageapp.services.UserService;
import com.umg.usageapp.util.Mailer;

@RestController
@RequestMapping(value = "/evento")
public class EventoController {
	
	@Autowired
	EventoRepository eventoRepository;
	@Autowired
	EmpresaService empresaService;
	@Autowired
	AplicacionRepository aplicacionRepository;
	@Autowired
	AccionRepository accionRepository;
	@Autowired
	EmpresaRepository  empresaRepository;
	@Autowired
	UserService userService;
	@Autowired 
	AsistentesRepository  asistentesRepository; 
	@Autowired
	AplicacionEventoRepository aplicacionEventoRepository;
	@Autowired
	AccionEventoRepository accionEventoRepository;
	@Autowired
	MonitoreoRepository monitoreoRepository;
	@Autowired
	EntityManager em ;
	@Autowired
	Mailer mailer;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	
    @PostMapping(value = "/registro")
    public ResponseEntity<?> registroEvento(@RequestBody EventoCreator eventoCreator){
    	String result = null;
    
    	try {
    		
//    		User userCReator = userService.findByUsername(principal.getName());
    		User userCReator = userService.findById(new Long(eventoCreator.idcapacitador));
    		
    		
    		
    		
    		Evento evento = new Evento();
        	Empresa empresa = empresaService.getEmpresaById(eventoCreator.idEmpresa);
            int diferencia=(int) ((eventoCreator.fechaInicio.getTime()-eventoCreator.fechaFin.getTime())/1000);
            int horas=(int)Math.floor(diferencia/3600);
            evento.setDuracion(horas+"");
            evento.setEmpresa(empresa);
            evento.setFechaFin(eventoCreator.fechaInicio);
            evento.setFechaInicio(eventoCreator.fechaFin);
            evento.setNombre(eventoCreator.nombre);
            evento.setIdUserCreator(userCReator.getId());
            evento.setEstatus(1);
            eventoRepository.save(evento);
            
            
            for ( Integer accion :eventoCreator.acciones) {
                System.out.println("entrando a acciones");
            		AccionEvento aevento = new AccionEvento();	
            		Accion Acc = accionRepository.getOne(accion);
            		aevento.setEvento(evento);
            		aevento.setAccion(Acc);
            		accionEventoRepository.save(aevento);
            }
            
            for ( Integer aplicacion :eventoCreator.aplicaciones) {
                System.out.println("entrando a Aplicaciones");
                Aplicacion App = aplicacionRepository.getOne(aplicacion);
                AplicacionEvento apevento = new AplicacionEvento();
                apevento.setEvento(evento);
                apevento.setAplicacion(App);
                aplicacionEventoRepository.save(apevento);
                
       
                
            }
            
            for ( Integer usuario :eventoCreator.usuarios) {
                System.out.println("entrando a Asistente");
            	Asistentes asistentes = new Asistentes();
            	User user = userService.findById(new Long(usuario));
            	asistentes.setEvento(evento);
            	asistentes.setUsuario(user);
            	asistentes.setNumeroTelefono(user.getPhoneNumber());
            	asistentesRepository.save(asistentes);
	
            }

            result = "SUCCESS";
			
		} catch (Exception e) {
			System.out.println("error en ala creacion del evento"+e);
            result = "ERROR";
		}

		return ResponseEntity.accepted().body(result);
    }
    
    @Transactional
    @PostMapping(value = "/updateEvento")
    public ResponseEntity<?> updateEvento(@RequestBody EventoUpdate eventoUpdate){
    	String result = null;
    
    	try {
    		
            System.out.println("Actualizando");

    		Evento evento = eventoRepository.getEventoById(eventoUpdate.id);
            System.out.println("evento");




            int diferencia=(int) ((eventoUpdate.fechaInicio.getTime()-eventoUpdate.fechaFin.getTime())/1000);
            int horas=(int)Math.floor(diferencia/3600);
            evento.setIdEvento(eventoUpdate.id);
            evento.setDuracion(horas+"");
            evento.setFechaFin(eventoUpdate.fechaInicio);
            evento.setFechaInicio(eventoUpdate.fechaFin);
            evento.setNombre(eventoUpdate.nombre);
            evento.setEstatus(eventoUpdate.status);
            eventoRepository.save(evento);
            System.out.println("evento");

            

           
            
            Query deleteAcciones = em.createNamedQuery("deleteAccionByEvento");
            deleteAcciones.setParameter("idEvento",evento.getIdEvento());
            deleteAcciones.executeUpdate();
            Query deleteAplicaciones = em.createNamedQuery("deleteAplicacionByEvento");
            deleteAplicaciones.setParameter("idEvento",evento.getIdEvento());
            deleteAplicaciones.executeUpdate();
            Query deleteAsistentes = em.createNamedQuery("deleteAsistentesByEvento");
            deleteAsistentes.setParameter("idEvento",evento.getIdEvento());
            deleteAsistentes.executeUpdate();
            
     
  

            
            for ( Integer accion :eventoUpdate.acciones) {
                System.out.println("entrando a acciones");
                
            		AccionEvento aevento = new AccionEvento();	
            		Accion Acc = accionRepository.getOne(accion);
            		aevento.setEvento(evento);
            		aevento.setAccion(Acc);
            		accionEventoRepository.save(aevento);
            }
            
            for ( Integer aplicacion :eventoUpdate.aplicaciones) {

                System.out.println("entrando a Aplicaciones");
                AplicacionEvento apevento = new AplicacionEvento();
                Aplicacion App = aplicacionRepository.getOne(aplicacion);
                apevento.setEvento(evento);
                apevento.setAplicacion(App);

                aplicacionEventoRepository.save(apevento);
            }
            
            for ( Integer usuario :eventoUpdate.usuarios) {
                System.out.println("entrando a Asistente");
            	Asistentes asistentes = new Asistentes();
            	User user = userService.findById(new Long(usuario));
            	asistentes.setEvento(evento);
            	asistentes.setUsuario(user);
            	asistentes.setNumeroTelefono(user.getPhoneNumber());
            	asistentesRepository.save(asistentes);
	
            }

            result = "SUCCESS";
			
		} catch (Exception e) {
			System.out.println("error en la actualizacion"+e.getStackTrace());
			System.out.println("error en la actualizacion"+e);
			System.out.println("error en la actualizacion"+e.getMessage());
            result = "ERROR";
		}

		return ResponseEntity.accepted().body(result);
    }

    
    
    
    @PostMapping(value = "/getEvento")
    public UpdateEvento getEventoByID (@RequestParam Integer idEvento){ 
    	
    	UpdateEvento updateEvento = new UpdateEvento();
    	
    	List<User> user = new ArrayList<>();
    	List<Aplicacion> aplicacion = new ArrayList<>();
    	List<Accion> accion = new ArrayList<>();
    	Evento evento = eventoRepository.getEventoById(idEvento);
    	
    	List<AplicacionEvento> aplicaciones = aplicacionEventoRepository.getAplicacionEventoByEvento(idEvento);
    	List<AccionEvento> acciones = accionEventoRepository.getAccionEventoByEvento(idEvento);
    	List<Asistentes> asistentes = asistentesRepository.getAsistentesByEvento(idEvento);
    	
    	
    	for (AccionEvento accionEvento : acciones) {
    		
    		Accion acc = accionRepository.getAccionById(accionEvento.getAccion().getIdAccion());
    		accion.add(acc);
    		
    	}
    	
    	for (AplicacionEvento aplicacionEvento : aplicaciones) {
    		
    		Aplicacion app = aplicacionRepository.getAplicacionById(aplicacionEvento.getAplicacion().getIdAplicacion());
    		aplicacion.add(app);
    		
    	}
    	
    	for(Asistentes asistente : asistentes) {
    		
    		User us = userService.findById(asistente.getUsuario().getId());
    		user.add(us);
    		
    	}
    	
    	updateEvento.setAcciones(accion);
    	updateEvento.setAplicaciones(aplicacion);
    	updateEvento.setUsuarios(user);
    	updateEvento.setEvento(evento);
    	
    	
    	
		return updateEvento;

    }
    
    
    
    
    @PostMapping(value = "/getAplicaciones")
    public List<Aplicacion> getAplicaciones (){
		return aplicacionRepository.findAll();

    }
    
    
    @PostMapping(value = "/getAcciones")
    public List<Accion> getAcciones (){
		return accionRepository.findAll();

    }
    
    
    
    @PostMapping(value = "/createAccion")
    @PreAuthorize("permitAll")
    public String createAccion(@RequestBody Accion accion){
    	String response = null;
    	
    	try {
			accionRepository.save(accion);
			response = "SUCCESS";
		} catch (Exception e) {
			response = "FAIL";
		}
		return response ;    	
    }
    
    @PostMapping(value = "/createAplicacion")
    @PreAuthorize("permitAll")
    public String createAplicacion(@RequestBody Aplicacion aplicacion){
    	String response = null;
    	
    	try {
    		aplicacionRepository.save(aplicacion);
    		response = "SUCCESS";
		} catch (Exception e) {
			response = "FAIL";
		}
		return response ;    	
    }
    
    
    @PostMapping(value = "/getEmpleadosByEmpresa")
    public List<User> getEmpleadosByEMpresa(@RequestParam Integer idEmpresa){
    	List<User> users =   empresaService.getUserByEmpresa(idEmpresa);
		return  users;    	
    }
    
    
    
    @PostMapping(value = "/getEventosByEmpresa")
    public List<Evento> getEventosByEMpresa(@RequestParam Integer idEmpresa){
    	List<Evento> eventos = eventoRepository.getEventosByEmpresa(idEmpresa);
		return  eventos;    	
    }
    
    @PostMapping(value = "/RegistarIncidente")
    public Monitoreo Incidente(@RequestBody Incidencia incidencia, Principal principal){
    	String result;
		Monitoreo monitoreo = new Monitoreo();

    	try {
        	
        	
        	Aplicacion aplicacion = aplicacionRepository.getAplicacionById(incidencia.idaplicacion);
        	Accion accion = accionRepository.getAccionById(incidencia.idaccion);
        	Evento evento = eventoRepository.getEventoById(incidencia.idevento);
        	User user = userService.findByUsername(principal.getName());

        	
        	monitoreo.setAccion(accion);
        	monitoreo.setAplicacion(aplicacion);
        	monitoreo.setEvento(evento);
        	monitoreo.setFechaInicio(incidencia.fechaInicio);;
        	monitoreo.setDuracion(incidencia.duracion);
        	monitoreo.setUser(user);
        	monitoreoRepository.save(monitoreo);
            mailer.executeNotifications(monitoreo.getIdMonitoreo());

        	
        	result = "SUCCESS";
		} catch (Exception e) {
        	result = "ERROR";
		}
    	
    	
        return monitoreo;
   	
    }
    
    @Transactional
    @PostMapping(value = "/CierreIncidente")
    public String CierreIncidente(@RequestBody UpdatateIncidencia incidencia){
    	String result;

    	try {
    			
    		Monitoreo monitoreo = monitoreoRepository.getMonitoreoById(incidencia.idIncidencia);
    		
            float diferencia=(float) ((incidencia.fechaFIn.getTime()-monitoreo.getFechaInicio().getTime())/1000);
            float horas=(float)Math.floor(diferencia/3600);
            monitoreo.setDuracion(horas);
    		
    		  Query deleteAsistentes = em.createNamedQuery("updateIncidencia").setParameter("idMonitoreo",incidencia.idIncidencia).
              setParameter("fechaFin", incidencia.fechaFIn).
              setParameter("duracion", horas);
              deleteAsistentes.executeUpdate();
              System.out.println("antes de enviar correo");
              mailer.executeNotifications(incidencia.idIncidencia);
              System.out.println("despues de enviar correo");

        	result = "SUCCESS";
		} catch (Exception e) {
        	result = "ERROR";
        	System.out.println(e);
		}
    	
    	
        return result;
   	
    }
    
    
    @Transactional
    @PostMapping(value = "/getIncidente")
    public List <MonitoreoResult> getIncidente(@RequestParam("idEmpresa") Optional<Integer> idEmpresa, @RequestParam("fechaInicio") Optional<String> fechaInicio,
    		@RequestParam("fechaFin") Optional<String> fechaFin,
    		@RequestParam("idEvento") Optional<Integer> idEvento,
    		@RequestParam("idUsuario") Optional<Integer> idUsuario,
    		@RequestParam("idAplicacion") Optional<Integer> idAplicacion,
    		@RequestParam("idAccion") Optional<Integer> idAccion){


    	
    	String consulta = "select em.nombre as Nombre_Empresa,e.nombre as Nombre_Evento,u.email as Uuario_Genero,u.first_name as Nombre_Usuario,u.last_name as Apellido_Usuario,ap.nombre as Nombre_Aplicacion,ac.nombre as Notificacion_Utilizada , m.fecha_inicio as FECHA_INICIO ,m.fecha_fin as FECHA_FIN,m.duracion as DURACION from monitoreo m inner join evento e on m.id_evento = e.id_evento inner join empresa em  on e.id_empresa = em.id_empresa inner join users u on u.id = m.id inner join aplicacion ap on ap.id_aplicacion = m.id_aplicacion inner join accion ac on ac.id_accion = m.id_accion";

    	
    	if(idEmpresa.isPresent()||idEvento.isPresent()||(fechaFin.isPresent()&&fechaInicio.isPresent())||idUsuario.isPresent()||idAccion.isPresent()||idAplicacion.isPresent()) {
    		consulta = consulta + " where ";
    		int present = 0;
    		
    		if(idEmpresa.isPresent()) {
    			
    			consulta = consulta + "em.id_empresa="+idEmpresa.get();
    			present ++;
    		}
    		if(idEvento.isPresent()) {
    			if (present !=0) {
        			consulta = consulta + " and e.id_evento="+idEvento.get();

    			}else {
        			consulta = consulta + "e.id_evento="+idEvento.get();
        			present++;
    			}
    			
    			
    		}
    		if(fechaFin.isPresent() && fechaInicio.isPresent() ) {
    			

    			Date inicio = generateDate(fechaInicio.get());
    			Date fin = generateDate(fechaFin.get());


    			if (present !=0) {
        			consulta = consulta + " and m.fecha_inicio BETWEEN '"+fechaInicio.get()+"' AND '"+fechaFin.get()+"'";

    			}else {
        			consulta = consulta + " m.fecha_inicio BETWEEN '"+fechaInicio.get()+"' AND '"+fechaFin.get()+"'";
        			present++;
    			}
    			
    			
    		}
    		if(idUsuario.isPresent()) {
    			
    			if (present !=0) {
        			consulta = consulta + " and u.id="+idUsuario.get();

    			}else {
        			consulta = consulta + "u.id="+idUsuario.get();
        			present++;
    			}
    			
    		}
    		if(idAccion.isPresent()) {
    			
    			if (present !=0) {
        			consulta = consulta + " and ac.id_accion="+idAccion.get();

    			}else {
        			consulta = consulta + "ac.id_accion="+idAccion.get();
        			present++;
    			}
    			
    		}
    		if(idAplicacion.isPresent()) {
    			
    			if (present !=0) {
        			consulta = consulta + " and ap.id_aplicacion="+idAplicacion.get();

    			}else {
        			consulta = consulta + "ap.id_aplicacion="+idAplicacion.get();
        			present++;
    			}
    			
    		}
   
    		
    		
    		System.out.println(consulta);

    		
    		
    		
    	}
    	
    	
    	
    	Query resultado =  em.createNativeQuery(consulta);

    	
    	List<Object[]> rows = resultado.getResultList();
    	List<MonitoreoResult> lista = new ArrayList<>(rows.size());
    	for (Object[] row : rows) {
    		lista.add(new MonitoreoResult((String) row[0], (String) row[1], (String) row[2], (String) row[3], (String) row[4],(String) row[5], (String) row[6] , (Date) row[7], (Date) row[8], (Float) row[9] ));
    	}
    	for(MonitoreoResult moni : lista) {
    	}
    	
    	
        return lista;
   	
    }
    

    
    static class EventoCreator {
        public String nombre ;
        public Date fechaInicio;
        public Date fechaFin;
        public Integer idEmpresa;
        public Integer idcapacitador;
        public List<Integer> usuarios;
        public List<Integer> aplicaciones;
        public List<Integer> acciones;     
    }
    
    static class EventoUpdate {
    	public Integer id ;
        public String nombre ;
        public Date fechaInicio;
        public Date fechaFin;
        public Integer idcapacitador;
        public Integer status;
        public List<Integer> usuarios;
        public List<Integer> aplicaciones;
        public List<Integer> acciones;     
    }
    
    
    static class AplicacionCreate {
        public String nombre ;
        public Date fechaInAcio;
        public Date fechaFin;
        public Integer idEmpresa;
    }
    
    static class AccionCreate {
        public String nombre ;
        public Date fechaInAcio;
        public Date fechaFin;
        public Integer idEmpresa;
    }
    
    static class Incidencia {

        public Integer idevento;
        public Integer idaplicacion;
        public Integer idaccion;
        public Float duracion;
        public Date fechaInicio;
        
    }
    
    static class UpdatateIncidencia {

        public Integer idIncidencia;
        public Date fechaFIn;
  
    }
    

    private Date generateDate(String Date) {
    	
		
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();
        
        try {

             date = formatter.parse(Date);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    	
    	
		return date;
    	
    }
	
    
}
