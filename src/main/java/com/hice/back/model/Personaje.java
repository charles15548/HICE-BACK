package com.hice.back.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "personaje")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id_personaje")
    private Integer idPersonaje;

    private String nombre;
    private String descripcion;
    @Column(name = "img_personaje", nullable = false, length = 500)
    private String imgPersonaje;
    @Column(name = "id_proyecto")
    private Integer idProyecto;

    @ManyToOne
    @JoinColumn(name = "id_proyecto", insertable = false, updatable = false)
    private Usuario objPersonajeProyecto;

}