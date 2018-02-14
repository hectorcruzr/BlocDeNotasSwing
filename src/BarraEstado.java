import javax.swing.*;
import java.awt.*;

public class BarraEstado extends JPanel{
    private JLabel ruta=new JLabel("ruta:");
    private JLabel length=new JLabel("ruta:");
    private int numeroLineas=0;
    private JLabel lineas=new JLabel("0:");
    private JLabel fila=new JLabel("0:");
    private JLabel columna=new JLabel("0:");
    private JLabel seleccion=new JLabel("0:");
    private JLabel seleccionOrigen=new JLabel("0:");
    private JLabel seleccionDestino=new JLabel("0:");
    private JLabel formato=new JLabel("0:");
    private JLabel insertar=new JLabel("habilitado:");

    public BarraEstado(){
        setLayout(new FlowLayout());
        add(ruta);
        add(length);
        add(lineas);
        add(fila);
        add(columna);
        add(seleccion);
        add(seleccionOrigen);
        add(seleccionDestino);
        add(formato);
        add(insertar);
        setVisible(true);
    }

    public JLabel getRuta() {
        return ruta;
    }

    public JLabel getLength() {
        return length;
    }

    public int getNumeroLineas() {
        return numeroLineas;
    }

    public JLabel getLineas() {
        return lineas;
    }

    public JLabel getFila() {
        return fila;
    }

    public JLabel getColumna() {
        return columna;
    }

    public JLabel getSeleccion() {
        return seleccion;
    }

    public JLabel getSeleccionOrigen() {
        return seleccionOrigen;
    }

    public JLabel getSeleccionDestino() {
        return seleccionDestino;
    }

    public JLabel getFormato() {
        return formato;
    }

    public JLabel getInsertar() {
        return insertar;
    }

    public void setRuta(JLabel ruta) {
        this.ruta = ruta;
    }

    public void setLength(JLabel length) {
        this.length = length;
    }

    public void setNumeroLineas(int numeroLineas) {
        this.numeroLineas = numeroLineas;
        lineas.updateUI();
    }

    public void setLineas(JLabel lineas) {
        this.lineas = lineas;
    }

    public void setFila(JLabel fila) {
        this.fila = fila;
    }

    public void setColumna(JLabel columna) {
        this.columna = columna;
    }

    public void setSeleccion(JLabel seleccion) {
        this.seleccion = seleccion;
    }

    public void setSeleccionOrigen(JLabel seleccionOrigen) {
        this.seleccionOrigen = seleccionOrigen;
    }

    public void setSeleccionDestino(JLabel seleccionDestino) {
        this.seleccionDestino = seleccionDestino;
    }

    public void setFormato(JLabel formato) {
        this.formato = formato;
    }

    public void setInsertar(JLabel insertar) {
        this.insertar = insertar;
    }
}
