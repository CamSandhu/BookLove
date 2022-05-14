package com.karmadevelop.PenguinPublishers.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

	private List<String> subjects;

	private String description;

	private String title;

	private List<Integer> covers;

	public Book() {
		
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Integer> getCovers() {
		return covers;
	}

	public void setCovers(List<Integer> covers) {
		this.covers = covers;
	}

	@Override
	public String toString() {
		return "Book [subjects=" + subjects + ", description=" + description + ", title=" + title + ", covers=" + covers
				+ "]";
	}

}
