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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hice.back.model.Personaje;
import com.hice.back.repository.PersonajeRepository;
import com.hice.back.service.PersonajeService;

@Service
public class PersonajeServiceImplement implements PersonajeService{

	private String folderPersonaje = "Archivo//Personaje//";
	@Autowired
	private PersonajeRepository dao;
	@Override
	public ResponseEntity<Map<String, Object>> listarPersonaje() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Personaje> personaje = dao.findAll();
		if(!personaje.isEmpty()) {
			respuesta.put("mensaje", "Lista de Personajes");
			respuesta.put("Personaje", personaje);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen personajes");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarPorProyecto(Integer idProyecto) {
		Map<String,Object> r = new HashMap<>();
		List<Personaje> personaje = dao.findByIdProyecto(idProyecto);
		if(!personaje.isEmpty()) {
			r.put("mensaje", "lista de personajes por proyecto" + idProyecto);
			r.put("Personaje", personaje);
			r.put("status", HttpStatus.OK);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.OK).body(r);
		}else {
			r.put("mensaje", "sin personajes con el id proyecto "+ idProyecto);
			r.put("status", HttpStatus.NOT_FOUND);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> buscarPersonaje(String nombre) {
		Map<String, Object> r = new HashMap<>();
		List<Personaje> personaje = dao.findByNombreContaining(nombre);
		if(!personaje.isEmpty()) {
			r.put("mesaje", "busqueda exitosa");
			r.put("Personaje", personaje);
			r.put("status", HttpStatus.OK);
			r.put("fecha",new Date());
			return ResponseEntity.status(HttpStatus.OK).body(r);
		}else {
			r.put("mensaje", "no se encontro");
			r.put("status", HttpStatus.NOT_FOUND);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> listaPersonajePorId(Integer id) {
		Map<String, Object> respuesta = new HashMap<>();
		Optional<Personaje> personaje = dao.findById(id);
		if(personaje.isPresent()) {
			respuesta.put("mensaje", "personaje "+ id +" existe");
			respuesta.put("Personaje", personaje);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existe el personaje: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public Optional<Personaje> get(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public ResponseEntity<Map<String, Object>> AgregarPersonaje(Personaje personaje, MultipartFile file) throws IOException{
		
		Map<String, Object> respuesta = new HashMap<>();
		
		
		String nombreImagen = manejarArchivo(file);
		Personaje nuevoPersonaje = new Personaje();
		nuevoPersonaje.setDescripcion(personaje.getDescripcion());
		nuevoPersonaje.setNombre(personaje.getNombre());
		nuevoPersonaje.setImgPersonaje(nombreImagen);
		nuevoPersonaje.setIdProyecto(personaje.getIdProyecto());
		
		
		dao.save(nuevoPersonaje);
		respuesta.put("mensaje", "Personaje creado correctamente");
		respuesta.put("Personaje", nuevoPersonaje);
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

	@Override
	public ResponseEntity<Map<String, Object>> EditarPersonaje(Personaje personajeEntrada, MultipartFile file, Integer id)
			throws IOException {
		Map<String, Object> respuesta = new HashMap<>();
		String nombreImagen;
		
		
		  
		
		Optional<Personaje> personajePresente = dao.findById(id);
		if(personajePresente.isPresent()) {
			Personaje personaje = personajePresente.get();  String imgdelete = personaje.getImgPersonaje();
			if (file != null && !file.isEmpty()) {
				if(!imgdelete.equals("default.jpg")) {
					deleteImg(imgdelete);
				}
				
				nombreImagen = manejarArchivo(file);
				
			}
			else {
				nombreImagen = personaje.getImgPersonaje();
			}
			
			
			
			personaje.setNombre(personajeEntrada.getNombre());
			personaje.setDescripcion(personajeEntrada.getDescripcion());
			personaje.setImgPersonaje(nombreImagen);
			personaje.setIdProyecto(personaje.getIdProyecto());
			
			dao.save(personaje);
			
			respuesta.put("mensaje", "personaje editado correctamente");
			respuesta.put("Personaje", personaje);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existe el personaje: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> EliminarPersonaje(Integer id, String imgPersonaje) throws IOException {
		Map<String, Object> respuesta = new HashMap<>();
		
		Optional<Personaje> personaje = dao.findById(id);
		if(personaje.isPresent()) {
			Personaje p = personaje.get();
			dao.delete(p);
			deleteImg(imgPersonaje);
			
			respuesta.put("mensaje","Personaje eliminado con id:  "+ id  );
			respuesta.put("status", HttpStatus.NO_CONTENT);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
		}else{
			respuesta.put("mensaje","No encontramos el personaje:  "+ id  );
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}
	
	public String manejarArchivo(MultipartFile file) throws IOException{
		if(file != null && !file.isEmpty()) {
			Path folderPath = Paths.get(folderPersonaje);
			if(!Files.exists(folderPath)) {
				Files.createDirectories(folderPath);
			}
			String uniqueFileName = System.currentTimeMillis()+ "_" + file.getOriginalFilename();
			Path filePath = folderPath.resolve(uniqueFileName);
			Files.write(filePath, file.getBytes());
			return uniqueFileName;
		}
		return "default.jpg";
	}
	
	public void deleteImg(String filename) throws IOException{
		String ruta = folderPersonaje;
		File file = new File(ruta+filename);
		file.delete();
	}

}
