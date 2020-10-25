package com.jacaranda.entity;

import java.io.Serializable;

public class Serie extends Product implements Serializable{
	
	//Variables
	private String seasson;
	private int chapter;
	
	
	
	//Constructores
	public Serie(String title, String description, int agno, String seasson, int chapter) {
		super(title, description, agno);
		this.seasson = seasson;
		this.chapter = chapter;
	}


	public Serie() {
		super();
		
	}

	//GET y SET
	public String getSeasson() {
		return seasson;
	}


	public void setSeasson(String seasson) {
		this.seasson = seasson;
	}


	public int getChapter() {
		return chapter;
	}


	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	



}
