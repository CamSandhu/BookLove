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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karmadevelop.PenguinPublishers.model.Author;
import com.karmadevelop.PenguinPublishers.model.Book;
import com.karmadevelop.PenguinPublishers.model.Work;

@Service
public class FetchAuthors {

	@Autowired
	private FetchWork fetchWork;

	@Autowired
	private HttpConnect connect;

	// method to fetch an author
	public List<Author> fetchAuthors(String authorName) throws JSONException, IOException, InterruptedException {

		String PENGUIN_AUTHORS_URL = null;
		String firstName = null;
		String lastName = null;

		if (authorName.trim().contains(" ")) {

			String[] str = authorName.split(" ");
			firstName = str[0];
			lastName = str[1];
			System.out.println(firstName + " " + lastName);

			PENGUIN_AUTHORS_URL = "https://reststop.randomhouse.com/resources/authors?firstName=" + firstName
					+ "&lastName=" + lastName;
		}

////////// checking if it is only the first name or the last name
		else if (!authorName.trim().contains(" ")) {

			PENGUIN_AUTHORS_URL = "https://reststop.randomhouse.com/resources/authors?firstName=" + authorName;
		}

		HttpClient client;
		HttpRequest request;
		HttpResponse response;

		// using the HTTP connection defined in HttpConnect class
		JSONObject json = connect.Connect(PENGUIN_AUTHORS_URL);

		System.out.println(json);

/////////// checking if the json sent back with first name does not contain the authors
		// info and knowing it is the last name
		if (!json.toString().contains("\"author\":")) {
			PENGUIN_AUTHORS_URL = "https://reststop.randomhouse.com/resources/authors?lastName=" + authorName;

			json = connect.Connect(PENGUIN_AUTHORS_URL);

		}

		System.out.println(json.length() + "  " + json);

		// creating the mapper object
		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// intilizing list to fill with the Author class objects
		List<Author> authors = new ArrayList<>();

		JSONObject jsontest = (JSONObject) json.get("authors");

//////////// mapping for a single author
		if (jsontest.get("author") instanceof JSONObject) {

			JSONObject json1 = (JSONObject) json.get("authors");

			authors.add(mapper.readValue(json1.get("author").toString(), Author.class));

			json1 = (JSONObject) json1.get("author");

			JSONObject works = (JSONObject) json1.get("works");

			String[] workIds = works.get("works").toString().replaceAll("\\[", "").replaceAll("]", "").split(",");

			List<Work> WorkIds = new ArrayList<>();

			// getting all the Works related to the workIds
			for (String workid : workIds) {

				WorkIds.add(fetchWork.fetchWork(Integer.parseInt(workid)));

			}

			authors.get(0).setWorkIDS(WorkIds);

			if (authors.get(0).getSpotlight() != null)
				authors.get(0).setSpotlight(authors.get(0).getSpotlight().replaceAll("\\<.*?\\>", " ")
						.replaceAll("[-+^]*", "").replaceAll("[&#-;-160]", " "));

		}

///////////////// mapping for an Array of authors
		else {
			// reaching the author array in json
			JSONArray jsonArray = json.getJSONObject("authors").getJSONArray("author");

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

					WorkIds.add(fetchWork.fetchWork(Integer.parseInt(workid)));

				}

				authors.add(mapper.readValue(jsonArray.get(i).toString(), Author.class));

				authors.get(authors.size() - 1).setWorkIDS(WorkIds);

				if (authors.get(authors.size() - 1).getSpotlight() != null)
					authors.get(authors.size() - 1).setSpotlight(authors.get(authors.size() - 1).getSpotlight()
							.replaceAll("\\<.*?\\>", " ").replaceAll("[-+^]*", "").replaceAll("[&#-;-160]", " "));
			}

		}

		if (authors.isEmpty())
			return null;

		return authors;

	}

}
