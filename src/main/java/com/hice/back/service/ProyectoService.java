package com.hice.back.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.hice.back.model.Proyecto;
import com.hice.back.model.Usuario;

public interface ProyectoService {
	public ResponseEntity<Map<String, Object>> listarProyectos();
	public ResponseEntity<Map<String, Object>> listarProyectoPorId(Integer id);
	public ResponseEntity<Map<String, Object>> AgregarProyecto(Proyecto proyecto);
	public ResponseEntity<Map<String, Object>> EditarProyecto(Proyecto proyecto,Integer id);
	public ResponseEntity<Map<String, Object>> EliminarProyecto(Integer id);
}
