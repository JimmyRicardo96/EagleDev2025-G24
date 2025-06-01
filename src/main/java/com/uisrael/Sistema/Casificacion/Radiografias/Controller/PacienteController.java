package com.uisrael.Sistema.Casificacion.Radiografias.Controller;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.PacienteEntity;
import com.uisrael.Sistema.Casificacion.Radiografias.Services.PacienteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Value("${pacientes.upload-dir}")
    private String uploadDir;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Mostrar lista de pacientes en vista HTML
    @GetMapping
    public String mostrarPacientes(Model model) {
        List<PacienteEntity> pacientes = pacienteService.obtenerTodos();
        model.addAttribute("pacientes", pacientes);
        return "pacientes/listar"; // vista HTML en /templates/pacientes/listar.html
    }

    // Mostrar formulario de nuevo paciente
    @GetMapping("/nuevo")
    public String nuevoPaciente(Model model) {
        model.addAttribute("paciente", new PacienteEntity());
        return "pacientes/formulario"; // vista HTML en /templates/pacientes/formulario.html
    }

    // Guardar paciente (con imagen)
    @PostMapping("/guardar")
    public String guardarPaciente(@ModelAttribute("paciente") PacienteEntity paciente,
                                  @RequestParam("imagen") MultipartFile imagen) throws IOException {

        if (imagen != null && !imagen.isEmpty()) {
            String uploadDir = "C:/imagenes_subidas"; // ruta local para guardar imágenes
            File directorio = new File(uploadDir);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            String originalFilename = StringUtils.cleanPath(imagen.getOriginalFilename());
            String extension = "";

            int lastDot = originalFilename.lastIndexOf(".");
            if (lastDot != -1) {
                extension = originalFilename.substring(lastDot);
            }

            // Formatear fecha a String para evitar errores y caracteres inválidos
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fechaFormateada = formatter.format(paciente.getFecha());

            String filename = paciente.getIdentificacion() + "_" + fechaFormateada + extension;

            String rutaCompleta = uploadDir + File.separator + filename;

            imagen.transferTo(new File(rutaCompleta));
            paciente.setRutaImagen("/imagenes_subidas/" + filename);
        }

        pacienteService.guardarPaciente(paciente);
        return "redirect:/pacientes";
    }



    // Editar paciente
    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable("id") Integer id, Model model) {
        PacienteEntity paciente = pacienteService.obtenerPorId(id).orElse(null);
        if (paciente != null) {
            model.addAttribute("paciente", paciente);
            return "pacientes/formulario";
        }
        return "redirect:/pacientes";
    }

    // Eliminar paciente
    @GetMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable("id") Integer id) {
        pacienteService.eliminarPaciente(id);
        return "redirect:/pacientes";
    }

}
