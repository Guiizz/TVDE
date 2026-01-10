import UI.Menu;

/**
 * Classe principal da aplicacao de gestao TVDE.
 * Ponto de entrada do programa.
 */
public class TVDE {

    /**
     * Metodo principal que inicia a aplicacao.
     *
     * @param args Argumentos da linha de comandos (nao utilizados)
     */
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║               SISTEMA DE GESTAO DE EMPRESA TVDE              ║");
        System.out.println("║                                                              ║");
        System.out.println("║                                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("A iniciar o sistema...");
        System.out.println();

        // Criar e iniciar o menu
        Menu menu = new Menu();
        menu.iniciar();

        System.out.println("\nSistema encerrado.");
    }
}
//falta fazer
//        menus acabar o meunu condutor os detalhes
//        acabar menu viaturas e detalhes
//        acabar menu clientes e detalhes
//        menus reservas e viagens
//        voltar a meio de açoes
//        menu relstats
//        fazer checklist de todos os topicos que o stor pediu
//        rever o codigo todo
//        java docs do resto