package com.jacaranda.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Visual implements Serializable{
	//Variables
	private static int idGeneral = 1;
	private int id;
	private Date inicioPlay;
	private Date finPlay;
	private Customer customer;
	private List<Product> productos;
	
	public Visual() {
		super();
		productos = new ArrayList<Product>();
		id = idGeneral++;
	}
//Constructores	
	public Visual(Date inicioPlay, Date finPlay, Customer customer) {
		super();
		this.inicioPlay = inicioPlay;
		this.finPlay = finPlay;
		this.customer = customer;
		productos = new ArrayList<Product>();
		id = idGeneral++;
	}


//GET Y SET
	public int getId() {
		return id;
	}

	public Date getInicioPlay() {
		return inicioPlay;
	}

	public Date getFinPlay() {
		return finPlay;
	}

	public Customer getCustomer() {
		return customer;
	}

	public List<Product> getProductos() {
		return productos;
	}

	
	
}
