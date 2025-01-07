package com.hice.back.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hice.back.model.Usuario;


public interface UsuarioService {

	public ResponseEntity<Map<String, Object>> listarUsuarios();
	public ResponseEntity<Map<String, Object>> listarUsuariosPorId(Integer id);
	public ResponseEntity<Map<String, Object>> AgregarUsuario(Usuario user);
	public ResponseEntity<Map<String, Object>> EditarUsuario(Usuario user,Integer id);
	public ResponseEntity<Map<String, Object>> EliminarUsuario(Integer id);
}
