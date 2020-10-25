package com.jacaranda.entity;

import java.io.Serializable;

public class Category implements Serializable{
	//Variables
	private static int idGeneral = 1;
	private int id;
	private tipoCategory tipoCategory;
	
	
	
	public Category() {
		super();
		id = idGeneral++;
	}
	
	//Constructores
	public Category(tipoCategory tipoCategory) {
		super();
		this.tipoCategory = tipoCategory;
		id = idGeneral++;
	}

	//GET y SET
	public tipoCategory getTipoCategory() {
		return tipoCategory;
	}


	public void setTipoCategory(tipoCategory tipoCategory) {
		this.tipoCategory = tipoCategory;
	}


	public int getId() {
		return id;
	}
	
	
	
}
