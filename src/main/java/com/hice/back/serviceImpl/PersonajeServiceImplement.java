package com.hice.back.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hice.back.model.Personaje;
import com.hice.back.repository.PersonajeRepository;
import com.hice.back.service.PersonajeService;

@Service
public class PersonajeServiceImplement implements PersonajeService{

	@Autowired
	private PersonajeRepository dao;
	@Override
	public ResponseEntity<Map<String, Object>> listarPersonaje() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Personaje> personaje = dao.findAll();
		if(!personaje.isEmpty()) {
			respuesta.put("mensaje", "Lista de Personajes");
			respuesta.put("Personaje", personaje);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen personajes");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarPorProyecto(Integer idProyecto) {
		Map<String,Object> r = new HashMap<>();
		List<Personaje> personaje = dao.findByIdProyecto(idProyecto);
		if(!personaje.isEmpty()) {
			r.put("mensaje", "lista de personajes por proyecto" + idProyecto);
			r.put("Personaje", personaje);
			r.put("status", HttpStatus.OK);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.OK).body(r);
		}else {
			r.put("mensaje", "sin personajes con el id proyecto "+ idProyecto);
			r.put("status", HttpStatus.NOT_FOUND);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> buscarPersonaje(String nombre) {
		Map<String, Object> r = new HashMap<>();
		List<Personaje> personaje = dao.findByNombreContaining(nombre);
		if(!personaje.isEmpty()) {
			r.put("mesaje", "busqueda exitosa");
			r.put("Personaje", personaje);
			r.put("status", HttpStatus.OK);
			r.put("fecha",new Date());
			return ResponseEntity.status(HttpStatus.OK).body(r);
		}else {
			r.put("mensaje", "no se encontro");
			r.put("status", HttpStatus.NOT_FOUND);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
		}
	}

}
