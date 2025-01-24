package com.hice.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hice.back.model.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{
	
	 List<Proyecto> findByIdUsuario(Integer idUsuario);
	 List<Proyecto> findByNombreContaining(String Nombre );
}
