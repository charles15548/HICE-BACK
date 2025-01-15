package com.hice.back.service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.hice.back.model.Personaje;

public interface PersonajeService {

	public ResponseEntity<Map<String, Object>> listarPersonaje();
	public ResponseEntity<Map<String,Object>> listaPersonajePorId(Integer id);
	public Optional<Personaje> get(Integer id);
	public ResponseEntity<Map<String, Object>> listarPorProyecto(Integer id);
	public ResponseEntity<Map<String, Object>> buscarPersonaje(String nombre);
	public ResponseEntity<Map<String, Object>> AgregarPersonaje(Personaje personaje, MultipartFile file) throws IOException;
	public ResponseEntity<Map<String, Object>> EditarPersonaje(Personaje personaje, MultipartFile file, Integer id) throws IOException;
	public ResponseEntity<Map<String, Object>> EliminarPersonaje(Integer id, String imgPersonaje) throws IOException;
	
}
