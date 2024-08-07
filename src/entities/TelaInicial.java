package entities;
import entities.personagens.Barbaro;
import entities.personagens.Guerreiro;
import entities.personagens.Paladino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {
    public TelaInicial() {
        setTitle("Bem-vindo!");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 1));

        JLabel legenda = new JLabel("Bem-vindo ao Jogo!", SwingConstants.CENTER);
        painel.add(legenda);

        JButton botaoJogar = new JButton("Jogar");
        JButton botaoDebug = new JButton("Debug");
        JButton botaoSair = new JButton("Sair");

        painel.add(botaoJogar);
        painel.add(botaoDebug);
        painel.add(botaoSair);

        add(painel);
        setVisible(true);

        botaoJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escolherPersonagem(false);
            }
        });
        
        botaoDebug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escolherPersonagem(true);
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void escolherPersonagem(boolean debug) {
        JFrame frameDoPersonagem = new JFrame("Escolha seu Personagem");
        frameDoPersonagem.setSize(300, 200);
        frameDoPersonagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameDoPersonagem.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 1));

        JLabel legenda = new JLabel("Escolha um personagem:", SwingConstants.CENTER);
        painel.add(legenda);

        JButton barbaro = new JButton("Barbaro");
        JButton guerreiro = new JButton("Guerreiro");
        JButton paladino = new JButton("Paladino");

        painel.add(barbaro);
        painel.add(guerreiro);
        painel.add(paladino);

        frameDoPersonagem.add(painel);
        frameDoPersonagem.setVisible(true);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String personagem = e.getActionCommand();
                try {
                    startGame(personagem, debug);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                frameDoPersonagem.dispose();
                dispose();
            }
        };

        barbaro.addActionListener(actionListener);
        guerreiro.addActionListener(actionListener);
        paladino.addActionListener(actionListener);
    }

    private void startGame(String personagem, boolean debug) throws Exception {
        Tabuleiro tabuleiro;
        switch (personagem) {
            case "Barbaro":
                tabuleiro = new Tabuleiro(new Barbaro(30, 20, 100), debug);
                break;
            case "Guerreiro":
                tabuleiro = new Tabuleiro(new Guerreiro(30, 20, 100), debug);
                break;
            case "Paladino":
                tabuleiro = new Tabuleiro(new Paladino(30, 20, 100), debug);
                break;
            default:
                throw new IllegalArgumentException("Personagem inválido");
        }
        tabuleiro.printTabuleiro();
    }
}