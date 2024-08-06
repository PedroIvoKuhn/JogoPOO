package entities.personagens;

import entities.Personagem;

public class Chefao extends Personagem {
    public Chefao() {
    }

    public Chefao(int ataque, int defesa, int saude) {
        super(ataque, defesa, saude, "chefao");
    }
    
    // Caso queira adicionar uma habilidade futuramente
    @Override
    public void habilidade() {
    }
    @Override
    public void retirarHabilidade() {
    }
    
}
