package com.uisrael.Sistema.Casificacion.Radiografias.Controller;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.PacienteEntity;
import com.uisrael.Sistema.Casificacion.Radiografias.Services.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class Homecontroller {
    private final PacienteService pacienteService;

    public Homecontroller(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        List<PacienteEntity> pacientes = pacienteService.obtenerTodos();

        // Agrupar por diagnóstico
        Map<String, Long> diagnosticoStats = pacientes.stream()
                .collect(Collectors.groupingBy(PacienteEntity::getPrediccion, Collectors.counting()));

        model.addAttribute("pacientes", pacientes);
        model.addAttribute("diagnosticoStats", diagnosticoStats);

        return "index";
    }


    @GetMapping("/blank")
    public String blankPage() {
        return "blank";
    }

    @GetMapping("sistemas")
    public String sistemasPage() {
        return "sistemas";
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/streamlit")
    public String mostrarClasificador() {
        return "streamlit"; // o "clasificador" si decides cambiar el nombre del HTML
    }

}
