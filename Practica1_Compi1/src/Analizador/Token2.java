package Analizador;

/**
 *
 * @author Robert Hernandez
 */
public class Token2 {

    public enum Tipo {

        I,
        J,
        L,
        O,
        S,
        Z,
        T,
        MENOR,
        MAYOR,
        UVE,
        COMA,
        CIRCUNFLEJO
    }

    private Tipo tipoToken;
    private String valor;

    public Token2(Tipo tipoToken, String valor) {
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
            case L:
                return "L";
            case I:
                return "I";
            case J:
                return "J";
            case O:
                return "O";
            case S:
                return "S";
            case Z:
                return "Z";
            case T:
                return "T";                
            case MENOR:
                return "Menor";
            case MAYOR:
                return "Mayor";
            case UVE:
                return "Uve";
            case CIRCUNFLEJO:
                return "Circunflejo";
            case COMA:
                return "Coma";

            default:
                return "desconocido";

        }

    }
     @Override
    public String toString (){
    return this.tipoToken +" ->" +this.valor;
    }
}
