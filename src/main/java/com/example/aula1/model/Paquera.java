package com.example.aula1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Paquera {

    private String id, nome, idade, signo, gostaDe, naoGostaDe;

    public Paquera() {}

    public Paquera(String id, String nome, String idade, String signo, String gostaDe, String naoGostaDe) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.signo = signo;
        this.gostaDe = gostaDe;
        this.naoGostaDe = naoGostaDe;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getIdade() { return idade; }
    public String getSigno() { return signo; }
    public String getGostaDe() { return gostaDe; }
    public String getNaoGostaDe() { return naoGostaDe; }

    public void setId(String id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(String idade) { this.idade = idade; }
    public void setSigno(String signo) { this.signo = signo; }
    public void setGostaDe(String gostaDe) { this.gostaDe = gostaDe; }
    public void setNaoGostaDe(String naoGostaDe) { this.naoGostaDe = naoGostaDe; }

    public static Paquera converter(Map<String, Object> registro) {
        UUID id = (UUID) registro.get("id");
        String nome = (String) registro.get("nome");
        String idade = (String) registro.get("idade");
        String signo = (String) registro.get("signo");
        String gostaDe = (String) registro.get("gostade");
        String naoGostaDe = (String) registro.get("naogostade");
        return new Paquera(id.toString(), nome, idade, signo, gostaDe, naoGostaDe);
    }

    public static ArrayList<Paquera> converterTodos(List<Map<String, Object>> registros) {
        ArrayList<Paquera> aux = new ArrayList<>();
        for (Map<String, Object> registro : registros) {
            aux.add(converter(registro));
        }
        return aux;
    }
}
