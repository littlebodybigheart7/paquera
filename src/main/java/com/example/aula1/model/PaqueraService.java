package com.example.aula1.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaqueraService {

    @Autowired
    PaqueraDAO PaqueraDAO;

    public void inserirPaquera(Paquera Paquera){
        PaqueraDAO.inserirPaquera(Paquera);
    }
     public Paquera mostrarPaquera(String uuid){
        return PaqueraDAO.mostrarPaquera(uuid);
    }

    public ArrayList<Paquera> listarPaqueras(){
        return PaqueraDAO.listarPaqueras();
    }
}
