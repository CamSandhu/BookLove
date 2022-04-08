package com.karmadevelop.PenguinPublishing.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.karmadevelop.PenguinPublishing.service.AllRequests;

@Controller
public class TheController {
    
	@Autowired
	private AllRequests allRequests;
	
	@GetMapping("/works/{id}")
	public String FetchTitles(@PathVariable int id, Model model) throws IOException, InterruptedException {
		
		model =model.addAttribute("Work" , allRequests.FetchTitles(id));
				
		return "Books";
		
	}

}
