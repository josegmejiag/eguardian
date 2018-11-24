package com.umg.usageapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.umg.usageapp.models.Empresa;
import com.umg.usageapp.models.User;

public interface EmpresaService {
	
	Empresa getEmpresaById(Integer id);
	String findByName(String name);
	Empresa findUseradmin(Integer idEmpresa);
	Empresa createEmpresa(Empresa empresa);
	Empresa updateEmpresa(Empresa empresa);
	List<User> getUserByEmpresa(Integer id_empresa);
	
}
