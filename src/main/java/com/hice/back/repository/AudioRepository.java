package com.hice.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hice.back.model.Audio;
import com.hice.back.model.Personaje;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Integer>{


    List<Audio> findByIdPersonaje(Integer idPersonaje);
    List<Audio> findByNombreContaining(String nombre);
    
    @Query("SELECT a FROM Audio a " +
            "JOIN a.personaje p " +
            "JOIN p.proyecto pro " +
            "WHERE pro.idProyecto = :idProyecto AND p.idPersonaje = :idPersonaje")
     List<Audio> findAudiosByProyectoAndPersonaje(@Param("idProyecto") int idProyecto, 
                                                  @Param("idPersonaje") int idPersonaje);

}
