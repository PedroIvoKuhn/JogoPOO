import javax.swing.SwingUtilities;

import entities.TelaInicial;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new TelaInicial();
            }
        });
    }
}
