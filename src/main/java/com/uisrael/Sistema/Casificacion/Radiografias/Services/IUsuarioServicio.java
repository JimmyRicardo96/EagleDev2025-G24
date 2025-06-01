package com.uisrael.Sistema.Casificacion.Radiografias.Services;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface IUsuarioServicio {
    UsuarioEntity guardarUsuario(UsuarioEntity usuario);

    Optional<UsuarioEntity> findByUsername(String username);

    List<UsuarioEntity> findAll();

    UsuarioEntity findById(int id);

    void deleteById(int id);
}
