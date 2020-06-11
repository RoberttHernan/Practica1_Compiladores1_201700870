package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tetris extends JPanel {

    /**
     * Variable que controla las filas y las columnas del tablero de juego
     */
    private int fil;
    private int col;
    /**
     * Lista enlazada de Coordenadas ocupadas que se reciben del archivo del entrada
     */
    private LinkedList<Coordenada> coordenadasOcupadas = new LinkedList<>();

    /**
     * Variable que apunta al origen de cada pieza creada
     */
    private Point piezaOrigen;
    /**
     * Variable que guarda la pieza actual en el juego
     */
    private int piezaActual;
    /**
     * Variable que controla las rotaciones de las piezas
     */
    private int rotacion;
    /**
     * Lista de enteros que representan las piezas en cola a ser jugadas
     */
    private LinkedList<Integer> listaPiezas = new LinkedList<>();// lista de numeros que representa las piezas 

    /**
     * Lista de enteros que guarda la lista de piezas obtenidas del analisis del archivo
     */
    private LinkedList<Integer> listaNumeros = new LinkedList<>();// lista de numero que vienen del analisis

    /**
     * Variable que Guarda el score del juego
     */
    private int score;
    /**
     * Matriz que representara el tablero de juego
     */
    private Color[][] well;

    /**
     * Constructor de la clase tetris
     * @param filas Representa las filas del juego
     * @param col Representa la columnas del juego
     */
    public Tetris(int filas, int col) {
        this.fil = filas;
        this.col = col;
    }

    /**
     * Matriz de puntos que representan cada una de la rotaciones y formas de la piezas
     */
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

    /**
     * Array que representa los colores de cada una de la formas
     */
    private final Color[] FormaColors = {
        Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red, Color.cyan, Color.blue, Color.blue, Color.blue,
        Color.orange, Color.orange, Color.orange, Color.green, Color.pink, Color.pink, Color.pink, Color.red

    };
/**
 * Getter de lista de Coordenadas
 * @return Lista de coordenadas ocupadas
 */
    public LinkedList<Coordenada> getCoordenadasOcupadas() {
        return coordenadasOcupadas;
    }

    /**
     * Setter de lista de coordenadas
     * @param coordenadasOcupadas Recibe una lista de coordenadas y la agrega al objeto
     */
    public void setCoordenadasOcupadas(LinkedList<Coordenada> coordenadasOcupadas) {
        this.coordenadasOcupadas = coordenadasOcupadas;
    }

    /**
     * Inicializador del tablero de juego, pinta el tablero de juego de acuerdo a las especificaciones
     * de entrada y agrega la primera pieza al juego
     */
    public void init() {
        well = new Color[fil][col];

        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {

                if (buscarCoordenada(coordenadasOcupadas, i, j)) {
                    well[i][j] = Color.red;
                } else if (i == fil - 1) {
                    well[i][j] = Color.gray;
                } else {
                    well[i][j] = Color.WHITE;
                }
            }
        }
        PiezaNueva();
    }

    /**
     * Permite mover las piezas de derecha a izquierda, y luego repinta el tablero
     * @param i representa el movimiente de la pieza
     */
    public void mover(int i) {
        piezaOrigen.x += i;
        repaint();
    }

    /**
     * Crea una pieza nueva en el punto de origen 1.0, con un rotacion de 0
     */
    public void PiezaNueva() {
        piezaOrigen = new Point(1, 0);
        rotacion = 0;
        if (listaPiezas.isEmpty()) {
            for (int n : listaNumeros) {
                listaPiezas.add(n);
            }

        }
        piezaActual = listaPiezas.get(0);
        listaPiezas.remove(0);
    }

    /**
     * Detecta la colision entre piezas en el tablero
     * @param x detecta la colision respecto a el eje x
     * @param y detecta la colision respecto al eje y
     * @param rotacion Detecta si al rotar la pieza existe una colision
     * @return returna un valor booleano verdadero si encuentra una rotacion
     */
    private boolean colision(int x, int y, int rotacion) {
        for (Point p : formas[piezaActual][rotacion]) {

            if (y == fil - 1) {
                return true;
            }
            if (well[(int) p.getY() + y][(int) p.getX() + x] != Color.WHITE) {
                return true;
            }

        }
        return false;
    }

    /**
     * Rota las piezas de acuerdo a la rotaciones creadas en la matriz de piezas
     * @param n entero de 0 a 3 y representa cada una de las rotaciones
     */
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

    /**
     * Genera la animacion de la pieza cayendo hacia abajo, y si detecta una colision 
     * deja la pieza en el lugar de la colision con el metodo pegarAlTablero()
     * Luego repinta
     */
    public void dropDown() {
        if (!colision((int) piezaOrigen.x, (int) piezaOrigen.y + 1, rotacion)) {
            piezaOrigen.y += 1;
        } else {
            pegarAlTablero();
        }
        repaint();
    }

    /**
     * Pinta el tablero respecto a las piezas que tengan colisiones y genera una pieza nueva
     */
    public void pegarAlTablero() {
        for (Point p : formas[piezaActual][rotacion]) {
            well[((int) piezaOrigen.getY()) + p.y][((int) piezaOrigen.getX()) + p.x] = FormaColors[piezaActual];
        }
        LimpiarFilas();
        PiezaNueva();
    }

    /**
     * Copia a la fila y+1 la fila y y elimina la fila y+1
     * @param fila fila a borrar
     */
    public void BorrarFila(int fila) {
        for (int i = fila-1; i > 0; i--) {
            for (int j = 0; j < col; j++) {
                well[i+1][j] = well[i][j];
                
            }
        }
    }

    /**
     * Mueve cada una de la filas una pocision hacia abajo eliminando la ultima fila
     * y ademas agrega puntos al score
     */
    public void LimpiarFilas() {
        boolean bandera;
        int n = 0;
        for (int j = fil-2; j > 0; j--) {

            bandera = false;
            for (int i = 0; i < col; i++) {
                if (well[j][i] == Color.WHITE) {
                    bandera = true;
                    break;
                }
            }
            if (!bandera) {

                BorrarFila(j);
                j++;
                n++;
                if (score>=500){
                    JOptionPane.showMessageDialog(null, "Felicidades! Ganaste el Nivel!!!");
                }
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

    /**
     * Metodo que dibujar nueva pieza 
     * @param g Grafico que representa la nueva pieza
     */
    private void DibujarPieza(Graphics g) {
        g.setColor(FormaColors[piezaActual]);
        for (Point p : formas[piezaActual][rotacion]) {
            g.fillRect((p.x + piezaOrigen.x) * 26, (p.y + piezaOrigen.y) * 26, 25, 25);
        }
    }
    
    /**
     * Pinta la nueva pieza en el tablero de juego
     * @param g Grafico a pintar en el tablero
     */

    @Override
    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, 26 * col, 26 * (fil));
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                g.setColor(well[i][j]);
                g.fillRect(26 * j, 26 * i, 25, 25);
            }
        }

        g.setColor(Color.BLACK);
		g.drawString("" + score, 19*12, 25);

        DibujarPieza(g);
    }

    /**
     * Busca una coordenada en la lista obtenida del analisis
     * @param lista lista recibida del analisis
     * @param fil fila a buscar
     * @param col columna a buscar
     * @return Verdadero si encuentra la coordenada, falso si no la encuentra
     */
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
