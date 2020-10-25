package com.jacaranda.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Customer;
import com.jacaranda.services.CheckService;
import com.jacaranda.services.UpdateService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/api")

public class CustomerController {
	
	private List<Customer> customers = new ArrayList();
	
	//Creamos el servicio
	@Autowired
	private CheckService checkService;
	@Autowired
	private UpdateService updateService;
	
	//Método GET
	@GetMapping("/customers")
	public ResponseEntity<?>getCustomer(){
		ResponseEntity<?> response = null;
		 if(customers.isEmpty()) {
			 
			 response= ResponseEntity
					 .status(HttpStatus.NOT_FOUND)
					 .body("No hay clientes. La lista está vacía");
			 
		 }else{
			 response= ResponseEntity
					 .status(HttpStatus.OK)
					 .body(customers);
			 
		 }
		 return response;
	}
	
	//Método Get por parámetro id
		@GetMapping("/customers/{id}")
		public ResponseEntity<?> getCustomerId(@PathVariable int id){
			Customer c = checkService.comprobarCustomer(customers,id);
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
	
	//Método POST
	@PostMapping("/customers")
	public ResponseEntity<?>createCustomer(@RequestBody Customer sent){
		Customer c = checkService.comprobarCustomerDni(customers,sent.getDni());
		ResponseEntity<?> respuesta;
		if(c ==null) {
			customers.add(sent);
			respuesta=ResponseEntity.status(HttpStatus.CREATED).body(sent);
		}else {
			respuesta=ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe el customer");
		}
		
		return respuesta;
	}
	
	//Método PUT
	//@PutMapping("/customers/{id}") mejor opción para que no lo vea cliente
	@PutMapping("/customers")
	public ResponseEntity<?>updateCustomer(@RequestBody Customer sent){
		Customer c = checkService.comprobarCustomerDni(customers,sent.getDni());
		ResponseEntity<?> respuesta;
		if(c == null) {
			respuesta =ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el customer");
		}else {
			updateService.upCustomer(c, sent);
			respuesta =ResponseEntity.status(HttpStatus.ACCEPTED).body("Se ha actualizado");
		}
		return respuesta;
	}
	
	
	//Método DELETE
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?>deleteCustomer(@PathVariable int id){
		Customer c = checkService.comprobarCustomer(customers,id);
		ResponseEntity<?>respuesta;
		if(c == null) {
			respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el customer");
		}else {
			customers.remove(c);
			respuesta = ResponseEntity.status(HttpStatus.ACCEPTED).body("Se ha eliminado el customer");
		}
		return respuesta;
	}
	
}



