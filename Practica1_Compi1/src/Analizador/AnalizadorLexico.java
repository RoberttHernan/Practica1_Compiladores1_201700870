/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

import java.util.LinkedList;

/**
 *
 * @author Robert Hernandez
 */
public class AnalizadorLexico {

    private LinkedList<Token> salida;
    private int estado;
    private String lexema;

    private void AddToken(Token.Tipo tipo) {

        salida.addLast(new Token(tipo, lexema));
        lexema = "";
        estado = 0;
    }

    public LinkedList<Token> AnalizadorCadena(String entrada) {
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
                    if (Character.isLetter(c)) {
                        estado = 1;
                        lexema += c;
                    } else if (Character.isDigit(c)) {
                        estado = 2;
                        lexema += c;
                    } else if (c == ' ') {
                        estado = 0;
                    } else if (c == '\t') {
                        estado = 0;
                    } else if (c == '\n') {
                        estado = 0;
                    } else if (c == '*') {
                        lexema += c;
                        AddToken(Token.Tipo.ASTERISCO);
                    } else if (c == '#' && i != entrada.length() - 1) {
                        lexema += c;
                        AddToken(Token.Tipo.NUMERAL);
                    }
                    else if(c=='-'){
                    lexema +=c;
                        AddToken(Token.Tipo.GUION);
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
                case 1:
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        lexema += c;
                        estado = 1;
                    } else {
                        AddToken(Token.Tipo.IDENTIFICADOR);
                        lexema = "";
                        estado = 0;
                        i--;
                    }
                    break;

                case 2:
                    if (Character.isDigit(c)) {
                        lexema += c;
                        estado = 2;
                    } else if (c == 'x') {
                        AddToken(Token.Tipo.NUMERO_ENTERO);
                        lexema = Character.toString(c);
                        i--;
                        estado = 3;
                    } else {
                        AddToken(Token.Tipo.NUMERO_ENTERO);
                        i--;
                    }
                    break;
                default:
                    System.out.println("Error lexico en " + c);
                    break;
            }
        }
        return salida;
    }

    public void imprimirListaTokens(LinkedList<Token> lista) {
        for (Token lista1 : lista) {
            System.out.println(lista1.toString());
        }
    }
}
