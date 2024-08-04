import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Movimento do Boneco");
        Board board = new Board();
        frame.add(board);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                board.move(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
/*
 * 
 *      import javax.swing.*;
import java.awt.*;

public class Armadilha {
    public static void main(String[] args) {
        JFrame frameArmadilha = new JFrame("Armadilha");
        frameArmadilha.setSize(300, 200);
        frameArmadilha.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameArmadilha.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        frameArmadilha.add(painel); // Adiciona o painel ao JFrame

        // Cria as labels e define o layout do painel
        painel.setLayout(new BorderLayout());
        JLabel legenda = new JLabel("Você encontrou uma armadilha:");
        JLabel legenda2 = new JLabel("Você encontrou uma armadilaaaha:");
        painel.add(legenda, BorderLayout.NORTH);
        painel.add(legenda2, BorderLayout.CENTER);

        frameArmadilha.setVisible(true);
    }
}

 * 
 * 
 */







class Board extends JPanel {
    private int[][] grid;
    private int playerX;
    private int playerY;

    public Board() {
        grid = new int[10][10];  // Tabuleiro 10x10
        playerX = 5;
        playerY = 5;
        grid[playerY][playerX] = 1;  // Boneco inicial
    }

    public void move(int keyCode) {
        grid[playerY][playerX] = 0;  // Limpa a posição anterior

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (playerY > 0) playerY--;
                break;
            case KeyEvent.VK_DOWN:
                if (playerY < grid.length - 1) playerY++;
                break;
            case KeyEvent.VK_LEFT:
                if (playerX > 0) playerX--;
                break;
            case KeyEvent.VK_RIGHT:
                if (playerX < grid[0].length - 1) playerX++;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);  // Sai do programa
                break;
        }

        grid[playerY][playerX] = 1;  // Atualiza a nova posição
        repaint();  // Atualiza a tela
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 1) {
                    g.fillRect(x * 40, y * 40, 40, 40);  // Desenha o boneco
                } else {
                    g.drawRect(x * 40, y * 40, 40, 40);  // Desenha a grade
                }
            }
        }
    }
}
