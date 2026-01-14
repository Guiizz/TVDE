package Menu;

import Gestão.GestaoTVDE;
import Classes.*;
import Validador.Validador;
import Ficheiros.GestorFicheiros;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private GestaoTVDE gestao;
    private Scanner scanner;
    private DateTimeFormatter data;
    private GestorFicheiros gestorFicheiros;

    public Menu() {
        this.gestao = new GestaoTVDE();
        this.scanner = new Scanner(System.in);
        this.data = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.gestorFicheiros = new GestorFicheiros();
        try {
            System.out.println("A carregar dados...");
            // Substitui a gestão vazia pela que vem do ficheiro
            this.gestao = this.gestorFicheiros.lerTudo("empresa");
            System.out.println("Dados carregados com sucesso!");
        } catch (Exception e) {
            // Se der erro (ex: 1ª vez a correr), não faz mal. Segue com a lista vazia.
            System.out.println("Iniciado com base de dados vazia (ou ficheiros não encontrados).");
        }
    }
    public void iniciar(){
        menuPrincipal();
    }

    private void menuPrincipal() {
        int opcao;
        do {
            limparEcra();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                     MENU PRINCIPAL                           ║");
            System.out.println("║                      EMPRESA TVDE                            ║");
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

// Menu Condutores
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
     * Lista todos os condutores.
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
     * Consulta um condutor pelo ID.
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
     * Altera os dados de um condutor.
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
     * Remove um condutor.
     */
    private void removerCondutor() {
        limparEcra();
        System.out.println("=== REMOVER CONDUTOR ===\n");

        int id = lerInteiroPositivo("ID do condutor: ");
        int resultado = gestao.removerCondutor(id);

        switch (resultado) {
            case 0:
                System.out.println("\nCondutor removido com sucesso!");
                break;
            case -1:
                System.out.println("\nCondutor nao encontrado!");
                break;
            case -2:
                System.out.println("\nNao e possivel remover! O condutor tem viagens associadas.");
                break;
        }
        pausar();
    }

// Menu Viaturas
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

    private void removerViatura() {
        limparEcra();
        System.out.println("=== REMOVER VIATURA ===\n");

        int id = lerInteiroPositivo("ID da viatura: ");

        // Chama o metodo na gestão que verifica se tem viagens/reservas antes de apagar
        int resultado = gestao.removerViatura(id);

        switch (resultado) {
            case 0:
                System.out.println("\nViatura removida com sucesso!");
                break;
            case -1:
                System.out.println("\nViatura nao encontrada!");
                break;
            case -2:
                System.out.println("\nERRO: Nao e possivel remover! A viatura tem viagens ou reservas associadas.");
                break;
            default:
                System.out.println("\nErro desconhecido.");
        }
        pausar();
    }


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
     * Lista todos os clientes.
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
     * Consulta um cliente pelo ID.
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
     * Altera os dados de um cliente.
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

        //VERIFICA DUPLICADOS
        boolean emailValido = false;
        do {
            String inputEmail = lerStringOpcional("Novo Email [" + c.getEmail() + "]: ");

            if (inputEmail.isEmpty()) {
                emailValido = true; // Mantém o antigo
            } else {
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
     * Remove um cliente.
     */
    private void removerCliente() {
        limparEcra();
        System.out.println("=== REMOVER CLIENTE ===\n");

        int id = lerInteiroPositivo("ID do cliente: ");
        int resultado = gestao.removerCliente(id);

        switch (resultado) {
            case 0:
                System.out.println("\nCliente removido com sucesso!");
                break;
            case -1:
                System.out.println("\nCliente nao encontrado!");
                break;
            case -2:
                System.out.println("\nNao e possivel remover! O condutor tem viagens associadas.");
                break;
        }
        pausar();
    }

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

    private void criarReserva() {
        limparEcra();
        System.out.println("=== CRIAR RESERVA ===\n");

        //Listar clientes
        if (gestao.getNumeroClientes() == 0) {
            System.out.println("Não existem clientes resgistados!");
            pausar();
            return;
        }

        System.out.println("Clientes Disponiveis:");
        ArrayList<Cliente> clientes = gestao.getClientes();
        int numClientes = gestao.getNumeroClientes();
        for (int i = 0; i < numClientes; i++) {
            System.out.println(clientes.get(i).toString());
        }

        int idCliente = lerInteiroPositivo("\nID do Cliente: ");
        Cliente cliente = gestao.procurarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("\nCliente nao encontrado!");
            pausar();
            return;
        }

        //Listar viaturas
        int idViatura = 0;
        if (gestao.getNumeroViaturas() > 0) {
            System.out.println("\nViaturas Disponiveis:");
            ArrayList<Viatura> viaturas = gestao.getViaturas();
            int numViaturas = gestao.getNumeroViaturas();
            for (int i = 0; i < numViaturas; i++) {
                System.out.println(viaturas.get(i).toString());
            }

            idViatura = lerInteiroPositivo("\nID da Viatura: ");
            Viatura viatura = gestao.procurarViaturaPorId(idViatura);
            if (viatura == null) {
                System.out.println("\nViatura nao encontrada!");
                pausar();
                return;
            }
        } else {
            System.out.println("\nNao existem viaturas registadas!");
            pausar();
            return;
        }

        System.out.println("\nData e hora da reserva:");
        LocalDateTime dataHora = lerDataHora("Data e hora da reserva");
        if (dataHora == null) {
            System.out.println("\nData/hora invalida!");
            pausar();
            return;
        }

        //Morada de origem
        String origem = lerStringComValidacao("Morada de Origem (minimo 5 caracteres): ",5);

        //Morada de destino
        String destino = lerStringComValidacao("Morada de Destino (minimo 5 caracteres): ",5);

        //Kms
        double kms;
        do {
            kms = lerDoublePositivo("Distancia estimada (km): ");
            if (!Validador.validarKms(kms)) {
                System.out.println(Validador.getMensagemErroKms());
            }
        } while (!Validador.validarKms(kms));

        Reserva reserva = new Reserva(idCliente, idViatura, dataHora, origem, destino, kms);

        if (gestao.adicionarReserva(reserva)) {
            System.out.println("\nReserva criada com sucesso! (ID: " + reserva.getId() + ")");
        } else {
            System.out.println("\nErro ao criar reserva!");
        }
        pausar();
    }

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
            System.out.println("   Origem: " + reserva.getMoradaOrigem());
            System.out.println("   Destino: " + reserva.getMoradaDestino());
        }

        System.out.println("\n0. Voltar");
        int opcao = lerInteiro("\nEscolha uma reserva para alterar (0 para voltar): ");

        if (opcao > 0 && opcao <= numReservas) {
            alterarReservaEspecifica(reservas.get(opcao - 1));
        }
    }

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

    private void cancelarReserva() {
        limparEcra();
        System.out.println("=== CANCELAR RESERVA ===\n");

        int id = lerInteiroPositivo("ID da reserva: ");
        Reserva reserva = gestao.procurarReservaPorId(id);

        if (reserva == null) {
            System.out.println("\nReserva nao encontrada!");
        } else if (!reserva.isAtiva()){
            System.out.println("\nA reserva ja se encontra cancelada!");
        } else {
            System.out.println("\nReserva cancelada com sucesso!");
        }
        pausar();
    }

    private void removerReserva() {
        limparEcra();
        System.out.println("=== REMOVER RESERVA ===\n");

        int id = lerInteiroPositivo("ID da reserva: ");

        if (gestao.removerReserva(id)) {
            System.out.println("\nReserva removida com sucesso!");
        } else {
            System.out.println("\nReserva nao encontrada!");
        }

        pausar();
    }

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

        System.out.println("\nData e hora de fim da viagem:");
        LocalDateTime dataFim = lerDataHora("Data e hora da reserva");
        if (dataFim == null) {
            System.out.println("\nData/hora invalida!");
            pausar();
            return;
        }

        // Validar que data fim e posterior a data inicio
        // Falta implementar esta validação na classe de leitura de data/hora

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
     * Adiciona uma nova viagem
     */
    private void adicionarViagem() {
        limparEcra();
        System.out.println("=== ADICIONAR VIAGEM ===\n");

        //VERIFICAR SE EXISTE OS DADOS NECESSARIOS
        if (gestao.getNumeroCondutores() == 0) {
            System.out.println("Não existe condutores registados!");
            pausar();
            return;
        }
        if (gestao.getNumeroViaturas() == 0) {
            System.out.println("Não existe viaturas registados!");
            pausar();
            return;
        }
        if (gestao.getNumeroClientes() == 0) {
            System.out.println("Não existe clientes registados!");
            pausar();
            return;
        }

        //Selecionar condutor
        System.out.println("Condutores disponiveis:");
        ArrayList<Condutor> condutores = gestao.getCondutores();
        int numCondutores = gestao.getNumeroCondutores();
        for (int i = 0; i < numCondutores; i++){
            System.out.println(condutores.get(i).toString());
        }
        int idCondutor = lerInteiroPositivo("\nID do condutor: ");
        if (gestao.procurarCondutorPorId(idCondutor) == null) {
            System.out.println("\nCondutor não encontrado!");
            pausar();
            return;
        }
        //Selecionar viatua
        System.out.println("Viaturas disponiveis:");
        ArrayList<Viatura> viaturas = gestao.getViaturas();
        int numViaturas = gestao.getNumeroViaturas();
        for (int i = 0; i < numViaturas; i++){
            System.out.println(viaturas.get(i).toString());
        }
        int idViatura = lerInteiroPositivo("\nID do condutor: ");
        if (gestao.procurarViaturaPorId(idViatura) == null) {
            System.out.println("\nViatura não encontrada!");
            pausar();
            return;
        }
        //Selecionar cliente
        System.out.println("Clientes disponiveis:");
        ArrayList<Cliente> clientes = gestao.getClientes();
        int numCliente = gestao.getNumeroClientes();
        for (int i = 0; i < numCliente; i++){
            System.out.println(clientes.get(i).toString());
        }
        int idCliente = lerInteiroPositivo("\nID do condutor: ");
        if (gestao.procurarClientePorId(idCliente) == null) {
            System.out.println("\nCliente não encontrado!");
            pausar();
            return;
        }

        //Ler datas
        LocalDateTime inicio = lerDataHora("Data/Hora inicio");
        LocalDateTime fim;
        do {
            fim = lerDataHora("Data/Hora Fim");
            if (fim.isBefore(inicio)) {
                System.out.println("A Data/Hora de fim não pode ser anterior ao inico!");
            }
        } while(fim.isBefore(inicio));
        String origem = lerStringComValidacao("Origem: ",3);
        String destino = lerStringComValidacao("Destino: ",3);
        double kms = lerDoublePositivo("Distância (kms): ");
        double custo = lerDoublePositivo("Custo (€): ");

        Viagem novaViagem = new Viagem(idCondutor, idCliente, idViatura, inicio, fim, origem, destino, kms, custo);

        if (gestao.adicionarViagem(novaViagem)){
            System.out.println("\nViagem registada com sucesso!");
        } else {
            System.out.println("\nErro: Sobreposição de horario detetada!");
        }
        pausar();
    }

    /**
     * Lista todos as viagens.
     */
    private void listarViagens() {
        limparEcra();
        System.out.println("=== LISTA DE VIAGENS ===\n");

        ArrayList<Viagem> viagems = gestao.getViagens();
        int numViagens = gestao.getNumeroViagens();

        if (numViagens == 0) {
            System.out.println("Nenhuma viagem registada.");
        } else {
            for (int i = 0; i < numViagens; i++) {
                System.out.println(viagems.get(i).toString());
            }
            System.out.println("\nTotal: " + numViagens + " Viagem(es)");
        }
        pausar();
    }

    /**
     * Consulta uma viagem pelo ID.
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
     * Alterar dados de uma viagem.
     */
    private void alterarViagem() {
        limparEcra();
        System.out.println("=== CONSULTAR VIAGEM ===\n");

        int id = lerInteiroPositivo("ID da viagem: ");
        Viagem v = gestao.procurarViagemPorId(id);

        if (v == null) {
            System.out.println("\nErro: Viagem não encontrada!");
            pausar();
            return;
        }

        System.out.println("\nDados atuais:");
        System.out.println(v.toStringDetalhado());
        System.out.println("\n(Deixe em branco para manter o valor atual)\n");

        //Alterar Data/hora inicio
        System.out.println("Nova Data inicio [" + v.getDataHoraInicio().format(data) + "]: ");
        String inicio = scanner.nextLine().trim();

        LocalDateTime novoInicio = v.getDataHoraInicio();
        if (!inicio.isEmpty()) {
            try {
                novoInicio = LocalDateTime.parse(inicio, data);
            } catch (Exception e) {
                System.out.println("\nFormato inválido! A data de inicio antiga foi mantida.");
            }
        }
        //Alterar Data/hora fim
        System.out.println("Nova Data Fim [" + v.getDataHoraFim().format(data) + "]: ");
        String fim = scanner.nextLine().trim();

        LocalDateTime novoFim = v.getDataHoraFim();
        if (!fim.isEmpty()) {
            try {
                LocalDateTime tempFim = LocalDateTime.parse(fim, data);
                if (tempFim.isAfter(novoInicio)) {
                    novoFim = tempFim;
                } else {
                    System.out.println("\nErro: A data de fim tem que ser posterior ao inicio! Valor antigo mantigo.");
                }
            } catch (Exception e) {
                System.out.println("\nFormato invalido! A data de fim antiga foi mantida");
            }
        } else {
            //Caso o utilizador mude o inicio mas não o fim
            if (novoFim.isBefore(novoInicio)) {
                System.out.println("\nAviso: A nova data de inicio era posterior ao fim antigo");
                novoFim = novoInicio.plusMinutes(30);//ajuste automático de segurança;
                System.out.println(" -> Data de fim ajustada automaticamente para: " + novoFim.format(data));
            }
        }
        //aplicar datas
        v.setDataHoraInicio(novoInicio);
        v.setDataHoraFim(novoFim);

        //alterar origem e destino
        System.out.println("Nova Origem [" + v.getMoradaOrigem() + "]: ");
        String origem = scanner.nextLine().trim();
        if (!origem.isEmpty()) {
            v.setMoradaOrigem(origem);
        }

        System.out.println("Nova Destino [" + v.getMoradaDestino() + "]: ");
        String destino = scanner.nextLine().trim();
        if (!destino.isEmpty()) {
            v.setMoradaDestino(destino);
        }

        //Alterar kms
        System.out.println("Novos kms [" + v.getKms() + "]: ");
        String kms = scanner.nextLine().trim();
        if (!kms.isEmpty()) {
            try {
                double k = Double.parseDouble(kms);
                if (k > 0) {
                    v.setKms(k);
                } else {
                    System.out.println("\nKms devem ser positivos. Valor mantido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nNúmero inválido. valor mantido.");
            }
        }

        //Alterar o custo
        System.out.println("Novo Custo [" + v.getCusto() + "]: ");
        String custo = scanner.nextLine().trim();
        if (!custo.isEmpty()) {
            try {
                double c = Double.parseDouble(custo);
                if (c > 0) {
                    v.setCusto(c);
                } else {
                    System.out.println("\nCusto deve ser positivo. Valor mantido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nNúmero inválio. Valor mantido.");
            }
        }
        System.out.println("\nViagem atualizada com sucesso!");
        pausar();
    }

    /**
     * Remove um condutor.
     */
    private void removerViagem() {
        limparEcra();
        System.out.println("=== REMOVER VIAGEM ===\n");

        int id = lerInteiroPositivo("ID da viagem: ");

        Viagem v = gestao.procurarViagemPorId(id);

        if (v != null) {
            System.out.println("Vai apagar: " + v.toString());
            if (lerString("Deseja remover viagem? (S/N").equalsIgnoreCase("S")) {
                gestao.removerViagem(id);
                System.out.println("Viagem removida com sucesso.");
            }
        } else {
            System.out.println("Viagem não encontrada.");
        }
        pausar();
    }



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

    // --- RELATÓRIO 1 ---
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

    // --- RELATÓRIO 2 ---
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

    // --- RELATÓRIO 3 ---
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

    // --- RELATÓRIO 4 ---
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

    // --- RELATÓRIO 5 ---
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
                        this.gestao = gestorFicheiros.lerTudo("Empresa");

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
     * Le um numero inteiro do utilizador.
     * @param mensagem Mensagem a apresentar
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
     * Le um numero inteiro positivo do utilizador.
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
     * Le uma string do utilizador (obrigatoria).
     * @param mensagem Mensagem a apresentar
     * @return String lida
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
     * Le uma string do utilizador (opcional).
     * @param mensagem Mensagem a apresentar
     * @return String lida (pode ser vazia)
     */
    private String lerStringOpcional(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }


    /**
     * Le um inteiro dentro de um intervalo.
     * @param mensagem Mensagem a apresentar
     * @param min Valor minimo
     * @param max Valor maximo
     * @return Inteiro lido
     */
    private int lerInteiroEntre(String mensagem, int min, int max) {
        int valor;
        do {
            valor = lerInteiro(mensagem);
            if (valor < min || valor > max) {
                System.out.println("Valor deve estar entre " + min + " e " + max + ".");
            }
        } while (valor < min || valor > max);
        return valor;
    }

    /**
     * Pausa a execucao ate o utilizador pressionar Enter.
     */
    private void pausar() {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Limpa o ecra (simula limpeza com linhas em branco).
     */
    private void limparEcra() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

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
}