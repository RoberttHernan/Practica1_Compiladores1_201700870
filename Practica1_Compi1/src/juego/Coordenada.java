
package juego;

/**
 *
 * @author Robert Hernandez
 * Representa una Coordenada en el juego
 */
public class Coordenada {
    private int fil;
    private int col;

    public Coordenada(int fil, int col) {
        this.fil = fil;
        this.col = col;
    }

    public int getfil() {
        return fil;
    }

    public void setfil(int fil) {
        this.fil = fil;
    }

    public int getcol() {
        return col;
    }

    public void setcol(int col) {
        this.col = col;
    }
   
    @Override
    public String toString (){
    return "(" + fil +","+col+")";
    }
}
