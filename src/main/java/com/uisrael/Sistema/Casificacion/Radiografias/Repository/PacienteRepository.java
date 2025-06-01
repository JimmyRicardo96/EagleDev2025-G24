package com.uisrael.Sistema.Casificacion.Radiografias.Repository;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Integer> {
Optional<PacienteEntity> findByIdentificacion(String identificacion);
}
