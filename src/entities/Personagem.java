package entities;

import java.util.ArrayList;
public abstract class Personagem {
    private int ataque;
    private int defesa;
    private int saude;
    private String nome;
    private Boolean usouHabilidade = false;

    private int ataqueInicial;
    private int defesaInicial;
    private int saudeInicial;

    private ArrayList<Elixir> elixires = new ArrayList<Elixir>();

    public Personagem() {
    }

    public Personagem(int ataque, int defesa, int saude, String nome) {
        this.ataque = ataque;
        this.ataqueInicial = ataque;
        this.defesa = defesa;
        this.defesaInicial = defesa;
        this.saude = saude;
        this.saudeInicial = saude;
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

    public Boolean getUsouHabilidade() {
        return usouHabilidade;
    }

    public void setUsouHabilidade(Boolean usouHabilidade) {
        this.usouHabilidade = usouHabilidade;
    }


    public void redefinirValores() {
        this.saude = this.saudeInicial;
        this.defesa = this.defesaInicial;
        this.ataque = this.ataqueInicial;
    }

    public Elixir getElixir(){
        System.out.println(elixires.size());
        if (elixires.size() > 0) {
            return elixires.remove(0);
        }
        return null;
    }

    public void addElixir(Elixir elixir){
        elixires.add(elixir);
        System.out.println(elixires);
    }

    public void tomarElixir(){
        if (!elixires.isEmpty()) {
            this.saude += getElixir().getVida();
        }
    }

    public abstract void habilidade();
    public abstract void retirarHabilidade();

    @Override
    public String toString() {
        return  "   Saúde: "  + getSaude() +
                "   Ataque: " + getAtaque() +
                "   Defesa: " + getDefesa();
    }
}
