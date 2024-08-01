package entities;

import java.util.Random;

import entities.personagens.Chefao;
import entities.personagens.Monstro;

public class Tabuleiro {
    private Casa[][] tabuleiro = new Casa[5][10];
    private int CHANCE_ARMADILHA = 20;
    private int CHANCE_ARMADILHA_ALEATORIA = 20;
    
    // gerando o tabuleiro
    public Tabuleiro(Personagem personagem) {
        Random random = new Random();
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (i == 0 && j == 0) {
                    // inicia o personagem na primeira casa
                    this.tabuleiro[i][j] = new Casa(personagem);
                } else if (probabilidade(CHANCE_ARMADILHA)) {
                    // adiciona armadilha de acordo com a probabilidade
                    if (probabilidade(CHANCE_ARMADILHA_ALEATORIA)) {
                        // adiciona um numero aleatorio entre 0 e 50
                        this.tabuleiro[i][j] = new Casa( new Armadilha(10 + random.nextInt(51)));
                    } else {
                        this.tabuleiro[i][j] = new Casa( new Armadilha(10));
                    }
                } else {
                    this.tabuleiro[i][j] = new Casa();

                }
            }
        }
        
        for (int i = 0; i < tabuleiro.length; i++) {
            int num = random.nextInt(10);
            // se for a linha do chefão
            if (i == 4) {
                // se a posição sorteada da ultima linha não estiver ocupada
                if (!tabuleiro[i][num].isOcupado()) {
                    this.tabuleiro[i][num] = new Casa( new Chefao(100, 100, 1000));
                } else {
                    num = random.nextInt(10);
                    this.tabuleiro[i][num] = new Casa( new Chefao(100, 100, 1000));
                }
            } else {
                if (!tabuleiro[i][num].isOcupado()) {
                    this.tabuleiro[i][num] = new Casa( new Monstro(100, 100, 150));
                }else {
                    num = random.nextInt(10);
                    this.tabuleiro[i][num] = new Casa( new Monstro(100, 100, 150));
                }
            }
        }
    }

    public boolean probabilidade(int chance) {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        return randomNumber < chance;
    }

    public void printTabuleiro(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j].toString() + "\t");
            }
            System.out.println();
        }
    }
}
