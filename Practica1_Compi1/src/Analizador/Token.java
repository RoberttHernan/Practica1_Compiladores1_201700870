package Analizador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates

 package OtrasClases;

 /**
 *
 * @author Roberto Calvillo
 */

public class Token {

    public enum Tipo {

        NUMERO_ENTERO,
        SIGNO_POR,
        IDENTIFICADOR,
        COMA,
        ASTERISCO,
        NUMERAL,
        ULTIMO

    }
    private Tipo tipoToken;
    private String valor;

    public Token(Tipo tipoToken, String valor) {
        this.tipoToken = tipoToken;
        this.valor = valor;
    }

    public Tipo getTipoToken() {
        return this.tipoToken;
    }

    public String getValor() {
        return valor;
    }

    public String getTipoString() {
        switch (tipoToken) {

            case NUMERO_ENTERO:
                return "Numero entero";
            case SIGNO_POR:
                return "Signo Por";
            case IDENTIFICADOR:
                return "Identificador";
            case COMA:
                return "Coma";
            case ASTERISCO:
                return "asterisco";
            case NUMERAL:
                return "numeral";
            default:
                return "Desconocido";

        }

    }
    @Override
    public String toString (){
    return this.tipoToken +" ->" +this.valor;
    }
}
