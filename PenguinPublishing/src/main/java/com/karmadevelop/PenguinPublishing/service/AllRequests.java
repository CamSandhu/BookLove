package com.karmadevelop.PenguinPublishing.service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.apache.catalina.mapper.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.FrameworkServlet;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.karmadevelop.PenguinPublishing.dataValidation.DAtaValidation;
import com.karmadevelop.PenguinPublishing.model.Author;
import com.karmadevelop.PenguinPublishing.model.Book;
import com.karmadevelop.PenguinPublishing.model.Work;

@Service
public class AllRequests {

	@Autowired
	DAtaValidation dAtaValidation;

	public Work FetchWork(int id) throws IOException, InterruptedException {

		String BOOKS_TITLE_URL = "https://reststop.randomhouse.com/resources/works/" + id + "/";

		// creating client object
		HttpClient client = HttpClient.newHttpClient();

		// creating an request object
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BOOKS_TITLE_URL)).build();

		// executing the request
		HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
		// HttpResponse response =
		// client.sendAsync(request,HttpResponse.BodyHandlers.ofString())

		// getting body from response to string
		String xml = response.body().toString();

		// converting XML to json
		JSONObject json = XML.toJSONObject(xml);

		// getting the work keyword from the array
		json = json.getJSONObject("work");

		// System.out.println(json);

		ObjectMapper mapper = new ObjectMapper();

		// configure mapper to not fail on unknown properties
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// mapping the Json object to pojo
		Work work = mapper.readValue(json.toString(), Work.class);

		return work;
	}

	// method to fetch an author
	public List<Author> FetchAuthors(String lastName) throws IOException, InterruptedException {

		if (lastName.contains(" ")) {
			int index = lastName.indexOf(' ');
			lastName = lastName.substring(index + 1);
		}

		// creating url object
		String PENGUIN_AUTHORS_URL = "https://reststop.randomhouse.com/resources/authors?lastName=" + lastName;

		// creating http client
		HttpClient client = HttpClient.newHttpClient();

		// create request Object
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(PENGUIN_AUTHORS_URL)).build();

		// execute the request
		HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

		// parsing xml to json
		JSONObject json = XML.toJSONObject(response.body().toString());

		// reaching the author array in json
		JSONArray jsonArray = json.getJSONObject("authors").getJSONArray("author");

		// intilizing list to fill with the Author class objects
		List<Author> authors = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// removing null works object and extracting works ids before mapping the
		// Jsonarray to Author class
		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject jObject = (JSONObject) jsonArray.get(i);

			if (jObject.get("works") == "")
				continue;

			JSONObject works = (JSONObject) jObject.get("works");

			String[] workIds = works.get("works").toString().replaceAll("\\[", "").replaceAll("]", "").split(",");

			List<Work> WorkIds = new ArrayList<>();

			// getting all the Works related to the workIds
			for (String workid : workIds) {

				WorkIds.add(FetchWork(Integer.parseInt(workid)));
			}

			authors.add(mapper.readValue(jsonArray.get(i).toString(), Author.class));

			authors.get(authors.size() - 1).setWorkIDS(WorkIds);

			if (authors.get(authors.size() - 1).getSpotlight() != null)
				authors.get(authors.size() - 1).setSpotlight(authors.get(authors.size() - 1).getSpotlight()
						.replaceAll("\\<.*?\\>", " ").replaceAll("[-+^]*", "").replaceAll("[&#-;-160]", " "));

		}

		return authors;

	}

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

	public String fetchBooks(String keyword) throws IOException, InterruptedException {

		String BOOK_TITLE_URL = "https://reststop.randomhouse.com/resources/titles?keyword=" + keyword;

		// String BOOK_TITLE_URL =
		// "https://reststop.randomhouse.com/resources/titles?start=0&max=4&expandLevel=0&onsaleStart=MM/dd/yyyy&onsaleEnd=MM/dd/yyyy&authorid=0&workid=0&keyword=Grisham%20Christmas";

		// creating client object
		HttpClient client = HttpClient.newHttpClient();

		// creating an request object
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BOOK_TITLE_URL)).build();

		// executing the request
		HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
		// HttpResponse response =
		// client.sendAsync(request,HttpResponse.BodyHandlers.ofString())

		// getting body from response to string
		String xml = response.body().toString();

		// converting XML to json
		JSONObject json = XML.toJSONObject(xml);

		return json.toString();
	}

}
