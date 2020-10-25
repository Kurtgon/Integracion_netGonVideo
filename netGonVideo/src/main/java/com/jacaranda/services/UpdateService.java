package com.jacaranda.services;

import org.springframework.stereotype.Service;

import com.jacaranda.entity.Category;
import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Film;
import com.jacaranda.entity.Product;
import com.jacaranda.entity.Serie;

@Service
public class UpdateService {
	
	public void upCustomer(Customer c, Customer sent) {
		c.setName((sent.getName()== null)? c.getName(): sent.getName());
		c.setSurnames((sent.getSurnames()== null)? c.getSurnames(): sent.getSurnames());
		c.setBirthDate((sent.getBirthDate()== null)? c.getBirthDate(): sent.getBirthDate());
	}
	
	public void upProduct(Product p, Product sent) {
		p.setDescription((sent.getDescription()== null)? p.getDescription():sent.getDescription());
		p.setAgno((sent.getAgno()== 0)? p.getAgno():sent.getAgno());
	}
	
	public void upSerie(Serie s, Serie sent) {
		s.setDescription((sent.getDescription()== null)? s.getDescription():sent.getDescription());
		s.setAgno((sent.getAgno()== 0)? s.getAgno():sent.getAgno());
		s.setSeasson((sent.getSeasson()== null)? s.getSeasson(): sent.getSeasson());
		s.setChapter((sent.getChapter()== 0)? s.getChapter(): sent.getChapter());
		
	}
	
	public void upFilm(Film f, Film sent) {
		f.setDescription((sent.getDescription()== null)? f.getDescription():sent.getDescription());
		f.setAgno((sent.getAgno()== 0)? f.getAgno():sent.getAgno());
		f.setDuration((sent.getDuration()== null)? f.getDuration(): sent.getDuration());
		f.setPegy((sent.getPegy()== null)? f.getPegy(): sent.getDuration());
	}
	
	public void upCategory(Category c, Category sent) {
		c.setTipoCategory((sent.getTipoCategory()== null) ? c.getTipoCategory(): sent.getTipoCategory());
	}

}
