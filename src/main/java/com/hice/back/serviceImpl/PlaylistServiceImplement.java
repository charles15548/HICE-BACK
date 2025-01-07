package com.hice.back.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hice.back.model.Audio;
import com.hice.back.model.Playlist;
import com.hice.back.model.Proyecto;
import com.hice.back.model.Usuario;
import com.hice.back.repository.PlaylistRepository;
import com.hice.back.repository.ProyectoRepository;
import com.hice.back.service.PlaylistService;
import com.hice.back.service.ProyectoService;

@Service
public class PlaylistServiceImplement implements PlaylistService{

	@Autowired
	private PlaylistRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listarPlaylist() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Playlist> playlists = dao.findAll();
		if(!playlists.isEmpty()) {
			respuesta.put("mensaje", "Lista de playlist");
			respuesta.put("Playlist", playlists);
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}else {
			respuesta.put("mensaje", "No existen playlist");
			respuesta.put("status", HttpStatus.NOT_FOUND);
			respuesta.put("fecha", new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

}
