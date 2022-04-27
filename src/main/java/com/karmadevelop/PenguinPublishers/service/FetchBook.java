package com.karmadevelop.PenguinPublishers.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karmadevelop.PenguinPublishers.model.Author;
import com.karmadevelop.PenguinPublishers.model.Book;
import com.karmadevelop.PenguinPublishers.model.Work;

@Service
public class FetchBook  {
	// fetch Books data
	public Book fetchBook(Long workID) throws IOException, InterruptedException {

		String ISBN_DATA_URL = "https://reststop.randomhouse.com/resources/titles?workid=" + workID;

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ISBN_DATA_URL)).build();

		HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

		// getting the response body
		String xml = response.body().toString();

		// converting xml to jsonObject
		JSONObject json = XML.toJSONObject(xml);

		System.out.println(json);

		json = json.getJSONObject("titles");

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Book book = new Book();

		if (json.has("title") && json.toString().contains("\"title\":[")) {
			book = mapper.readValue(json.getJSONArray("title").get(0).toString(), Book.class);
		} else if (json.has("title")) {

			book = mapper.readValue(json.getJSONObject("title").toString(), Book.class);

			return book;
		}

		else {
			book.setTitle("there is no info about this book");
			return book;
		}
		return book;
	}



}
