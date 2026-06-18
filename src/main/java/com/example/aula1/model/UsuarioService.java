package com.example.aula1.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;

    public void cadastrar(Usuario usuario) {
        usuarioDAO.inserirUsuario(usuario);
    }

    public boolean usuarioJaExiste(String nome) {
        return usuarioDAO.existeUsuario(nome);
    }

    public String obterIdPorNome(String nome) {
        return usuarioDAO.obterIdPorNome(nome);
    }
}
