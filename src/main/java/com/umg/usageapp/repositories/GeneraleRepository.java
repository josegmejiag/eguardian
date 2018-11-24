package com.umg.usageapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umg.usageapp.models.Generales;

@Repository
public interface GeneraleRepository extends JpaRepository<Generales,Integer>{

}
