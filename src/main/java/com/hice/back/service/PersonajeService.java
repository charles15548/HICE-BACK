package com.hice.back.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface PersonajeService {

	public ResponseEntity<Map<String, Object>> listarPersonaje();
	public ResponseEntity<Map<String, Object>> listarPorProyecto(Integer id);
	public ResponseEntity<Map<String, Object>> buscarPersonaje(String nombre);
}
