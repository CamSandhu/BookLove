package com.karmadevelop.PenguinPublishers.service;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karmadevelop.PenguinPublishers.model.AuthDetails;

@Service
public class FetchAuthDetails {
		
	@Autowired
	private HttpConnect connect;

	public AuthDetails fetchAuthDetails(String authId) throws IOException, InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		JSONObject json= connect.Connect( "https://openlibrary.org/authors/"+authId+".json");
		
		System.out.println(json);
		
		return null;

	}

}
