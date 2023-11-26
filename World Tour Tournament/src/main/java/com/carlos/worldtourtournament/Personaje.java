package com.carlos.worldtourtournament;

import java.util.Random;

public class Personaje {

    private int vida = 100;
    private int ataque;
    private String imagen;
    private int id;

    public Personaje(String imagen) {
        this.imagen = imagen;
    }

    public Personaje(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int generarAtaqueAleatorio() {
        Random random = new Random();
        return random.nextInt(16) + 10;
    }

    public double getVidaProgress() {
        return (double) vida / 100.0;
    }
}
