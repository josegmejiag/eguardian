package com.umg.usageapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.umg.usageapp.models.Empresa;
import com.umg.usageapp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public final static String GET_USER_BY_ID = "  Select * FROM users   WHERE id = :id";

 
	User findByUsername( String username );
	
	@Query(value = GET_USER_BY_ID , nativeQuery = true)
	User getUSerById(@Param("id") Long id); 
	
	
	
}

