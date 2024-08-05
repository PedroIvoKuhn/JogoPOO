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
    }

    public Casa(Personagem personagem) {
        this.personagem = personagem;
        this.armadilha = null;
        this.elixir = null;
        this.ocupado = true;

        String temp = getTipoPersonagem(personagem);
        if (temp == "N"){ // se por agum motivo não for uma das opções o icone sera do guerreiro
            ImageIcon icon = new ImageIcon("img/warrior.png");
            setIcon(icon);
            setBackground(Color.GREEN);
        }
        
    }

    public Casa(Armadilha armadilha) {
        ImageIcon icon = new ImageIcon("img/bomb.png");
        setIcon(icon);

        this.personagem = null;
        this.armadilha = armadilha;
        this.ocupado = true;
        setBackground(Color.RED);
    }

    public Casa(Elixir elixir) {
        ImageIcon icon = new ImageIcon("img/elixir.png");
        setIcon(icon);

        this.personagem = null;
        this.armadilha = null;
        this.elixir = elixir;
        this.ocupado = true;
        setBackground(Color.GRAY);
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

    public String getTipoPersonagem(Personagem personagem) {
        if (personagem instanceof Chefao) {
            ImageIcon icon = new ImageIcon("img/chefao.png");
            setIcon(icon);
            setBackground(Color.CYAN);
            return "C";
        } else if (personagem instanceof Monstro) {
            ImageIcon icon = new ImageIcon("img/monstro.png");
            setIcon(icon);
            setBackground(Color.ORANGE);
            return "M";
        } else if (personagem instanceof Guerreiro){
            ImageIcon icon = new ImageIcon("img/warrior.png");
            setIcon(icon);
            setBackground(Color.GREEN);
            return "G";
        } else if (personagem instanceof Paladino){
            ImageIcon icon = new ImageIcon("img/paladin.png");
            setIcon(icon);
            setBackground(Color.GREEN);
            return "P";
        } else if (personagem instanceof Barbaro){
            ImageIcon icon = new ImageIcon("img/barbaro.png");
            setIcon(icon);
            setBackground(Color.GREEN);
            return "B";
        }
        return "N";
    }

    public void setPersonagem(Personagem personagem){
        this.personagem = personagem;
        if (getTipoPersonagem(personagem) == "N") {
            setIcon(null);
            setBackground(null);
        }
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
