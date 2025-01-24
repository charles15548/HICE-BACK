package com.hice.back.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.hice.back.model.Proyecto;
import com.hice.back.model.Usuario;
import com.hice.back.repository.UsuarioRepository;
import com.hice.back.service.ProyectoService;

@RestController
@RequestMapping("api/proyecto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoController {

	@Autowired
	private ProyectoService dao;
	@Autowired
	private UsuarioRepository urepo;
	
	@GetMapping()
	public ResponseEntity<Map<String, Object>> listarProyecto () {
		return dao.listarProyectos();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listarProyectoPorId (@PathVariable Integer id) {
		return dao.listarProyectoPorId(id);
	}
	@GetMapping("/usuario")
	public ResponseEntity<Map<String, Object>> listarProyectoPorIdUsuario (Authentication authentication) {
		String email= authentication.getName();
		
		Usuario usuario = urepo.findOneByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("Proyecto no encontrado"));
		Integer idUsuario = usuario.getIdUsuario();
		return dao.listarPorIdUsuario(idUsuario);
	}
	@GetMapping("/buscar")
	public ResponseEntity<Map<String, Object>> buscarProyecto(@PathVariable Integer id){
		return null;
	}
	@PostMapping()
	public ResponseEntity<Map<String, Object>> CrearProyecto(@RequestParam("proyectoParam") String proyecto,
			@RequestParam(value = "proyectoFile", required = false) MultipartFile file) throws IOException{
		/*
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String email  = authentication.getName();
	    Usuario usuario = urepo.findOneByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("Proyecto no encontrado"));
		Integer idUsuario = usuario.getIdUsuario();
		*/
		Gson gson = new Gson();
		Proyecto p = gson.fromJson(proyecto, Proyecto.class);
		
		return dao.AgregarProyecto(p, file);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> EditarProyecto(@PathVariable Integer id, @RequestParam("proyectoParam") String proyecto,
			@RequestParam(value = "proyectoFile", required = false) MultipartFile file) throws IOException{
		
		JsonReader reader = new JsonReader(new StringReader(proyecto));
		reader.setLenient(true);
		Gson gson = new Gson();
		Proyecto p = gson.fromJson(reader, Proyecto.class);
		
		
		
		return dao.EditarProyecto( p, file,id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> EliminarProyecto(@PathVariable Integer id) throws IOException{
		Proyecto proyecto = new Proyecto();
		proyecto = dao.get(id).get();
		String imgProyecto = proyecto.getImgProyecto();
		return dao.EliminarProyecto(id, imgProyecto);
	}
}
