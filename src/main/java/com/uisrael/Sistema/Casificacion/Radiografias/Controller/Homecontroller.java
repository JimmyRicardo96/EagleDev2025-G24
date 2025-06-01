package com.uisrael.Sistema.Casificacion.Radiografias.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String homePage() {
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
}
