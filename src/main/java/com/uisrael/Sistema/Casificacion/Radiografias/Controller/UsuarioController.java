package com.uisrael.Sistema.Casificacion.Radiografias.Controller;

import com.uisrael.Sistema.Casificacion.Radiografias.Entity.UsuarioEntity;
import com.uisrael.Sistema.Casificacion.Radiografias.Services.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioServicio servicioUsuario;

    @GetMapping
    public String mostrarUsuarios(Model model) {
        model.addAttribute("usuarios",servicioUsuario.findAll());
        return "usuarios/listar";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
        UsuarioEntity usuario = new UsuarioEntity();
        model.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardaUsuario(@ModelAttribute("usuario") UsuarioEntity usuario) {
        servicioUsuario.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") int id, Model model) {
        UsuarioEntity usuario = servicioUsuario.findById(id);
        model.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminaUsuario(@PathVariable("id") int id) {
        UsuarioEntity usuario = servicioUsuario.findById(id);
        servicioUsuario.deleteById(usuario.getId());
        return "redirect:/usuarios";
    }
}
