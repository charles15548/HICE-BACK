package com.hice.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hice.back.model.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Integer>{

	    List<Personaje> findByIdProyecto(Integer idProyecto);
	    List<Personaje> findByNombreContaining(String nombre);
}
