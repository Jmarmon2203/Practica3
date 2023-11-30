package ModeloVistaControlador;

import javax.swing.SwingUtilities;

public class Prueba {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interfaz interfaz = new Interfaz();
            interfaz.setVisible(true);
        });
    }
}