package com.umg.usageapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.umg.usageapp.models.Negocios;


@Repository
public interface NegociosRepository extends JpaRepository<Negocios,Integer> {
	
	public final static String GET_NEGOCIOS_BY_EMPRESA = "  Select neg FROM Negocios neg   WHERE neg.Empresa.id = :idEmpresa";
	
	@Query(value = GET_NEGOCIOS_BY_EMPRESA, nativeQuery = true)
	List<Negocios> getNegociosByIdEmpresa(@Param("idEmpresa")Integer idEmpresa);
	
	

}
