package com.karmadevelop.PenguinPublishers.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.karmadevelop.PenguinPublishers.service.AllRequests;
import com.karmadevelop.PenguinPublishers.service.FetchAuthors;
import com.karmadevelop.PenguinPublishers.service.FetchBook;
import com.karmadevelop.PenguinPublishers.service.FetchWork;

@RestController
public class TheController {

	@Autowired
	private FetchAuthors fetchAuthors;

	@Autowired
	private FetchBook fetchBook;

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");

		return mav;
	}

	/*
	 * @GetMapping("/work/{id}") public String fetchBooks(@PathVariable int id,
	 * Model model) throws IOException, InterruptedException {
	 * 
	 * model = model.addAttribute("Work", fetchWork.fetchWork(id));
	 * 
	 * return "Books";
	 * 
	 * }
	 */

	// fetch all authors
	@GetMapping("/author")
	public ModelAndView FetchAuthor(@RequestParam(name = "query") String authorName, ModelAndView mav)
			throws IOException, InterruptedException {

		mav.addObject("Authors", fetchAuthors.fetchAuthors(authorName.trim())).setViewName("index");

		return mav;

	}

	@GetMapping("/Book")
	public ModelAndView FetchBookISBN(@RequestParam String workID, ModelAndView mav)
			throws IOException, InterruptedException {

		mav.addObject("Book", fetchBook.fetchBook(workID)).setViewName("Book");

		return mav;

	}

	/*
	 * @GetMapping("/bookDisplay") public String fetchBookPicture(@RequestParam
	 * Map<String, String> allParams, Model model) throws IOException,
	 * InterruptedException {
	 * 
	 * String keyword = allParams.get("lastName") + "%20" +
	 * allParams.get("title").replaceAll("\\s", "");
	 * 
	 * model.addAttribute("AuthorLast", allRequests.fetchBooks(keyword));
	 * 
	 * return "index"; }
	 * 
	 * @GetMapping("/bookISBN") public String fetchBookISBNS(Model model) throws
	 * IOException, InterruptedException {
	 * 
	 * model.addAttribute("AuthorLast", allRequests.fetchBooks());
	 * 
	 * return "Books"; }
	 */

}
