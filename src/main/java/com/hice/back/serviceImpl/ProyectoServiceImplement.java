package com.hice.back.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hice.back.model.Audio;
import com.hice.back.model.Proyecto;
import com.hice.back.model.Usuario;
import com.hice.back.repository.ProyectoRepository;
import com.hice.back.service.ProyectoService;

@Service
public class ProyectoServiceImplement implements ProyectoService{

	@Autowired
	private ProyectoRepository dao;
	@Override
	public ResponseEntity<Map<String, Object>> listarProyectos() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Proyecto> proyecto = dao.findAll();
		if(!proyecto.isEmpty()) {
			respuesta.put("mensaje", "Lista de proyectos");
			respuesta.put("Proyecto", proyecto);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen proyectos");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarProyectoPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public ResponseEntity<Map<String, Object>> EliminarProyecto(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Map<String, Object>> AgregarProyecto(Proyecto proyecto) {
		Map<String, Object> respuesta = new HashMap<>();
		dao.save(proyecto);
		respuesta.put("mensaje", "Proyecto creado correctamente");
		respuesta.put("Proyecto", proyecto);
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	}

	@Override
	public ResponseEntity<Map<String, Object>> EditarProyecto(Proyecto proyecto, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
