package entities.personagens;

import entities.Personagem;

public class Guerreiro extends Personagem {
    private int rodada;
    private int defesaAnterior;

    public Guerreiro() {
    }

    public Guerreiro(int ataque, int defesa, int saude) {
        super(ataque, defesa, saude, "guerreiro");
        this.rodada = 0;
    }

    public int getRodada() {
        return rodada;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    @Override
    public void habilidade() {
        this.defesaAnterior = getDefesa();
        setUsouHabilidade(true);
        setDefesa((int) (getDefesa() * 1.5));
    }

    @Override
    public void retirarHabilidade() {
        setUsouHabilidade(false);
        setDefesa(this.defesaAnterior);
    }
    
}
