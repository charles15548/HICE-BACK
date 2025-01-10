package com.hice.back.serviceImpl;

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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hice.back.model.Audio;
import com.hice.back.model.Proyecto;
import com.hice.back.model.Usuario;
import com.hice.back.repository.ProyectoRepository;
import com.hice.back.service.ProyectoService;

@Service
public class ProyectoServiceImplement implements ProyectoService{

	private String folderProyecto = "Archivo//Proyecto//";
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
		Map<String, Object> respuesta = new HashMap<>();
		Optional<Proyecto> proyecto = dao.findById(id);
		if(proyecto.isPresent()) {
			respuesta.put("mensaje", "audios "+ id +"existe");
			respuesta.put("Proyeto", proyecto);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existe el proyecto: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	
	@Override
	public ResponseEntity<Map<String, Object>> EliminarProyecto(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public ResponseEntity<Map<String, Object>> AgregarProyecto(Proyecto proyecto, MultipartFile file) throws IOException {
		Map<String, Object> respuesta = new HashMap<>();
		
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(folderProyecto + file.getOriginalFilename());
			Files.write(path, bytes);
			
			
		}
		Proyecto p = new Proyecto();
		p.setDescripcion(proyecto.getDescripcion());
		p.setNombre(proyecto.getNombre());
		p.setImgProyecto(file.getOriginalFilename());
		p.setIdUsuario(proyecto.getIdUsuario());
		
		
		dao.save(p);
		respuesta.put("mensaje", "Proyecto creado correctamente");
		respuesta.put("Proyecto", p);
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}*/

	@Override
	public ResponseEntity<Map<String, Object>> AgregarProyecto(Proyecto proyecto, MultipartFile file) throws IOException {
		Map<String, Object> respuesta = new HashMap<>();
		String nombreImagen;
		if(file != null && !file.isEmpty()) {
			Path folderPath = Paths.get(folderProyecto);
			if(!Files.exists(folderPath)) {
				Files.createDirectories(folderPath);
			}
			
			
			String uniqueFileName = System.currentTimeMillis()+ "_"+ file.getOriginalFilename();
			Path filePath = folderPath.resolve(uniqueFileName);
			
			byte[] bytes = file.getBytes();
			Files.write(filePath, bytes);
			nombreImagen = uniqueFileName;
		}else {
			nombreImagen = "default.jpg";
		}
		
		
		Proyecto nuevoProyecto = new Proyecto();
		nuevoProyecto.setDescripcion(proyecto.getDescripcion());
		nuevoProyecto.setNombre(proyecto.getNombre());
		nuevoProyecto.setImgProyecto(nombreImagen);
		nuevoProyecto.setIdUsuario(proyecto.getIdUsuario());
		
		
		dao.save(nuevoProyecto);
		respuesta.put("mensaje", "Proyecto creado correctamente");
		respuesta.put("Proyecto", nuevoProyecto);
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}
	
	
	@Override
	public ResponseEntity<Map<String, Object>> EditarProyecto(Proyecto proyectoEntrada,MultipartFile file, Integer id) throws IOException{
		Map<String, Object> respuesta = new HashMap<>();
		String nombreImagen;
		if(file != null && !file.isEmpty()) {
			Path folderPath = Paths.get(folderProyecto);
			
			
			
			String uniqueFileName = System.currentTimeMillis()+ "_"+ file.getOriginalFilename();
			Path filePath = folderPath.resolve(uniqueFileName);
			
			byte[] bytes = file.getBytes();
			Files.write(filePath, bytes);
			nombreImagen = uniqueFileName;
		}else {
			nombreImagen = proyectoEntrada.getImgProyecto();
		}
		
		Optional<Proyecto> proyectoPresente = dao.findById(id);
		if(proyectoPresente.isPresent()) {
			Proyecto proyecto = proyectoPresente.get();
			proyecto.setNombre(proyectoEntrada.getNombre());
			proyecto.setDescripcion(proyectoEntrada.getDescripcion());
			proyecto.setImgProyecto(nombreImagen);
			proyecto.setIdUsuario(proyectoEntrada.getIdUsuario());
			
			dao.save(proyecto);
			
			respuesta.put("mensaje", "proyecto editado correctamente");
			respuesta.put("Proyecto", proyecto);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existe el proyecto: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	

}
