package com.umg.usageapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.umg.usageapp.models.CatalogoPuestos;
import com.umg.usageapp.models.Puesto;

@Service
public interface CatalogoPuestosService {
	
	List<CatalogoPuestos> getAllCatalogoPuestos();
	CatalogoPuestos findById(Integer id);
	CatalogoPuestos createPuesto(CatalogoPuestos puesto);
	CatalogoPuestos updatePuesto(CatalogoPuestos puesto);
	

}
