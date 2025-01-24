package com.hice.back.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.hice.back.model.Audio;

public interface PaisService {


	public ResponseEntity<Map<String, Object>> listarPais();
	
	
}
