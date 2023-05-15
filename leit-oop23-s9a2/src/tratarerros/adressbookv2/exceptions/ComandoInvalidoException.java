package tratarerros.adressbookv2.exceptions;

public class ComandoInvalidoException extends Exception{
    private String comando;

    public ComandoInvalidoException(String comando) {
        this.comando = comando;
    }

    public String getComando() {
        return comando;
    }
    public String toString() {
        return "A palavra" + comando + "não é comado válido" ;
    }
}
