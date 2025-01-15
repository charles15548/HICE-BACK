package com.hice.back.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.hice.back.model.Audio;
import com.hice.back.model.Usuario;
import com.hice.back.service.AudioService;
import com.hice.back.serviceImpl.AudioServiceImplement;
import com.hice.back.serviceImpl.UploadService;

@RestController
@RequestMapping("api/audio")
public class AudioController {

	@Autowired
	private AudioService audioService;
	@Autowired
	private AudioServiceImplement implement;
	@Autowired
	private UploadService upload;

	@GetMapping()
	public ResponseEntity<Map<String, Object>> listarAudio() {
		return audioService.listarAudio();
	}
	
/*	
 @GetMapping("/proyecto/{idProyecto}/personaje/{idPersonaje}")
    public ResponseEntity<List<Audio>> getAudiosByProyectoAndPersonaje(
            @PathVariable int idProyecto, 
            @PathVariable int idPersonaje) {
        List<Audio> audios = implement.getAudiosByProyectoAndPersonaje(idProyecto, idPersonaje);
        if (audios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(audios);
    }
    */
	
	
	@GetMapping("/proyecto/{idProyecto}/personaje/{idPersonaje}")
    public ResponseEntity<Map<String, Object>> getAudioByProyectoAndPersonaje(
            @PathVariable int idProyecto, 
            @PathVariable int idPersonaje) {
       
        return audioService.findAudiosByProyectoAndPersonaje(idProyecto, idPersonaje);
    }

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listarAudioPorId(@PathVariable Integer id) {
		return audioService.listarAudioPorId(id);
	}
	@GetMapping("/personaje/{id}")
	public ResponseEntity<Map<String, Object>> listarAudioPorPersonaje(@PathVariable Integer id) {
		return audioService.listarPorPersonaje(id);
	}

	@GetMapping("/buscar")
	public ResponseEntity<Map<String, Object>> buscarAudio(@RequestParam String nombre){
		return audioService.buscarAudio(nombre);
	}

	@PostMapping()
	public ResponseEntity<Map<String, Object>> CrearAudio(@RequestParam("audioParam") String audios,
			@RequestParam("audioFile") MultipartFile file) throws IOException {

		Gson gson = new Gson();
		Audio audio = gson.fromJson(audios, Audio.class);
		String nombreAudio = upload.saveAudio(file);
		audio.setVoz(nombreAudio);
	;
		return audioService.CrearAudio(audio);

	}
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> EditarAudio(@PathVariable Integer id,@RequestParam("audioParam") String audios,
			@RequestParam(value = "audioFile", required = false) MultipartFile file ) throws IOException {

		JsonReader reader = new JsonReader(new StringReader(audios));
		reader.setLenient(true);
		Gson gson = new Gson();
		Audio audio = gson.fromJson(reader, Audio.class);
		// Obtener el audio existente desde la base de datos
		Audio audioVacio = new Audio();
		Optional<Audio> optionalAudio = audioService.get(id);
		audioVacio = optionalAudio.get();
		
		//momentaneo antes de hacer la parte del usuario
		//audio.setIdAudio(audioVacio.getIdAudio());
		//audio.setNombre(audioVacio.getNombre());
		//audio.setDescripcion(audioVacio.getDescripcion());
		audio.setIdPersonaje(audioVacio.getIdPersonaje());

		
		
		if(file != null && !file.isEmpty()) {
			upload.deleteAudio(audio.getVoz());
			String nuevoNombreArchivo = upload.saveAudio(file);
			audio.setVoz(nuevoNombreArchivo);
		}else {

			audio.setVoz(audioVacio.getVoz());
		}
		
			return audioService.EditarAudio(audio, id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteAudio(@PathVariable Integer id) throws IOException {
		Audio a = new Audio();
		a = audioService.get(id).get();
			upload.deleteAudio(a.getVoz());
		
		
		return audioService.EliminarAudio(id);
	}
  

	/*
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editarAudio(@RequestParam("audioParam") String audios,
			@RequestParam(value = "audioFile", required = false) MultipartFile file, @PathVariable Integer id) throws IOException {
		JsonReader reader = new JsonReader(new StringReader(audios));
		reader.setLenient(true);
		Gson gson = new Gson();
		Audio audioClass = gson.fromJson(reader, Audio.class);
		// Obtener el audio existente desde la base de datos
		Audio audioVacio = new Audio();
		Optional<Audio> optionalAudio = audioService.get(id);
		audioVacio = optionalAudio.get();
		//momentaneo antes de hacer la parte del usuario
		audioClass.setIdPersonaje(1);
	if(file != null && !file.isEmpty()) {
		String nuevoNombreArchivo = upload.updateAudio(file, audioVacio.getVoz());
			audioClass.setVoz(nuevoNombreArchivo);
	}else {
		audioClass.setVoz(audioVacio.getVoz());
	}
		return audioService.EditarAudio(audioClass, id);
	}


	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editarAudio(@PathVariable Integer id,@RequestParam("audioParam") String audios,
			@RequestParam(value = "audioFile", required = false) MultipartFile file ) throws IOException {

		//JsonReader reader = new JsonReader(new StringReader(audios));
		//reader.setLenient(true);
		Gson gson = new Gson();
		Audio audioClass = gson.fromJson(audios, Audio.class);
		// Obtener el audio existente desde la base de datos
		Audio audioVacio = new Audio();
		Optional<Audio> optionalAudio = audioService.get(id);
		audioVacio = optionalAudio.get();
		
		//momentaneo antes de hacer la parte del usuario
		audioClass.setIdPersonaje(1);

		
		if(file != null && !file.isEmpty()) {
			upload.deleteAudio(audioClass.getVoz());
			String nuevoNombreArchivo = upload.saveAudio(file);
			audioClass.setVoz(nuevoNombreArchivo);
		}else {
			audioClass.setVoz(audioVacio.getVoz());
		}
		
			return audioService.EditarAudio(audioClass, id);
	}
	*/
	
	
  


}
