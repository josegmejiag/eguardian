package com.umg.usageapp.util;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;


@Service
public class PinGenerator {

	public String generatePing() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		String formatted = String.format("%05d", num); 
		System.out.println(formatted);
		return formatted;
	}
	
	
}
