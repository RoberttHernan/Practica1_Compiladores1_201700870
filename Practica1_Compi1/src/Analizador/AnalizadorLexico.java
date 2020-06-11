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
 * @author Robert Hernandez
 */
public class AnalizadorLexico {

    /**
     * Lista enlazada que representa la salida de tokens validos obtenidos del analisis
     */
    private LinkedList<Token> salida;
    /**
     * Lista enlzada que representa la salida de Errores obtenidos del analisis
     */
    private LinkedList<String> errores = new LinkedList<>();
    /**
     * Representa el estado dependiendo del el caracter obtenido
     */
    private int estado;
    /**
     * Representa un lexema obtenido del analisis sintactico
     */
    private String lexema;

    /**
     * Añade un nuevo token a la lista de salida de Tokens
     * @param tipo representa el tipo de token obtenido
     * @param numToken 
     */
    private void AddToken(Token.Tipo tipo, int numToken) {

        salida.addLast(new Token(tipo, lexema, numToken));
        lexema = "";
        estado = 0;
    }
/**
 * Analiza un texto de entrada para relizar el analisis lexico 
 * @param entrada Texto a analizar
 * @return Lista de tokens y errores
 */
    public LinkedList<Token> AnalizadorCadena(String entrada) {
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
                        fila++;
                        columna = 1;
                    } else if (c == '*') {
                        lexema += c;
                        AddToken(Token.Tipo.ASTERISCO,5);
                    } else if (c == '#' && i != entrada.length() - 1) {
                        lexema += c;
                        AddToken(Token.Tipo.NUMERAL,4);
                    } else if (c == '-') {
                        lexema += c;
                        AddToken(Token.Tipo.GUION,6);
                    } else if (c == '/') {
                        i++;
                        c = entrada.charAt(i);
                        if (c == '/') {
                            estado = 3;
                        } else {
                            estado = -99;
                            i--;
                        }
                    } else if (c == '<') {
                        i++;
                        c = entrada.charAt(i);
                        if (c == '!') {
                            estado = 4;
                        } else {
                            estado = -99;
                        }
                    } else {
                        if (c == '#' && i == entrada.length() - 1) {
                            System.out.println("Analisis concluido con exito ");
                        } else {
                            estado = -99;
                            i--;
                        }
                    }
                    break;
                case 1:
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        lexema += c;
                        estado = 1;
                    } else {
                        AddToken(Token.Tipo.IDENTIFICADOR,3);
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
                        AddToken(Token.Tipo.NUMERO_ENTERO,2);
                        lexema = Character.toString(c);
                        i--;
                        estado = 3;
                    } else {
                        AddToken(Token.Tipo.NUMERO_ENTERO,2);
                        i--;
                    }
                    break;
                case 3:
                    if (c != '\n' && i!=entrada.length()-2) {
                        lexema += c;
                        estado = 3;
                    } else {
                        AddToken(Token.Tipo.COMENTARIO,1);
                        estado = 0;
                    }

                    break;
                case 4:
                    if (c != '!') {
                        lexema += c;
                        estado = 4;

                    } else {
                        i++;
                        c = entrada.charAt(i);
                        if (c == '>') {
                            AddToken(Token.Tipo.COMENTARIO,1);
                            estado = 0;
                        } else {
                            estado = -99;
                        }
                    }
                    break;
                case -99:
                    errores.add("Error lexico: " + c +" Fila" + fila +" Columna "+ columna);
                    salida.clear();
                    estado = entrada.length()-1;

                    break;
                default:
                    break;
            }
        }
        return salida;
    }

    /**
     * Imprime la lista de tokens obtenida del analizador lexico
     * @param lista Lista de tokens a imprimir
     */
    public void imprimirListaTokens(LinkedList<Token> lista) {
        for (Token lista1 : lista) {
            System.out.println(lista1.toString());
        }
    }
/**
 * Genera el codigo de la pagina html del reporte de Tokens
 * @return String que representa el codigo de la pagina html de reportes
 */
    public String TextoHtmlListaTokens() {
        int i = 0;
        String texto = "<html> +\n <head><title>Listado Tokens</title></head> \n <body>\n"
                + "<h1>Listado Tokens</h1> \n <table>\n"
                + "<tr>\n"
                + "<td><strong>Num Token</strong></td>\n"
                + "<td><strong>NombreToken</strong></td>\n"
                + "<td><strong>Valor</strong></td>\n";

        for (Token token : salida) {
            texto += "<tr>\n"
                    + "<td><strong>" + token.getNumToken() + "</strong></td>\n"
                    + "<td><strong>" + token.getTipoString() + "</strong></td>\n"
                    + "<td><strong>" + token.getValor() + "</strong></td>\n";
            i++;
        }

        texto += "</table>\n</body>\n</html>\n";
        return texto;
    }

    /**
     * Crea la pagina HTML del reporte
     */
    public void ReporteHtmlTokens() {

        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter("ReporteTokens.html");
            pw = new PrintWriter(fichero);
            pw.println(TextoHtmlListaTokens());
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
    
    /**
     * Retorna el texto codigo de la pagina de Errores lexicos
     * @return String que representa el codigo de la pagina de reporte de Errores
     */
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
    
    /**
     * Genera el reporte de errores
     */
    public void ReporteHtmlErrores() {

        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter("ReporteErroresArchivo1.html");
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
