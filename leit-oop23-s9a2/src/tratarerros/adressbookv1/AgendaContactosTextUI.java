package tratarerros.adressbookv1;

import tratarerros.adressbookv2.exceptions.ContactoInexistenteException;
import tratarerros.adressbookv2.exceptions.DuplicateKeyException;

/**
 *
 * Fornecer uma interface de utilizador via linha de comando  para uma
 * AgendaContactos.
 * Diferentes comandos fornecem acesso aos dados de endereços na agenda:
 *
 *      Um para pesquisar o agenda de contactos
 *
 *      Um para permitir que um conjunto de detalhes de contato seja inserido.
 *
 *      Um para mostrar todas as entradas da agenda
 *
 * @author Paulo .
 * @version 2023.05.12
 */
public class AgendaContactosTextUI
{
    // a agenda de contactos a ser visualizado e manipulado.
    private AgendaContactos agenda;
    // Um analisador para lidar com os comandos do usuário.
    private Parser parser;
    
    /**
     * Construtor para objetos da classe AgendaContactosTextUI
     * @param book A agenda de contactos a ser manipulado.
     */
    public AgendaContactosTextUI(AgendaContactos book)
    {
        this.agenda = book;
        parser = new Parser();
    }
    
    /**
     * Leia uma série de comandos do usuário para interagir
     * com o livro de endereços. Pare quando o usuário digitar 'sair'.
     */
    public void run()
    {
        System.out.println("Agenda de contactos.");
        System.out.println("Digite 'help' para obter uma lista de comandos.");
        
        String command;
        do{
            command = parser.getCommand();
            if(command.equals("adi")){
                add();
            }
            else if(command.equals("listar")){
                listar();
            }
            else if(command.equals("procurar")){
                encontrar();
            }
            else if(command.equals("buscar")){
                buscar();
            }
            else if(command.equals("alterar")){
                alterar();
            }
            else if(command.equals("remover")){
                remover();
            }

            else if(command.equals("help")){
                help();
            }
            else{
                // Do nothing.
            }
        } while(!(command.equals("sair")));

        System.out.println("Tchau.");
    }

    private void remover() {
        System.out.print("Digite chave de contacto que quer remover ");
        String key = parser.readLine();

            agenda.removeDetails(key);
            System.out.print("Contacto removido ");

    }

    private void buscar() {
        System.out.print("Digite chave de contacto procura: ");
        String key = parser.readLine();
        try {
            System.out.println(agenda.obterDetalhes(key).toString());
        } catch (NullPointerException e) {
            System.out.println("Chave invalida!");
        }  catch (IllegalArgumentException e) {
            System.out.println("Chave invalida, key não pode ser vazia!");
        }


    }

    /**
     * Adicionar uma nova entrada.
     */
    private void add()
    {
        System.out.print("Nome: ");
        String name = parser.readLine();
        System.out.print("Telefone: ");
        String phone = parser.readLine();
        System.out.print("Endereço: ");
        String address = parser.readLine();
        agenda.addDetails(new DetalhesContacto(name, phone, address));
    }
    
    /**
     * Encontre entradas correspondentes a um prefixo de chave.
     */
    private void alterar() {
        System.out.print("Chave do contato a ser alterado: ");
        String chave = parser.readLine();
        try {

            tratarerros.adressbookv2.DetalhesContacto detalhes = new tratarerros.adressbookv2.DetalhesContacto(chave);
            // implementar lógica para obter os novos detalhes do contato a serem alterados

            agenda.alterarDetalhes(chave, detalhes);
            System.out.println("Contacto alterado com sucesso.");
        } catch (ContactoInexistenteException e) {
            System.out.println("Erro: " + e.getMessage() + ". Não foi possível alterar.");
        } catch (DuplicateKeyException e) {
            System.out.println("Erro: " + e.getMessage() + ". Não foi possível alterar.");
        }
    }
    private void encontrar()
    {
        System.out.println("Digite um prefixo da chave a ser encontrada.");
        String prefix = parser.readLine();
        DetalhesContacto[] results = agenda.search(prefix);
        for(int i = 0; i < results.length; i++){
            System.out.println(results[i]);
            System.out.println("=====");
        }
    }
    
    /**
     * Liste os comandos disponíveis.
     */
    private void help()
    {
        parser.showCommands();
    }
    
    /**
     * Liste o conteúdo do agenda de contactos
     */
    private void listar()
    {
        System.out.println(agenda.listaDetalhes());
    }
}
