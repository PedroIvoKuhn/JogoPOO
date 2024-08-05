package entities;

public class Elixir {
    private int vida;

    public Elixir(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    public String toString() {
        return "Vida: " + getVida();
    }
    
}
