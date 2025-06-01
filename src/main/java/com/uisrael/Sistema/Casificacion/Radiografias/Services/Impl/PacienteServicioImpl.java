package com.uisrael.Sistema.Casificacion.Radiografias.Services.Impl;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.PacienteEntity;
import com.uisrael.Sistema.Casificacion.Radiografias.Repository.PacienteRepository;
import com.uisrael.Sistema.Casificacion.Radiografias.Services.PacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServicioImpl implements PacienteService {
    private  final PacienteRepository pacienteRepository;
    public PacienteServicioImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    @Override
    public PacienteEntity guardarPaciente(PacienteEntity paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public List<PacienteEntity> obtenerTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<PacienteEntity> obtenerPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Optional<PacienteEntity> obtenerPorIdentificacion(String identificacion) {
        return Optional.empty();
    }

    @Override
    public PacienteEntity actualizarPaciente(Integer id, PacienteEntity paciente) {
        return pacienteRepository.findById(id).map(p -> {
            p.setNombres(paciente.getNombres());
            p.setApellidos(paciente.getApellidos());
            p.setIdentificacion(paciente.getIdentificacion());
            p.setDireccion(paciente.getDireccion());
            p.setRutaImagen(paciente.getRutaImagen());
            p.setPrediccion(paciente.getPrediccion());
            p.setFecha(paciente.getFecha());
            return pacienteRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    @Override
    public void eliminarPaciente(Integer id) {
        pacienteRepository.deleteById(id);
    }

}
