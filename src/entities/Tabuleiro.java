package entities;

import java.util.Random;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import entities.personagens.Chefao;
import entities.personagens.Monstro;

public class Tabuleiro extends JFrame {
    private Casa[][] tabuleiro = new Casa[5][10];
    private int CHANCE_ARMADILHA = 15;
    private int CHANCE_ARMADILHA_ALEATORIA = 20;
    private int posicaoX = 0;
    private int posicaoY = 0;

    JPanel pTela = new JPanel( new GridLayout(5, 10));
    JLabel informacoes;
    
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
                this.tabuleiro[i][num] = new Casa( new Chefao(100, 100, 1000));
            } else {
                if (i == 0 && num == 0) {
                    // não deixa colocar um monstro na primeira posição
                    num = num + 2;
                }
                this.tabuleiro[i][num] = new Casa( new Monstro(100, 100, 150));
            }
        }
        configuraJanela(personagem);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                moverJogador(e.getKeyCode());
            }
        });
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    private void configuraJanela(Personagem personagem){
        setTitle("Tabuleiro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);

        String rotulo = "   Saúde: "  + personagem.getSaude() +
                        "   Ataque: " + personagem.getAtaque() +
                        "   Defesa: " + personagem.getDefesa();
        informacoes = new JLabel(rotulo);
        informacoes.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new BorderLayout());

        add(informacoes, BorderLayout.NORTH);

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                pTela.add(tabuleiro[i][j]);
            }
        }
        add(pTela, BorderLayout.CENTER);
        setVisible(true);
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

    private void moverJogador(int keyCode){
        int novoX = posicaoX;
        int novoY = posicaoY;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (posicaoX > 0) novoX--;
                break;
            case KeyEvent.VK_DOWN:
                if (posicaoX < tabuleiro.length - 1) novoX++;
                break;
            case KeyEvent.VK_LEFT:
                if (posicaoY > 0) novoY--;
                break;
            case KeyEvent.VK_RIGHT:
                if (posicaoY < tabuleiro[0].length - 1) novoY++;
                break;
        }

        if (novoX != posicaoX || novoY != posicaoY) {
            Casa atual = tabuleiro[posicaoX][posicaoY];
            Casa nova = tabuleiro[novoX][novoY];

            if (nova.isOcupado()) {
                if (nova.getArmadilha() != null) {
                    achoArmadilha(atual.getPersonagem(), nova.getArmadilha());
                    nova.removeArmadilha();
                }
            }

            nova.setConteudo(atual.getPersonagem());

            atual.setConteudo(null);

            posicaoX = novoX;
            posicaoY = novoY;

            pTela.repaint();
        }
    }

    private void achoArmadilha(Personagem personagem, Armadilha armadilha){
        JFrame frameArmadilha = new JFrame("Armadilha");
        frameArmadilha.setSize(300, 200);
        frameArmadilha.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameArmadilha.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        
        String texto = "Você encontrou uma armadilha:";
        JLabel legenda = new JLabel(texto);
        legenda.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(legenda, BorderLayout.NORTH);
        
        String texto2 = "Dano: " + armadilha.getDano();
        JLabel legenda2 = new JLabel(texto2);
        legenda2.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(legenda2, BorderLayout.CENTER);

        personagem.levaDano(armadilha.getDano());
        
        frameArmadilha.add(painel);
        frameArmadilha.setVisible(true);
    }
}
