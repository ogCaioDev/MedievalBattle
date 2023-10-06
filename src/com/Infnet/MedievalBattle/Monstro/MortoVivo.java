package com.Infnet.MedievalBattle.Monstro;

import com.Infnet.MedievalBattle.Personagem.Personagem;

public class MortoVivo extends Personagem {
    public MortoVivo(String nome){
        super(nome, 25,4,0,1,4);
    }

    public String toString() {
        return "Guerreiro";
    }
}
