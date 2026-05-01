package com.example.aula1.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaqueraService {

    @Autowired
    private PaqueraDAO paqueraDAO;

    // CREATE ou UPDATE
    public void salvar(Paquera paquera) {
        paqueraDAO.inserirPaquera(paquera);
    }

    // DELETE
    public void deletarPaquera(String uuid) {
        paqueraDAO.deletarPaquera(uuid);
    }

    // READ (um só)
    public Paquera buscarPorId(String id) {
        return paqueraDAO.mostrarPaquera(id);
    }

    // READ (lista)
    public ArrayList<Paquera> listarPaqueras() {
        return paqueraDAO.listarPaqueras();
    }
}
