package com.karmadevelop.PenguinPublishing.controllers;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.karmadevelop.PenguinPublishing.service.AllRequests;

@Controller
public class TheController {
    
	@Autowired
	private AllRequests allRequests;
	
	@GetMapping("/work/{id}")
	public String fetchBooks(@PathVariable int id, Model model) throws IOException, InterruptedException {
		
		model =model.addAttribute("Work" , allRequests.FetchWork(id));
				
		return "Books";
		
	}
	
	
	//fetch all authors
	@GetMapping("/author")
	public String FetchAuthor(@RequestParam(name="lastName") String lastName,Model model) throws IOException, InterruptedException {
		
		model =model.addAttribute("Authors" , allRequests.FetchAuthors(lastName));
				
		return "Authors";
		
	}
	
	@GetMapping("/Book/{isbn}")
	public String FetchBookISBN(@PathVariable Long isbn , Model model) throws IOException, InterruptedException {
		
		model =model.addAttribute("Book" , allRequests.fetchBook(isbn));
				
		return "Books";
		
	}
	

	@GetMapping("/bookDisplay")
	public String fetchBookPicture(@RequestParam Map<String,String> allParams,  Model model) throws IOException, InterruptedException {
		
		String keyword=allParams.get("lastName")+"%20" +allParams.get("title").replaceAll("\\s", "");
		
		model.addAttribute("AuthorLast",    allRequests.fetchBooks( keyword));
		
		return "Books";
	}
	
	/*
	 * @GetMapping("/bookISBN") public String fetchBookISBNS(Model model) throws
	 * IOException, InterruptedException {
	 * 
	 * 
	 * model.addAttribute("AuthorLast", allRequests.fetchBooks());
	 * 
	 * return "Books"; }
	 */
	
}
