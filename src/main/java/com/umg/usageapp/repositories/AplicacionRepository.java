package com.umg.usageapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.umg.usageapp.models.Aplicacion;
import com.umg.usageapp.models.Evento;

public interface AplicacionRepository extends JpaRepository<Aplicacion,Integer> {

	
	
	public final static String GET_ACCION_BY_ID = "  SELECT * FROM prueba.aplicacion where id_aplicacion = :idAplicacion";

	@Query(value = GET_ACCION_BY_ID , nativeQuery = true)
	Aplicacion getAplicacionById(@Param("idAplicacion") Integer idAplicacion);
	
}
