package com.hice.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hice.back.model.Pais;

@Repository
public interface PaisRepository  extends JpaRepository<Pais, Integer>{

}
