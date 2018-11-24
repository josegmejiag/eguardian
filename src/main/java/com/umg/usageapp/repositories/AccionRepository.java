package com.umg.usageapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.umg.usageapp.models.Accion;
import com.umg.usageapp.models.Evento;

public interface AccionRepository extends JpaRepository<Accion,Integer> {
	
	public final static String GET_ACCION_BY_ID = "  SELECT * FROM prueba.accion where id_accion = :idAccion";

	@Query(value = GET_ACCION_BY_ID , nativeQuery = true)
	Accion getAccionById(@Param("idAccion") Integer idAccion);
	
	
	public final static String GET_DELETE_BY_ID = "  SELECT * FROM prueba.accion where id_accion = :idAccion";

	@Query(value = GET_DELETE_BY_ID , nativeQuery = true)
	Accion DeleteById(@Param("idAccion") Integer idAccion);
	
	
}
