

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

@Entity
@Data
@Table(name = "audio")
public class Audio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_audio")
	private Integer idAudio;
	private String nombre;
	private String descripcion;
	private String voz;
	@Column(name = "id_personaje")
	private Integer idPersonaje;
	
	
	@ManyToOne
    @JoinColumn(name = "id_personaje" ,insertable = false, updatable = false)
    private Personaje personaje;
}
