package com.umg.usageapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.umg.usageapp.models.Monitoreo;
import com.umg.usageapp.models.Puesto;

@Repository
public interface MonitoreoRepository extends JpaRepository<Monitoreo,Integer> {
public final static String GET_MONITOREO_BY_ID = "  Select * FROM monitoreo WHERE id_monitoreo = :idMonitoreo";
	
	
	@Query(value = GET_MONITOREO_BY_ID , nativeQuery = true)
	Monitoreo getMonitoreoById(@Param("idMonitoreo") Integer idMonitoreo);
	
	

}
