package com.Infnet.MedievalBattle.Heroi;

import com.Infnet.MedievalBattle.Personagem.Personagem;

public class Paladino extends Personagem {
    public Paladino(String nome){
        super(nome, 15,2,5,1,4);
    }

    public String toString() {
        return "Paladino";
    }
}
