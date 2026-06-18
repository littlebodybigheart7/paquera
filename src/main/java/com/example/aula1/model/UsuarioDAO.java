package com.example.aula1.model;

import java.util.Map;
import java.util.UUID;

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
        String sql = "INSERT INTO usuario(nome, email, password, cargo) VALUES (?, ?, ?, 'ROLE_USER')";
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

    public String obterIdPorNome(String nome) {
        String sql = "SELECT id FROM usuario WHERE nome = ?";
        Map<String, Object> mp = jdbc.queryForMap(sql, nome);
        UUID id = (UUID) mp.get("id");
        return id.toString();
    }
}
