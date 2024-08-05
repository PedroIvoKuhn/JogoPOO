package entities.personagens;

import entities.Personagem;

public class Barbaro extends Personagem {    
    public Barbaro() {
    }

    public Barbaro(int ataque, int defesa, int saude) {
        super(ataque, defesa, saude, "barbaro");
    }

    @Override
    public void habilidade() {
        setAtaque((int) (getAtaque() * 1.5));
    }
}
