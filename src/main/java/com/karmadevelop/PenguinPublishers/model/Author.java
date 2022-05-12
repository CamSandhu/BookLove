package com.karmadevelop.PenguinPublishers.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {

	@JsonProperty("key")
	private String authorid;

	@JsonProperty("name")
	private String authordisplay;

	@JsonProperty("work_count")
	private int workCount;

	@JsonProperty("birth_date")
	private String DOB;

	@JsonProperty("death_date")
	private String DOD;

	@JsonProperty("top_work")
	private String topWork;

	@JsonProperty("top_subjects")
	private List<String> works;

	public Author() {
		super();
	}

	public String getAuthordisplay() {
		return authordisplay;
	}

	public void setAuthordisplay(String authordisplay) {
		this.authordisplay = authordisplay;
	}

	public String getAuthorid() {
		return authorid;
	}

	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}

	public List<String> getWorks() {
		return works;
	}

	public void setWorks(List<String> works) {
		this.works = works;
	}

	public int getWorkCount() {
		return workCount;
	}

	public void setWorkCount(int workCount) {
		this.workCount = workCount;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getDOD() {
		return DOD;
	}

	public void setDOD(String dOD) {
		DOD = dOD;
	}

	public String getTopWork() {
		return topWork;
	}

	public void setTopWork(String topWork) {
		this.topWork = topWork;
	}

}
