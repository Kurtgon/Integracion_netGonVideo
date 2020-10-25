package com.jacaranda.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable{

	//Variables
	private static int idGeneral = 1;
	private int id;
	private String title;
	private String description;
	private int agno;
	private List<Visual> visualizaciones;
	private List<Category> categorias;
	
	
	public Product() {
		super();
		visualizaciones = new ArrayList<Visual>();
		categorias = new ArrayList<Category>();
		id = idGeneral++;
	}
	//Constructores
	public Product(String title, String description, int agno) {
		super();
		this.title = title;
		this.description = description;
		this.agno = agno;
		visualizaciones = new ArrayList<Visual>();
		categorias = new ArrayList<Category>();
		id = idGeneral++;
	}
	
	//GET Y SET

	public String getTitle() {
		return title;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAgno() {
		return agno;
	}

	public void setAgno(int agno) {
		this.agno = agno;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public List<Visual> getVisualizaciones() {
		return visualizaciones;
	}

	public List<Category> getCategorias() {
		return categorias;
	}
	
	
	
	
}
