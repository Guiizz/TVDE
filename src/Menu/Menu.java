package Menu;

import Gestão.GestaoTVDE;
import Classes.*;
import Validador.Validador;
import Ficheiros.GestorFicheiros;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe responsável pela Interface com o Utilizador (Consola).
 * Gere os menus, a leitura de dados, a navegação entre ecrãs e a interação com a lógica de negócio (GestaoTVDE).
 */
public class Menu {
    private GestaoTVDE gestao;
    private Scanner scanner;
    private DateTimeFormatter data;
    private GestorFicheiros gestorFicheiros;

    /**
     * Construtor da classe Menu.
     * Inicializa os componentes essenciais: Scanner para leitura, Formatador de datas e o Gestor de Ficheiros.
     * A instância de 'gestao' inicia a null até ser carregada ou criada uma empresa.
     */
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.data = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.gestorFicheiros = new GestorFicheiros();
        this.gestao = null;
    }

    /**
     * Metodo de entrada do menu.
     * Inicia o fluxo de navegação chamando o menu inicial.
     */
    public void iniciar(){
        menuInicial();
    }

    /**
     * Exibe o menu inicial da aplicação.
     * Permite ao utilizador selecionar uma empresa existente, criar uma nova ou remover uma empresa.
     */
    private void menuInicial() {
        int opcao;
        do {
            pausar();
            limparEcra();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                 SELEÇÃO DA EMPRESA TVDE                      ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Selecionar Empresa Existente                             ║");
            System.out.println("║  2. Criar Nova Empresa                                       ║");
            System.out.println("║  3. Remover Empresa                                          ║");
            System.out.println("║  0. Sair                                                     ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            opcao = lerInteiro("Opção: ");

            switch (opcao) {
                case 1:
                    selecionarEmpresa();
                    break;
                case 2:
                    criarNovaEmpresa();
                    break;
                case 3:
                    removerEmpresa();
                    break;
                case 0:
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("\nOpção invalida!");
                    pausar();

            }
        } while (opcao != 0);
    }

    /**
     * Lista as empresas disponíveis nos ficheiros e carrega a escolhida para a memória.
     * Se não existirem empresas, avisa o utilizador.
     */
    private void selecionarEmpresa() {
        limparEcra();
        System.out.println("=== SELECIONAR EMPRESA ===\n");
        ArrayList<String> empresas = gestorFicheiros.listarEmpresasExistentes();

        if (empresas.isEmpty()) {
            System.out.println("Não existem empresas registadas. Crie uma nova.");
            pausar();
            return;
        }

        System.out.println("Empresas disponiveis:");
        for (int i = 0; i < empresas.size(); i++) {
            System.out.println((i + 1) + ". " + empresas.get(i));
        }
        System.out.println("0. Voltar");
        int escolha = lerInteiro("\nEscolha uma empresa:");

        if (escolha > 0 && escolha <= empresas.size()) {
            String nomeEmpresa = empresas.get(escolha - 1);
            try {
                System.out.println("A carregar " + nomeEmpresa + "...");
                this.gestao = gestorFicheiros.lerTudo(nomeEmpresa);
                System.out.println("Carregado com sucesso!");
                pausar();

                menuPrincipal();

            } catch (Exception e) {
                System.out.println("Erro ao carregar empresa: " + e.getMessage());
                pausar();
            }
        }
    }

    /**
     * Cria uma nova estrutura de empresa.
     * Pede o nome, inicializa a GestaoTVDE e cria as pastas/ficheiros necessários.
     */
    private void criarNovaEmpresa() {
        limparEcra();
        System.out.println("=== CRIAR NOVA EMPRESA ===\n");

        String nome = lerString("Nome da nova empresa: ");
        this.gestao = new GestaoTVDE();
        this.gestao.setNomeEmpresa(nome);

        try{
            gestorFicheiros.guardarTudo(this.gestao);
            System.out.println("Empresa '" + nome + "' criada com sucesso!");
            pausar();

            menuPrincipal();
        }catch (Exception e) {
            System.out.println("Erro ao criar os ficheiros da empresa: " + e.getMessage());
            pausar();
        }
    }

    /**
     * Remove uma empresa existente e apaga os seus ficheiros de dados.
     * Solicita confirmação explícita ao utilizador antes de apagar.
     */
    private void removerEmpresa() {
        limparEcra();
        System.out.println("=== REMOVER EMPRESA ===\n");
        System.out.println("ATENCAO: Isto irá apagar TODOS os dados da empresa permanentemente!\n");

        // Usa o metodo que criámos no passo anterior para obter a lista
        ArrayList<String> empresas = gestorFicheiros.listarEmpresasExistentes();

        if (empresas.isEmpty()) {
            System.out.println("Não existem empresas registadas para remover.");
            pausar();
            return;
        }

        System.out.println("Empresas disponiveis para remover:");
        for (int i = 0; i < empresas.size(); i++) {
            System.out.println((i + 1) + ". " + empresas.get(i));
        }
        System.out.println("0. Voltar");

        int escolha = lerInteiro("\nEscolha a empresa a remover: ");

        if (escolha > 0 && escolha <= empresas.size()) {
            String nomeEmpresa = empresas.get(escolha - 1);

            // Segurança extra: obriga a escrever para confirmar
            System.out.println("\nTem a certeza que deseja apagar a empresa '" + nomeEmpresa + "'?");
            String confirmacao = lerString("Deseja eliminar esta empresa? (S/N): ");

            if (confirmacao.equalsIgnoreCase("S")) {
                if (gestorFicheiros.removerEmpresa(nomeEmpresa)) {
                    System.out.println("\nEmpresa '" + nomeEmpresa + "' removida com sucesso.");
                } else {
                    System.out.println("\nErro ao remover a empresa. Verifique se os ficheiros não estão abertos noutro programa.");
                }
            } else if (confirmacao.equalsIgnoreCase("N")){
                System.out.println("\nOperacao cancelada.");
            }
        }
        pausar();
    }

    /**
     * Menu Principal da aplicação.
     * Verifica automaticamente se existem reservas expiradas ao iniciar.
     * Encaminha para os sub-menus de gestão (Condutores, Viaturas, Clientes, Reservas, Viagens, etc.).
     */
    private void menuPrincipal() {
        int opcao;
        do {
            if (gestao != null) {
                int convertidas = gestao.processarReservasExpiradas();
                if (convertidas > 0) {
                    System.out.println("\n[AVISO AUTOMATICO] " + convertidas + " reserva(s) convertida(s) a viagem.");
                    pausar();
                }
            }
            limparEcra();
            String nomeEmpresa = (gestao.getNomeEmpresa() != null && !gestao.getNomeEmpresa().isEmpty())
                    ? gestao.getNomeEmpresa().toUpperCase()
                    : "EMPRESA TVDE";
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            imprimirLinhaCentrada("MENU PRINCIPAL");
            imprimirLinhaCentrada(nomeEmpresa);
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Gestao de Condutores                                     ║");
            System.out.println("║  2. Gestao de Viaturas                                       ║");
            System.out.println("║  3. Gestao de Clientes                                       ║");
            System.out.println("║  4. Gestao de Reservas                                       ║");
            System.out.println("║  5. Gestao de Viagens                                        ║");
            System.out.println("║  6. Relatorios e Estatisticas                                ║");
            System.out.println("║  7. Gestao de Ficheiros                                      ║");
            System.out.println("║  0. Sair                                                     ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            opcao = lerInteiro("Opcao: ");

            switch (opcao) {
                case 1:
                    menuCondutores();
                    break;
                case 2:
                    menuViaturas();
                    break;
                case 3:
                    menuClientes();
                    break;
                case 4:
                    menuReservas();
                    break;
                case 5:
                    menuViagens();
                    break;
                case 6:
                    menuRelStats();
                    break;
                case 7:
                    menuFicheiros();
                    break;
                case 0:
                    menuSair();
                    break;
                default:
                    System.out.println("\nOpcao invalida!");
                    pausar();
            }
        } while (opcao != 0);
    }

    /**
     * Apresenta o menu de gestão de Condutores.
     */
    private void menuCondutores() {
        int opcao;
        do {
            limparEcra();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                   GESTAO DE CONDUTORES                       ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Adicionar condutor                                       ║");
            System.out.println("║  2. Listar todos os condutores                               ║");
            System.out.println("║  3. Consultar condutor                                       ║");
            System.out.println("║  4. Alterar condutor                                         ║");
            System.out.println("║  5. Remover condutor                                         ║");
            System.out.println("║  0. Voltar                                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            opcao = lerInteiro("Opcao: ");

            switch (opcao) {
                case 1:
                    adicionarCondutor();
                    break;
                case 2:
                    listarCondutores();
                    break;
                case 3:
                    consultarCondutor();
                    break;
                case 4:
                    alterarCondutor();
                    break;
                case 5:
                    removerCondutor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpcao invalida!");
                    pausar();
            }
        } while (opcao != 0);
    }

    /**
     * Adiciona um novo condutor ao sistema.
     * Solicita todos os dados necessários (Nome, CC, Carta, NSS, NIF, Telemóvel, Morada).
     * Efetua validações de formato e verifica se os dados únicos (CC, NIF, Carta, NSS) já existem.
     */
    private void adicionarCondutor() {
        limparEcra();
        System.out.println("=== ADICIONAR CONDUTOR ===\n");

        // Nome
        String nome = lerStringComValidacao("Nome (minimo 3 caracteres): ", 3);

        // Numero de identificacao (Validacao formato + Unicidade)
        String numId;
        boolean numIdExiste;
        do {
            numIdExiste = false;
            numId = lerString("N. Identificacao (CC - minimo 8 digitos): ");
            if (!Validador.validarNumeroIdentificacao(numId)) {
                System.out.println(Validador.getMensagemErroNumId());
            } else if (gestao.procurarCondutorPorNumeroIdentificacao(numId) != null) {
                System.out.println("Erro: Ja existe um condutor com este numero de identificacao!");
                numIdExiste = true;
            }
        } while (!Validador.validarNumeroIdentificacao(numId) || numIdExiste);


        // Carta de conducao (Validacao formato + Unicidade)
        String carta;
        boolean cartaExiste;
        do {
            cartaExiste = false;
            // O utilizador pode escrever "ab123456" à vontade
            carta = lerString("Carta de Conducao (ex: AB-123456): ");

            // O validador agora é "inteligente" e aceita sem hifens
            if (!Validador.validarCartaConducao(carta)) {
                System.out.println(Validador.getMensagemErroCarta());
            }
            // IMPORTANTE: Formatar antes de verificar se existe, para comparar "maçãs com maçãs"
            else if (gestao.procurarCondutorPorCartaConducao(Validador.formatarCartaConducao(carta)) != null) {
                System.out.println("Erro: Ja existe um condutor com esta Carta de Conducao!");
                cartaExiste = true;
            }
        } while (!Validador.validarCartaConducao(carta) || cartaExiste);

        carta = Validador.formatarCartaConducao(carta);

        // Numero de Seguranca Social (Validacao formato + Unicidade)
        String nss;
        boolean nssExiste;
        do {
            nssExiste = false;
            nss = lerString("N. Seguranca Social (11 digitos): ");
            if (!Validador.validarNss(nss)) {
                System.out.println(Validador.gerMensagemErroNss());
            } else if (gestao.procurarCondutorPorNss(nss) != null) {
                System.out.println("Erro: Ja existe um condutor com este NSS!");
                nssExiste = true;
            }
        } while (!Validador.validarNss(nss) || nssExiste);


        // NIF (Validacao formato + Unicidade)
        String nif;
        boolean nifExiste;
        do {
            nifExiste = false;
            nif = lerString("NIF (9 digitos): ");
            if (!Validador.validarNif(nif)) {
                System.out.println(Validador.getMensagemErroNif());
            } else if (gestao.procurarCondutorPorNif(nif) != null) {
                System.out.println("Erro: Ja existe um condutor com este NIF!");
                nifExiste = true;
            }
        } while (!Validador.validarNif(nif) || nifExiste);


        // Telemovel
        String tel;
        do {
            tel = lerString("Telemovel: ");
            if (!Validador.validarTelefone(tel)) {
                System.out.println(Validador.getMensagemErroTelefone());
            }
        } while (!Validador.validarTelefone(tel));

        // Morada
        String morada = lerStringComValidacao("Morada (minimo 5 caracteres): ", 5);

        // Criar e adicionar
        Condutor condutor = new Condutor(nome, numId, carta, nss, nif, tel, morada);

        if (gestao.adicionarCondutor(condutor)) {
            System.out.println("\nCondutor adicionado com sucesso! (ID: " + condutor.getId() + ")");
        } else {
            System.out.println("\nErro ao adicionar condutor!");
        }
        pausar();
    }

    /**
     * Lista todos os condutores registados.
     */
    private void listarCondutores() {
        limparEcra();
        System.out.println("=== LISTA DE CONDUTORES ===\n");

        ArrayList<Condutor> condutores = gestao.getCondutores();
        int numCondutores = gestao.getNumeroCondutores();

        if (numCondutores == 0) {
            System.out.println("Nenhum condutor registado.");
        } else {
            for (int i = 0; i < numCondutores; i++) {
                System.out.println(condutores.get(i).toString());
            }
            System.out.println("\nTotal: " + numCondutores + " condutor(es)");
        }
        pausar();
    }

    /**
     * Consulta os detalhes de um condutor específico pelo ID.
     */
    private void consultarCondutor() {
        limparEcra();
        System.out.println("=== CONSULTAR CONDUTOR ===\n");

        int id = lerInteiroPositivo("ID do condutor: ");
        Condutor c = gestao.procurarCondutorPorId(id);

        if (c != null) {
            System.out.println("\n" + c.toStringDetalhado());
        } else {
            System.out.println("\nCondutor nao encontrado!");
        }
        pausar();
    }

    /**
     * Altera os dados de um condutor existente.
     * Permite alterar campos individuais, mantendo os valores atuais se deixado em branco.
     * Realiza validações de duplicados para campos únicos (NIF, CC, etc.).
     */
    private void alterarCondutor() {
        limparEcra();
        System.out.println("=== ALTERAR CONDUTOR ===\n");

        int id = lerInteiroPositivo("ID do condutor: ");
        Condutor c = gestao.procurarCondutorPorId(id);

        if (c == null) {
            System.out.println("\nCondutor nao encontrado!");
            pausar();
            return;
        }

        System.out.println("\nDados atuais:");
        System.out.println(c.toStringDetalhado());
        System.out.println("\n(Deixe em branco para manter o valor atual)\n");

        // 1. NOME
        String nome = lerStringOpcional("Novo nome [" + c.getNome() + "]: ");
        if (!nome.isEmpty()) {
            if (Validador.validarComprimentoMinimo(nome, 3)) {
                c.setNome(nome);
            } else {
                System.out.println("Nome inválido (mínimo 3 caracteres). Valor mantido.");
            }
        }

        // 2. N. IDENTIFICAÇÃO (Com verificação de duplicado)
        boolean numIdValido = false;
        do {
            String numId = lerStringOpcional("Novo N. Identificacao [" + c.getNumeroIdentificacao() + "]: ");
            if (numId.isEmpty()) {
                numIdValido = true; // Mantém o antigo
            } else {
                if (!Validador.validarNumeroIdentificacao(numId)) {
                    System.out.println(Validador.getMensagemErroNumId());
                } else {
                    // Verificar se existe em OUTRO condutor (ignoramos se for o próprio)
                    Condutor existente = gestao.procurarCondutorPorNumeroIdentificacao(numId);
                    if (existente != null && existente.getId() != c.getId()) {
                        System.out.println("Erro: Este N. Identificacao ja pertence a outro condutor!");
                    } else {
                        c.setNumeroIdentificacao(numId);
                        numIdValido = true;
                    }
                }
            }
        } while (!numIdValido);

        // 3. CARTA DE CONDUÇÃO (Com verificação de duplicado)
        boolean cartaValida = false;
        do {
            String inputCarta = lerStringOpcional("Nova Carta de Conducao [" + c.getCartaConducao() + "]: ");
            if (inputCarta.isEmpty()) {
                cartaValida = true; // Mantém a antiga
            } else {
                if (!Validador.validarCartaConducao(inputCarta)) {
                    System.out.println(Validador.getMensagemErroCarta());
                } else {
                    // Formata temporariamente para verificar duplicados
                    String cartaFormatada = Validador.formatarCartaConducao(inputCarta);

                    Condutor existente = gestao.procurarCondutorPorCartaConducao(cartaFormatada);
                    if (existente != null && existente.getId() != c.getId()) {
                        System.out.println("Erro: Esta Carta de Conducao ja esta registada noutro condutor!");
                    } else {
                        // === AQUI: Guarda a versão formatada ===
                        c.setCartaConducao(cartaFormatada);
                        cartaValida = true;
                    }
                }
            }
        } while (!cartaValida);

        // 4. SEGURANÇA SOCIAL (Com verificação de duplicado)
        boolean nssValido = false;
        do {
            String nss = lerStringOpcional("Novo N. Seguranca Social [" + c.getNumeroSegurancaSocial() + "]: ");
            if (nss.isEmpty()) {
                nssValido = true;
            } else {
                if (!Validador.validarNss(nss)) {
                    System.out.println(Validador.gerMensagemErroNss());
                } else {
                    Condutor existente = gestao.procurarCondutorPorNss(nss);
                    if (existente != null && existente.getId() != c.getId()) {
                        System.out.println("Erro: Este NSS ja pertence a outro condutor!");
                    } else {
                        c.setNumeroSegurancaSocial(nss);
                        nssValido = true;
                    }
                }
            }
        } while (!nssValido);

        // 5. NIF (Com verificação de duplicado)
        boolean nifValido = false;
        do {
            String nif = lerStringOpcional("Novo NIF [" + c.getNif() + "]: ");
            if (nif.isEmpty()) {
                nifValido = true;
            } else {
                if (!Validador.validarNif(nif)) {
                    System.out.println(Validador.getMensagemErroNif());
                } else {
                    Condutor existente = gestao.procurarCondutorPorNif(nif);
                    if (existente != null && existente.getId() != c.getId()) {
                        System.out.println("Erro: Este NIF ja pertence a outro condutor!");
                    } else {
                        c.setNif(nif);
                        nifValido = true;
                    }
                }
            }
        } while (!nifValido);

        // 6. TELEMOVEL
        boolean telValido = false;
        do {
            String tel = lerStringOpcional("Novo Telemovel [" + c.getTelemovel() + "]: ");
            if (tel.isEmpty()) {
                telValido = true;
            } else {
                if (Validador.validarTelefone(tel)) {
                    c.setTelemovel(tel);
                    telValido = true;
                } else {
                    System.out.println(Validador.getMensagemErroTelefone());
                }
            }
        } while (!telValido);

        // 7. MORADA
        String morada = lerStringOpcional("Nova Morada [" + c.getMorada() + "]: ");
        if (!morada.isEmpty()) {
            if (Validador.validarComprimentoMinimo(morada, 5)) {
                c.setMorada(morada);
            } else {
                System.out.println(Validador.getMensagemErroMorada() + " Valor mantido.");
            }
        }

        System.out.println("\nCondutor atualizado com sucesso!");
        pausar();
    }

    /**
     * Remove um condutor do sistema.
     * Permite pesquisar por ID ou NIF e solicita confirmação.
     * Impede remoção se o condutor tiver viagens associadas.
     */
    private void removerCondutor() {
        limparEcra();
        System.out.println("=== REMOVER CONDUTOR ===\n");
        System.out.println("Procurar por:");
        System.out.println("1. ID");
        System.out.println("2. NIF");
        System.out.println("0. Cancelar");

        int opcao = lerInteiro("\nOpcao: ");
        Condutor c = null;

        if (opcao == 1) {
            int id = lerInteiroPositivo("ID do condutor: ");
            c = gestao.procurarCondutorPorId(id);
        } else if (opcao == 2) {
            String nif = lerString("NIF do condutor: ");
            c = gestao.procurarCondutorPorNif(nif);
        } else {
            return;
        }

        if (c == null) {
            System.out.println("\nCondutor não encontrado!");
            pausar();
            return;
        }

        // Mostrar resumo e confirmar
        System.out.println("\nVai remover o seguinte condutor:");
        System.out.println(c.toString());

        String confirmacao = lerString("\nTem a certeza que deseja remover este condutor? (S/N): ");

        if (confirmacao.equalsIgnoreCase("S")) {
            int resultado = gestao.removerCondutor(c.getId());

            switch (resultado) {
                case 0:
                    System.out.println("\nCondutor removido com sucesso!");
                    break;
                case -1:
                    System.out.println("\nErro: Condutor não encontrado.");
                    break;
                case -2:
                    System.out.println("\nNão é possível remover! O condutor tem viagens associadas.");
                    break;
            }
        } else {
            System.out.println("\nOperação cancelada.");
        }
        pausar();
    }

    /**
     * Apresenta o menu de gestão de Viaturas.
     */
    private void menuViaturas() {
        int opcao;
        do {
            limparEcra();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                   GESTAO DE VIATURAS                         ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Adicionar viatura                                        ║");
            System.out.println("║  2. Listar todas as viaturas                                 ║");
            System.out.println("║  3. Consultar viatura                                        ║");
            System.out.println("║  4. Alterar viatura                                          ║");
            System.out.println("║  5. Remover viatura                                          ║");
            System.out.println("║  0. Voltar                                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            opcao = lerInteiro("Opcao: ");

            switch (opcao) {
                case 1:
                    adicionarViatura();
                    break;
                case 2:
                    listarViaturas();
                    break;
                case 3:
                    consultarViatura();
                    break;
                case 4:
                    alterarViatura();
                    break;
                case 5:
                    removerViatura();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpcao invalida!");
                    pausar();
            }
        } while (opcao != 0);
    }

    /**
     * Adiciona uma nova viatura ao sistema.
     * Valida matrícula, marca, modelo, ano de fabrico, cor e lugares.
     */
    private void adicionarViatura() {
        limparEcra();
        System.out.println("=== ADICIONAR VIATURA ===\n");

        String marca = lerStringComValidacao("Marca da Viatura(mínimo 2 caracteres): ",2);

        String modelo = lerStringComValidacao("Modelo da Viatura(mínimo 1 caracteres): ",1);

        String matricula;
        do {
            matricula = lerString("Matrícula :");
            if (!Validador.validarMatricula(matricula)){
                System.out.println(Validador.getMensagemErroMatricula());
            }
            if (gestao.procurarViaturaPorMatricula(matricula) != null){
                System.out.println("\nJá existe uma viatura com esta matrícula.");
            }
        }while (!Validador.validarMatricula(matricula) || gestao.procurarViaturaPorMatricula(matricula) != null);

        matricula = Validador.formatarMatricula(matricula);

        int anoFabrico;
        do {
            anoFabrico = lerInteiro("Ano de Fabrico :");
            if (!Validador.validarAnoFabrico(anoFabrico)){
                System.out.println(Validador.getMensagemErroAno());
            }
        }while (!Validador.validarAnoFabrico(anoFabrico));

        String cor = lerStringComValidacao("Cor :",4);

        int lugares;
        do {
            lugares = lerInteiro("Lugares :");
            if (!Validador.validarLugares(lugares)){
                System.out.println(Validador.getMensagemErroLugares());
            }
        }while (!Validador.validarLugares(lugares));

        Viatura viatura = new Viatura(matricula,marca,modelo,anoFabrico,cor,lugares);

        if (gestao.adicionarViatura(viatura)){
            System.out.println("\nViatura adicionada com sucesso! (ID: " + viatura.getId() + ")");
        }else{
            System.out.println("\nErro ao adicionar viatura!");
        }
        pausar();
    }

    /**
     * Lista todas as viaturas registadas.
     */
    private void listarViaturas (){
        limparEcra();
        System.out.println("=== LISTA DE VIATURAS ===\n");

        ArrayList<Viatura> viaturas = gestao.getViaturas();
        int numViaturas = gestao.getNumeroViaturas();

        if (numViaturas == 0) {
            System.out.println("Nenhum condutor registado.");
        } else {
            for (int i = 0; i < numViaturas; i++) {
                System.out.println(viaturas.get(i).toString());
            }
            System.out.println("\nTotal: " + numViaturas + " condutor(es)");
        }
        pausar();
    }

    /**
     * Consulta os detalhes de uma viatura.
     * Permite pesquisar por ID ou Matrícula.
     */
    private void consultarViatura() {
        limparEcra();
        System.out.println("=== CONSULTAR VIATURA ===\n");

        System.out.println("Pesquisar por:");
        System.out.println("1. ID");
        System.out.println("2. Matrícula");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("\nOpção: ");
        Viatura viatura = null;

        switch (opcao) {
            case 1:
                int id = lerInteiroPositivo("ID da viatura: ");
                viatura = gestao.procurarViaturaPorId(id);
                break;
            case 2:
                String matricula = lerString("Matrícula (ex: AA-00-AA): ");
                // Formatar para garantir o formato correto (XX-XX-XX) antes de pesquisar
                matricula = Validador.formatarMatricula(matricula);
                viatura = gestao.procurarViaturaPorMatricula(matricula);
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida.");
                pausar();
                return;
        }

        if (viatura != null) {
            System.out.println("\nResultado da pesquisa:");
            System.out.println(viatura.toStringDetalhado());
        } else {
            System.out.println("\nViatura não encontrada!");
        }
        pausar();
    }

    /**
     * Altera os dados de uma viatura existente.
     * Verifica se a nova matrícula já existe noutra viatura.
     */
    private void alterarViatura() {
        limparEcra();
        System.out.println("=== ALTERAR VIATURA ===\n");

        int id = lerInteiroPositivo("ID da viatura: ");
        Viatura v = gestao.procurarViaturaPorId(id);

        if (v == null) {
            System.out.println("\nViatura nao encontrada!");
            pausar();
            return;
        }

        System.out.println("\nDados atuais:");
        System.out.println(v.toStringDetalhado());
        System.out.println("\n(Deixe em branco para manter o valor atual)\n");

        // 1. MARCA
        String marca = lerStringOpcional("Nova Marca [" + v.getMarca() + "]: ");
        if (!marca.isEmpty()) {
            if (Validador.validarComprimentoMinimo(marca, 2)) {
                v.setMarca(marca);
            } else {
                System.out.println("Marca invalida (minimo 2 caracteres). Valor mantido.");
            }
        }

        // 2. MODELO
        String modelo = lerStringOpcional("Novo Modelo [" + v.getModelo() + "]: ");
        if (!modelo.isEmpty()) {
            if (Validador.validarComprimentoMinimo(modelo, 1)) {
                v.setModelo(modelo);
            } else {
                System.out.println("Modelo invalido. Valor mantido.");
            }
        }

        // 3. MATRÍCULA (Com verificação de duplicados e formatação)
        boolean matriculaValida = false;
        do {
            String inputMatricula = lerStringOpcional("Nova Matricula [" + v.getMatricula() + "]: ");

            if (inputMatricula.isEmpty()) {
                matriculaValida = true; // Mantém a antiga
            } else {
                // Valida o formato (aceita aa00aa ou AA-00-AA)
                if (!Validador.validarMatricula(inputMatricula)) {
                    System.out.println(Validador.getMensagemErroMatricula());
                } else {
                    // Formata para verificar duplicados (transforma em AA-00-AA)
                    String matriculaFormatada = Validador.formatarMatricula(inputMatricula);

                    Viatura existente = gestao.procurarViaturaPorMatricula(matriculaFormatada);

                    // Se existe E não é a própria viatura que estamos a editar
                    if (existente != null && existente.getId() != v.getId()) {
                        System.out.println("Erro: Ja existe uma viatura com esta matricula!");
                    } else {
                        v.setMatricula(matriculaFormatada);
                        matriculaValida = true;
                    }
                }
            }
        } while (!matriculaValida);

        // 4. ANO DE FABRICO
        // Como o lerInteiro não aceita Enter vazio, lemos como String e tentamos converter
        String inputAno = lerStringOpcional("Novo Ano [" + v.getAnoFabrico() + "]: ");
        if (!inputAno.isEmpty()) {
            try {
                int novoAno = Integer.parseInt(inputAno);
                if (Validador.validarAnoFabrico(novoAno)) {
                    v.setAnoFabrico(novoAno);
                } else {
                    System.out.println(Validador.getMensagemErroAno() + " Valor mantido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ano invalido (tem de ser numero). Valor mantido.");
            }
        }

        // 5. COR
        String cor = lerStringOpcional("Nova Cor [" + v.getCor() + "]: ");
        if (!cor.isEmpty()) {
            if (Validador.validarComprimentoMinimo(cor, 4)) {
                v.setCor(cor);
            } else {
                System.out.println("Cor deve ter no minimo 4 caracteres. Valor mantido.");
            }
        }

        // 6. LUGARES
        String inputLugares = lerStringOpcional("Novos Lugares [" + v.getLugares() + "]: ");
        if (!inputLugares.isEmpty()) {
            try {
                int novosLugares = Integer.parseInt(inputLugares);
                if (Validador.validarLugares(novosLugares)) {
                    v.setLugares(novosLugares);
                } else {
                    System.out.println(Validador.getMensagemErroLugares() + " Valor mantido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Numero de lugares invalido. Valor mantido.");
            }
        }

        System.out.println("\nViatura atualizada com sucesso!");
        pausar();
    }

    /**
     * Remove uma viatura do sistema com confirmação.
     * Impede remoção se a viatura estiver associada a viagens ou reservas.
     */
    private void removerViatura() {
        limparEcra();
        System.out.println("=== REMOVER VIATURA ===\n");
        System.out.println("Procurar por:");
        System.out.println("1. ID");
        System.out.println("2. Matrícula");
        System.out.println("0. Cancelar");

        int opcao = lerInteiro("\nOpcao: ");
        Viatura v = null;

        if (opcao == 1) {
            int id = lerInteiroPositivo("ID da viatura: ");
            v = gestao.procurarViaturaPorId(id);
        } else if (opcao == 2) {
            String mat = lerString("Matrícula: ");
            String matFormatada = Validador.formatarMatricula(mat);
            v = gestao.procurarViaturaPorMatricula(matFormatada);
        } else {
            return;
        }

        if (v == null) {
            System.out.println("\nViatura não encontrada!");
            pausar();
            return;
        }

        // Mostrar resumo e confirmar
        System.out.println("\nVai remover a seguinte viatura:");
        System.out.println(v.toStringDetalhado());

        String confirmacao = lerString("\nTem a certeza que deseja remover esta viatura? (S/N): ");

        if (confirmacao.equalsIgnoreCase("S")) {
            int resultado = gestao.removerViatura(v.getId()); // Remove sempre pelo ID interno

            switch (resultado) {
                case 0:
                    System.out.println("\nViatura removida com sucesso!");
                    break;
                case -1:
                    System.out.println("\nViatura não encontrada!");
                    break;
                case -2:
                    System.out.println("\nERRO: Não é possível remover! A viatura tem viagens ou reservas associadas.");
                    break;
            }
        } else {
            System.out.println("\nOperação cancelada.");
        }
        pausar();
    }


    /**
     * Apresenta o menu de gestão de Clientes.
     */
    private void menuClientes() {
        int opcao;
        do {
            limparEcra();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                   GESTAO DE CLIENTES                         ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Adicionar cliente                                        ║");
            System.out.println("║  2. Listar todos os clientes                                 ║");
            System.out.println("║  3. Consultar cliente                                        ║");
            System.out.println("║  4. Alterar cliente                                          ║");
            System.out.println("║  5. Remover cliente                                          ║");
            System.out.println("║  0. Voltar                                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            opcao = lerInteiro("Opcao: ");

            switch (opcao) {
                case 1:
                    adicionarCliente();
                    break;
                case 2:
                    listarCliente();
                    break;
                case 3:
                    consultarCliente();
                    break;
                case 4:
                    alterarCliente();
                    break;
                case 5:
                    removerCliente();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpcao invalida!");
                    pausar();
            }
        } while (opcao != 0);
    }

    /**
     * Adiciona um novo cliente ao sistema.
     * Valida NIF e Email.
     */
    private void adicionarCliente() {
        limparEcra();
        System.out.println("=== ADICIONAR CLIENTE ===\n");

        // Nome (minimo 3 caracteres)
        String nome = lerStringComValidacao("Nome (minimo 3 caracteres): ",3);


        // NIF (9 digitos com validacao)
        String nif;
        do {
            nif = lerString("NIF (9 digitos): ");
            if (!Validador.validarNif(nif)){
                System.out.println(Validador.getMensagemErroNif());
            }
            // Verificar se NIF ja existe
            if (gestao.procurarClientePorNif(nif) != null) {
                System.out.println("\nJa existe um cliente com este NIF!");
            }
        }while (!Validador.validarNif(nif) || gestao.procurarClientePorNif(nif) != null);


        // Telemovel
        String tel;
        do {
            tel = lerString("Telemovel: ");
            if (!Validador.validarTelefone(tel)){
                System.out.println(Validador.getMensagemErroTelefone());
            }
        }while (!Validador.validarTelefone(tel));

        // Morada (minimo 5 caracteres)
        String morada = lerStringComValidacao("Morada (minimo 5 caracteres): ",5);

        // Email (verificar formato + unicidade)
        String email;
        boolean emailExiste;
        do {
            emailExiste = false;
            email = lerString("Email: ");

            email = Validador.formatarEmail(email);

            if (!Validador.validarEmail(email)) {
                System.out.println(Validador.getMensagemErroEmail());
            } else if (gestao.procurarClientePorEmail(email) != null) {
                System.out.println("Erro: Ja existe um cliente com este Email!");
                emailExiste = true;
            }
        } while (!Validador.validarEmail(email) || emailExiste);

        Cliente cliente = new Cliente(nome, nif, tel, morada, email);

        if (gestao.adicionarCliente(cliente)) {
            System.out.println("\nCliente adicionado com sucesso! (ID: " + cliente.getId() + ")");
        } else {
            System.out.println("\nErro ao adicionar cliente!");
        }

        pausar();
    }

    /**
     * Lista todos os clientes registados.
     */
    private void listarCliente() {
        limparEcra();
        System.out.println("=== LISTA DE CLIENTES ===\n");

        ArrayList<Cliente> clientes = gestao.getClientes();
        int numClientes = gestao.getNumeroClientes();

        if (numClientes == 0) {
            System.out.println("Nenhum cliente registado.");
        } else {
            for (int i = 0; i < numClientes; i++) {
                System.out.println(clientes.get(i).toString());
            }
            System.out.println("\nTotal: " + numClientes + " cliente(s)");
        }
        pausar();
    }

    /**
     * Consulta os detalhes de um cliente pelo ID.
     */
    private void consultarCliente() {
        limparEcra();
        System.out.println("=== CONSULTAR CLIENTE ===\n");

        int id = lerInteiroPositivo("ID do cliente: ");
        Cliente c = gestao.procurarClientePorId(id);

        if (c != null) {
            System.out.println("\n" + c.toStringDetalhado());
        } else {
            System.out.println("\nCliente nao encontrado!");
        }
        pausar();
    }

    /**
     * Altera os dados de um cliente existente.
     * Verifica duplicidade de NIF e Email ao alterar.
     */
    private void alterarCliente() {
        limparEcra();
        System.out.println("=== ALTERAR CLIENTE ===\n");

        int id = lerInteiroPositivo("ID do cliente: ");
        Cliente c = gestao.procurarClientePorId(id);

        if (c == null) {
            System.out.println("\nCliente nao encontrado!");
            pausar();
            return;
        }

        System.out.println("\nDados atuais:");
        System.out.println(c.toStringDetalhado());
        System.out.println("\n(Deixe em branco para manter o valor atual)\n");

        String nome = lerStringOpcional("Novo nome [" + c.getNome() + "]: ");
        if (!nome.isEmpty()){
            if(Validador.validarComprimentoMinimo(nome, 3)){
                c.setNome(nome);
            }else {
                System.out.println("Nome deve ter minimo 3 caracteres. Valor mantido.");
            }
        }

        //Verifica duplicados
        boolean nifValido = false;
        do {
            String inputNif = lerStringOpcional("Novo NIF [" + c.getNif() + "]: ");

            if (inputNif.isEmpty()) {
                nifValido = true; // Mantém o antigo
            } else {
                if (!Validador.validarNif(inputNif)) {
                    System.out.println(Validador.getMensagemErroNif());
                } else {
                    // Verificar se já existe noutro cliente
                    Cliente existente = gestao.procurarClientePorNif(inputNif);

                    if (existente != null && existente.getId() != c.getId()) {
                        System.out.println("Erro: Este NIF ja pertence a outro cliente!");
                    } else {
                        c.setNif(inputNif);
                        nifValido = true;
                    }
                }
            }
        } while (!nifValido);

        String tel = lerStringOpcional("Novo Telemovel [" + c.getTelemovel() + "]: ");
        if (!tel.isEmpty()) {
            if (Validador.validarTelefone(tel)) {
                c.setTelemovel(tel);
            } else {
                System.out.println(Validador.getMensagemErroTelefone() + " Valor mantido.");
            }
        }

        String morada = lerStringOpcional("Nova Morada [" + c.getMorada() + "]: ");
        if (!morada.isEmpty()) {
            if (Validador.validarComprimentoMinimo(morada, 5)) {
                c.setMorada(morada);
            } else {
                System.out.println(Validador.getMensagemErroMorada() + " Valor mantido");
            }
        }

        // VERIFICA DUPLICADOS
        boolean emailValido = false;
        do {
            String inputEmail = lerStringOpcional("Novo Email [" + c.getEmail() + "]: ");

            if (inputEmail.isEmpty()) {
                emailValido = true; // Mantém o antigo
            } else {

                inputEmail = Validador.formatarEmail(inputEmail);

                if (!Validador.validarEmail(inputEmail)) {
                    System.out.println(Validador.getMensagemErroEmail());
                } else {
                    // Verificar duplicados
                    Cliente existente = gestao.procurarClientePorEmail(inputEmail);

                    // Se existe E não é o próprio cliente que estamos a editar
                    if (existente != null && existente.getId() != c.getId()) {
                        System.out.println("Erro: Este Email ja pertence a outro cliente!");
                    } else {
                        c.setEmail(inputEmail);
                        emailValido = true;
                    }
                }
            }
        } while (!emailValido);
        System.out.println("\nCliente atualizado com sucesso!");
        pausar();
    }

    /**
     * Remove um cliente com confirmação.
     * Impede remoção se o cliente tiver histórico de viagens ou reservas.
     */
    private void removerCliente() {
        limparEcra();
        System.out.println("=== REMOVER CLIENTE ===\n");
        System.out.println("Procurar por:");
        System.out.println("1. ID");
        System.out.println("2. NIF");
        System.out.println("0. Cancelar");

        int opcao = lerInteiro("\nOpcao: ");
        Cliente c = null;

        if (opcao == 1) {
            int id = lerInteiroPositivo("ID do cliente: ");
            c = gestao.procurarClientePorId(id);
        } else if (opcao == 2) {
            String nif = lerString("NIF do cliente: ");
            c = gestao.procurarClientePorNif(nif);
        } else {
            return;
        }

        if (c == null) {
            System.out.println("\nCliente não encontrado!");
            pausar();
            return;
        }

        // Mostrar resumo e confirmar
        System.out.println("\nVai remover o seguinte cliente:");
        System.out.println(c.toString());

        String confirmacao = lerString("\nTem a certeza que deseja remover este cliente? (S/N): ");

        if (confirmacao.equalsIgnoreCase("S")) {
            int resultado = gestao.removerCliente(c.getId());

            switch (resultado) {
                case 0:
                    System.out.println("\nCliente removido com sucesso!");
                    break;
                case -1:
                    System.out.println("\nErro: Cliente não encontrado!");
                    break;
                case -2:
                    System.out.println("\nNão é possível remover! O cliente tem viagens ou reservas associadas.");
                    break;
            }
        } else {
            System.out.println("\nOperação cancelada.");
        }
        pausar();
    }

    /**
     * Apresenta o menu de gestão de Reservas.
     */
    private void menuReservas() {
        int opcao;
        do {
            limparEcra();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                   GESTAO DE RESERVAS                         ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Criar nova reserva                                       ║");
            System.out.println("║  2. Listar todos as reservas                                 ║");
            System.out.println("║  3. Consultar reservas                                       ║");
            System.out.println("║  4. Alterar reserva                                          ║");
            System.out.println("║  5. Cancelar reserva                                         ║");
            System.out.println("║  6. Remover reserva                                          ║");
            System.out.println("║  7. Converter reserva em viagem                              ║");
            System.out.println("║  0. Voltar                                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            opcao = lerInteiro("Opcao: ");

            switch (opcao) {
                case 1:
                    criarReserva();
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    consultarReserva();
                    break;
                case 4:
                    alterarReserva();
                    break;
                case 5:
                    cancelarReserva();
                    break;
                case 6:
                    removerReserva();
                    break;
                case 7:
                    converterReservaEmViagem();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpcao invalida!");
                    pausar();
            }
        } while (opcao != 0);
    }

    /**
     * Cria uma nova reserva de transporte.
     * Verifica disponibilidade da viatura para o intervalo de tempo selecionado.
     * Valida datas (devem ser futuras e fim posterior ao início).
     */
    private void criarReserva() {
        limparEcra();
        System.out.println("=== CRIAR RESERVA ===\n");

        if (gestao.getNumeroClientes() == 0 || gestao.getNumeroViaturas() == 0) {
            System.out.println("Faltam dados (Clientes ou Viaturas).");
            pausar();
            return;
        }

        // 1. Cliente e Viatura
        for (Cliente c : gestao.getClientes()) System.out.println(c.toString());
        int idCliente = lerInteiroPositivo("ID do Cliente: ");
        if (gestao.procurarClientePorId(idCliente) == null) { System.out.println("Cliente não encontrado."); pausar(); return; }

        for (Viatura v : gestao.getViaturas()) System.out.println(v.toString());
        int idViatura = lerInteiroPositivo("ID da Viatura: ");
        if (gestao.procurarViaturaPorId(idViatura) == null) { System.out.println("Viatura não encontrada."); pausar(); return; }

        // 2. Datas
        LocalDateTime inicio;
        do {
            inicio = lerDataHora("Data Inicio");
            if (inicio.isBefore(LocalDateTime.now())) {
                System.out.println("Erro: A reserva tem de ser feita para uma data futura!");
            }
        } while (inicio.isBefore(LocalDateTime.now()));

        LocalDateTime fim;
        do {
            fim = lerDataHora("Data Fim");
            if (fim.isBefore(inicio)) {
                System.out.println("A data de fim tem de ser posterior ao inicio.");
            }
        } while (fim.isBefore(inicio));

        // 3. Detalhes
        String origem = lerStringComValidacao("Morada Origem: ", 3);
        String destino = lerStringComValidacao("Morada Destino: ", 3);
        double kms = lerDoublePositivo("KMS: ");

        Reserva r = new Reserva(idCliente, idViatura, inicio, fim, origem, destino, kms);

        if (gestao.adicionarReserva(r)) {
            System.out.println("\nReserva criada com sucesso!");
        } else {
            System.out.println("\nErro: A viatura já está ocupada ou reservada neste horário.");
        }
        pausar();
    }

    /**
     * Lista todas as reservas existentes.
     */
    private void listarReservas (){
        limparEcra();
        System.out.println("=== LISTA DE RESERVAS ===\n");

        ArrayList<Reserva> reservas = gestao.getReservas();
        int numReservas = gestao.getNumeroReservas();

        if (numReservas == 0) {
            System.out.println("Nenhuma reserva registado.");
        } else {
            for (int i = 0; i < numReservas; i++) {
                System.out.println(reservas.get(i).toString());
            }
            System.out.println("\nTotal: " + numReservas + " reserva(s)");
        }
        pausar();
    }

    /**
     * Consulta os detalhes de uma reserva específica pelo ID.
     * Apresenta também o nome do cliente e dados da viatura associada.
     */
    private void consultarReserva() {
        limparEcra();
        System.out.println("=== CONSULTAR RESERVA ===\n");

        int id = lerInteiroPositivo("ID da reserva: ");
        Reserva reserva = gestao.procurarReservaPorId(id);

        if (reserva != null) {
            System.out.println("\n" + reserva.toStringDetalhado());

            //Mostrar nome do cliente
            Cliente cliente = gestao.procurarClientePorId(reserva.getIdCliente());
            if (cliente != null) {
                System.out.println("Cliente: " + cliente.getNome());
            }

            //Mostrar viatura
            if (reserva.getIdViatura() > 0) {
                Viatura viatura = gestao.procurarViaturaPorId(reserva.getIdViatura());
                if (viatura != null) {
                    System.out.println("Viatura: " + viatura.getMarca() + " " + viatura.getModelo() + " (" + viatura.getMatricula() + ")");
                }
            }
        } else {
            System.out.println("\nReserva nao encontrada!");
        }
        pausar();
    }

    /**
     * Permite alterar os dados de uma reserva existente.
     * Lista primeiro as reservas de um determinado cliente para facilitar a seleção.
     */
    private void alterarReserva() {
        limparEcra();
        System.out.println("=== RESERVAS DE UM CLIENTE ===\n");

        int idCliente = lerInteiroPositivo("ID do cliente: ");
        Cliente cliente = gestao.procurarClientePorId(idCliente);

        if (cliente == null) {
            System.out.println("\nCliente nao encontrado!");
            pausar();
            return;
        }

        System.out.println("\nCliente: " + cliente.getNome());
        System.out.println("\nReservas:");

        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        int numReservas = gestao.getReservasCliente(idCliente, reservas);

        if (numReservas == 0) {
            System.out.println("Nenhuma reserva encontrada para este cliente.");
            pausar();
            return;
        }

        for (int i = 0; i < numReservas; i++) {
            Reserva reserva = reservas.get(i);
            System.out.println((i + 1) + ". " + reserva.toString());
            System.out.println("   Morada de Origem: " + reserva.getMoradaOrigem());
            System.out.println("   Morada de Destino: " + reserva.getMoradaDestino());
        }

        System.out.println("\n0. Voltar");
        int opcao = lerInteiro("\nEscolha uma reserva para alterar (0 para voltar): ");

        if (opcao > 0 && opcao <= numReservas) {
            alterarReservaEspecifica(reservas.get(opcao - 1));
        }
    }

    /**
     * Metodo auxiliar para alterar uma reserva específica.
     * Permite alterar moradas e estimativa de Kms.
     *
     * @param reserva Objeto Reserva a ser editado
     */
    private void alterarReservaEspecifica(Reserva reserva) {
        limparEcra();
        System.out.println("=== ALTERAR RESERVA ===\n");
        System.out.println(reserva.toStringDetalhado());
        System.out.println("\n(Deixe em branco para manter o valor atual)\n");

        String origem = lerStringOpcional("Nova Morada de Origem [" + reserva.getMoradaOrigem() + "]: ");
        if (!origem.isEmpty()) {
            if (Validador.validarComprimentoMinimo(origem, 5)) {
                reserva.setMoradaOrigem(origem);
            } else {
                System.out.println("Morada de origem invalida (minimo 5 caracteres). Valor mantido.");
            }
        }

        String destino = lerStringOpcional("Nova Morada de Destino [" + reserva.getMoradaDestino() + "]: ");
        if (!destino.isEmpty()) {
            if (Validador.validarComprimentoMinimo(destino, 5)) {
                reserva.setMoradaDestino(destino);
            } else {
                System.out.println("Morada de destino invalida (minimo 5 caracteres). Valor mantido.");
            }
        }

        String kmsStr = lerStringOpcional("Nova Distancia Estimada (km) [" + reserva.getKms() + "]: ");
        if (!kmsStr.isEmpty()) {
            try {
                double kms = Double.parseDouble(kmsStr);
                if (Validador.validarKms(kms)) {
                    reserva.setKms(kms);
                } else {
                    System.out.println(Validador.getMensagemErroKms() + " Valor mantido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Distancia invalida. Valor mantido.");
            }
        }

        System.out.println("\nReserva atualizada com sucesso!");
        pausar();
    }

    /**
     * Cancela uma reserva, tornando-a inativa.
     * Requer confirmação do utilizador.
     */
    private void cancelarReserva() {
        limparEcra();
        System.out.println("=== CANCELAR RESERVA ===\n");

        int id = lerInteiroPositivo("ID da reserva: ");
        Reserva reserva = gestao.procurarReservaPorId(id);

        if (reserva == null) {
            System.out.println("\nReserva não encontrada!");
        } else if (!reserva.isAtiva()) {
            System.out.println("\nA reserva já se encontra cancelada ou concluída!");
        } else {
            System.out.println("\nVai cancelar a seguinte reserva:");
            System.out.println(reserva.toString());

            String confirmacao = lerString("\nTem a certeza que deseja cancelar esta reserva? (S/N): ");

            if (confirmacao.equalsIgnoreCase("S")) {
                reserva.setAtiva(false);
                System.out.println("\nReserva cancelada com sucesso!");
            } else {
                System.out.println("\nOperação anulada.");
            }
        }
        pausar();
    }

    /**
     * Remove permanentemente uma reserva dos registos.
     * Requer confirmação.
     */
    private void removerReserva() {
        limparEcra();
        System.out.println("=== REMOVER RESERVA ===\n");

        int id = lerInteiroPositivo("ID da reserva: ");

        Reserva r = gestao.procurarReservaPorId(id);

        if (r == null) {
            System.out.println("\nReserva não encontrada!");
            pausar();
            return;
        }

        System.out.println("\nVai apagar permanentemente a reserva:");
        System.out.println(r.toString());

        String confirmacao = lerString("\nTem a certeza que deseja apagar? (S/N): ");

        if (confirmacao.equalsIgnoreCase("S")) {
            if (gestao.removerReserva(id)) {
                System.out.println("\nReserva removida com sucesso!");
            } else {
                System.out.println("\nErro ao remover reserva!");
            }
        } else {
            System.out.println("\nOperação cancelada.");
        }

        pausar();
    }

    /**
     * Converte uma reserva ativa numa viagem efetiva.
     * Permite ao utilizador atribuir condutor, viatura (se não definida) e introduzir os dados finais (kms).
     * Calcula o custo final da viagem.
     */
    private void converterReservaEmViagem() {
        limparEcra();
        System.out.println("=== CONVERTER RESERVA EM VIAGEM ===\n");

        //Listar reservas ativas
        ArrayList<Reserva> reservas = gestao.getReservas();
        int numReservas = gestao.getNumeroReservas();
        ArrayList<Reserva> reservasAtivas = new ArrayList<Reserva>();
        int numReservasAtivas = 0;

        for (int i = 0; i < numReservas; i++) {
            Reserva reserva = reservas.get(i);
            if (reserva.isAtiva()) {
                reservasAtivas.add(reserva);
                numReservasAtivas++;
            }
        }

        if (numReservasAtivas == 0) {
            System.out.println("Nao existem reservas ativas.");
            pausar();
            return;
        }

        System.out.println("Reservas Ativas:");
        for (int i = 0; i < numReservasAtivas; i++) {
            Reserva reserva = reservasAtivas.get(i);
            Cliente cliente = gestao.procurarClientePorId(reserva.getIdCliente());
            String nomeCliente = (cliente != null) ? cliente.getNome() : "Desconhecido";
            System.out.println(reserva.toString() + " | Cliente: " + nomeCliente);
        }

        int idReserva = lerInteiroPositivo("\nID da reserva: ");
        Reserva reserva = gestao.procurarReservaPorId(idReserva);

        if (reserva == null || !reserva.isAtiva()) {
            System.out.println("\nReserva nao encontrada ou ja foi convertida/cancelada!");
            pausar();
            return;
        }

        //Verifica se a reserva tem viatura
        if (reserva.getIdViatura() == 0) {
            System.out.println("\nA reserva nao tem viatura associada. Selecione uma:");
            ArrayList<Viatura> viaturas = gestao.getViaturas();
            int numViaturas = gestao.getNumeroViaturas();
            for (int i = 0; i < numViaturas; i++) {
                System.out.println(viaturas.get(i).toString());
            }
            int idViatura = lerInteiroPositivo("ID da viatura: ");
            if (gestao.procurarViaturaPorId(idViatura) == null) {
                System.out.println("\nViatura nao encontrada!");
                pausar();
                return;
            }
            reserva.setIdViatura(idViatura);
        }

        // Selecionar condutor
        if (gestao.getNumeroCondutores() == 0) {
            System.out.println("\nNao existem condutores registados!");
            pausar();
            return;
        }

        System.out.println("\nCondutores disponiveis:");
        ArrayList<Condutor> condutores = gestao.getCondutores();
        int numCondutores = gestao.getNumeroCondutores();
        for (int i = 0; i < numCondutores; i++) {
            System.out.println(condutores.get(i).toString());
        }

        int idCondutor = lerInteiroPositivo("\nID do condutor: ");
        if (gestao.procurarCondutorPorId(idCondutor) == null) {
            System.out.println("\nCondutor nao encontrado!");
            pausar();
            return;
        }

        LocalDateTime dataFim;
        do {
            System.out.println("\nData e hora de fim da viagem:");
            // A mensagem dentro do lerDataHora pode ser ajustada para ser mais clara
            dataFim = lerDataHora("Data/Hora Fim");

            // Verifica se a data de fim é ANTERIOR à de início
            if (dataFim.isBefore(reserva.getDataHoraInicio())) {
                System.out.println("ERRO: A data de fim não pode ser anterior ao início da reserva (" +
                        reserva.getDataHoraInicio().format(data) + ")!");
            }
            // Repete enquanto a data for inválida (anterior ao início)
        } while (dataFim.isBefore(reserva.getDataHoraInicio()));


        double kmsReais;
        do {
            kmsReais = lerDoublePositivo("Kms reais percorridos: ");
            if (!Validador.validarKms(kmsReais)) {
                System.out.println(Validador.getMensagemErroKms());
            }
        } while (!Validador.validarKms(kmsReais));

        Viagem viagem = gestao.converterReservaEmViagem(idReserva, idCondutor, dataFim, kmsReais);

        if (viagem != null) {
            System.out.println("\nViagem criada com sucesso!");
            System.out.println("ID: " + viagem.getId());
            System.out.println("Custo: " + String.format("%.2f", viagem.getCusto()) + " EUR");
        } else {
            System.out.println("\nErro ao converter reserva!");
        }

        pausar();
    }

    /**
     * Apresenta o menu de gestão de Viagens.
     */
    private void menuViagens() {
        int opcao;
        do {
            limparEcra();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                   GESTÃO DE VIAGENS                          ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Adicionar viagem                                         ║");
            System.out.println("║  2. Listar todas as viagens                                  ║");
            System.out.println("║  3. Consultar viagem                                         ║");
            System.out.println("║  4. Alterar viagem                                           ║");
            System.out.println("║  5. Remover viagem                                           ║");
            System.out.println("║  0. Voltar                                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            opcao = lerInteiro("Opcao: ");

            switch (opcao) {
                case 1:
                    adicionarViagem();
                    break;
                case 2:
                    listarViagens();
                    break;
                case 3:
                    consultarViagem();
                    break;
                case 4:
                    alterarViagem();
                    break;
                case 5:
                    removerViagem();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpcao invalida!");
                    pausar();
            }
        } while (opcao != 0);
    }

    /**
     * Adiciona uma nova viagem diretamente (sem reserva prévia).
     * Solicita todos os intervenientes (Condutor, Cliente, Viatura) e valida horários.
     * Calcula o custo automaticamente.
     */
    private void adicionarViagem() {
        limparEcra();
        System.out.println("=== ADICIONAR VIAGEM ===\n");

        if (gestao.getNumeroCondutores() == 0 || gestao.getNumeroViaturas() == 0 || gestao.getNumeroClientes() == 0) {
            System.out.println("Faltam dados (Condutores, Viaturas ou Clientes) para criar viagem.");
            pausar();
            return;
        }

        // 1. Condutor
        System.out.println("Condutores disponiveis:");
        for (Condutor c : gestao.getCondutores()) System.out.println(c.toString());
        int idCondutor = lerInteiroPositivo("\nID do condutor: ");
        if (gestao.procurarCondutorPorId(idCondutor) == null) { System.out.println("Não encontrado!"); pausar(); return; }

        // 2. Viatura
        System.out.println("\nViaturas disponiveis:");
        for (Viatura v : gestao.getViaturas()) System.out.println(v.toString());
        int idViatura = lerInteiroPositivo("\nID da viatura: ");
        if (gestao.procurarViaturaPorId(idViatura) == null) { System.out.println("Não encontrada!"); pausar(); return; }

        // 3. Cliente
        System.out.println("\nClientes disponiveis:");
        for (Cliente c : gestao.getClientes()) System.out.println(c.toString());
        int idCliente = lerInteiroPositivo("\nID do cliente: ");
        if (gestao.procurarClientePorId(idCliente) == null) { System.out.println("Não encontrado!"); pausar(); return; }

        // 4. Datas (COM VALIDAÇÃO DE FUTURO)
        LocalDateTime inicio;
        do {
            inicio = lerDataHora("Data/Hora inicio");
            if (inicio.isBefore(LocalDateTime.now())) {
                System.out.println("Erro: A data de inicio tem de ser superior à data atual (futuro)!");
            }
        } while (inicio.isBefore(LocalDateTime.now()));

        LocalDateTime fim;
        do {
            fim = lerDataHora("Data/Hora Fim");
            if (fim.isBefore(inicio)) {
                System.out.println("A Data/Hora de fim não pode ser anterior ao inicio!");
            }
        } while(fim.isBefore(inicio));

        // 5. Detalhes
        String origem = lerStringComValidacao("Morada Origem: ", 3);
        String destino = lerStringComValidacao("Morada Destino: ", 3);
        double kms = lerDoublePositivo("Distância (kms): ");
        double custo = gestao.calcularCustoViagem(kms); // Custo automático

        System.out.println("Custo calculado: " + String.format("%.2f", custo) + " EUR");

        Viagem novaViagem = new Viagem(idCondutor, idCliente, idViatura, inicio, fim, origem, destino, kms, custo);

        if (gestao.adicionarViagem(novaViagem)){
            System.out.println("\nViagem registada com sucesso!");
        } else {
            System.out.println("\nErro: Sobreposição de horário (viatura ou condutor ocupados)!");
        }
        pausar();
    }

    /**
     * Menu de listagem de viagens com opções de filtro.
     * Permite listar todas ou filtrar por Cliente, Condutor ou Viatura.
     */
    private void listarViagens() {
        limparEcra();
        System.out.println("=== LISTAR VIAGENS ===\n");
        System.out.println("1. Listar TODAS as viagens");
        System.out.println("2. Filtrar por Cliente");
        System.out.println("3. Filtrar por Condutor");
        System.out.println("4. Filtrar por Viatura");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("\nOpcao: ");

        switch (opcao) {
            case 1:
                listarTodasViagens();
                break;
            case 2:
                listarViagensCliente();
                break;
            case 3:
                listarViagensCondutor();
                break;
            case 4:
                listarViagensViatura();
                break;
            case 0:
                return;
            default:
                System.out.println("Opcao invalida.");
                pausar();
        }
    }

    /**
     * Lista todas as viagens registadas sem filtros.
     */
    private void listarTodasViagens() {
        limparEcra();
        System.out.println("\n--- TODAS AS VIAGENS ---");
        imprimirListaViagens(gestao.getViagens());
        pausar();
    }

    /**
     * Lista viagens de um cliente específico.
     * Permite sub-filtro por intervalo de datas.
     */
    private void listarViagensCliente() {
        limparEcra();
        int idCliente = lerInteiroPositivo("\nID do Cliente: ");
        Cliente c = gestao.procurarClientePorId(idCliente);

        if (c == null) {
            System.out.println("Cliente nao encontrado.");
            pausar();
            return;
        }

        System.out.println("\n--- Viagens do Cliente: " + c.getNome() + " ---");
        System.out.println("1. Ver todas as viagens");
        System.out.println("2. Filtrar por intervalo de datas");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("\nOpcao: ");

        ArrayList<Viagem> lista = new ArrayList<>();

        if (opcao == 1) {
            gestao.getViagensCliente(idCliente, lista);

        } else if (opcao == 2) {
            System.out.println("\nDefina o intervalo:");
            LocalDateTime inicio = lerDataHora("Data Inicio ");
            LocalDateTime fim = lerDataHora("Data Fim ");

            if (inicio.isAfter(fim)) {
                System.out.println("\nErro: A data de inicio nao pode ser superior a data de fim.");
                pausar();
                return;
            }

            gestao.getViagensClienteEntreDatas(idCliente, inicio, fim, lista);

        } else if (opcao == 0) {
            return;
        } else {
            System.out.println("Opcao invalida.");
            pausar();
            return;
        }

        imprimirListaViagens(lista);
        pausar();
    }

    /**
     * Lista viagens efetuadas por um condutor específico.
     */
    private void listarViagensCondutor() {
        limparEcra();
        int idCondutor = lerInteiroPositivo("\nID do Condutor: ");
        Condutor c = gestao.procurarCondutorPorId(idCondutor);

        if (c == null) {
            System.out.println("Condutor nao encontrado.");
            pausar();
            return;
        }

        System.out.println("\n--- Viagens do Condutor: " + c.getNome() + " ---");
        imprimirListaViagens(gestao.getViagensPorCondutor(idCondutor));
        pausar();
    }

    /**
     * Lista viagens efetuadas numa viatura específica.
     * Permite pesquisar a viatura por ID ou Matrícula.
     */
    private void listarViagensViatura() {
        limparEcra();
        System.out.println("=== VIAGENS POR VIATURA ===\n");
        System.out.println("Pesquisar viatura por:");
        System.out.println("1. ID");
        System.out.println("2. Matrícula");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("\nOpcao: ");
        Viatura viatura = null;

        if (opcao == 1) {
            int idViatura = lerInteiroPositivo("ID da Viatura: ");
            viatura = gestao.procurarViaturaPorId(idViatura);

        } else if (opcao == 2) {
            String inputMatricula = lerString("Matrícula: ");

            String matriculaFormatada = Validador.formatarMatricula(inputMatricula);

            viatura = gestao.procurarViaturaPorMatricula(matriculaFormatada);

        } else if (opcao == 0) {
            return;
        } else {
            System.out.println("Opcao invalida.");
            pausar();
            return;
        }

        if (viatura == null) {
            System.out.println("Viatura nao encontrada.");
            pausar();
            return;
        }

        System.out.println("\n--- Viagens da Viatura: " + viatura.getMatricula() +
                " (" + viatura.getMarca() + " " + viatura.getModelo() + ") ---");

        ArrayList<Viagem> lista = gestao.getViagensPorViatura(viatura.getId());

        imprimirListaViagens(lista);
        pausar();
    }


    /**
     * Consulta os detalhes de uma viagem específica.
     */
    private void consultarViagem() {
        limparEcra();
        System.out.println("=== CONSULTAR VIAGEM ===\n");

        int id = lerInteiroPositivo("ID da viagem: ");
        Viagem v = gestao.procurarViagemPorId(id);

        if (v != null) {
            System.out.println("\n" + v.toStringDetalhado());
        } else {
            System.out.println("\nViagem nao encontrada!");
        }
        pausar();
    }

    /**
     * Altera os dados de uma viagem existente.
     * Valida datas e calcula outra vez o custo se a distância for alterada.
     * Verifica sobreposição de horários ao alterar as datas.
     */
    private void alterarViagem() {
        limparEcra();
        System.out.println("=== ALTERAR VIAGEM ===\n");

        int id = lerInteiroPositivo("ID da viagem: ");
        Viagem v = gestao.procurarViagemPorId(id);

        if (v == null) {
            System.out.println("\nErro: Viagem não encontrada!");
            pausar();
            return;
        }

        System.out.println("\nDados atuais: " + v.toStringDetalhado());
        System.out.println("(Deixe em branco para manter o valor atual)\n");

        // --- 1. DATA INICIO (COM VALIDAÇÃO) ---
        LocalDateTime novoInicio = v.getDataHoraInicio();
        boolean inicioValido = false;
        do {
            String input = lerStringOpcional("Nova Data Inicio [" + v.getDataHoraInicio().format(data) + "]: ");
            if (input.isEmpty()) {
                inicioValido = true;
            } else {
                try {
                    LocalDateTime temp = LocalDateTime.parse(input, data);
                    // Validação de data futura
                    if (temp.isBefore(LocalDateTime.now())) {
                        System.out.println("Erro: A nova data não pode ser anterior ao momento atual.");
                    } else {
                        novoInicio = temp;
                        inicioValido = true;
                    }
                } catch (Exception e) {
                    System.out.println("Formato inválido! Use dd-MM-yyyy HH:mm");
                }
            }
        } while (!inicioValido);

        // --- 2. DATA FIM ---
        LocalDateTime novoFim = v.getDataHoraFim();
        boolean fimValido = false;
        do {
            String input = lerStringOpcional("Nova Data Fim [" + v.getDataHoraFim().format(data) + "]: ");
            if (input.isEmpty()) {
                if (v.getDataHoraFim().isAfter(novoInicio)) {
                    novoFim = v.getDataHoraFim();
                    fimValido = true;
                } else {
                    System.out.println("Erro: Data de fim antiga inválida para o novo inicio.");
                }
            } else {
                try {
                    LocalDateTime tempFim = LocalDateTime.parse(input, data);
                    if (tempFim.isAfter(novoInicio)) {
                        novoFim = tempFim;
                        fimValido = true;
                    } else {
                        System.out.println("A data de fim deve ser posterior à data de inicio.");
                    }
                } catch (Exception e) {
                    System.out.println("Formato inválido!");
                }
            }
        } while (!fimValido);

        // Verifica Sobreposição e aplica datas
        if (!novoInicio.isEqual(v.getDataHoraInicio()) || !novoFim.isEqual(v.getDataHoraFim())) {
            if (gestao.existeSobreposicaoViagem(v.getIdViatura(), v.getIdCondutor(), novoInicio, novoFim, v.getId())) {
                System.out.println("\nERRO: Conflito de horário! Datas não alteradas.");
                novoInicio = v.getDataHoraInicio();
                novoFim = v.getDataHoraFim();
            } else {
                v.setDataHoraInicio(novoInicio);
                v.setDataHoraFim(novoFim);
            }
        }

        // Resto das edições (Origem, Destino, Kms + Custo Auto)
        String origem = lerStringOpcional("Nova Origem [" + v.getMoradaOrigem() + "]: ");
        if (!origem.isEmpty() && Validador.validarComprimentoMinimo(origem, 3)) v.setMoradaOrigem(origem);

        String destino = lerStringOpcional("Novo Destino [" + v.getMoradaDestino() + "]: ");
        if (!destino.isEmpty() && Validador.validarComprimentoMinimo(destino, 3)) v.setMoradaDestino(destino);

        String inputKms = lerStringOpcional("Novos Kms [" + v.getKms() + "]: ");
        if (!inputKms.isEmpty()) {
            try {
                double kms = Double.parseDouble(inputKms);
                if (Validador.validarKms(kms)) {
                    v.setKms(kms);
                    double novoCusto = gestao.calcularCustoViagem(kms);
                    v.setCusto(novoCusto);
                    System.out.println(" -> Custo recalculado: " + String.format("%.2f", novoCusto) + " EUR");
                }
            } catch (Exception e) { System.out.println("Valor inválido."); }
        }

        System.out.println("\nViagem atualizada!");
        pausar();
    }

    /**
     * Remove uma viagem do sistema com confirmação.
     */
    private void removerViagem() {
        limparEcra();
        System.out.println("=== REMOVER VIAGEM ===\n");

        int id = lerInteiroPositivo("ID da viagem: ");

        Viagem v = gestao.procurarViagemPorId(id);

        if (v != null) {
            System.out.println("\nVai remover o seguinte registo de viagem:");
            System.out.println(v.toString());

            String confirmacao = lerString("\nTem a certeza que deseja remover esta viagem? (S/N): ");

            if (confirmacao.equalsIgnoreCase("S")) {
                gestao.removerViagem(id);
                System.out.println("\nViagem removida com sucesso.");
            } else {
                System.out.println("\nOperação cancelada.");
            }
        } else {
            System.out.println("\nViagem não encontrada.");
        }
        pausar();
    }


    /**
     * Apresenta o menu de Relatórios e Estatísticas.
     */
    private void menuRelStats() {
        int opcao;
        do {
            limparEcra();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║             MENU DE RELATÓRIOS E ESTATÍSTICAS                ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Clientes que utilizaram uma viatura                      ║");
            System.out.println("║  2. Faturacao de um condutor (Intervalo de datas)            ║");
            System.out.println("║  3. Distância média de viagens (Intervalo de datas)          ║");
            System.out.println("║  4. Destino mais solicitado (Intervalo de datas)             ║");
            System.out.println("║  5. Clientes com viagens dentro de intervalo (kms ou datas)  ║");
            System.out.println("║  0. Voltar                                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            opcao = lerInteiro("Opcao: ");

            switch (opcao) {
                case 1:
                    relatorioClientesPorViatura();
                    break;
                case 2:
                    relatorioFaturacaoCondutor();
                    break;
                case 3:
                    relatorioDistanciaMedia();
                    break;
                case 4:
                    relatorioDestinoMaisSolicitado();
                    break;
                case 5:
                    relatorioClientesEmIntervalo();
                    pausar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpcao invalida!");
                    pausar();
            }
        } while (opcao != 0);
    }

    /**
     * Gera relatório: Clientes que utilizaram uma determinada viatura.
     */
    private void relatorioClientesPorViatura() {
        limparEcra();
        System.out.println("=== CLIENTES POR VIATURA ===\n");

        String matricula = lerString("Indique a matricula da viatura: ");
        // Formatar para garantir que bate certo (ex: aa-00-aa -> AA-00-AA)
        matricula = Validador.formatarMatricula(matricula);

        if (gestao.procurarViaturaPorMatricula(matricula) == null) {
            System.out.println("Erro: Viatura nao encontrada.");
            pausar();
            return;
        }

        ArrayList<Cliente> lista = gestao.getClientesPorViatura(matricula);

        if (lista.isEmpty()) {
            System.out.println("\nEsta viatura ainda nao foi utilizada por nenhum cliente.");
        } else {
            System.out.println("\nClientes que utilizaram a viatura " + matricula + ":");
            System.out.println("--------------------------------------------------");
            for (Cliente c : lista) {
                System.out.println("- " + c.getNome() + " (NIF: " + c.getNif() + ")");
            }
            System.out.println("--------------------------------------------------");
            System.out.println("Total: " + lista.size() + " cliente(s).");
        }
        pausar();
    }

    /**
     * Gera relatório: Total faturado por um condutor num intervalo de tempo.
     */
    private void relatorioFaturacaoCondutor() {
        limparEcra();
        System.out.println("=== FATURACAO DE CONDUTOR ===\n");

        int id = lerInteiroPositivo("ID do Condutor: ");
        Condutor condutor = gestao.procurarCondutorPorId(id);

        if (condutor == null) {
            System.out.println("Erro: Condutor nao encontrado.");
            pausar();
            return;
        }

        System.out.println("\nDefina o intervalo de tempo:");
        // Usa o teu metodo auxiliar lerDataHora
        LocalDateTime inicio = lerDataHora("Data de Inicio ");
        LocalDateTime fim = lerDataHora("Data de Fim ");

        if (inicio.isAfter(fim)) {
            System.out.println("\nErro: A data de inicio nao pode ser superior a data de fim.");
            pausar();
            return;
        }

        double total = gestao.calcularFaturacaoCondutor(id, inicio, fim);

        System.out.println("\nResultados para o condutor: " + condutor.getNome());
        System.out.println("Periodo: " + inicio.format(data) + " ate " + fim.format(data));
        System.out.println("--------------------------------------------------");
        System.out.printf("Total Faturado: %.2f €\n", total); // %.2f formata para 2 casas decimais
        System.out.println("--------------------------------------------------");

        pausar();
    }

    /**
     * Gera relatório: Distância média de todas as viagens num intervalo de tempo.
     */
    private void relatorioDistanciaMedia() {
        limparEcra();
        System.out.println("=== DISTÂNCIA MÉDIA POR VIAGEM ===\n");

        System.out.println("Defina o intervalo de tempo:");
        LocalDateTime inicio = lerDataHora("Data de Inicio ");
        LocalDateTime fim = lerDataHora("Data de Fim ");

        if (inicio.isAfter(fim)) {
            System.out.println("\nErro: A data de inicio nao pode ser superior a data de fim.");
            pausar();
            return;
        }

        double media = gestao.getDistanciaMediaViagens(inicio, fim);

        System.out.println("\n--------------------------------------------------");
        if (media == 0) {
            System.out.println("Nao foram encontradas viagens neste periodo.");
        } else {
            System.out.printf("A distância média das viagens foi: %.2f Kms\n", media);
        }
        System.out.println("--------------------------------------------------");
        pausar();
    }

    /**
     * Gera relatório: Destino mais frequente num intervalo de tempo.
     */
    private void relatorioDestinoMaisSolicitado() {
        limparEcra();
        System.out.println("=== DESTINO MAIS SOLICITADO ===\n");

        System.out.println("Defina o intervalo de tempo:");
        LocalDateTime inicio = lerDataHora("Data de Inicio ");
        LocalDateTime fim = lerDataHora("Data de Fim ");

        if (inicio.isAfter(fim)) {
            System.out.println("\nErro: A data de inicio nao pode ser superior a data de fim.");
            pausar();
            return;
        }

        String resultado = gestao.getDestinoMaisSolicitado(inicio, fim);

        System.out.println("\n--------------------------------------------------");
        if (resultado == null) {
            System.out.println("Nao foram encontrados registos neste periodo.");
        } else {
            System.out.println("O destino mais popular foi:");
            System.out.println("-> " + resultado);
        }
        System.out.println("--------------------------------------------------");
        pausar();
    }

    /**
     * Gera relatório: Lista de clientes com filtros dinâmicos (por datas ou por distância de viagens).
     */
    private void relatorioClientesEmIntervalo() {
        limparEcra();
        System.out.println("=== LISTA DE CLIENTES COM VIAGENS ===\n");

        System.out.println("Deseja filtrar por:");
        System.out.println("1. Intervalo de Datas");
        System.out.println("2. Intervalo de KMs (Distancia)");
        System.out.println("0. Voltar");

        int opcao = lerInteiro("\nOpcao: ");

        // Vamos guardar a lista de resultados aqui
        ArrayList<Cliente> lista = new ArrayList<>();
        String criterio = ""; // Para usar no print final

        if (opcao == 1) {
            // --- FILTRO POR DATAS ---
            System.out.println("\n--- Filtro por Datas ---");
            LocalDateTime inicio = lerDataHora("Data de Inicio ");
            LocalDateTime fim = lerDataHora("Data de Fim ");

            if (inicio.isAfter(fim)) {
                System.out.println("\nErro: A data de inicio nao pode ser superior a data de fim.");
                pausar();
                return;
            }

            lista = gestao.getClientesComViagensEntreDatas(inicio, fim);
            criterio = "no intervalo de datas";

        } else if (opcao == 2) {
            // --- FILTRO POR KMs (Lógica antiga) ---
            System.out.println("\n--- Filtro por Distancia ---");
            double min = lerDoublePositivo("Distancia Minima (Km): ");
            double max = lerDoublePositivo("Distancia Maxima (Km): ");

            if (min > max) {
                System.out.println("\nErro: O minimo nao pode ser maior que o maximo.");
                pausar();
                return;
            }

            lista = gestao.getClientesComViagensEntreKms(min, max);
            criterio = "com viagens entre " + min + " e " + max + " Kms";

        } else if (opcao == 0) {
            return;
        } else {
            System.out.println("Opcao invalida.");
            pausar();
            return;
        }

        // --- APRESENTAÇÃO DOS RESULTADOS (Comum aos dois) ---
        System.out.println("\nClientes encontrados " + criterio + ":");
        System.out.println("--------------------------------------------------");

        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente encontrado com estes criterios.");
        } else {
            for (Cliente c : lista) {
                System.out.println("- " + c.getNome() + " (NIF: " + c.getNif() + ")");
            }
            System.out.println("--------------------------------------------------");
            System.out.println("Total: " + lista.size() + " cliente(s).");
        }

        pausar();
    }


    /**
     * Apresenta o menu de gestão de Ficheiros.
     * Permite guardar ou carregar manualmente o estado da aplicação.
     */
    private void menuFicheiros() {
        int opcao;
        do {
            limparEcra();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                   GESTAO DE FICHEIROS                        ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Gravar dados (Memoria -> Ficheiro)                       ║");
            System.out.println("║  2. Carregar dados (Ficheiro -> Memoria)                     ║");
            System.out.println("║  0. Voltar                                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            opcao = lerInteiro("Opcao: ");

            switch (opcao) {
                case 1:
                    limparEcra();
                    System.out.println("=== GRAVAR DADOS ===\n");
                    try {
                        gestorFicheiros.guardarTudo(gestao);
                        System.out.println("\nDados gravados com sucesso!");
                    } catch (java.io.IOException e) {
                        System.out.println("\nErro ao gravar ficheiros: " + e.getMessage());
                    }
                    pausar();
                    break;
                case 2:
                    limparEcra();
                    System.out.println("=== CARREGAR DADOS ===\n");
                    System.out.println("ATENCAO: Isto ira substituir os dados atuais pelos do ficheiro.");

                    try {
                        // "empresa" é o nome da pasta padrão.
                        // O lerTudo devolve uma nova gestão, por isso atualizamos o "this.gestao"
                        this.gestao = gestorFicheiros.lerTudo(this.gestao.getNomeEmpresa());

                        System.out.println("\nDados carregados com sucesso!");
                    } catch (java.io.IOException e) {
                        System.out.println("\nErro ao ler ficheiros: " + e.getMessage());
                        System.out.println("Verifica se a pasta 'dados/empresa' existe e tem os ficheiros .txt");
                    }

                    pausar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpcao invalida!");
                    pausar();
            }
        } while (opcao != 0);
    }

    /**
     * Menu de saída.
     * Pergunta ao utilizador se deseja guardar as alterações antes de terminar a execução.
     */
    private void menuSair () {
        String resposta = lerString("Deseja guardar as alterações antes de sair? (S/N): ");

        // Verifica se a resposta é "S" ou "s" (ignora maiúsculas/minúsculas)
        if (resposta.equalsIgnoreCase("S")) {
            try {
                System.out.println("A guardar dados...");
                gestorFicheiros.guardarTudo(gestao);
                System.out.println("Dados guardados com sucesso!");
            } catch (java.io.IOException e) {
                System.out.println("ERRO: Não foi possível guardar os dados: " + e.getMessage());
            }
        } else if (resposta.equalsIgnoreCase("N")){
            System.out.println("A sair sem guardar alterações...");
        }
    }

    // ==================== METODOS AUXILIARES ====================

    /**
     * Le um numero inteiro do utilizador, garantindo que é um valor numérico válido.
     *
     * @param mensagem Mensagem a apresentar antes de pedir o valor
     * @return Numero inteiro lido
     */
    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor invalido. " + mensagem);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        return valor;
    }

    /**
     * Le um numero inteiro positivo do utilizador (> 0).
     *
     * @param mensagem Mensagem a apresentar
     * @return Numero inteiro positivo lido
     */
    private int lerInteiroPositivo(String mensagem) {
        int valor;
        do {
            valor = lerInteiro(mensagem);
            if (valor <= 0) {
                System.out.println("O valor deve ser maior que zero.");
            }
        } while (valor <= 0);
        return valor;
    }

    /**
     * Le um numero decimal do utilizador.
     *
     * @param mensagem Mensagem a apresentar
     * @return Numero decimal lido
     */
    private double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor invalido. " + mensagem);
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        return valor;
    }

    /**
     * Le um numero decimal positivo do utilizador.
     *
     * @param mensagem Mensagem a apresentar
     * @return Numero decimal positivo lido
     */
    private double lerDoublePositivo(String mensagem) {
        double valor;
        do {
            valor = lerDouble(mensagem);
            if (valor <= 0) {
                System.out.println("O valor deve ser maior que zero.");
            }
        } while (valor <= 0);
        return valor;
    }

    /**
     * Le uma string do utilizador (obrigatorio ser - não vazia).
     *
     * @param mensagem Mensagem a apresentar
     * @return String lida e limpa de espaços extra
     */
    private String lerString(String mensagem) {
        String valor;
        do {
            System.out.print(mensagem);
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("Este campo e obrigatorio.");
            }
        } while (valor.isEmpty());
        return valor;
    }

    /**
     * Le uma string do utilizador garantindo um comprimento mínimo.
     *
     * @param mensagem Mensagem a apresentar
     * @param minimo Numero mínimo de caracteres exigido
     * @return String válida lida
     */
    private String lerStringComValidacao(String mensagem, int minimo) {
        String valor;
        do {
            valor = lerString(mensagem);
            if (!Validador.validarComprimentoMinimo(valor, minimo)) {
                System.out.println("Deve ter pelo menos " + minimo + " caracteres.");
            }
        } while (!Validador.validarComprimentoMinimo(valor, minimo));
        return valor;
    }


    /**
     * Le uma string do utilizador (opcional). Aceita inputs vazios.
     *
     * @param mensagem Mensagem a apresentar
     * @return String lida (pode ser vazia)
     */
    private String lerStringOpcional(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    /**
     * Pausa a execucao do programa ate o utilizador pressionar Enter.
     */
    private void pausar() {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Limpa o ecra da consola.
     */
    private void limparEcra() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Lê uma data e hora no formato dd-MM-yyyy HH:mm.
     * Garante que o utilizador introduz um formato válido.
     *
     * @param mensagem Texto a apresentar (Ex: "Data de Inicio")
     * @return Objeto LocalDateTime válido
     */
    private LocalDateTime lerDataHora(String mensagem){
        while (true) {
            try {
                String dataHora = lerString(mensagem + "(dd-MM-yyyy HH:mm): ");
                return LocalDateTime.parse(dataHora, data);
            } catch (Exception erro) {
                System.out.println("Erro: Formato inválido! Use o formato: dia-mês-ano hora:minutos (Ex: 15-01-2026 14:30)");
            }
        }
    }

    /**
     * Imprime uma linha de menu com o texto centrado entre as bordas '║'.
     * A largura interna fixa é de 62 caracteres.
     *
     * @param texto Texto a centrar
     */
    private void imprimirLinhaCentrada(String texto) {
        int larguraTotal = 62; // Largura interna entre as barras ║

        if (texto == null) texto = "";

        // Se o texto for maior que a largura, corta para não estragar o menu
        if (texto.length() > larguraTotal) {
            texto = texto.substring(0, larguraTotal);
        }

        int espacosVazios = larguraTotal - texto.length();
        int espacosEsquerda = espacosVazios / 2;
        int espacosDireita = espacosVazios - espacosEsquerda;

        System.out.print("║");
        // Desenha espaços à esquerda
        for (int i = 0; i < espacosEsquerda; i++) System.out.print(" ");
        // Escreve o texto
        System.out.print(texto);
        // Desenha espaços à direita
        for (int i = 0; i < espacosDireita; i++) System.out.print(" ");
        System.out.println("║");
    }

    /**
     * Metodo auxiliar para imprimir uma lista de viagens.
     *
     * @param lista Lista de viagens a imprimir
     */
    private void imprimirListaViagens(ArrayList<Viagem> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Nenhuma viagem encontrada para este criterio.");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-12s | %-12s | %-16s | %-10s | %-8s\n",
                "ID", "Condutor(ID)", "Cliente(ID)", "Data", "Kms", "Custo");
        System.out.println("----------------------------------------------------------------------------------");

        for (Viagem v : lista) {
            System.out.printf("%-5d | %-12d | %-12d | %-16s | %-10.1f | %-8.2f\n",
                    v.getId(),
                    v.getIdCondutor(),
                    v.getIdCliente(),
                    v.getDataHoraInicio().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM HH:mm")),
                    v.getKms(),
                    v.getCusto());
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Total: " + lista.size() + " viagem(ns)");
    }
}