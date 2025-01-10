package com.hice.back.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.hice.back.model.Proyecto;
import com.hice.back.service.ProyectoService;

@RestController
@RequestMapping("api/proyecto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoController {

	@Autowired
	private ProyectoService dao;
	
	@GetMapping()
	public ResponseEntity<Map<String, Object>> listarProyecto () {
		return dao.listarProyectos();
	}
	@PostMapping()
	public ResponseEntity<Map<String, Object>> CrearProyecto(@RequestParam("proyectoParam") String proyecto,
			@RequestParam(value = "proyectoFile", required = false) MultipartFile file) throws IOException{
		Gson gson = new Gson();
		Proyecto p = gson.fromJson(proyecto, Proyecto.class);
		
		p.setIdUsuario(1);
		return dao.AgregarProyecto(p, file);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> EditarProyecto(@PathVariable Integer id, @RequestParam("proyectoParam") String proyecto,
			@RequestParam(value = "proyectoFile", required = false) MultipartFile file) throws IOException{
		Gson gson = new Gson();
		Proyecto p = gson.fromJson(proyecto, Proyecto.class);
		
		
		
		p.setIdUsuario(1);
		return dao.EditarProyecto( p, file,id);
	}
}
