package com.example.aula1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.aula1.model.Paquera;
import com.example.aula1.model.PaqueraService;
import com.example.aula1.model.UsuarioService;

@Controller
public class MenuController {

    @Autowired
    private PaqueraService paqueraService;

    @Autowired
    private UsuarioService usuarioService;

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

    @GetMapping("/formpaquera")
    public String formularioPaquera(Model model) {
        model.addAttribute("Paquera", new Paquera());
        return "formpaquera";
    }

    @PostMapping("/Paquera")
    public String salvarPaquera(@ModelAttribute Paquera paquera, Authentication auth) {
        String usuarioId = usuarioService.obterIdPorNome(auth.getName());
        paqueraService.salvar(paquera, usuarioId);
        return "redirect:/paqueras";
    }

    @GetMapping("/paqueras")
    public String listarPaqueras(Model model, Authentication auth) {
        model.addAttribute("paqueras", paqueraService.listarPaqueras());

        boolean isLoggedIn = auth != null && auth.isAuthenticated()
                             && !auth.getName().equals("anonymousUser");
        boolean isAdmin = isLoggedIn && auth.getAuthorities().stream()
                          .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("isAdmin", isAdmin);

        if (isLoggedIn && !isAdmin) {
            model.addAttribute("usuarioId", usuarioService.obterIdPorNome(auth.getName()));
        } else {
            model.addAttribute("usuarioId", null);
        }

        return "paqueras";
    }

    @PostMapping("/deletar/{id}")
    public String deletarPaquera(@PathVariable String id, Authentication auth) {
        boolean isAdmin = auth.getAuthorities().stream()
                          .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            String usuarioId = usuarioService.obterIdPorNome(auth.getName());
            Paquera p = paqueraService.buscarPorId(id);
            if (p.getUsuarioId() == null || !p.getUsuarioId().equals(usuarioId)) {
                return "redirect:/paqueras";
            }
        }

        paqueraService.deletarPaquera(id);
        return "redirect:/paqueras";
    }

    @GetMapping("/editar/{id}")
    public String editarPaquera(@PathVariable String id, Model model, Authentication auth) {
        boolean isAdmin = auth.getAuthorities().stream()
                          .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        Paquera paquera = paqueraService.buscarPorId(id);

        if (!isAdmin) {
            String usuarioId = usuarioService.obterIdPorNome(auth.getName());
            if (paquera.getUsuarioId() == null || !paquera.getUsuarioId().equals(usuarioId)) {
                return "redirect:/paqueras";
            }
        }

        model.addAttribute("Paquera", paquera);
        return "formpaquera";
    }
}
