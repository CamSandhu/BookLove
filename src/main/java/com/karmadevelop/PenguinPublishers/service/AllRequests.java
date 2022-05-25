package com.karmadevelop.PenguinPublishers.service;

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
import org.json.JSONException;
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
import com.karmadevelop.PenguinPublishers.dataValidation.DAtaValidation;
import com.karmadevelop.PenguinPublishers.model.AuthDetails;
import com.karmadevelop.PenguinPublishers.model.Author;
import com.karmadevelop.PenguinPublishers.model.Book;
import com.karmadevelop.PenguinPublishers.model.Work;

@Service
public class AllRequests {

	@Autowired
	private FetchAuthors fetchAuthors;
	
	@Autowired
	private FetchAll fetchAll;

	@Autowired
	private FetchBook fetchBook;

	@Autowired
	private FetchWork fetchWorks;

	@Autowired
	private FetchAuthDetails fetchAuthDetails;

	/*-----------------------------------------Method delegation to fetch authordetails-------------------------------*/
	public AuthDetails fetchAuthDetails(String authId) throws IOException, InterruptedException {

		return fetchAuthDetails.fetchAuthDetails(authId);

	}

///////////////
/////////////
///////////
/////////
///////
/////
///
//
	/*-----------------------------------------Method delegation to fetch authors-------------------------------*/
	public List<Author> fetchAuthors(String authorName) throws JSONException, IOException, InterruptedException {

		return fetchAuthors.fetchAuthors(authorName);
	}

///////////////                                                                        
/////////////
///////////
/////////
///////
/////
///
//
	/*-----------------------------------------Method delegation to fetch Works/books-------------------------------*/
	public List<Book> fetchWork(String authId, Integer offset) throws IOException, InterruptedException {

		return fetchWorks.fetchWork(authId, offset);
	}

///////////////                                                                        
/////////////
///////////
/////////
///////
/////
///
//
	/*-----------------------------------------Method delegation to fetch all Subjects-------------------------------*/
	public List<Book> fetchAll(String title ,Integer offset) throws IOException, InterruptedException {

		return fetchAll.fetchAll(title, offset);
	}

}
