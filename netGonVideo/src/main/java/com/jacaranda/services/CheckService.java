package com.jacaranda.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jacaranda.entity.Category;
import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Film;
import com.jacaranda.entity.Product;
import com.jacaranda.entity.Serie;
import com.jacaranda.entity.tipoCategory;

@Service
public class CheckService {

	public Customer comprobarCustomer(List<Customer> customers,int other) {
		 boolean encontrado = false;
		 Customer resultado = null;
		 Iterator<Customer> iterador = customers.iterator(); 
		 
		 while(!encontrado && iterador.hasNext()) {
			 Customer nuevoCustomers = iterador.next();
			 if(nuevoCustomers.getId()==other) {
				 encontrado = true;
				 resultado=nuevoCustomers;
			 }
		 }
		 
		 return resultado;
	}
	
	public Customer comprobarCustomerDni(List<Customer> customers,String other) {
		 boolean encontrado = false;
		 Customer resultado = null;
		 Iterator<Customer> iterador = customers.iterator(); 
		 
		 while(!encontrado && iterador.hasNext()) {
			 Customer nuevoCustomers = iterador.next();
			 if(nuevoCustomers.getDni().equalsIgnoreCase(other)) {
				 encontrado = true;
				 resultado=nuevoCustomers;
			 }
		 }
		 
		 return resultado;
	}
	
	public Product comprobarProduct(List<Product> products,int other) {
		boolean encontrado = false;
		Product resultado = null;
		Iterator<Product> iterador = products.iterator();
		while(!encontrado && iterador.hasNext()) {
			Product nuevoProduct = iterador.next();
			if(nuevoProduct.getId()==other) {
				 encontrado = true;
				 resultado=nuevoProduct;
			 }
		}
		return resultado;
	}
	
	public Product comprobarProduct(List<Product> products,String other) {
		boolean encontrado = false;
		Product resultado = null;
		Iterator<Product> iterador = products.iterator();
		while(!encontrado && iterador.hasNext()) {
			Product nuevoProduct = iterador.next();
			if(nuevoProduct.getTitle().equalsIgnoreCase(other)) {
				 encontrado = true;
				 resultado=nuevoProduct;
			 }
		}
		return resultado;
	}
	
	public Serie comprobarSerie(List<Serie> series,int other) {
		boolean encontrado = false;
		Serie resultado = null;
		Iterator<Serie> iterador = series.iterator();
		
		while(!encontrado && iterador.hasNext()) {
			Serie nuevaSerie = iterador.next();
			if(nuevaSerie.getId()==other) {
				 encontrado = true;
				 resultado=nuevaSerie;
			 }
		}
		return resultado;
	}
	
	public Serie comprobarSerie(List<Serie> series,String other) {
		boolean encontrado = false;
		Serie resultado = null;
		Iterator<Serie> iterador = series.iterator();
		while(!encontrado && iterador.hasNext()) {
			Serie nuevaSerie = iterador.next();
			if(nuevaSerie.getTitle().equalsIgnoreCase(other)) {
				 encontrado = true;
				 resultado=nuevaSerie;
			 }
		}
		return resultado;
	}
	
	public Film comprobarFilm(List<Film> films,int other) {
		 boolean encontrado = false;
		 Film resultado = null;
		 Iterator<Film> iterador = films.iterator(); 
		 
		 while(!encontrado && iterador.hasNext()) {
			 Film nuevoFilm = iterador.next();
			 if(nuevoFilm.getId()==other) {
				 encontrado = true;
				 resultado=nuevoFilm;
			 }
		 }
		 
		 return resultado;
	}
	
	public Film comprobarFilm(List<Film> films,String other) {
		 boolean encontrado = false;
		 Film resultado = null;
		 Iterator<Film> iterador = films.iterator(); 
		 
		 while(!encontrado && iterador.hasNext()) {
			 Film nuevoFilm = iterador.next();
			 if(nuevoFilm.getTitle().equalsIgnoreCase(other)) {
				 encontrado = true;
				 resultado=nuevoFilm;
			 }
		 }
		 
		 return resultado;
	}
	
	public Category comprobarCategory(List<Category> categorys,int other) {
		boolean encontrado = false;
		Category resultado = null;
		Iterator<Category> iterador = categorys.iterator();
		while(!encontrado && iterador.hasNext()) {
			Category nuevoCategory = iterador.next();
			if(nuevoCategory.getId()==other) {
				 encontrado = true;
				 resultado=nuevoCategory;
			 	}
			}
		return resultado;
	}
	
	public Category comprobarCategory(List<Category> categorys,tipoCategory other) {
		boolean encontrado = false;
		Category resultado = null;
		Iterator<Category> iterador = categorys.iterator();
		while(!encontrado && iterador.hasNext()) {
			Category nuevoCategory = iterador.next();
			if(nuevoCategory.getTipoCategory()==other) {
				 encontrado = true;
				 resultado=nuevoCategory;
			 	}
			}
		return resultado;
	}
}
