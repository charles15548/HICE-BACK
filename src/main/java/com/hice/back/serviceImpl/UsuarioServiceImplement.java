package com.hice.back.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hice.back.model.Usuario;
import com.hice.back.repository.UsuarioRepository;
import com.hice.back.service.UsuarioService;

@Service
public class UsuarioServiceImplement implements UserDetailsService, UsuarioService{

	private String folderUsuario = "Archivo//Usuario//";
	@Autowired
	private UsuarioRepository dao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = dao.findOneByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario con dicho email: "+email+ "no existe."));
		
		return new UserDetailImplement(usuario);
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarUsuarios() {
		Map<String,Object> respuesta = new HashMap<>();	
		List<Usuario> usuarios = dao.findAll();
		
		if(!usuarios.isEmpty()) {
			respuesta.put("mensaje", "Lista de Usuarios");
			respuesta.put("Usuario", usuarios);
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen usuarios");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarUsuariosPorId(Integer id) {
		Map<String,Object> r = new HashMap<>();
		Optional<Usuario> usuarios = dao.findById(id);
		if(!usuarios.isEmpty()) {
			r.put("mensaje", "usuario por ID");
			r.put("Usuario", usuarios);
			r.put("status", HttpStatus.OK);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.OK).body(r);
		}else {
			r.put("mensaje", "sin usuario por ID "+ id);
			r.put("status", HttpStatus.NOT_FOUND);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> AgregarUsuario(Usuario user, MultipartFile file) throws IOException {
		Map<String,Object> r = new HashMap<>();
		String nombreImagen = manejarArchivo(file);
		Usuario usuario = new Usuario();
		usuario.setNombre(user.getNombre());
		usuario.setApellidoPaterno(user.getApellidoPaterno());
		usuario.setApellidoMaterno(user.getApellidoMaterno());
		
		usuario.setEmail(user.getEmail());
		usuario.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()) );
		usuario.setImgUsuario(nombreImagen);
		usuario.setEdad(user.getEdad());
		usuario.setGenero(user.getGenero());
		usuario.setIdPais(user.getIdPais());
		usuario.setIdDepartamento(user.getIdDepartamento());
		dao.save(usuario);
		r.put("mensaje", "Usuario creado");
		r.put("Usuario", user);
		r.put("status", HttpStatus.CREATED);
		r.put("fecha", new Date());
		return ResponseEntity.status(HttpStatus.CREATED).body(r);
	}
	@Override
	public ResponseEntity<Map<String, Object>> EditarUsuario(Usuario user, MultipartFile file, Integer id)
			throws IOException {
		Map<String,Object> r = new HashMap<>();
		String nombreImagen;
		
		Optional<Usuario> usuarioPresente =dao.findById(id);
		if(usuarioPresente.isPresent()) {
			Usuario usuario =usuarioPresente.get();
			
			String imgdelete = usuario.getImgUsuario();
			if(file != null && !file.isEmpty()) {
				
					deleteImg(imgdelete);
				
				nombreImagen = manejarArchivo(file);
			}else {
				nombreImagen = usuario.getImgUsuario();
			}
			
			usuario.setNombre(user.getNombre());
			usuario.setApellidoPaterno(user.getApellidoPaterno());
			usuario.setApellidoMaterno(user.getApellidoMaterno());
			
			usuario.setEmail(user.getEmail());
			usuario.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()) );
			usuario.setImgUsuario(nombreImagen);
			usuario.setEdad(user.getEdad());
			usuario.setGenero(user.getGenero());
			usuario.setIdPais(user.getIdPais());
			usuario.setIdDepartamento(user.getIdDepartamento());
			dao.save(usuario);
			r.put("mensaje", "Usuario editado");
			r.put("Usuario", usuario);
			r.put("status", HttpStatus.CREATED);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.CREATED).body(r);
		
		}else {
			r.put("mensaje", "No existe el usuario: " + id);
			r.put("status", HttpStatus.NOT_FOUND);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
		}
	}
	@Override
	public ResponseEntity<Map<String, Object>> EliminarUsuario(Integer id, String imgUsuario) throws IOException {
		Map<String, Object> r = new HashMap<>();
		Optional<Usuario> usuario = dao.findById(id);
		if(usuario.isPresent()) {
			Usuario user = usuario.get();
			dao.delete(user);
			deleteImg(imgUsuario);
			r.put("mensaje","Usuario eliminado" );
			r.put("status", HttpStatus.NO_CONTENT);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(r);
		}else {
			r.put("mensaje","No encontramos "+ id  );
			r.put("status", HttpStatus.NOT_FOUND);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
		}
	}
	public String manejarArchivo(MultipartFile file) throws IOException{
		if(file!= null && !file.isEmpty()) {
			Path folderPath = Paths.get(folderUsuario); 
			if(!Files.exists(folderPath)) {
				Files.createDirectories(folderPath);
			}
			String uniqueFileName = System.currentTimeMillis()+"_" + file.getOriginalFilename();
			Path filePath = folderPath.resolve(uniqueFileName);
			Files.write(filePath, file.getBytes());
			return uniqueFileName;
		}
		return "default.jpg";
	}
	public void deleteImg(String fileName) throws IOException{
		String ruta = folderUsuario;
		File file = new File(ruta + fileName);
		if(!fileName.equals("default.jpg")) {
			file.delete();
		}
		
	}
	
	@Override
	public Optional<Usuario> get (Integer id){
		return dao.findById(id);
	}

	

	

}
