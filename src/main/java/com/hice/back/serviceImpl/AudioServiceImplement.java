package com.hice.back.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hice.back.model.Audio;
import com.hice.back.model.Personaje;
import com.hice.back.model.Usuario;
import com.hice.back.repository.AudioRepository;
import com.hice.back.service.AudioService;

@Service
public class AudioServiceImplement implements AudioService{

	@Autowired
	private AudioRepository dao;
	
	@Autowired
	private UploadService uploadService;
	@Override
	public ResponseEntity<Map<String, Object>> listarAudio() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Audio> audio = dao.findAll();
		if(!audio.isEmpty()) {
			respuesta.put("mensaje", "Lista de audios");
			respuesta.put("Audio", audio);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen audios");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}

	@Override
	public ResponseEntity<Map<String, Object>> CrearAudio(Audio audio) {
		Map<String, Object> respuesta = new HashMap<>();
		dao.save(audio);
		respuesta.put("mensaje", "Audio creado correctamente");
		respuesta.put("Audio", audio);
		respuesta.put("fecha", new Date());
		respuesta.put("status", HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

	@Override
	public ResponseEntity<Map<String, Object>> EditarAudio(Audio audioEntrada, Integer id) {
		Map<String,Object> respuesta = new HashMap<>();
		Optional<Audio> audioPresente =dao.findById(id);
		if(audioPresente.isPresent()) {
			Audio audio =audioPresente.get();
			audio.setNombre(audioEntrada.getNombre());
			audio.setDescripcion(audioEntrada.getDescripcion());
			audio.setVoz(audioEntrada.getVoz());
			audio.setIdPersonaje(audioEntrada.getIdPersonaje());
			
			dao.save(audio);
			respuesta.put("mensaje", "Audio editado correctamente");
			respuesta.put("Audio", audio);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		
		}else {
			
			respuesta.put("mensaje", "No existe el audios: " + id);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	
	
	@Override
	public ResponseEntity<Map<String, Object>> listarAudioPorId(Integer id) {
		Map<String, Object> respuesta = new HashMap<>();
		Optional<Audio> audio = dao.findById(id);
		if(!audio.isEmpty()) {
			respuesta.put("mensaje", "Lista de audios con id " + id);
			respuesta.put("Audio", audio);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen audio");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		
	}
	
	
	@Override
	public ResponseEntity<Map<String, Object>> EliminarAudio(Integer id) {
		Map<String, Object> r = new HashMap<>();
		Optional<Audio> audio = dao.findById(id);
		if(audio.isPresent()) {
			Audio aud = audio.get();
			dao.delete(aud);
			r.put("mensaje","Audio eliminado" +id);
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



	@Override
	public Optional<Audio> get(Integer id) {
		
		return dao.findById(id);
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarPorPersonaje(Integer id) {
		Map<String,Object> r = new HashMap<>();
		List<Audio> audio = dao.findByIdPersonaje(id);
		if(!audio.isEmpty()) {
			r.put("mensaje", "lista de audio por personaje" + id);
			r.put("Audio", audio);
			r.put("status", HttpStatus.OK);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.OK).body(r);
		}else {
			r.put("mensaje", "sin audio con el id personaje "+ id);
			r.put("status", HttpStatus.NOT_FOUND);
			r.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> buscarAudio(String nombre) {
		Map<String, Object> r = new HashMap<>();
		List<Audio> audio = dao.findByNombreContaining(nombre);
		if(!audio.isEmpty()) {
			r.put("mesaje", "busqueda exitosa");
			r.put("Personaje", audio);
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

	
	
	
}
