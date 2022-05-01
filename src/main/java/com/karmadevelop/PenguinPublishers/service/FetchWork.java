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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karmadevelop.PenguinPublishers.model.Author;
import com.karmadevelop.PenguinPublishers.model.Book;
import com.karmadevelop.PenguinPublishers.model.Work;

@Service
public class FetchWork {

	@Autowired
	private HttpConnect connect;

	public Work fetchWork(int id) throws IOException, InterruptedException {

		String BOOKS_TITLE_URL = "https://reststop.randomhouse.com/resources/works/" + id + "/";


		// converting XML to json
		JSONObject json = connect.Connect(BOOKS_TITLE_URL);

		// getting the work keyword from the array
		json = json.getJSONObject("work");

		 System.out.println(json);

		ObjectMapper mapper = new ObjectMapper();

		// configure mapper to not fail on unknown properties
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// mapping the Json object to pojo
		Work work = mapper.readValue(json.toString(), Work.class);

		return work;
	}

}
