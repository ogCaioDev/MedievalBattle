package com.Infnet.MedievalBattle.Personagem;

import java.util.Random;

public class Personagem {
    protected String nome;
    protected int saude;
    protected int forca;
    protected int defesa;
    protected int agilidade;
    protected int fatorDano;

    public int calcularIniciativa() {
        Random random = new Random();
        // Rola um dado de 10 faces (gera um número aleatório entre 1 e 10)
        int resultadoDoDado = random.nextInt(10) + 1;
        // Soma o fator de agilidade ao resultado do dado
        int iniciativa = resultadoDoDado + agilidade;
        return iniciativa;
    }


    public Personagem(String nome, int saude, int forca, int defesa, int agilidade, int fatorDano) {
        this.nome = nome;
        this.saude = saude;
        this.forca = forca;
        this.defesa = defesa;
        this.agilidade = agilidade;
        this.fatorDano = fatorDano;
    }

    public boolean estaVivo() {
        return saude > 0;
    }

    public int calcularDano() {
        int danoBase = fatorDano;

        Random random = new Random();
        int danoAleatorio = random.nextInt(fatorDano) + 1;

        return danoBase + danoAleatorio;
    }

    public void receberDano(int quantidadeDano) {
        saude -= quantidadeDano;
    }

    public int getSaude(){
        return saude;
    }

    public String getNome() {
        return nome;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getFatorDano() {
        return fatorDano;
    }

    public int getForca() {
        return forca;
    }

}

