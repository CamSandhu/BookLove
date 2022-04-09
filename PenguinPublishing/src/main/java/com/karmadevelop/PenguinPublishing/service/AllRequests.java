package com.karmadevelop.PenguinPublishing.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.karmadevelop.PenguinPublishing.model.Work;

@Service
public class AllRequests {

	public Work FetchTitles(int id) throws IOException, InterruptedException {

		String BOOKS_TITLE_URL = "https://reststop.randomhouse.com/resources/works/" + id + "/";

		// creating client object
		HttpClient client = HttpClient.newHttpClient();

		// creating an request object
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BOOKS_TITLE_URL)).build();

		// executing the request
		HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

		// getting body from response to string
		String xml = response.body().toString();

		// converting XML to json
		JSONObject json = XML.toJSONObject(xml);

		// getting the work keyword from the array
		json = json.getJSONObject("work");

		ObjectMapper mapper = new ObjectMapper();

		// configure mapper to not fail on unknown properties
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// mapping the Json object to pojo
		Work work = mapper.readValue(json.toString(), Work.class);

		return work;
	}

	public String FetchAuthors() throws IOException, InterruptedException {

		// creating url object

		String PENGUIN_AUTHORS_URL = "https://reststop.randomhouse.com/resources/authors?lastName=towles";

		// creating http client
		HttpClient client = HttpClient.newHttpClient();

		// create request Object
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(PENGUIN_AUTHORS_URL)).build();

		// execute the request
		HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
        String xml= response.body().toString();
		
		JSONObject json=  XML.toJSONObject(xml);
		
		return json.toString();
	}

}
