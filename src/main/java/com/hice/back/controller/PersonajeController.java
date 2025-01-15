package com.hice.back.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.hice.back.model.Personaje;
import com.hice.back.service.PersonajeService;

@RestController
@RequestMapping("api/personaje")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonajeController {

	@Autowired
	private PersonajeService dao;
	
	@GetMapping()
	public ResponseEntity<Map<String, Object>> listarPersonaje () {
		return dao.listarPersonaje();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listarPersonajePorId (@PathVariable Integer id) {
		return dao.listaPersonajePorId(id);
	}
	
	@GetMapping("/proyecto/{id}")
	public ResponseEntity<Map<String, Object>> listarPersonajePorIdProyecto(@PathVariable Integer id) {
		return dao.listarPorProyecto(id);
	}
	@GetMapping("/buscar")
	public ResponseEntity<Map<String, Object>> buscarPersonaje(@RequestParam String nombre){
		return dao.buscarPersonaje(nombre);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> CrearPersonaje(@RequestParam("personajeParam") String personaje,
			@RequestParam(value = "personajeFile", required = false) MultipartFile file) throws IOException{
		Gson gson = new Gson();
		Personaje p = gson.fromJson(personaje, Personaje.class);
		
		return dao.AgregarPersonaje(p, file);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> EditarPersonaje(@PathVariable Integer id, @RequestParam("personajeParam") String personaje,
			@RequestParam(value = "personajeFile", required = false) MultipartFile file) throws IOException{
		
		JsonReader reader = new JsonReader(new StringReader(personaje));
		reader.setLenient(true);
		Gson gson = new Gson();
		Personaje p = gson.fromJson(reader, Personaje.class);
		
		return dao.EditarPersonaje( p, file,id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> EliminarPersonaje(@PathVariable Integer id) throws IOException{
		Personaje personaje = new Personaje();
		personaje = dao.get(id).get();
		String imgPersonaje = personaje.getImgPersonaje();
		
		return dao.EliminarPersonaje(id, imgPersonaje);
	}
}
