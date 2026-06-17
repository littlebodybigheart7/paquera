package com.example.aula1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                         @RequestParam(value = "logout", required = false) String logout,
                         @RequestParam(value = "cadastrado", required = false) String cadastrado,
                         Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "Usuário ou senha inválidos.");
        }
        if (logout != null) {
            model.addAttribute("logoutMsg", "Você saiu da sua conta.");
        }
        if (cadastrado != null) {
            model.addAttribute("cadastradoMsg", "Cadastro feito! Agora você já pode entrar.");
        }
        return "login";
    }
}
