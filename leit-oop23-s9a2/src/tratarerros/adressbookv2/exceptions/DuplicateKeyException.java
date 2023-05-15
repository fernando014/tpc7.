package tratarerros.adressbookv2.exceptions;

public class DuplicateKeyException extends Exception {
    private String nome;
    private String telefone;
    private ChaveComDuplicado chaveQueDuplica;
    public DuplicateKeyException(String key, ChaveComDuplicado chave) {
        if (chave.toString().equals("NOME")) {
            this.nome = key;
        } else
            this.telefone = key;

        this.chaveQueDuplica = chave;
    }
    public String getKey() {
        if (chaveQueDuplica.ordinal() == 0) {
            return  nome;
        } else {
            return telefone;
        }
    }

    public String toString() {
        return "Já existe contacto definido para a chave: " + getKey() ;
    }

}
