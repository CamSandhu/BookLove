package com.karmadevelop.PenguinPublishers.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
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
	private FetchBook fetchBook;

	@Autowired
	private HttpConnect connect;

	public List<Book> fetchWork(String authId, Integer offset) throws IOException, InterruptedException {


		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		List<Work> works = new ArrayList();

		List<Book> books = new ArrayList();

		JSONObject json = connect.Connect("https://openlibrary.org/authors/" + authId + "/works.json?limit=10&offset="+offset);

		System.out.println(json);

		if (json.has("entries")) {

			JSONArray workArray = json.getJSONArray("entries");

			for (int i = 0; i < workArray.length(); i++) {

				works.add(mapper.readValue(workArray.get(i).toString(), Work.class));

				String[] workId = (works.get(i).getKey().split("/"));

				books.add(fetchBook.fetchBook(workId[2]));

			}
		}

		if (json.has("links") && (json.getJSONObject("links")).has("next")) {
			String next = (json.getJSONObject("links")).getString("next");
            
			 String[] offSet=  next.split("\\?");
			 
			offSet=offSet[1].split("&");
			
			for(String oFFset:offSet) {
				if((oFFset).contains("offset")) {
					offset=Integer.parseInt(oFFset.replaceAll("[^0-9]", ""));
				} 
				
			}

		}
		books.get(books.size() - 1).setOffset(offset);
		
		books.get(books.size() - 1).setAuthorId(authId);
		
		return books;
	}

}
