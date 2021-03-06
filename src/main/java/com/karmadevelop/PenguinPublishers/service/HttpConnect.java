package com.karmadevelop.PenguinPublishers.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class HttpConnect {

	JSONObject Connect(String URL) throws IOException, InterruptedException {
		// creating http client
		HttpClient client = HttpClient.newHttpClient();

		// create request Object
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();

		// execute the request
		HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return new JSONObject(response.body().toString());

	}

}
