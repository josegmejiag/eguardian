package com.umg.usageapp.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.usageapp.models.Pin;
import com.umg.usageapp.repositories.PinRepositorie;
import com.umg.usageapp.services.PinService;

@Service
public class PinServiceImpl implements PinService{

	@Autowired
	PinRepositorie pinRepositorie;
	
	
	@Override
	public Pin getPinByPin(Integer pin) {
		return pinRepositorie.getPinByPin(pin);
	}

	@Override
	public String getEmailByPin(Integer pin) {

		return pinRepositorie.getEmailByPin(pin);
	}

}
