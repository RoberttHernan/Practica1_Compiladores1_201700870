package juego;

import java.util.LinkedList;

/**
 *
 * @author Robert Hernandez
 */
public class Nivel {

    String nombre_nivel;
    LinkedList<Coordenada> lista_coordenadas;
    int tamanio_tablero;
    private int filas;

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    private int columnas;

    public int getTamanio_tablero() {
        return tamanio_tablero;
    }

    public void setTamanio_tablero(int tamanio_tablero) {
        this.tamanio_tablero = tamanio_tablero;
    }

    public Nivel() {
        this.nombre_nivel = "";
        lista_coordenadas = new LinkedList<>();

    }

    public String getNombre_nivel() {
        return nombre_nivel;
    }

    public void setNombre_nivel(String nombre_nivel) {
        this.nombre_nivel = nombre_nivel;
    }

    public LinkedList<Coordenada> getLista_coordenadas() {
        return lista_coordenadas;
    }

    public void setLista_coordenadas(LinkedList<Coordenada> lista_coordenadas) {
        this.lista_coordenadas = lista_coordenadas;
    }

    private String imprimirCoordenadas ()
    {
    
        String texto="";
        for (Coordenada coordenada : lista_coordenadas){
        texto += coordenada.toString()+"\n";
        }
        return texto;
        
    
    }
    
    @Override
    public String toString (){
    
    return nombre_nivel +"\n"+ imprimirCoordenadas();
    }
    
}
