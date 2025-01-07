package com.hice.back.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hice.back.service.PersonajeService;
import com.hice.back.service.ProyectoService;

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
	
	@GetMapping("/proyecto/{id}")
	public ResponseEntity<Map<String, Object>> listarPersonajePorIdProyecto(@PathVariable Integer id) {
		return dao.listarPorProyecto(id);
	}
	@GetMapping("/buscar")
	public ResponseEntity<Map<String, Object>> buscarPersonaje(@RequestParam String nombre){
		return dao.buscarPersonaje(nombre);
	}
}
