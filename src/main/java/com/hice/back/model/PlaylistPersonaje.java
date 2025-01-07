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
@Table(name = "playlist_personaje")
public class PlaylistPersonaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id_playlist_personaje")
    private Integer idPlaylistPersonaje;

    private String nombre;
    private String descripcion;
    @Column(name = "img_playlist_personaje")
    private String imgPlaylistPersonaje;

    @Column(name = "id_playlist")
    private Integer idPlaylist;
    @Column(name = "id_audio")
    private Integer idAudio;

    @ManyToOne
    @JoinColumn(name = "id_playlist", insertable = false, updatable = false)
    private Usuario objPlayPersonaje;
    @ManyToOne
    @JoinColumn(name = "id_audio", insertable = false, updatable = false)
    private Usuario objPlayPersonajeAudio;

}