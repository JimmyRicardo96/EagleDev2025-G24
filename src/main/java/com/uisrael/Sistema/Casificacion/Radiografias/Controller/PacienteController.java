package com.uisrael.Sistema.Casificacion.Radiografias.Controller;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.PacienteEntity;
import com.uisrael.Sistema.Casificacion.Radiografias.Services.PacienteService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @Value("${pacientes.upload-dir}")
    private String uploadDir;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @PostMapping
    public ResponseEntity<PacienteEntity> registrarPaciente(
            @RequestParam("identificacion") String identificacion,
            @RequestParam("nombres") String nombres,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("direccion") String direccion,
            @RequestParam("prediccion") String prediccion,
            @RequestParam("fecha") String fecha,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {

        // Obtener extensión del archivo original
        String originalFilename = StringUtils.cleanPath(imagen.getOriginalFilename());
        String extension = "";

        int lastDot = originalFilename.lastIndexOf(".");
        if (lastDot != -1) {
            extension = originalFilename.substring(lastDot);
        }

        // Generar nombre de archivo: cedula_fecha.extensión
        String filename = identificacion + "_" + fecha.replaceAll("[:\\s]", "_") + extension;
        String rutaCompleta = uploadDir + File.separator + filename;

        // Crear directorio si no existe
        File directorio = new File(uploadDir);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Guardar el archivo
        imagen.transferTo(new File(rutaCompleta));

        // Crear entidad paciente
        PacienteEntity paciente = new PacienteEntity();
        paciente.setIdentificacion(identificacion);
        paciente.setNombres(nombres);
        paciente.setApellidos(apellidos);
        paciente.setDireccion(direccion);
        paciente.setPrediccion(prediccion);
        paciente.setFecha(fecha);
        paciente.setRutaImagen("uploads/" + filename); // Ruta relativa

        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @GetMapping
    public List<PacienteEntity> listar() {
        return pacienteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteEntity> obtener(@PathVariable Integer id) {
        return pacienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{identificacion}")
    public ResponseEntity<PacienteEntity> obtenerIdentificacion(@PathVariable String identificacion) {
        return pacienteService.obtenerPorIdentificacion(identificacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteEntity> actualizar(@PathVariable Integer id, @RequestBody PacienteEntity paciente) {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(id, paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
