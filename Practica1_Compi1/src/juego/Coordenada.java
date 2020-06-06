/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

/**
 *
 * @author Robert Hernandez>
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
