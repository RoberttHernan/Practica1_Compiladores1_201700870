/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert Hernandez>
 */
public class AnalizadorLexico2 {

    private LinkedList<Token2> salida;
    private LinkedList<String> errores = new LinkedList<>();
    private int estado;
    private String lexema;

    private void AddToken(Token2.Tipo tipo, int numToken) {

        salida.addLast(new Token2(tipo, lexema, numToken));
        lexema = "";
        estado = 0;
    }

    public LinkedList<Token2> AnalizadorCadena(String entrada) {
        salida = new LinkedList<>();
        entrada += "#";
        estado = 0;
        int columna = 1;
        int fila = 1;
        lexema = "";
        char c;

        for (int i = 0; i <= entrada.length() - 1; i++) {
            c = entrada.charAt(i);
            columna++;

            switch (estado) {
                case 0:
                    if (c == 'L') {
                        lexema += c;
                        AddToken(Token2.Tipo.L, 3);
                    } else if (c == 'I') {
                        lexema += c;
                        AddToken(Token2.Tipo.I, 1);
                    } else if (c == 'J') {
                        lexema += c;
                        AddToken(Token2.Tipo.J, 2);
                    } else if (c == 'O') {
                        lexema += c;
                        AddToken(Token2.Tipo.O, 4);
                    } else if (c == 'S') {
                        lexema += c;
                        AddToken(Token2.Tipo.S, 5);
                    } else if (c == 'Z') {
                        lexema += c;
                        AddToken(Token2.Tipo.Z, 6);
                    } else if (c == 'T') {
                        lexema += c;
                        AddToken(Token2.Tipo.T, 7);
                    } else if (c == ' ') {
                        estado = 0;
                    } else if (c == '\t') {
                        estado = 0;
                    } else if (c == '\n') {
                        estado = 0;
                        fila++;
                        columna = 1;

                    } else if (c == ',') {
                        lexema += c;
                        AddToken(Token2.Tipo.COMA, 8);
                    } else if (c == '>') {
                        lexema += c;
                        AddToken(Token2.Tipo.MAYOR, 9);
                    } else if (c == '<') {
                        i++;
                        c = entrada.charAt(i);
                        if (c == '!') {
                            estado = 2;
                        } else {
                            i--;
                            c = entrada.charAt(i);
                            lexema += c;

                            AddToken(Token2.Tipo.MENOR, 10);
                        }

                    } else if (c == 'v') {
                        lexema += c;
                        AddToken(Token2.Tipo.UVE, 11);
                    } else if (c == '^') {
                        lexema += c;
                        AddToken(Token2.Tipo.CIRCUNFLEJO, 12);
                    } else if (c == '/') {
                        i++;
                        c = entrada.charAt(i);
                        if (c == '/') {
                            estado = 1;
                        } else {
                            estado = -99;
                            i--;
                        }
                    } else {
                        if (c == '#' && i == entrada.length() - 1) {
                            System.out.println("Analisis concluido con exito ");
                        } else {
                           estado = -99;
                        }
                    }
                    break;
                case 1:
                    if (c != '\n' && i != entrada.length() - 2) {
                        lexema += c;
                        estado = 1;
                    } else {
                        AddToken(Token2.Tipo.COMENTARIO, 13);
                        estado = 0;
                    }
                    break;
                case 2:
                    if (c != '!') {
                        lexema += c;
                        estado = 2;
                    } else {
                       estado = 3;
                       lexema+=c;
                    }
                    break;
                case 3:
                    if (c=='>'){
                    AddToken(Token2.Tipo.COMENTARIO, 13);
                        estado = 0;
                    }
                    else {
                    estado = -99;
                      
                    }
                    
                    break;
                case -99:
                    errores.add("Error lexico: " + c +" Fila :" +fila +" Columna:" + columna);
                    salida.clear();
                    i = entrada.length() - 1;

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

    public String TextoHtmlListaTokens() {
        int i = 0;
        String texto = "<html> +\n <head><title>Listado Tokens</title></head> \n <body>\n"
                + "<h1>Listado Tokens</h1> \n <table>\n"
                + "<tr>\n"
                + "<td><strong>Num Token</strong></td>\n"
                + "<td><strong>NombreToken</strong></td>\n"
                + "<td><strong>Valor</strong></td>\n";

        for (Token2 token : salida) {
            texto += "<tr>\n"
                    + "<td><strong>" + token.getNumToken() + "</strong></td>\n"
                    + "<td><strong>" + token.getTipoString() + "</strong></td>\n"
                    + "<td><strong>" + token.getValor() + "</strong></td>\n";
            i++;
        }

        texto += "</table>\n</body>\n</html>\n";
        return texto;
    }

    public void ReporteHtmlTokens() {

        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter("Reporte2Tokens.html");
            pw = new PrintWriter(fichero);
            pw.println(TextoHtmlListaTokens());
        } catch (IOException ex) {
            Logger.getLogger(AnalizadorLexico2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public String TextoHtmlListaErrores() {
       
        String texto = "<html> +\n <head><title>Listado Tokens</title></head> \n <body>\n"
                + "<h1>Listado Errores</h1> \n <table>\n"
                + "<ol>\n";
                

        for (String error : errores) {
            texto += "<li>"+error +"/<li>\n";
  
        }

        texto += "</ol>\n</body>\n</html>\n";
        return texto;
    }
    
    public void ReporteHtmlErrores() {

        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter("ReporteErroresArchivo2.html");
            pw = new PrintWriter(fichero);
            pw.println(TextoHtmlListaErrores());
        } catch (IOException ex) {
            Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
