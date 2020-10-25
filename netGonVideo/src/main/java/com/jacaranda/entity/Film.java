package com.jacaranda.entity;

import java.io.Serializable;

public class Film extends Product implements Serializable {

	//Variables
	private String duration;
	private String pegy;

	
	//Constructores
	public Film() {
		super();
	
	}

	public Film(String title, String description, int agno,String duration, String pegy) {
		super(title, description, agno);
		this.duration=duration;
		this.pegy=pegy;
	}

	//GET Y SET
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPegy() {
		return pegy;
	}

	public void setPegy(String pegy) {
		this.pegy = pegy;
	}
	
	
	
}
