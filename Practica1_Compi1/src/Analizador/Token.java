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

    /**
     * Clase enumerada que representa el tipo de Token registrado
     */
    public enum Tipo {

        NUMERO_ENTERO,
        COMENTARIO,
        IDENTIFICADOR,
        ASTERISCO,
        NUMERAL,
        GUION,
        ULTIMO

    }
    /**
     * String que representa el tipo de token obtenido de la clase Enumerada Tipo
     */
    private Tipo tipoToken;
    /**
     * Valor asignado segun el tipo asignado
     */
    private String valor;
    /**
     * Representa el numero de token segun el metodo del arbol
     */
    
   private int NumToken;
   /**
    * Costructor de la clase Token
    * @param tipoToken Asigna el tipoToke al objeto
    * @param valor Asigna una valor dependiendo del tipo de token detectado
    * @param numToken Asigna un numero de token a cada Token
    */ 

    public Token(Tipo tipoToken, String valor, int numToken) {
        this.tipoToken = tipoToken;
        this.valor = valor;
        this.NumToken = numToken;
    }

    public Tipo getTipoToken() {
        return this.tipoToken;
    }

    public String getValor() {
        return valor;
    }

    public int getNumToken() {
        return NumToken;
    }

    public void setNumToken(int NumToken) {
        this.NumToken = NumToken;
    }
    

    public String getTipoString() {
        switch (tipoToken) {

            case NUMERO_ENTERO:
                return "Numero_entero";//2
            case COMENTARIO:
                    return "Comentario";//1
            case IDENTIFICADOR:
                return "Identificador";//3
            case ASTERISCO:
                return "asterisco";//5
            case NUMERAL:
                return "numeral";//4
            case GUION:
                return "guion";//6
            default:
                return "Desconocido";

        }

    }
    @Override
    public String toString (){
    return this.tipoToken +" ->" +this.valor;
    }
}
