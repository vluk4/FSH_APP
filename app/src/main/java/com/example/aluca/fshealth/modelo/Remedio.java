package com.example.aluca.fshealth.modelo;

import android.support.annotation.NonNull;

public class Remedio {

    private Long id;
    private String nome;
    private int hora;
    private int minuto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
