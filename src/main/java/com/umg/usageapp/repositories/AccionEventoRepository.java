package com.umg.usageapp.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.umg.usageapp.models.Accion;
import com.umg.usageapp.models.AccionEvento;
import com.umg.usageapp.models.Evento;
import com.umg.usageapp.models.User;


@Repository
public interface AccionEventoRepository extends JpaRepository<AccionEvento,Integer> {
	
	public final static String GET_ACCION_EVENTO_BY_EVENTO = "  SELECT * FROM prueba.accion_evento where id_evento = :idEvento";
	public final static String GET_DELETE_BY_ID = "DELETE FROM prueba.accion_evento WHERE id_evento = :idEvento";
	
	
    

	
	@Query(value = GET_ACCION_EVENTO_BY_EVENTO , nativeQuery = true)
	List<AccionEvento> getAccionEventoByEvento(@Param("idEvento") Integer idEvento);
	



	

}