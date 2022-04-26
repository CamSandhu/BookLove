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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karmadevelop.PenguinPublishing.service.AllRequests;
import com.karmadevelop.PenguinPublishing.service.AllServices;
import com.karmadevelop.PenguinPublishing.service.FetchAuthors;
import com.karmadevelop.PenguinPublishing.service.FetchBook;
import com.karmadevelop.PenguinPublishing.service.FetchWork;

@Controller
public class TheController {

	@Autowired
	private FetchAuthors fetchAuthors;

	@Autowired
	private FetchBook fetchBook;

	/*
	 * @RequestMapping("/") public String index() { return "index"; }
	 */

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
	public String FetchAuthor(@RequestParam(name = "authorName") String authorName, Model model)
			throws IOException, InterruptedException {

		model = model.addAttribute("Authors", fetchAuthors.fetchAuthors(authorName.trim()));
		return "index";

	}

	@GetMapping("/Book")
	public String FetchBookISBN(@RequestParam Long workID, Model model) throws IOException, InterruptedException {

		model = model.addAttribute("Book", fetchBook.fetchBook(workID));

		return "index";

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
