package com.umg.usageapp.services;

import java.util.List;

import com.umg.usageapp.models.Negocios;

public interface NegociosService {
	
	
	Negocios registerNegocios (Negocios negocios);
	List<Negocios> getAllNegociosByIdEmpresa(Integer idEmpresa);

}
