package com.umg.usageapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.umg.usageapp.models.Accion;
import com.umg.usageapp.models.AplicacionEvento;
import com.umg.usageapp.models.Evento;

public interface AplicacionEventoRepository  extends JpaRepository<AplicacionEvento,Integer>{
	
	public final static String GET_APP_EVENTO_BY_EMPRESA = "  SELECT * FROM prueba.aplicacion_evento where id_evento = :idEvento";
	
	
	@Query(value = GET_APP_EVENTO_BY_EMPRESA , nativeQuery = true)
	List<AplicacionEvento> getAplicacionEventoByEvento(@Param("idEvento") Integer idEvento);
	
	public final static String GET_DELETE_BY_ID = "DELETE FROM aplicacion_evento WHERE id_evento = :idEvento";

	@Query(value = GET_DELETE_BY_ID , nativeQuery = true)
	void DeleteById(@Param("idEvento") Integer idEvento);

}
