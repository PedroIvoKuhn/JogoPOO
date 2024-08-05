package entities;

public class Armadilha {
    private int dano;

    public Armadilha(int dano) {
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    @Override
    public String toString() {
        return "Dano: " + getDano();
    }
    
}
