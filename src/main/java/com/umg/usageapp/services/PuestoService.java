package com.umg.usageapp.services;

import org.springframework.data.repository.query.Param;

import com.umg.usageapp.models.Puesto;

public interface PuestoService {
	
	Puesto getPuestoByEmpleado(Long email);

}
