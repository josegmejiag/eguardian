package com.umg.usageapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.umg.usageapp.models.CatalogoPuestos;
import com.umg.usageapp.models.Empresa;


@Repository
public interface CatalogoPuestosRepository extends JpaRepository<CatalogoPuestos,Integer> {
	
	
	public final static String GET_PUESTO_BY_ID = "  Select * FROM catalogo_puestos em  WHERE id_catalogo_puestos = :id";

	
	@Query(value = GET_PUESTO_BY_ID , nativeQuery = true)
	CatalogoPuestos getPuestoID(@Param("id") Integer id);
	
	
	
}
