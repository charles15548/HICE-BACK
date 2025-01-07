package com.hice.back.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.hice.back.model.Audio;

public interface AudioService {


	public ResponseEntity<Map<String, Object>> listarAudio();
	public ResponseEntity<Map<String, Object>> listarAudioPorId(Integer id);
	public ResponseEntity<Map<String, Object>> CrearAudio(Audio audio);
	public ResponseEntity<Map<String, Object>> EditarAudio(Audio audio,Integer id);

	public ResponseEntity<Map<String, Object>> EliminarAudio(Integer id);
	public Optional<Audio> get(Integer id);
	
	public ResponseEntity<Map<String, Object>> listarPorPersonaje(Integer id);
	public ResponseEntity<Map<String, Object>> buscarAudio(String nombre);
}
