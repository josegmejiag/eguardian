package com.umg.usageapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.umg.usageapp.models.AplicacionEvento;
import com.umg.usageapp.models.Asistentes;

public interface AsistentesRepository extends JpaRepository<Asistentes,Integer> {
	
	public final static String GET_APP_EVENTO_BY_EMPRESA = "SELECT * FROM prueba.asistentes where id_evento = :idEvento";
	
	
	@Query(value = GET_APP_EVENTO_BY_EMPRESA , nativeQuery = true)
	List<Asistentes> getAsistentesByEvento(@Param("idEvento") Integer idEvento);
	
	
	public final static String DELETE_BY_EVENTO = "DELETE FROM aplicacion_evento WHERE id_evento= :idEvento";
	
	
	@Query(value = DELETE_BY_EVENTO , nativeQuery = true)
	void DeleteByEvento(@Param("idEvento") Integer idEvento);
	
	
	

}
