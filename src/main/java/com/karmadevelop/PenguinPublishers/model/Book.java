package com.karmadevelop.PenguinPublishers.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

	private String isbn;

	@JsonProperty("titleweb")
	private String title;

	@JsonProperty("uri")
	private String photoSource;

	@JsonProperty("authorweb")
	private String authorName;

	private String pages;

	private String workid;

	private int priceusa;

	private int pricecanada;

	public int getPriceusa() {
		return priceusa;
	}

	public void setPriceusa(int priceusa) {
		this.priceusa = priceusa;
	}

	public int getPricecanada() {
		return pricecanada;
	}

	public void setPricecanada(int pricecanada) {
		this.pricecanada = pricecanada;
	}

	public String getIsbn() {
		return isbn;
	}

	public Book() {
		super();
	}

	public Book(String isbn, String title, String photSource, String authorName, String pages, String workid) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.photoSource = photSource;
		this.authorName = authorName;
		this.pages = pages;
		this.workid = workid;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhotoSource() {
		return photoSource;
	}

	public void setPhotoSource(String photSource) {
		this.photoSource = photSource;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getWorkid() {
		return workid;
	}

	public void setWorkid(String workid) {
		this.workid = workid;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", photoSource=" + photoSource + ", authorName=" + authorName
				+ ", pages=" + pages + ", workid=" + workid + "]";
	}

}
