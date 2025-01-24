package com.hice.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hice.back.model.Departamento;
import com.hice.back.model.Pais;
import com.hice.back.model.Personaje;

@Repository
public interface DepartamentoRepository  extends JpaRepository<Departamento, Integer>{

	List<Departamento> findByIdPais(Integer idPais);
}

