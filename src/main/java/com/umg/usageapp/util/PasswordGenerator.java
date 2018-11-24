package com.umg.usageapp.util;


import java.util.Random;


import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {

	Random rand = new Random();
	
	public String genrar () {
		String out = null;
		char[] values1 = {'w','e','j','c','s','p','a','m','S','M','A','H','E','V'};
	    char[] values3 = {'1','2','3','4','5','6','7','8','9','0'};
	    String out1="";
	    String out2="";
	    String out3="";
	 
	    for (int i=0;i<6;i++)
	            {
	             int idx=rand.nextInt(values1.length);
	            out1+= values1[idx];
	            }
	 
	    for (int i=0;i<4;i++)
	            {
	            int idx=rand.nextInt(values3.length);
	             out2+= values3[idx];
	            }

	 
	    out= out1.concat(out3).concat(out2);
	    return out;
	}

}
