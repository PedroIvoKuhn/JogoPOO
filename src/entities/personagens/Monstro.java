package entities.personagens;

import entities.Personagem;

public class Monstro extends Personagem {
    public Monstro() {
    }

    public Monstro(int ataque, int defesa, int saude) {
        super(ataque, defesa, saude, "Monstro");
    }
    
    // Caso queira adicionar uma habilidade futuramente
    @Override
    public void habilidade() {
    }
    
}
