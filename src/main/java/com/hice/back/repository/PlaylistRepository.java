package com.hice.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hice.back.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>{

}
