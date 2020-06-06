package Analizador;

/*
Analizador de archivo No 1
S= {Com, Numero_Entero, Identificador, '-', '#', '*'}

 package OtrasClases;

 /**
 *
 * @author Roberto Calvillo
 */

public class Token {

    public enum Tipo {

        NUMERO_ENTERO,
        COMENTARIO,
        IDENTIFICADOR,
        ASTERISCO,
        NUMERAL,
        GUION,
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
                return "Numero_entero";
            case COMENTARIO:
                    return "comentario";
            case IDENTIFICADOR:
                return "Identificador";
            case ASTERISCO:
                return "asterisco";
            case NUMERAL:
                return "numeral";
            case GUION:
                return "guion";
            default:
                return "Desconocido";

        }

    }
    @Override
    public String toString (){
    return this.tipoToken +" ->" +this.valor;
    }
}
