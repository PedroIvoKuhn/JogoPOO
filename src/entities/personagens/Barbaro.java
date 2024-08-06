package entities.personagens;

import entities.Personagem;

public class Barbaro extends Personagem {   
    private int ataqueAntigo; 
    public Barbaro() {
    }

    public Barbaro(int ataque, int defesa, int saude) {
        super(ataque, defesa, saude, "barbaro");
    }

    @Override
    public void habilidade() {
        setUsouHabilidade(true);
        this.ataqueAntigo = getAtaque();
        setAtaque((int) (getAtaque() * 1.5));
    }

    @Override
    public void retirarHabilidade() {
        setUsouHabilidade(false);
        setAtaque(this.ataqueAntigo);
    }
}
