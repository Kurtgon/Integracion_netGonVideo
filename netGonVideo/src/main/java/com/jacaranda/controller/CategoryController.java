package com.jacaranda.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Category;
import com.jacaranda.entity.Customer;
import com.jacaranda.entity.tipoCategory;
import com.jacaranda.services.CheckService;
import com.jacaranda.services.UpdateService;


@RestController
@RequestMapping(path="/api")

public class CategoryController {
	
	private List<Category> categorys = new ArrayList();
	
	//Creamos el servicio
	@Autowired
	private CheckService checkService;
	@Autowired
	private UpdateService updateService;
	
	//Método Get
	@GetMapping("categorys")
	public ResponseEntity<?>getCategory(){
		ResponseEntity<?> response = null;
		if(categorys.isEmpty()) {
			response = ResponseEntity
					 .status(HttpStatus.NOT_FOUND)
					 .body("No hay categorías. La lista está vacía.");
		}else {
			response = ResponseEntity
					 .status(HttpStatus.OK)
					 .body(categorys);
		}
		return response;
	}
	
	//Método Get por parámetro id
			@GetMapping("/categorys/{id}")
			public ResponseEntity<?> getCustomerId(@PathVariable int id){
				Category c = checkService.comprobarCategory(categorys,id);
				ResponseEntity<?> respuesta;
				if(c == null) {
					respuesta = ResponseEntity
							.status(HttpStatus.NOT_FOUND)
							.body("El cliente no existe");
				}else {
					respuesta = ResponseEntity
							.status(HttpStatus.OK)
							.body(c);
				}
				return respuesta;
			}
	
	//Método Post
	@PostMapping("categorys")
	public ResponseEntity<?> createCategory(@RequestBody Category sent ){
		Category c = checkService.comprobarCategory(categorys,sent.getTipoCategory());
		ResponseEntity<?> respuesta;
		if(c == null) {
			categorys.add(sent);
			respuesta = ResponseEntity
					.status(HttpStatus.CREATED)
					.body(sent);
		}else {
			respuesta = ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("Ya existe la categoría");
		}
		return respuesta;
	}
	
	//Método Put
		@PutMapping("categorys")
		public ResponseEntity<?> updateCategory(@RequestBody Category sent){
			Category c = checkService.comprobarCategory(categorys,sent.getId());
			ResponseEntity<?> respuesta;
			if(c == null) {
				respuesta = ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("No existe la categoría");
			}else {
				updateService.upCategory(c, sent);
				respuesta = ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body("Se ha actualizado");
			}
			return respuesta;
		}
		
		//Método Delete
		@DeleteMapping("categorys")
		public ResponseEntity<?>deleteCategory(@RequestBody Category sent){
			Category c = checkService.comprobarCategory(categorys,sent.getId());
			ResponseEntity<?>respuesta;
			if(c == null) {
				respuesta = ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("No existe la categoría");
			}else {
				categorys.remove(c);
				respuesta = ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body("Se ha eliminado la categoría");
			}
			return respuesta;
		}
}