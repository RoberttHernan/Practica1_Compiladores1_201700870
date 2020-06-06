package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JPanel {

    private int fil;
    private int col;
    private LinkedList<Coordenada> coordenadasOcupadas = new LinkedList<>();

    private Point piezaOrigen;
    private int piezaActual;
    private int rotacion;
    private LinkedList<Integer> listaPiezas = new LinkedList<>();// lista de numeros que representa las piezas 

    private LinkedList<Integer> listaNumeros = new LinkedList<>();// lista de numero que vienen del analisis

    private  int score;
    private Color[][] well;

    public Tetris(int filas, int col) {
        this.fil = filas;
        this.col = col;
    }

    private final Point formas[][][] = {
        // I 0
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)}
        },
        // L 1
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0)}
        },
        //J 2
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0)}
        },
        // O 3
        {
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)}
        },
        // S 4
        {
            {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)}
        },
        // T 5
        {
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2)}
        },
        // Z 6
        {
            {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)},
            {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)}
        },
        // I rotacion 7
        {
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)}

        },
        //L rotacion (8)
        {
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0)}
        },
        //L rotacion (9)
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2)}

        },
        //L rotacion (10)
        {
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2)},},
        //J Rotacion (11)
        {
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2)},},
        //J Rotacion (12)
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2)}

        },
        //J Rotacion (13)
        {
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0)}
        },
        // S Rotacion (14)
        {
            {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)}

        },
        // T Rotacion  (15)
        {
            {new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)}

        },
        // T Rotacion  (16)
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2)}

        },
        // T Rotacion  (17)
        {
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2)}

        },
        // Z Rotacion 18
        {
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)},
            {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)},
            {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},}

    };

    private final Color[] FormaColors = {
        Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red, Color.cyan, Color.blue, Color.blue, Color.blue,
        Color.orange, Color.orange, Color.orange, Color.green, Color.pink, Color.pink, Color.pink, Color.red

    };

    public LinkedList<Coordenada> getCoordenadasOcupadas() {
        return coordenadasOcupadas;
    }

    public void setCoordenadasOcupadas(LinkedList<Coordenada> coordenadasOcupadas) {
        this.coordenadasOcupadas = coordenadasOcupadas;
    }

    public void init() {
        well = new Color[fil][col];

        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                
                if (buscarCoordenada(coordenadasOcupadas, i, j)) {
                    well[i][j] = Color.red;
                }
                else if (i == fil-1){
                well[i][j] = Color.gray;
                }else {
                    well[i][j] = Color.WHITE;
                }
            }
        }
        PiezaNueva();
    }

    public void mover (int i){
    piezaOrigen.x += i;
    repaint();
    }
    public void PiezaNueva() {
        piezaOrigen = new Point(1, 0);
        rotacion = 0;
        if (listaPiezas.isEmpty()) {

            Collections.addAll(listaPiezas, 1, 2, 3, 4, 5, 6);

        }
        piezaActual = listaPiezas.get(0);
        listaPiezas.remove(0);
    }

    private boolean colision(int x, int y, int rotacion) {
        for (Point p : formas[piezaActual][rotacion]) {
            
            if (y==fil-1){
            return true;
            }
            if (well[(int) p.getY() + y][(int) p.getX() + x] != Color.WHITE) {
                return true;
            }

        }
        return false;
    }

    public void rotar(int n) {
        int nueva_rotacion = (rotacion + n) % 4;
        if (nueva_rotacion < 0) {
            nueva_rotacion = 3;
        }
        if (!colision((int) piezaOrigen.getX(), (int) piezaOrigen.getY(), nueva_rotacion)) {
            rotacion = nueva_rotacion;
        }
        repaint();
    }

    public void dropDown() {
        if (!colision((int) piezaOrigen.x, (int) piezaOrigen.y + 1, rotacion)) {
            piezaOrigen.y += 1;
        } else {
            pegarAlTablero();
        }
        repaint();
    }

    public void pegarAlTablero() {
        for (Point p : formas[piezaActual][rotacion]) {
            well[((int) piezaOrigen.getY()) + p.y][((int) piezaOrigen.getX()) + p.x] = FormaColors[piezaActual];
        }
        LimpiarFilas();
        PiezaNueva();
    }

    public void BorrarFila(int fila) {
        for (int i = fila-1; i > 0; i--) {
            for (int j = 1; j < col; j++) {
                well[j][i + 1] = well[j][i];
            }
        }
    }

    public void LimpiarFilas() {
        boolean bandera;
        int n = 0;
        for (int j = fil; j > 0; j--) {
            bandera = false;
            for (int i = 1; i < col; i++) {
                bandera = true;
                break;
            }
            if (!bandera) {
                BorrarFila(j);
                j++;
                n++;
            }
        }
        switch (n) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
        }

    }

    private void DibujarPieza(Graphics g) {
        g.setColor(FormaColors[piezaActual]);
        for (Point p : formas[piezaActual][rotacion]) {
            g.fillRect((p.x + piezaOrigen.x) * 26, (p.y + piezaOrigen.y) * 26, 25, 25);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // Paint the well
        g.fillRect(0, 0, 26 * col, 26 * (fil));
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                g.setColor(well[i][j]);
                g.fillRect(26 * j, 26 * i, 25, 25);
            }
        }

        // Display the score
        g.setColor(Color.WHITE);
		//g.drawString("" + score, 19*12, 25);

        // Draw the currently falling piece
        DibujarPieza(g);
    }

    static boolean buscarCoordenada(LinkedList<Coordenada> lista, int fil, int col) {
        if (lista.isEmpty()) {
            return false;
        }
        for (Coordenada coordenada : lista) {
            if (coordenada.getfil() == fil && coordenada.getcol() == col) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<Integer> getListaNumeros() {
        return listaNumeros;
    }

    public void setListaNumeros(LinkedList<Integer> listaNumeros) {
        this.listaNumeros = listaNumeros;
    }

}
