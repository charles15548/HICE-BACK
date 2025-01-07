package com.hice.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hice.back.model.Audio;
import com.hice.back.model.Personaje;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Integer>{


    List<Audio> findByIdPersonaje(Integer idPersonaje);
    List<Audio> findByNombreContaining(String nombre);
}
