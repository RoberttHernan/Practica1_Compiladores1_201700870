/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

import java.util.LinkedList;

/**
 *
 * @author Robert Hernandez>
 */
public class AnalizadorLexico2 {

    private LinkedList<Token2> salida;
    private int estado;
    private String lexema;
    
    private void AddToken(Token2.Tipo tipo) {
        
        salida.addLast(new Token2(tipo, lexema));
        lexema = "";
        estado = 0;
    }
    
    public LinkedList<Token2> AnalizadorCadena(String entrada) {
        salida = new LinkedList<>();
        entrada += "#";
        estado = 0;
        int columna = 0;
        lexema = "";
        char c;
        
        for (int i = 0; i <= entrada.length() - 1; i++) {
            c = entrada.charAt(i);
            columna++;
            
            switch (estado) {
                case 0:
                    if (c == 'L') {
                        lexema += c;
                        AddToken(Token2.Tipo.L);
                    }
                    else if (c == 'I') {
                        lexema += c;
                        AddToken(Token2.Tipo.I);
                    }
                    else if (c == 'J') {
                        lexema += c;
                        AddToken(Token2.Tipo.J);
                    }
                    else if (c == 'O') {
                        lexema += c;
                        AddToken(Token2.Tipo.O);
                    }
                    else if (c == 'S') {
                        lexema += c;
                        AddToken(Token2.Tipo.S);
                    }
                    else if (c == 'Z') {
                        lexema += c;
                        AddToken(Token2.Tipo.Z);
                    }
                    else if (c == 'T') {
                        lexema += c;
                        AddToken(Token2.Tipo.T);
                    }
                    else if (c == ' ') {
                        estado = 0;
                    } else if (c == '\t') {
                        estado = 0;
                    } else if (c == '\n') {
                        estado = 0;
                    }
                    else if (c == ',') {
                        lexema += c;
                        AddToken(Token2.Tipo.COMA);
                    }
                    else if (c=='>'){
                    lexema +=c;
                        AddToken(Token2.Tipo.MAYOR);
                    }
                    else if (c=='<'){
                    lexema += c;
                        AddToken(Token2.Tipo.MENOR);
                    }else if (c=='v'){
                    lexema+= c;
                        AddToken(Token2.Tipo.UVE);
                    }
                    else if (c=='^'){
                    lexema+=c;
                        AddToken(Token2.Tipo.CIRCUNFLEJO);
                    }
                    else {
                        if (c == '#' && i == entrada.length() - 1) {
                            System.out.println("Analisis concluido con exito ");
                        } else {
                            System.out.println("Error lexico en " + c);
                            estado = 0;
                        }
                    }
                    break;
            }
        }
         return salida;
    }
    
    public void imprimirListaTokens(LinkedList<Token2> lista) {
        for (Token2 lista1 : lista) {
            System.out.println(lista1.toString());
        }
    }
}
