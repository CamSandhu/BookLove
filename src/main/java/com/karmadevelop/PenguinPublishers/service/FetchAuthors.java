package com.karmadevelop.PenguinPublishers.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karmadevelop.PenguinPublishers.model.Author;
import com.karmadevelop.PenguinPublishers.model.Book;
import com.karmadevelop.PenguinPublishers.model.Work;

@Service
public class FetchAuthors {

	

	@Autowired
	private HttpConnect connect;

	// method to fetch an author
	public List<Author> fetchAuthors(String authorName) throws JSONException, IOException, InterruptedException {

		if (authorName.contains(" ")) {
			authorName = authorName.replaceAll(" ", "%20");

		}

		ObjectMapper mapper = new ObjectMapper();

		List<Author> authors = new ArrayList<>();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);/// configuring mapper

		String url = "https://openlibrary.org/search/authors.json?q=";

		JSONObject json = connect.Connect(url + authorName);// geting the json

		JSONArray jsonArray = json.getJSONArray("docs");

		/////////////// looping through the array and filling the author list
		for (int i = 0; i < jsonArray.length(); i++) {

			authors.add(mapper.readValue(jsonArray.get(i).toString(), Author.class));

			/////////////// removing the fiction fact etc from the books list
			if (authors.get(i).getWorks() != null) {

				List<String[]> worksList = authors.get(i).getWorks().stream().map(n -> n.split(","))
						.collect(Collectors.toList());

				authors.get(i).getWorks().clear();///// clearing the list

				for (String[] workString : worksList) {
					authors.get(i).getWorks().add(workString[0]);

				}
			}
		}

		return authors;
	}
}
