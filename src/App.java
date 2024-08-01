import entities.Tabuleiro;
import entities.personagens.Barbaro;
import entities.personagens.Guerreiro;
import entities.personagens.Paladino;

public class App {
    public static void main(String[] args) throws Exception {


        Tabuleiro tabuleiro = new Tabuleiro(new Barbaro(100, 100, 100));

        tabuleiro.printTabuleiro();
    }
}
