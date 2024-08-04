package entities;

public abstract class Personagem {
    private int ataque;
    private int defesa;
    private int saude;
    private String nome;

    public Personagem() {
    }

    public Personagem(int ataque, int defesa, int saude, String nome) {
        this.ataque = ataque;
        this.defesa = defesa;
        this.saude = saude;
        this.nome = nome;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getSaude() {
        return saude;
    }

    public void setSaude(int saude) {
        this.saude = saude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void levaDano(int dano) {
        this.saude -= dano;
    }

    public abstract void habilidade();

    @Override
    public String toString() {
        return  nome;
    }
}
