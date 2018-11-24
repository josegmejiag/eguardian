package com.umg.usageapp.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.usageapp.models.Puesto;
import com.umg.usageapp.repositories.PuestoRepository;
import com.umg.usageapp.services.PuestoService;

@Service
public class PuestoServiceImpl implements PuestoService{

@Autowired
PuestoRepository puestoRepository;
	
	
	@Override
	public Puesto getPuestoByEmpleado(Long idEmpleado) {
		return puestoRepository.getPuestoByEmpleado(idEmpleado);
	}

}
