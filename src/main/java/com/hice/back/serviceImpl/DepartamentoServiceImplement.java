package com.hice.back.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hice.back.model.Departamento;
import com.hice.back.repository.AudioRepository;
import com.hice.back.repository.DepartamentoRepository;
import com.hice.back.service.AudioService;
import com.hice.back.service.DepartamentoService;

@Service
public class DepartamentoServiceImplement implements DepartamentoService {

	@Autowired
	private DepartamentoRepository dao;
	


	@Override
	public ResponseEntity<Map<String, Object>> listarDepartamento() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Departamento> depa = dao.findAll();
		if(!depa.isEmpty()) {
			respuesta.put("mensaje", "Lista de Departamentos");
			respuesta.put("Departamento", depa);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen departamentos");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}
	@Override
	public ResponseEntity<Map<String, Object>> findDepartamentoByPais(Integer idPais) {
		Map<String ,Object> respuesta = new HashMap<>();
		List<Departamento> depa = dao.findByIdPais(idPais);
		if(!depa.isEmpty()) {
			respuesta.put("mensaje", "Lista de Departamentos id Pais");
			respuesta.put("Departamento", depa);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "Lista de departamentos por id Pais");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}

	

	
	
	
}
