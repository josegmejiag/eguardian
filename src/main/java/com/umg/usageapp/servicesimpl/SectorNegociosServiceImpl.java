package com.umg.usageapp.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.usageapp.models.SectorNegocio;
import com.umg.usageapp.repositories.SectorNegocioRepository;
import com.umg.usageapp.services.SectorNegocioService;

@Service
public class SectorNegociosServiceImpl implements SectorNegocioService {

	@Autowired
	SectorNegocioRepository sectorNegocioRepository;
	
	@Override
	public SectorNegocio finById(Integer idSectorNegocio) {
		return sectorNegocioRepository.getOne(idSectorNegocio);
	}

	@Override
	public List<SectorNegocio> findAll() {
		return sectorNegocioRepository.findAll();
	}

	@Override
	public SectorNegocio createSector(SectorNegocio sectorNegocios) {
		return sectorNegocioRepository.save(sectorNegocios);
	}

	@Override
	public SectorNegocio updateSector(SectorNegocio sectorNegocios) {
		SectorNegocio sectornegocio = sectorNegocioRepository.getOne(sectorNegocios.getIdSectorNegocio());
		
		if(sectornegocio != null) {
			sectorNegocioRepository.save(sectorNegocios);
		}
		
		return sectornegocio;
	}



}
