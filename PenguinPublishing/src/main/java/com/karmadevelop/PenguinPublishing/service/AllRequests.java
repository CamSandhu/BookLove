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
