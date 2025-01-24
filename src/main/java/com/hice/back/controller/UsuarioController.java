package com.hice.back.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.hice.back.model.Usuario;
import com.hice.back.repository.UsuarioRepository;
import com.hice.back.service.UsuarioService;
import com.hice.back.serviceImpl.UsuarioServiceImplement;

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
	@Autowired
	private UsuarioRepository urepo;
	
	@GetMapping()
	public ResponseEntity<Map<String, Object>> listarUsuario () {
		return usuarioService.listarUsuarios();
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listarUsuarioPorId (@PathVariable Integer id) {
		return usuarioService.listarUsuariosPorId(id);
		
	}
	
	
	
	@GetMapping("/logueado")
	public ResponseEntity<Map<String, Object>> listarUsuarioPorUsuarioLogueado (Authentication authentication) {
		String email = authentication.getName();
		Usuario usuario = urepo.findOneByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		Integer idUsuario = usuario.getIdUsuario();
		return usuarioService.listarUsuariosPorId(idUsuario);
		
	}
	@PostMapping()
	public ResponseEntity<Map<String, Object>> agregarUsuario(@RequestParam("usuarioParam") String usuario,
		@RequestParam(value = "usuarioFile", required  = false) MultipartFile file) throws IOException{
		Gson gson = new Gson();
		Usuario user = gson.fromJson(usuario, Usuario.class);
		return usuarioService.AgregarUsuario(user, file);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> actualizarUsuario(@PathVariable Integer id,@RequestParam("usuarioParam") String usuario,
			@RequestParam(value = "usuarioFile", required  = false) MultipartFile file) throws IOException{
			JsonReader reader = new JsonReader(new StringReader(usuario));
			reader.setLenient(true);
			Gson gson = new Gson();
			Usuario user = gson.fromJson(reader, Usuario.class);
		return usuarioService.EditarUsuario(user,file, id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable Integer id)throws IOException{
		Usuario user = new Usuario();
		user = usuarioService.get(id).get();
		String imgUsuario = user.getImgUsuario();
		return usuarioService.EliminarUsuario(id, imgUsuario);
	}
	
}
