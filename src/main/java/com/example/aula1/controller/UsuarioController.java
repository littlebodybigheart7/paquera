package com.example.aula1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.aula1.model.Usuario;
import com.example.aula1.model.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String formCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String salvarCadastro(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioService.usuarioJaExiste(usuario.getNome())) {
            model.addAttribute("erro", "Esse nome de usuário já está em uso.");
            return "cadastro";
        }
        usuarioService.cadastrar(usuario);
        return "redirect:/login?cadastrado";
    }
}
