package com.uisrael.Sistema.Casificacion.Radiografias.Repository;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    UsuarioEntity findByUsername(String username);
}
