package com.umg.usageapp.services;

import com.umg.usageapp.models.Pin;

public interface PinService {

	Pin getPinByPin(Integer pin);
	String getEmailByPin(Integer pin);
	
	
	
	
}
