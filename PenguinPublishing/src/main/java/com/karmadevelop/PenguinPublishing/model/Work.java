package com.karmadevelop.PenguinPublishing.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Work {

	@JsonProperty
	private String titleshort;

	private String titleweb;

	private String onsaledate;

	private String titleSubtitleAuth;

	private String titleAuth;

	//private String titles;

	private String authorweb;
    
	private String workid;
	
	private List<Long> ISBNS;
	
	

	public List<Long> getISBNS() {
		return ISBNS;
	}

	public void setISBNS(List<Long> iSBNS) {
		ISBNS = iSBNS;
	}

	public Work() {
		super();
	}

	public String getTitleshort() {
		return titleshort;
	}

	public Work(String titleshort, String titleweb, String onsaledate, String titleSubtitleAuth, String titleAuth,
			String titles, String uri, String authorweb, String workid) {
		super();
		this.titleshort = titleshort;
		this.titleweb = titleweb;
		this.onsaledate = onsaledate;
		this.titleSubtitleAuth = titleSubtitleAuth;
		this.titleAuth = titleAuth;
		//this.titles = titles;
		this.authorweb = authorweb;
		this.workid = workid;
	}

	public String getTitleAuth() {
		return titleAuth;
	}

	public void setTitleAuth(String titleAuth) {
		this.titleAuth = titleAuth;
	}

	/*
	 * public String getTitles() { return titles; }
	 * 
	 * public void setTitles(String titles) { this.titles = titles; }
	 */

	public void setTitleshort(String titleshort) {
		this.titleshort = titleshort;
	}

	public String getTitleweb() {
		return titleweb;
	}

	public void setTitleweb(String titleweb) {
		this.titleweb = titleweb;
	}

	public String getOnsaledate() {
		return onsaledate;
	}

	public void setOnsaledate(String onsaledate) {
		this.onsaledate = onsaledate;
	}

	public String getTitleSubtitleAuth() {
		return titleSubtitleAuth;
	}

	public void setTitleSubtitleAuth(String titleSubtitleAuth) {
		this.titleSubtitleAuth = titleSubtitleAuth;
	}

	public String getAuthorweb() {
		return authorweb;
	}

	public void setAuthorweb(String authorweb) {
		this.authorweb = authorweb;
	}

	public String getWorkid() {
		return workid;
	}

	public void setWorkid(String workid) {
		this.workid = workid;
	}

	@Override
	public String toString() {
		return "Work [titleshort=" + titleshort + ", titleweb=" + titleweb + ", onsaledate=" + onsaledate
				+ ", titleSubtitleAuth=" + titleSubtitleAuth + ", titleAuth=" + titleAuth + ", authorweb=" + authorweb
				+ ", workid=" + workid + "]";
	}

	
}
