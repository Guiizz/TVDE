import UI.Menu;

/**
 * Classe principal da aplicacao de gestao TVDE.
 * Ponto de entrada do programa.
 *
 * @author Grupo APOO
 * @version 1.0
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
        System.out.println("║         SISTEMA DE GESTAO DE EMPRESA TVDE                    ║");
        System.out.println("║                                                              ║");
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
