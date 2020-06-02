/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Robert Hernandez>
 */
public class Tetris extends JPanel{
    private int N;//
    private int P;
    
    public Tetris(int n , int p){
    this.N =n;
    this.P= p;
    }
    
    @Override
    public void paintComponent (Graphics g){
    g.fillRect(0, 0, WIDTH, WIDTH);
    }
}
