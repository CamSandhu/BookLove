package com.karmadevelop.PenguinPublishing.model;

import java.util.Arrays;
import java.util.List;

public class Author {

	private Long authorid;

	private String authordisplay;

	private String spotlight;

	private List<Work> workIDS;
	
	private String authorlast;

	public Author() {
		super();
	}


	public String getAuthorlast() {
		return authorlast;
	}


	public void setAuthorlast(String authorlast) {
		this.authorlast = authorlast;
	}


	public List<Work> getWorkIDS() {
		return workIDS;
	}

	public void setWorkIDS(List<Work> workIDS) {
		this.workIDS = workIDS;
	}

	public Long getAuthorid() {
		return authorid;
	}

	public void setAuthorid(Long authorid) {
		this.authorid = authorid;
	}

	public String getAuthordisplay() {
		return authordisplay;
	}

	public void setAuthordisplay(String authordisplay) {
		this.authordisplay = authordisplay;
	}

	public String getSpotlight() {
		return spotlight;
	}

	public void setSpotlight(String spotlight) {
		this.spotlight = spotlight;
	}


	@Override
	public String toString() {
		return "Author [authorid=" + authorid + ", authordisplay=" + authordisplay + ", spotlight=" + spotlight
				+ ", workIDS=" + workIDS + "]";
	}

	
	
	
}
