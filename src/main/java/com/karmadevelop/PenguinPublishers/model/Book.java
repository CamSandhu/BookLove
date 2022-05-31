package com.karmadevelop.PenguinPublishers.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

	@JsonAlias("subject")
	private List<String> subjects;
	
	private String Descrip;
	
	private String query;

	private int revision;

	private String title;

	private List<Integer> covers;

	private int offset;

	private String authorId;

	@JsonAlias("author_key")
	private List<String> authorKey;

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public Book() {

	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
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

	public List<String> getAuthorKey() {
		return authorKey;
	}

	public void setAuthorKey(List<String> authorKey) {
		this.authorKey = authorKey;
	}

	@Override
	public String toString() {
		return "Book [subjects=" + subjects + ", Descrip=" + Descrip + ", revision=" + revision + ", title=" + title
				+ ", covers=" + covers + ", offset=" + offset + ", authorId=" + authorId + ", authorKey=" + authorKey
				+ "]";
	}

}
