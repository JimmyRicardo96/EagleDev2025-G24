package com.uisrael.Sistema.Casificacion.Radiografias.Services;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.PacienteEntity;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    PacienteEntity guardarPaciente(PacienteEntity paciente);
    List<PacienteEntity> obtenerTodos();
    Optional<PacienteEntity> obtenerPorId(Integer id);
    Optional<PacienteEntity> obtenerPorIdentificacion(String identificacion);
    PacienteEntity actualizarPaciente(Integer id, PacienteEntity paciente);
    void eliminarPaciente(Integer id);
}
