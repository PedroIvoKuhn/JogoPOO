package entities.personagens;

import entities.Personagem;

public class Paladino extends Personagem {
    private int qtdDeVida;

    public Paladino() {
    }

    public int getqtdDeVida() {
        return qtdDeVida;
    }

    public Paladino(int ataque, int defesa, int saude) {
        super(ataque, defesa, saude, "Paladino");
        this.qtdDeVida = (int) (saude * 0.5);
    }
    
    @Override
    public void habilidade() {
        setSaude(getSaude() + getqtdDeVida());
    }

}
