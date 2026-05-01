package com.example.aula1.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaqueraDAO {

    @Autowired
    private JdbcTemplate jdbc;

    // INSERT
    public void inserirPaquera(Paquera paquera) {
        String sql = "INSERT INTO paquera(id, nome, idade, signo, gostade, naogostade, foto) VALUES (gen_random_uuid(), ?, ?, ?, ?, ?, ?)";
        
        Object[] obj = new Object[6];
        obj[0] = paquera.getNome();
        obj[1] = paquera.getIdade();
        obj[2] = paquera.getSigno();
        obj[3] = paquera.getGostaDe();
        obj[4] = paquera.getNaoGostaDe();
        obj[5] = paquera.getFoto();

        jdbc.update(sql, obj);
    }

    // UPDATE
    public void atualizarPaquera(Paquera paquera) {
        String sql = "UPDATE paquera SET nome=?, idade=?, signo=?, gostade=?, naogostade=?, foto=? WHERE id = ?::uuid";
        
        Object[] obj = new Object[7];
        obj[0] = paquera.getNome();
        obj[1] = paquera.getIdade();
        obj[2] = paquera.getSigno();
        obj[3] = paquera.getGostaDe();
        obj[4] = paquera.getNaoGostaDe();
        obj[5] = paquera.getFoto();
        obj[6] = paquera.getId();

        jdbc.update(sql, obj);
    }

    public void deletarPaquera(String uuid) {
        String sql = "DELETE FROM paquera WHERE id = ?::uuid";
        jdbc.update(sql, uuid);
    }

    public Paquera mostrarPaquera(String uuid) {
        String sql = "SELECT * FROM paquera WHERE id = ?::uuid";
        return Paquera.converter(jdbc.queryForMap(sql, uuid));
    }

    public ArrayList<Paquera> listarPaqueras() {
        String sql = "SELECT * FROM paquera";
        return Paquera.converterTodos(jdbc.queryForList(sql));
    }
}
