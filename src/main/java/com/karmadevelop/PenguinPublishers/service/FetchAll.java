package com.karmadevelop.PenguinPublishers.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karmadevelop.PenguinPublishers.model.Author;
import com.karmadevelop.PenguinPublishers.model.Book;

@Service
public class FetchAll {

	@Autowired
	private HttpConnect connect;

	@Autowired
	private FetchBook fetchBook;

	public List<Book> fetchAll(String title, Integer offset) throws IOException, InterruptedException {

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		List<Book> books = new ArrayList<>();

		title = title.replaceAll(" ", "%20");

		JSONObject json = connect
				.Connect("http://openlibrary.org/search.json?title=" + title + "&limit=10&offset=" + offset);

		System.out.println(json);

		JSONArray jsonArray = json.getJSONArray("docs");

		for (int i = 0; i < jsonArray.length(); i++) {

			String[] key = ((jsonArray.getJSONObject(i)).get("key").toString().split("/"));

			books.add(fetchBook.fetchBook(key[2]));

		}

		System.out.println(json);
		
		System.out.println(json.get("offset"));
		if ((Integer) json.get("numFound") > 10) {
			offset = offset + 10;
		}
		
		books.get(books.size() - 1).setOffset(offset);
		
		books.get(books.size() - 1).setQuery(title);

		return books;
	}

}
