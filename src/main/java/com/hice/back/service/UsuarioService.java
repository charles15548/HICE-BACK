package com.hice.back.service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.hice.back.model.Usuario;


public interface UsuarioService {

	public ResponseEntity<Map<String, Object>> listarUsuarios();
	public ResponseEntity<Map<String, Object>> listarUsuariosPorId(Integer id);
	public ResponseEntity<Map<String, Object>> AgregarUsuario(Usuario user, MultipartFile file)throws IOException;
	public ResponseEntity<Map<String, Object>> EditarUsuario(Usuario user, MultipartFile file, Integer id)throws IOException;
	public ResponseEntity<Map<String, Object>> EliminarUsuario(Integer id, String imgUsuario)throws IOException;
	public Optional<Usuario> get(Integer id);
}
