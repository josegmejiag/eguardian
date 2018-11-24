package com.umg.usageapp.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.usageapp.models.Negocios;
import com.umg.usageapp.repositories.NegociosRepository;
import com.umg.usageapp.services.NegociosService;

@Service
public class NegociosServiceImlp implements NegociosService {
	
	@Autowired
	NegociosRepository negociosRepository;

	@Override
	public Negocios registerNegocios(Negocios negocios) {
		return 		negociosRepository.save(negocios);
	}

	@Override
	public List<Negocios> getAllNegociosByIdEmpresa(Integer idEmpresa) {
		return negociosRepository.getNegociosByIdEmpresa(idEmpresa);
	}

}
