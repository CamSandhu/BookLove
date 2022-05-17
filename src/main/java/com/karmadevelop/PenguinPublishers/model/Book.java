package com.karmadevelop.PenguinPublishers.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

	private List<String> subjects;

	private String Descrip;

	private String title;

	private List<Integer> covers;
	
	private int offset;

	private String authorId;

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public Book() {

	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public String getDescrip() {
		return Descrip;
	}

	public void setDescrip(String description) {
		Descrip = description;
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

	
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "Book [subjects=" + subjects + ", description=" + Descrip + ", title=" + title + ", covers=" + covers
				+ "]";
	}

}
