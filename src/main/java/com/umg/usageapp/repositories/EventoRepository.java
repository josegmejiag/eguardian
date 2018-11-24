package com.umg.usageapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.umg.usageapp.models.Evento;
import com.umg.usageapp.models.Puesto;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Integer> {
	
	public final static String GET_EVENTO_BY_EMPRESA = "  SELECT * FROM prueba.evento where id_empresa = :idEmpresa";
	public final static String GET_EVENTO_BY_ID = "  SELECT * FROM prueba.evento where id_evento = :idEvento";

	
	
	@Query(value = GET_EVENTO_BY_EMPRESA , nativeQuery = true)
	List<Evento> getEventosByEmpresa(@Param("idEmpresa") Integer idEmpresa);
	
	@Query(value = GET_EVENTO_BY_ID , nativeQuery = true)
	Evento getEventoById(@Param("idEvento") Integer idEvento);
	
	
	

}
