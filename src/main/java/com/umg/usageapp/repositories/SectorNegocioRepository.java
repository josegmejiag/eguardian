package com.umg.usageapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umg.usageapp.models.SectorNegocio;

@Repository
public interface SectorNegocioRepository extends JpaRepository<SectorNegocio,Integer> {

}
