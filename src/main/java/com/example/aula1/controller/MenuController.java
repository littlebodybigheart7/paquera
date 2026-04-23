package com.example.aula1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.aula1.model.Paquera;
import com.example.aula1.model.PaqueraService;

@Controller
public class MenuController {

    @Autowired
    private PaqueraService paqueraService;

    @GetMapping("/")
    public String paginaPrincipal() {
        return "index";
    }

    @GetMapping("/p1")
    public String pagina1() {
        return "p1";
    }

    @GetMapping("/p2")
    public String pagina2() {
        return "p2";
    }

    // Sem esse GET o Thymeleaf não consegue montar o th:object="${Paquera}"
    @GetMapping("/formpaquera")
    public String formularioPaquera(Model model) {
        model.addAttribute("Paquera", new Paquera());
        return "formpaquera";
    }

    @PostMapping("/Paquera")
    public String postPaquera(@ModelAttribute Paquera paquera, Model model) {
        paqueraService.inserirPaquera(paquera);
        return "sucesso";
    }
}
