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


import com.jacaranda.entity.Film;
import com.jacaranda.services.CheckService;
import com.jacaranda.services.UpdateService;

@RestController
@RequestMapping(path="/api")

public class FilmController {
	
	private List<Film> films = new ArrayList();
	
	//Creamos el servicio
	@Autowired
	private CheckService checkService;
	@Autowired
	private UpdateService updateService;
	
		//Método GET
		@GetMapping("/films")
		public ResponseEntity<?>getCustomer(){
			ResponseEntity<?> response = null;
			 if(films.isEmpty()) {
				 
				 response= ResponseEntity
						 .status(HttpStatus.NOT_FOUND)
						 .body("No hay peliculas. La lista está vacía");
				 
			 }else{
				 
				 response= ResponseEntity
						 .status(HttpStatus.OK)
						 .body(films);
				 
			 }
			 return response;
		}
		
		//Método Get por parámetro id
		@GetMapping("/films/{id}")
		public ResponseEntity<?> getCustomerId(@PathVariable int id){
			Film f = checkService.comprobarFilm(films,id);
			ResponseEntity<?> respuesta;
			if(f == null) {
				respuesta = ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("La pelicula no existe");
			}else {
				respuesta = ResponseEntity
						.status(HttpStatus.OK)
						.body(f);
			}
			return respuesta;
		}
		
		//Método POST
		@PostMapping("/films")
		public ResponseEntity<?>createFilm(@RequestBody Film sent){
			Film f = checkService.comprobarFilm(films,sent.getTitle());
			ResponseEntity<?> respuesta;
			if(f ==null) {
				films.add(sent);
				
				respuesta=ResponseEntity
						.status(HttpStatus.CREATED)
						.body(sent);
			}else {
				respuesta=ResponseEntity
						.status(HttpStatus.CONFLICT)
						.body("Ya existe la pelicula");
			}
			
			return respuesta;
		}
		
		//Método PUT
		@PutMapping("/films")
		public ResponseEntity<?>updateFilm(@RequestBody Film sent){
			Film f = checkService.comprobarFilm(films,sent.getTitle());
			ResponseEntity<?> respuesta;
			if(f == null) {
				respuesta =ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("No existe la pelicula");
			}else {
				updateService.upFilm(f, sent);
				respuesta =ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body("Se ha actualizado");
			}
			return respuesta;
		}
		
		//Método DELETE
		@DeleteMapping("/films/{id}")
		public ResponseEntity<?>deleteFilm(@PathVariable int id){
			Film f = checkService.comprobarFilm(films,id);
			ResponseEntity<?>respuesta;
			if(f == null) {
				respuesta = ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("No existe la pelicula");
			}else {
				films.remove(f);
				respuesta = ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body("Se ha eliminado la pelicula");
			}
			return respuesta;
		}
}
