package com.karmadevelop.PenguinPublishers.dataValidation;

import org.springframework.stereotype.Component;

@Component
public class DAtaValidation {

	public String removeSpecialCharacters(String str) {
		
	str=str.replaceAll("[0-9A-Za-z]", str);
		
		
		return str;
	}
	
}
