package com.karmadevelop.PenguinPublishers.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.karmadevelop.PenguinPublishers.service.AllRequests;
import com.karmadevelop.PenguinPublishers.service.FetchAuthDetails;
import com.karmadevelop.PenguinPublishers.service.FetchAuthors;
import com.karmadevelop.PenguinPublishers.service.FetchBook;
import com.karmadevelop.PenguinPublishers.service.FetchWork;

@RestController
public class TheController {

	@Autowired
	AllRequests allRequests;

	@Autowired
	FetchBook fetchBook;

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

		mav.addObject("Authors", allRequests.fetchAuthors(authorName.trim())).setViewName("index");

		return mav;

	}

	@GetMapping("/title")
	public ModelAndView FetchAll(@RequestParam(name = "query") String title,
			@RequestParam(name = "offset", required = false) Integer offset, ModelAndView mav)
			throws IOException, InterruptedException {

		if (offset == null)
			offset =0;


		mav.addObject("Book", allRequests.fetchAll(title.trim(), offset)).setViewName("Book");

		return mav;

	}

	@GetMapping("/book")
	public ModelAndView FetchBookISBN(@RequestParam(name = "key") String workID, ModelAndView mav)
			throws IOException, InterruptedException {

		mav.addObject("Book", fetchBook.fetchBook(workID)).setViewName("Book");

		return mav;

	}

	@GetMapping("/books")
	public ModelAndView FetchWorks(@RequestParam(name = "key") String authId,
			@RequestParam(name = "offset") Integer offset, ModelAndView mav) throws IOException, InterruptedException {

		mav.addObject("Book", allRequests.fetchWork(authId, offset));

		mav.setViewName("Book");

		return mav;

	}

	@GetMapping("/authorDetails")
	public ModelAndView FetchAuthorDetails(@RequestParam(name = "key") String authId, ModelAndView mav)
			throws IOException, InterruptedException {

		return null;

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
