package entities.personagens;

import entities.Personagem;

public class Guerreiro extends Personagem {
    private int rodadas;
    private int defesaAnterior;

    public Guerreiro() {
    }

    public Guerreiro(int ataque, int defesa, int saude) {
        super(ataque, defesa, saude, "guerreiro");
        this.rodadas = 0;
    }

    @Override
    public void habilidade() {
        if (rodadas == 0) {
            this.defesaAnterior = getDefesa();
            setDefesa((int) (getDefesa() * 1.5));
        }
        // -1 por causa do rodadas++ no final
        if (rodadas == 2) {
            setDefesa(this.defesaAnterior);
            rodadas = -1;
        }
        rodadas++;
    }
    
}
