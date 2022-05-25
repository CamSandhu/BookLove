package com.karmadevelop.PenguinPublishers.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.util.URLEncoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karmadevelop.PenguinPublishers.model.Author;
import com.karmadevelop.PenguinPublishers.model.Book;
import com.karmadevelop.PenguinPublishers.model.Work;

@Service
public class FetchBook {

	@Autowired
	private HttpConnect connect;

	// fetch Books data
	public Book fetchBook(String workID) throws IOException, InterruptedException {

		Book book = new Book();

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		JSONObject json = connect.Connect("https://openlibrary.org/works/" + workID + ".json");

		System.out.println(json);

		book = mapper.readValue(json.toString(), Book.class);

		if (json.has("description")) {

			if (json.get("description") instanceof JSONObject) {
				if (((JSONObject) json.get("description")).get("value").toString().length() > 260) {
					book.setDescrip(((JSONObject) json.get("description")).get("value").toString().substring(0, 200)
							.concat("..."));
				}

				else
					book.setDescrip(((JSONObject) json.get("description")).get("value").toString().concat("..."));
			}

			if (json.get("description") instanceof String) {
				if (json.get("description").toString().length() > 260) {
					book.setDescrip(json.get("description").toString().substring(0, 200).concat("..."));
				}

				else
					book.setDescrip(json.get("description").toString().concat("..."));
			}

		}

		if (book.getSubjects() != null && book.getSubjects().size() >= 3) {
			book.setSubjects(book.getSubjects().subList(0, 3));
		}

		return book;
	}

}
