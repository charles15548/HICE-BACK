package com.hice.back.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 50)
    private String apellidoMaterno;
    private String email;
    private String password;
    @Column(name = "img_usuario", nullable = false, length = 500)
    private String imgUsuario;
    private Integer edad;
    private String genero;
    @Column(name = "id_pais")
    private Integer idPais;
    @Column(name = "id_departamento")
    private Integer idDepartamento;
    

    @ManyToOne
    @JoinColumn(name = "id_pais",insertable = false, updatable = false )
    private Pais objPais;

  
    @ManyToOne
    @JoinColumn(name = "id_departamento", insertable = false, updatable = false)
    private Departamento objDepartamento;
}