package com.umg.usageapp.servicesimpl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.usageapp.models.Empresa;
import com.umg.usageapp.models.User;
import com.umg.usageapp.repositories.EmpresaRepository;
import com.umg.usageapp.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {
	
	@Autowired 
	EmpresaRepository empresaRepository;
	
	@Autowired
	EntityManager em;

	
	@Override
	public Empresa getEmpresaById(Integer id) {
		Empresa empresa = empresaRepository.getEmpresaById(id);
		return empresa;
	}

	@Override
	public Empresa findUseradmin(Integer idEmpresa) {

		return null;
	}

	@Override
	public Empresa createEmpresa(Empresa empresa) {
		return 		empresaRepository.save(empresa);
	}

	@Override
	public Empresa updateEmpresa(Empresa empresa) {
		Empresa empresaold = empresaRepository.getOne(empresa.getId());
		empresaold = empresa;
		return empresa;
	}

	@Override
	public String findByName(String name) {
		Empresa empresa = empresaRepository.getEmpresaByName(name);
		
		
		return empresa.getNombre();
	}

	@Override
	public List<User> getUserByEmpresa(Integer id_empresa) {
	
		 List<User> employees = em.createNamedQuery("getUserbyEmpresa", User.class)
				 .setParameter("idEmpresa", id_empresa)
                 .getResultList();
	
		return employees;
	}

}
