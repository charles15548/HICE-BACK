package com.hice.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hice.back.model.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{

	 List<Proyecto> findByNombreContaining(String Nombre );
}
