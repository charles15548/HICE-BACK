package com.hice.back.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.hice.back.model.Proyecto;
import com.hice.back.model.Usuario;

public interface ProyectoService {
	public ResponseEntity<Map<String, Object>> listarProyectos();
	public ResponseEntity<Map<String, Object>> listarProyectoPorId(Integer id);
	public ResponseEntity<Map<String, Object>> EditarProyecto(Proyecto proyecto, MultipartFile file,Integer id) throws IOException;
	public ResponseEntity<Map<String, Object>> EliminarProyecto(Integer id);
	public ResponseEntity<Map<String, Object>> AgregarProyecto(Proyecto proyecto, MultipartFile file) throws IOException;
}
