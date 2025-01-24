package com.hice.back.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hice.back.service.DepartamentoService;

@RestController
@RequestMapping("api/departamento")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping()
	public ResponseEntity<Map<String, Object>> listarDepartamento(){
		return departamentoService.listarDepartamento();
	}
	@GetMapping("/pais/{id}")
	public ResponseEntity<Map<String, Object>> listarDepartamentoPorPais(@PathVariable Integer id){
		return departamentoService.findDepartamentoByPais(id);
	}
	
	
	
	
}
