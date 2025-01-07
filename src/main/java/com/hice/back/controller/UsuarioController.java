package com.hice.back.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hice.back.model.Usuario;
import com.hice.back.service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping()
	public ResponseEntity<Map<String, Object>> listarUsuario () {
		return usuarioService.listarUsuarios();
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listarUsuarioPorId (@PathVariable Integer id) {
		return usuarioService.listarUsuariosPorId(id);
		
	}
	@PostMapping()
	public ResponseEntity<Map<String, Object>> agregarUsuario(@RequestBody Usuario user){
		return usuarioService.AgregarUsuario(user);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> actualizarUsuario(@RequestBody Usuario user, @PathVariable Integer id){
		return usuarioService.EditarUsuario(user, id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable Integer id){
		return usuarioService.EliminarUsuario(id);
	}
	
}
