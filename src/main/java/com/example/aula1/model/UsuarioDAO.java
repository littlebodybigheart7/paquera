package com.example.aula1.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UsuarioDAO {

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, email, password) VALUES (?, ?, ?)";
        jdbc.update(sql,
            usuario.getNome(),
            usuario.getEmail(),
            passwordEncoder.encode(usuario.getPassword())
        );
    }

    public boolean existeUsuario(String nome) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE nome = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, nome);
        return count != null && count > 0;
    }
}
