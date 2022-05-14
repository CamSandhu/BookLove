package com.karmadevelop.PenguinPublishers.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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
public class FetchWork {

	@Autowired
	private HttpConnect connect;

	public List<Work> fetchWork(String authId) throws IOException, InterruptedException {

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		List<Work> works = new ArrayList();

		JSONObject json = connect.Connect("https://openlibrary.org/authors/" + authId + "/works.json");

		System.out.println(json);

		if (json.has("entries")) {

			JSONArray workArray = json.getJSONArray("entries");

			for (int i = 0; i < workArray.length(); i++) {

				works.add(mapper.readValue(workArray.get(i).toString(), Work.class));

				String[] workId = (works.get(i).getKey().split("/"));

				works.get(i).setKey(workId[2]);
			}
		}

		return works;
	}

}
