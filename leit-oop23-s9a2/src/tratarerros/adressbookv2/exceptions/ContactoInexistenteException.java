package tratarerros.adressbookv2.exceptions;

public class ContactoInexistenteException extends Exception {
    private String key;
    public ContactoInexistenteException(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
    public String toString() {
        return "Nenhum contacto encontrado para chave: " + key ;
    }
}
