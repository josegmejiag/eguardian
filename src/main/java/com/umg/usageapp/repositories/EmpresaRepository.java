package com.umg.usageapp.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.umg.usageapp.models.Empresa;
import com.umg.usageapp.models.User;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Integer> {
	
	public final static String GET_EMPRESA_BY_NAME = "  Select * FROM empresa em WHERE em.nombre = :nombre" +"   limit 1";

	
	public final static String GET_EMPRESA_BY_ID = "  Select * FROM empresa em  WHERE em.id_empresa = :id_empresa";
	
	public final static String GET_USERS_BY_IDEMPRESA = "SELECT u.* FROM puesto p inner join users u on p.id_usuario = u.id  where p.id_empresa = :id_empresa";


	
	@Query(value = GET_EMPRESA_BY_NAME , nativeQuery = true)
	Empresa getEmpresaByName(@Param("nombre") String nombre); 
	
	 
	@Query(value = GET_EMPRESA_BY_ID , nativeQuery = true)
	Empresa getEmpresaById(@Param("id_empresa") Integer id_empresa); 
	




	

}
