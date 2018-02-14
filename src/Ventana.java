import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class Ventana extends JFrame {
    private JMenuBar menuBar = new JMenuBar();

    private JMenu menuArhivo = new JMenu("Archivo");
    private JMenu menuEdicion = new JMenu("Edicion");
    private JMenu menuFormato = new JMenu("Formato");
    private JMenu menuVista = new JMenu("Vista");
    private JMenu menuAyuda = new JMenu("Ayuda");

    private JMenuItem archivoNuevo = new JMenuItem("Nuevo");
    private JMenuItem archivoAbrir = new JMenuItem("Abrir");
    private JMenuItem archivoGuardar = new JMenuItem("Guardar");
    private JMenuItem archivoGuardarComo = new JMenuItem("Guardar como");
    private JMenuItem archivoConfigurarP = new JMenuItem("Configurar Pagina");
    private JMenuItem archivoImprimir = new JMenuItem("Imprimir");
    private JMenuItem archivoSalir = new JMenuItem("Salir");

    private JMenuItem edicionDeshacer = new JMenuItem("Deshacer");
    private JMenuItem edicionCortar = new JMenuItem("Cortar");
    private JMenuItem edicionCopiar = new JMenuItem("Copiar");
    private JMenuItem edicionPegar = new JMenuItem("Pegar");
    private JMenuItem edicionBorrar = new JMenuItem("Borrar");
    private JMenuItem edicionBuscar = new JMenuItem("Buscar");
    private JMenuItem edicionBuscarN = new JMenuItem("Buscar Siguiente");
    private JMenuItem edicionReemplazar = new JMenuItem("Reemplazar");
    private JMenuItem edicionSelTodo = new JMenuItem("Seleccionar todo");
    private JMenuItem formatoFuente = new JMenuItem("Fuente");
    private JMenuItem vistaBarra = new JMenuItem("Barra de Estado");
    private JMenuItem vistaLineas = new JMenuItem("Numero de Lineas");
    private JMenuItem ayudaVerAyuda = new JMenuItem("Ver Ayuda");
    private JMenuItem ayudaAcerca = new JMenuItem("Acerca");

    private JTextArea areaDeTexto = new JTextArea();
    private JFileChooser file = new JFileChooser();
    private BarraLineas barraLineas=new BarraLineas();
    private BarraEstado barraEstado=new BarraEstado();
    private boolean barraEstadoActivo=true;


    public Ventana() {

        setTitle("Ventana Principal");
        setBounds(0, 0, 640, 480);

        agregarMenu();

        setLayout(new BorderLayout());
        add(areaDeTexto, BorderLayout.CENTER);
        add(barraEstado, BorderLayout.SOUTH);
        eventos();
    }

    private void agregarMenu() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuBar.add(menuArhivo);
        menuArhivo.add(archivoNuevo);
        menuArhivo.add(archivoAbrir);
        menuArhivo.add(archivoGuardar);
        menuArhivo.add(archivoGuardarComo);
        menuArhivo.add(archivoConfigurarP);
        menuArhivo.add(archivoImprimir);
        menuArhivo.add(archivoSalir);

        menuBar.add(menuEdicion);
        menuEdicion.add(edicionDeshacer);
        menuEdicion.add(edicionCortar);
        menuEdicion.add(edicionCopiar);
        menuEdicion.add(edicionPegar);
        menuEdicion.add(edicionBorrar);
        menuEdicion.add(edicionBuscar);
        menuEdicion.add(edicionBuscarN);
        menuEdicion.add(edicionReemplazar);
        menuEdicion.add(edicionSelTodo);

        menuBar.add(menuFormato);
        menuFormato.add(formatoFuente);

        menuBar.add(menuVista);
        menuVista.add(vistaBarra);
        menuVista.add(vistaLineas);

        menuBar.add(menuAyuda);
        menuAyuda.add(ayudaVerAyuda);
        menuAyuda.add(ayudaAcerca);
    }

    private void eventos() {
        archivoNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaDeTexto.setText("");
                areaDeTexto.updateUI();
            }
        });
        archivoAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaDeTexto.setText(archivoAbrirDialogo());
                areaDeTexto.updateUI();
            }
        });
        archivoGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                archivoGuardar();
            }
        });
        archivoGuardarComo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                archivoGuardarComo();
            }
        });
        areaDeTexto.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    barraEstado.setNumeroLineas(barraEstado.getNumeroLineas()+1);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        vistaBarra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(barraEstadoActivo) {
                    barraEstado.setVisible(false);
                    barraEstadoActivo=false;
                }
                else{
                    barraEstado.setVisible(true);
                    barraEstadoActivo=true;
                }
                repaint();
            }
        });
        archivoSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ayudaAcerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ayudaAcerca();
            }
        });
    }

    private String archivoAbrirDialogo() {
        String aux = "";
        String texto = "";
        try {
            /**llamamos el metodo que permite cargar la ventana*/
            file.showOpenDialog(this);
            /**abrimos el archivo seleccionado*/
            File abre = file.getSelectedFile();

            /**recorremos el archivo, lo leemos para plasmarlo
             *en el area de texto*/
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);
                while ((aux = lee.readLine()) != null) {
                    texto += aux + "\n";
                }
                lee.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + "" +
                            "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
        return texto;//El texto se almacena en el JTextArea
    }
    private void archivoGuardar() {
        try {
            String nombre = "";
            JFileChooser file = new JFileChooser();
            file.showSaveDialog(this);
            File guarda = file.getSelectedFile();

            if (guarda != null) {
                /*guardamos el archivo y le damos el formato directamente,
                 * si queremos que se guarde en formato doc lo definimos como .doc*/
                FileWriter save = new FileWriter(guarda + ".txt");
                save.write(areaDeTexto.getText());
                save.close();
                JOptionPane.showMessageDialog(null,
                        "El archivo se a guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void archivoGuardarComo() {
        try {
            String nombre = "";
            JFileChooser file = new JFileChooser();
            file.showSaveDialog(this);
            File guarda = file.getSelectedFile();

            if (guarda != null) {
                /*guardamos el archivo y le damos el formato directamente,
                 * si queremos que se guarde en formato doc lo definimos como .doc*/
                FileWriter save = new FileWriter(guarda + ".txt");
                save.write(areaDeTexto.getText());
                save.close();
                JOptionPane.showMessageDialog(null,
                        "El archivo se a guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ayudaAcerca() {
        JDialog jDialogAyudaAcerca = new JDialog();
        JLabel acercaImagen = new JLabel(new ImageIcon(getClass().getResource("imagenes/acerca.jpg")));
        JLabel acercaInfoTitulo = new JLabel("WordsPad v17.2 (Build 20170212)");
        JLabel acercaAutor = new JLabel("Autor: Hector Cruz Rocabado");
        JLabel acercaHomePage = new JLabel("Home Page:   http://lindo.webcindario.com");
        JButton botonAcerca = new JButton("Aceptar");

        jDialogAyudaAcerca.setLayout(null);

        jDialogAyudaAcerca.setBounds(100, 100, 640, 360);
        acercaImagen.setBounds(30, 30,150, 150);
        acercaInfoTitulo.setLocation(200, 200);
        acercaAutor.setLocation(200, 220);
        acercaHomePage.setLocation(200, 240);
        botonAcerca.setLocation(200, 350);

        jDialogAyudaAcerca.add(acercaImagen);
        jDialogAyudaAcerca.add(acercaInfoTitulo);
        jDialogAyudaAcerca.add(acercaAutor);
        jDialogAyudaAcerca.add(acercaHomePage);
        jDialogAyudaAcerca.add(botonAcerca);

        jDialogAyudaAcerca.setVisible(true);
    }
}
