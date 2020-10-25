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


import com.jacaranda.entity.Serie;
import com.jacaranda.services.CheckService;
import com.jacaranda.services.UpdateService;

@RestController
@RequestMapping(path="/api")


public class SerieController {
	
	private List<Serie> series = new ArrayList();
	
	//Creamos el servicio
	@Autowired
	private CheckService checkService;
	@Autowired
	private UpdateService updateService;
	
	//Método Get
	@GetMapping("/series")
	public ResponseEntity<?>getSerie(){
		ResponseEntity<?> response = null;
		if(series.isEmpty()) {
			
			response = ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No hay series. La lista está vacía");	
	}else{
		
		 response = ResponseEntity
				 .status(HttpStatus.OK)
				 .body(series);
			}
		return response;
		}
	
	//Método Get por parámetro id
	@GetMapping("/series/{id}")
	public ResponseEntity<?> getSerieId(@PathVariable int id){
		Serie s = checkService.comprobarSerie(series,id);
		ResponseEntity<?> respuesta;
		if(s == null) {
			respuesta = ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("La serie no existe");
		}else {
			respuesta = ResponseEntity
					.status(HttpStatus.OK)
					.body(s);
		}
		return respuesta;
	}
	
	//Método POST
	@PostMapping("/series")
	public ResponseEntity<?>createSerie(@RequestBody Serie sent){
		Serie s = checkService.comprobarSerie(series,sent.getTitle());
		ResponseEntity<?> respuesta;
		if(s ==null) {
			series.add(sent);
			
			respuesta=ResponseEntity
					.status(HttpStatus.CREATED)
					.body(sent);
		}else {
			respuesta=ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("Ya existe la serie");
		}
		
		return respuesta;
	}
	
	//Método PUT
	@PutMapping("/series")
	public ResponseEntity<?>updateSerie(@RequestBody Serie sent){
		Serie s = checkService.comprobarSerie(series,sent.getTitle());
		ResponseEntity<?> respuesta;
		if(s == null) {
			respuesta =ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No existe la serie");
		}else {
			updateService.upSerie(s, sent);
			respuesta =ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Se ha actualizado correctamente la serie");
		}
		return respuesta;
	}
	
	//Método DELETE
	@DeleteMapping("/series/{id}")
	public ResponseEntity<?>deleteSerie(@PathVariable int id){
		Serie s = checkService.comprobarSerie(series,id);
		ResponseEntity<?>respuesta;
		if(s == null) {
			respuesta = ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("No existe la serie");
	}else {
		series.remove(s);
		respuesta = ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body("Se ha eliminado la serie");
		}
		return respuesta;
	}

}
