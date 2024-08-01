package entities.personagens;

import entities.Personagem;

public class Guerreiro extends Personagem {
    private int rodadas;

    public Guerreiro() {
    }

    public Guerreiro(int ataque, int defesa, int saude) {
        super(ataque, defesa, saude, "Guerreiro");
        this.rodadas = 0;
    }

    @Override
    public void habilidade() {
        if (rodadas == 0) {
            setDefesa((int) (getDefesa() * 1.5));
            rodadas++;
        }
        // calculo errada
        if (rodadas == 2) {
            setDefesa((int) (getDefesa() * 0.5));
        }
    }
    
}
