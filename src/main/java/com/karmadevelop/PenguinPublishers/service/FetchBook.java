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

		String[] params = workID.trim().split("/");

		workID = params[0];

		String title = params[1].replaceAll(" ", "%20");

		String authorName = params[2].replaceAll(" ", "%20");

		String ISBN_DATA_URL = "https://reststop.randomhouse.com/resources/titles?workid=" + workID;

		// converting xml to jsonObject
		JSONObject json = connect.Connect(ISBN_DATA_URL);

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

///////////////using the google api		
		else {

			json = connect.Connect("https://www.googleapis.com/books/v1/volumes?q=" + title + "+" + authorName
					+ ":keyes&key=AIzaSyAYVbSJ2sLh0xKtL0kBI4HEhPkUjvCmn54");

			if (json.get("items") instanceof JSONArray) {

				JSONArray bookInfoArray = new JSONArray();

				if (json.has("items")) {
					bookInfoArray = (JSONArray) json.get("items");
				} else
					return book = null;

				for (int i = 0; i < bookInfoArray.length(); i++) {

					if (bookInfoArray.get(i).toString().contains(params[1])) {

						JSONObject oneBookObject = (JSONObject) bookInfoArray.get(i);

						System.out.println(oneBookObject.get("volumeInfo"));

						JSONObject volumeinfo = (JSONObject) oneBookObject.get("volumeInfo");

						// setting title
						if (volumeinfo.has("title"))
							book.setTitle((String) volumeinfo.get("title"));

						// getting the isbn
						JSONArray isbnArray = volumeinfo.getJSONArray("industryIdentifiers");

						JSONObject isbn = (JSONObject) isbnArray.get(0);

						// setting isbn
						if (isbn.has("identifier"))
							book.setIsbn((String) (isbn.get("identifier")));

						JSONObject imageLinkObject = volumeinfo.getJSONObject("imageLinks");

						if (imageLinkObject.has("thumbnail"))
							book.setPhotoSource(imageLinkObject.get("thumbnail").toString());

						if (volumeinfo.has("pageCount"))
							book.setPages(Integer.toString((Integer) volumeinfo.get("pageCount")));

						System.out.println(book.toString());
					}
				}

			}
			if (book.getTitle() == null)
				book = null;

			return book;
		}
		if (book.getTitle() == null)
			book = null;
		return book;
	}

}
