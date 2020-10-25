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


import com.jacaranda.entity.Product;
import com.jacaranda.services.CheckService;
import com.jacaranda.services.UpdateService;

@RestController
@RequestMapping(path="/api")

public class ProductController {
	private List<Product> products = new ArrayList();
	
	//Creamos los servicios
	@Autowired
	private CheckService checkService;
	@Autowired
	private UpdateService updateService;
	
	//Método Get
	@GetMapping("/products")
	public ResponseEntity<?>getProduct(){
		ResponseEntity<?> response = null;
		if(products.isEmpty()) {
		response = ResponseEntity
				 .status(HttpStatus.NOT_FOUND)
				 .body("No hay productos. La lista está vacía");	
	}else{
		 response = ResponseEntity
				 .status(HttpStatus.OK)
				 .body(products);
			}
		return response;
		}
	
	//Método Get por parámetro id
	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProductId(@PathVariable int id){
		Product p = checkService.comprobarProduct(products,id);
		ResponseEntity<?> respuesta;
		if(p == null) {
			respuesta = ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("El producto no existe");
		}else {
			respuesta = ResponseEntity
					.status(HttpStatus.OK)
					.body(p);
		}
		return respuesta;
	}
	
	//Método Post
	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@RequestBody Product sent ){
		Product p = checkService.comprobarProduct(products,sent.getTitle());
		ResponseEntity<?> respuesta;
		if(p == null) {
			products.add(sent);
			respuesta = ResponseEntity
					.status(HttpStatus.CREATED)
					.body(sent);
		}else {
			respuesta = ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("Ya existe el producto");
		}
		return respuesta;
	}
	
	//Método Put
	@PutMapping("/products")
	public ResponseEntity<?> updateProduct(@RequestBody Product sent){
		Product p = checkService.comprobarProduct(products,sent.getTitle());
		ResponseEntity<?> respuesta;
		if(p == null) {
			respuesta = ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No existe el producto");
		}else {
			updateService.upProduct(p, sent);
			respuesta = ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Se ha actualizado");
		}
		return respuesta;
	}
	
	//Método Delete
	@DeleteMapping("/products")
	public ResponseEntity<?>deleteProduct(@RequestBody Product sent){
		Product p = checkService.comprobarProduct(products,sent.getId());
		ResponseEntity<?>respuesta;
		if(p == null) {
			respuesta = ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No existe el producto");
		}else {
			products.remove(p);
			respuesta = ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Se ha eliminado el producto");
		}
		return respuesta;
	}
	
}

