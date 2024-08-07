package entities;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import entities.personagens.Barbaro;
import entities.personagens.Chefao;
import entities.personagens.Guerreiro;
import entities.personagens.Monstro;
import entities.personagens.Paladino;

public class Casa extends JButton{
    private Personagem personagem;
    private Armadilha armadilha;
    private Elixir elixir;
    private boolean ocupado = false;
    
    public Casa() {
        setIcon(new ImageIcon("img/mato.png"));
        setBackground(Color.WHITE);
    }

    public Casa(Personagem personagem, boolean debug) {
        this.personagem = personagem;
        this.armadilha = null;
        this.elixir = null;
        this.ocupado = true;

        setIconPersonagem(personagem, debug); 
    }

    public Casa(Armadilha armadilha, boolean debug) {
        ImageIcon icon = debug ? new ImageIcon("img/bomb.png") 
                               : new ImageIcon("img/mato.png");
        setIcon(icon);

        this.personagem = null;
        this.armadilha = armadilha;
        this.ocupado = true;
        setBackground(debug ? Color.RED : Color.WHITE);
    }

    public Casa(Elixir elixir, boolean debug) {
        ImageIcon icon = debug ? new ImageIcon("img/elixir.png")
                               : new ImageIcon("img/mato.png");
        setIcon(icon);

        this.personagem = null;
        this.armadilha = null;
        this.elixir = elixir;
        this.ocupado = true;
        setBackground(debug ? Color.GRAY : Color.WHITE);
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public Armadilha getArmadilha() {
        return armadilha;
    }

    public Elixir getElixir() {
        return elixir;
    }

    public void removeArmadilha() {
        this.armadilha = null;
        setOcupado(false);
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void setIconPersonagem(Personagem personagem, boolean debug) {
        ImageIcon icon;
        if (personagem instanceof Chefao) {
            icon = new ImageIcon("img/chefao.png");
            setBackground(Color.CYAN);
        } else if (personagem instanceof Monstro) {
            if (debug) {
                icon = new ImageIcon("img/monstro.png");
                setBackground(Color.ORANGE);
            } else {
                icon = new ImageIcon("img/mato.png");
                setBackground(Color.WHITE);
            }
        } else if (personagem instanceof Guerreiro){
            icon = new ImageIcon("img/guerreiro.png");
            setBackground(Color.GREEN);
        } else if (personagem instanceof Paladino){
            icon = new ImageIcon("img/paladino.png");
            setBackground(Color.GREEN);
        } else if (personagem instanceof Barbaro){
            icon = new ImageIcon("img/barbaro.png");
            setBackground(Color.GREEN);
        } else {
            icon = new ImageIcon("img/guerreiro.png");
            setBackground(Color.GREEN);
        }
        setIcon(icon);
    }

    public void setPersonagem(Personagem personagem){
        this.personagem = personagem;
        if (personagem != null){
            setIconPersonagem(personagem, false);
            setBackground(Color.GREEN);
            return;
        }
        setIcon(null);
        setBackground(Color.WHITE);
    }

    public String getTipoConteudo(){
        if (this.personagem != null) {
            return "P";
        }
        if (this.armadilha != null) {
            return "A";
        }
        if (this.elixir != null) {
            return "E";
        }
        return "Null";
    }

    @Override
    public String toString() {
        String temp = "[]";
        if (getPersonagem() != null) {
            temp = getPersonagem().toString();
        }
        if (getArmadilha() != null) {
            temp = getArmadilha().toString();
        }
        if (getElixir() != null) {
            temp = getElixir().toString();
        }
        return temp;
    }
}
