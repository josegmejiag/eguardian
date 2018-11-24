package com.umg.usageapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.umg.usageapp.models.Empresa;
import com.umg.usageapp.models.Pin;

public interface PinRepositorie extends JpaRepository<Pin,Integer> {

	public final static String GET_USER_BY_PIN= "  Select pin.usuario.email FROM pin   WHERE pin = :pin";
	public final static String GET_PIN_BY_PIN= "  Select * FROM pin   WHERE ping = :pin";

	
	@Query(value = GET_USER_BY_PIN , nativeQuery = true)
	String getEmailByPin(@Param("pin") Integer pin); 	
	
	@Query(value = GET_PIN_BY_PIN , nativeQuery = true)
	Pin getPinByPin(@Param("pin") Integer pin); 	
	
	
}
