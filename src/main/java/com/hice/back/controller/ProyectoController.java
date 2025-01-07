package com.hice.back.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
