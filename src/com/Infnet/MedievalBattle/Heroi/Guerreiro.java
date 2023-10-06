package com.Infnet.MedievalBattle.Heroi;
import com.Infnet.MedievalBattle.Personagem.Personagem;

public class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        super(nome, 12, 4,3,3,4);
    }

    public String toString() {
        return "Guerreiro";
    }
}
