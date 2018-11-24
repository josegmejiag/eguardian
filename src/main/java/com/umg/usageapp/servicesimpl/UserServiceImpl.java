package com.umg.usageapp.servicesimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.umg.usageapp.models.User;
import com.umg.usageapp.repositories.UserRepository;
import com.umg.usageapp.services.UserService;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * Created by fan.jin on 2016-10-15.
 */



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    
	@Autowired
	EntityManager em;

    @Override
    public User findByUsername( String username ) throws UsernameNotFoundException {
        User u = userRepository.findByUsername( username );
        return u;
    }

    public User findById( Long id )  {
        User u = em.createNamedQuery("getUserbyId", User.class)
				 .setParameter("idUser", id)
                 .getSingleResult();        
        return u;
    }

    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}
}
