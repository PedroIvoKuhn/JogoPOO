package entities;

import java.util.Random;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import entities.personagens.Chefao;
import entities.personagens.Monstro;


public class Tabuleiro extends JFrame {
    private Casa[][] tabuleiro = new Casa[5][10];
    private Casa[][] backupTabuleiro = new Casa[5][10];
    private int CHANCE_ARMADILHA = 15;
    private int CHANCE_ARMADILHA_ALEATORIA = 20;
    private int DANO_BASE = 10;
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
                        this.tabuleiro[i][j] = new Casa( new Armadilha(DANO_BASE + random.nextInt(51)));
                    } else {
                        this.tabuleiro[i][j] = new Casa( new Armadilha(DANO_BASE));
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
        this.backupTabuleiro = copiarTabuleiro(this.tabuleiro);
        configuraJanela(personagem);
    }

    private void configuraJanela(Personagem personagem){
        setTitle("Tabuleiro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);

        String rotulo = personagem.toString();
        informacoes = new JLabel(rotulo);
        informacoes.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new BorderLayout());

        add(informacoes, BorderLayout.NORTH);

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                pTela.add(tabuleiro[i][j]);
                int x = i;
                int y = j;
                tabuleiro[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moverJogador(x, y);
                    }
                });
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
                System.out.print(backupTabuleiro[i][j].toString() + "\t");
            }
            System.out.println();
        }
    }

    private void moverJogador(int novoX, int novoY) {
        // se a posicao for uma ao lado da posicao do personagem
        if (Math.abs(novoX - posicaoX) + Math.abs(novoY - posicaoY) == 1) {

            Casa atual = tabuleiro[posicaoX][posicaoY];
            Casa nova = tabuleiro[novoX][novoY];
    
            if (nova.isOcupado()) {
                // se achar armadilha se não é personagem
                if (nova.getArmadilha() != null) {
                    achouArmadilha(atual.getPersonagem(), nova.getArmadilha());
                    nova.removeArmadilha();
                } else { // mais um if para colocar o elixir
                    achouInimigo(atual.getPersonagem(), nova.getPersonagem());
                }
            }
    
            nova.setPersonagem(atual.getPersonagem());
    
            atual.setPersonagem(null);
    
            posicaoX = novoX;
            posicaoY = novoY;
    
    
            informacoes.setText(nova.getPersonagem().toString());;
            pTela.repaint();
        }
    }
    
    private void achouArmadilha(Personagem personagem, Armadilha armadilha){
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
        
        String texto2 = armadilha.toString();
        JLabel legenda2 = new JLabel(texto2);
        legenda2.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(legenda2, BorderLayout.CENTER);

        personagem.levaDano(armadilha.getDano());
        if (personagem.getSaude() <= 0) {
            telafinal("Fim de Jogo. Você Morreu!");
        }
        
        JButton botaoFechar = new JButton("Sair");
        botaoFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 frameArmadilha.dispose();
            }
        });

        painel.add(botaoFechar, BorderLayout.SOUTH);
        frameArmadilha.add(painel);
        frameArmadilha.setVisible(true);
    }

    private void achouInimigo(Personagem personagem, Personagem inimigo){
        JFrame frameBatalha = new JFrame("Armadilha");
        frameBatalha.setSize(300, 200);
        frameBatalha.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameBatalha.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        
        String texto = "Você encontrou um Inimigo:";
        JLabel legenda = new JLabel(texto);
        legenda.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(legenda, BorderLayout.NORTH);
        
        String texto2 = inimigo.toString();
        JLabel legenda2 = new JLabel(texto2);
        legenda2.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(legenda2, BorderLayout.CENTER);

        if (personagem.getSaude() <= 0) {
            telafinal("Fim de Jogo. Você Morreu!");
        }
        
        JButton botaoFechar = new JButton("Sair");
        botaoFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 frameBatalha.dispose();
            }
        });

        painel.add(botaoFechar, BorderLayout.SOUTH);
        frameBatalha.add(painel);
        frameBatalha.setVisible(true);
    }

    private void telafinal(String mensagem) {
        JFrame frameFinal = new JFrame();
        frameFinal.setSize(300, 200);
        frameFinal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFinal.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 1));

        JLabel legenda = new JLabel(mensagem, SwingConstants.CENTER);
        painel.add(legenda);

        JButton sair = new JButton("Sair");
        JButton reiniciar = new JButton("Reiniciar Jogo");
        JButton novo = new JButton("Novo Jogo");

        painel.add(sair);
        painel.add(reiniciar);
        painel.add(novo);

        frameFinal.add(painel);
        frameFinal.setVisible(true);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String acao = e.getActionCommand();
                try {
                    switch (acao) {
                        case "Sair":
                            frameFinal.dispose();
                            dispose();
                        break;
                        case "Reiniciar Jogo":
                            frameFinal.dispose();
                            reiniciarJogo();
                        break;
                        case "Novo Jogo":
                            frameFinal.dispose();
                            dispose();
                            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new TelaInicial();
                            }
                            });
                        break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            }
        };

        sair.addActionListener(actionListener);
        reiniciar.addActionListener(actionListener);
        novo.addActionListener(actionListener);
    }

    private void reiniciarJogo() {
        this.tabuleiro = copiarTabuleiro(this.backupTabuleiro);
        this.posicaoX = 0;
        this.posicaoY = 0;
        this.tabuleiro[0][0].getPersonagem().redefinirValores();

        this.remove(informacoes);
        this.remove(pTela);
        this.pTela = new JPanel(new GridLayout(5, 10));
        this.configuraJanela(this.tabuleiro[0][0].getPersonagem());
    }
 
    private Casa[][] copiarTabuleiro(Casa[][] original) {
        Casa[][] copia = new Casa[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) {
                if (original[i][j] != null){
                    Armadilha temp = original[i][j].getArmadilha();
                    if (temp != null) {
                        copia[i][j] = new Casa(temp);
                    } else { // mais um if para colocar o elixir
                        copia[i][j] = new Casa(original[i][j].getPersonagem());
                    }
                }
            }
        }
        return copia;
    }
}
