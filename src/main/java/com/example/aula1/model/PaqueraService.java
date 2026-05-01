package com.example.aula1.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaqueraService {

    @Autowired
    PaqueraDAO paqueraDAO;

    public void inserirPaquera(Paquera paquera) {
        paqueraDAO.inserirPaquera(paquera);
    }

    public void deletarPaquera(String uuid) {
        paqueraDAO.deletarPaquera(uuid);
    }

    public Paquera mostrarPaquera(String uuid) {
        return paqueraDAO.mostrarPaquera(uuid);
    }

    public ArrayList<Paquera> listarPaqueras() {
        return paqueraDAO.listarPaqueras();
    }

    public Paquera buscarPorId(String id) {
        return repository.findById(id).orElseThrow();
    }

    public void salvar(Paquera paquera) {
        repository.save(paquera);
}
}
