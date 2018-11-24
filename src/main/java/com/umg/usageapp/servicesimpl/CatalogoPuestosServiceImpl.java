package com.umg.usageapp.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.usageapp.models.CatalogoPuestos;
import com.umg.usageapp.models.Puesto;
import com.umg.usageapp.models.SectorNegocio;
import com.umg.usageapp.repositories.CatalogoPuestosRepository;
import com.umg.usageapp.services.CatalogoPuestosService;


@Service
public class CatalogoPuestosServiceImpl implements CatalogoPuestosService{

	
	@Autowired
	CatalogoPuestosRepository catalogoPuestosRepository;
	
	@Override
	public List<CatalogoPuestos> getAllCatalogoPuestos() {
		return catalogoPuestosRepository.findAll();
	}

	@Override
	public CatalogoPuestos findById(Integer id) {
		return catalogoPuestosRepository.getOne(id) ;
	}

	@Override
	public CatalogoPuestos createPuesto(CatalogoPuestos puesto) {
		// TODO Auto-generated method stub
		return catalogoPuestosRepository.save(puesto);
	}

	@Override
	public CatalogoPuestos updatePuesto(CatalogoPuestos puesto) {

		CatalogoPuestos puesto1 = catalogoPuestosRepository.getOne(puesto.getIdCatalogoPuestos());
		
		if(puesto1 != null) {
			catalogoPuestosRepository.save(puesto);
		}
		return puesto1;
	}
	
	

}
