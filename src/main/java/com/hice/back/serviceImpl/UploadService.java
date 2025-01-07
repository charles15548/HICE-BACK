package com.hice.back.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {


		private String folder ="Archivo//Voz//";
	
	public String saveAudio(MultipartFile file ) throws IOException {
		
		if(!file.isEmpty()) {
			//convertir en file lo que recibimos del front
			byte[] bytes = file.getBytes();
			//a lo que recibimos le damos un nombre y una direccion
			Path path = Paths.get(folder+file.getOriginalFilename());
			// con FILES le damos el lugar con nombre y el tipo de archivo
			Files.write(path, bytes);
			// finalmente retornamos el nombre
			return file.getOriginalFilename();
		}
		return "default.jpg";
	}
	
	public String updateAudio(MultipartFile file, String existingFileName) throws IOException {
	   // obtenemos el file y lo convertimos en bytes
	        byte[] bytes = file.getBytes();
	        // como sabemos que lo estamos actualizando simplemente le pasamos el nombre que ya existe, obtenido del controller
	        Path path = Paths.get(folder + existingFileName); 
	        // si existe le pasamos los datos a un FILES
	        if (Files.exists(path)) {
	            Files.write(path, bytes);
	            return existingFileName;
	        } else {
	        	// si no existe le damos el nombre original que obtenemos
	        	Path pathupdate = Paths.get(folder+file.getOriginalFilename());
				Files.write(pathupdate, bytes);
				return file.getOriginalFilename();
	        }
	    
	   
	}

	
	// Eliminar archivo de audio
   public void deleteAudio(String fileName) throws IOException {
       String ruta=folder;
       File file = new File(ruta + fileName);
       file.delete();
   
   }
   
 
}
