package com.umg.usageapp.services;

import java.util.List;

import com.umg.usageapp.models.SectorNegocio;

public interface SectorNegocioService {

	SectorNegocio finById(Integer idSectorNegocio);
	List<SectorNegocio> findAll();
	SectorNegocio createSector(SectorNegocio sectorNegocios);
	SectorNegocio updateSector(SectorNegocio sectorNegocios);
	
}
