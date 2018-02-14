import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final  Ventana ventana = new Ventana();
                ventana.setTitle("Login");
                ventana.setSize(640,480);
                ventana.setVisible(true);
            }
        });
    }
}
