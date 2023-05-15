package tratarerros.adressbookv2.exceptions;

public class AgendaVaziaException extends Exception {
    @Override
    public String toString() {
        return "Agenda não tem nenhum contacto";
    }
}
