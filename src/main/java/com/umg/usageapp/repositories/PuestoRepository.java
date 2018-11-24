package com.umg.usageapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.umg.usageapp.models.Empresa;
import com.umg.usageapp.models.Puesto;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto,Integer> {


	public final static String GET_PUESTO_BY_EMAIL = "  Select * FROM puesto WHERE puesto.id_usuario = :usuario";
	
	
	@Query(value = GET_PUESTO_BY_EMAIL , nativeQuery = true)
	Puesto getPuestoByEmpleado(@Param("usuario") Long email);
	
}
