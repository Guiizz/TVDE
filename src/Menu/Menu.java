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
            System.out.println("║  8. Configuracoes da Empresa                                 ║");
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

        // Nome (minimo 3 caracteres)
        String nome = lerStringComValidacao("Nome (minimo 3 caracteres): ",3);

        // NIF (9 digitos com validacao)
        String nif;
        do {
            nif = lerString("NIF (9 digitos): ");
            if (!Validador.validarNif(nif)){
                System.out.println(Validador.getMensagemErroNif());
            }
            if (gestao.procurarCondutorPorNif(nif) != null) {    // Verificar se NIF ja existe
                System.out.println("\nJa existe um condutor com este NIF!");
            }
        }while (!Validador.validarNif(nif) || gestao.procurarCondutorPorNif(nif) != null);

        // Numero de identificacao (minimo 8 digitos)
        String numId;
        do {
            numId = lerString("N. Identificacao (CC - minimo 8 digitos): ");
            if (!Validador.validarNumeroIdentificacao(numId)) {
                System.out.println(Validador.getMensagemErroNumId());
            }
        }while (!Validador.validarNumeroIdentificacao(numId));


        // Carta de conducao
        String carta;
        do {
            carta = lerString("Carta de Conducao (ex: AB-123456): ");
            if (!Validador.validarCartaConducao(carta)){
                System.out.println(Validador.getMensagemErroCarta());
            }
        }while (!Validador.validarCartaConducao(carta));


        // Numero de Seguranca Social (11 digitos)
        String nss;
        do {
            nss = lerString("N. Seguranca Social (11 digitos): ");
            if (!Validador.validarNss(nss)){
                System.out.println(Validador.gerMensagemErroNss());
            }
        }while (!Validador.validarNss(nss));

        // Telemovel (9 digitos, comecar por 9, 2 ou 3)
        String tel;
        do {
            tel = lerString("Telemovel (9 digitos): ");
            if (!Validador.validarTelefone(tel)){
                System.out.println(Validador.getMensagemErroTelefone());
            }
        }while (!Validador.validarTelefone(tel));

        // Morada (minimo 5 caracteres)
        String morada = lerStringComValidacao("Morada (minimo 5 caracteres): ",5);

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

        String nome = lerStringOpcional("Novo nome [" + c.getNome() + "]: ");
        if (!nome.isEmpty()){
            if(Validador.validarComprimentoMinimo(nome, 3)){
                c.setNome(nome);
            }else {
                System.out.println("Nome deve ter minimo 3 caracteres. Valor mantido.");
            }
        }


        String nif = lerStringOpcional("Novo NIF [" + c.getNif() + "]: ");
        if (!nif.isEmpty()) {
            if (Validador.validarNif(nif)) {
                // Verifica se existe e se não é o próprio condutor (caso insira o mesmo NIF)
                Condutor existente = gestao.procurarCondutorPorNif(nif);
                if (existente != null && existente.getId() != c.getId()) {
                    System.out.println("\nJa existe um condutor com este NIF! Valor mantido.");
                } else {
                    c.setNif(nif);
                }
            } else {
                System.out.println(Validador.getMensagemErroNif() + " Valor mantido.");
            }
        }


        String numId = lerStringOpcional("Novo N. Identificacao [" + c.getNumeroIdentificacao() + "]: ");
        if (!numId.isEmpty()) {
            if (Validador.validarNumeroIdentificacao(numId)) {
                c.setNumeroIdentificacao(numId);
            } else {
                System.out.println(Validador.getMensagemErroNumId() + " Valor mantido.");
            }
        }


        String carta = lerStringOpcional("Nova Carta de Conducao [" + c.getCartaConducao() + "]: ");
        if (!carta.isEmpty()) {
            if (Validador.validarCartaConducao(carta)){
                c.setCartaConducao(carta);
            }else {
                System.out.println(Validador.getMensagemErroCarta() + " Valor mantido");
            }
        }


        String nss = lerStringOpcional("Novo N. Seguranca Social [" + c.getNumeroSegurancaSocial() + "]: ");
        if (!nss.isEmpty()) {
            if (Validador.validarNss(nss)){
                c.setNumeroSegurancaSocial(nss);
            } else {
                System.out.println(Validador.gerMensagemErroNss() + " Valor mantido.");
            }
        }


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

    private void consultarViatura (){
        limparEcra();
        System.out.println("=== CONSULTAR VIATURAS ===\n");

        int id = lerInteiroPositivo("ID da viatura: ");
        Viatura viatura = gestao.procurarViaturaPorId(id);

        if (viatura != null) {
            System.out.println("\n" + viatura.toStringDetalhado());
        } else {
            System.out.println("\nViatura nao encontrado!");
        }
        pausar();
    }

    private void alterarViatura (){
        limparEcra();
        System.out.println("=== ALTERAR VIATURA ===\n");

        int id = lerInteiroPositivo("ID da viatura: ");
        Viatura viatura = gestao.procurarViaturaPorId(id);

        if (viatura == null) {
            System.out.println("\nViatura nao encontrada!");
            pausar();
            return;
        }

        System.out.println("\nDados atuais:");
        System.out.println(viatura.toStringDetalhado());
        System.out.println("\n(Deixe em branco para manter o valor atual)\n");

//        String marca = lerStringOpcional("Novo modelo [" + viatura.getMarca() + "]: ")
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




        // Telemovel (9 digitos, comecar por 9, 2 ou 3)
        String tel;
        do {
            tel = lerString("Telemovel (9 digitos): ");
            if (!Validador.validarTelefone(tel)){
                System.out.println(Validador.getMensagemErroTelefone());
            }
        }while (!Validador.validarTelefone(tel));

        // Morada (minimo 5 caracteres)
        String morada = lerStringComValidacao("Morada (minimo 5 caracteres): ",5);

        // Email (verificar se o email tem o @)
        String email;
        do {
            email = lerString("Email: ");
            if (!Validador.validarEmail(email)){
                System.out.println(Validador.getMensagemErroEmail());
            }
        }while (!Validador.validarEmail(email));

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

        String nif = lerStringOpcional("Novo NIF [" + c.getNif() + "]: ");
        if (!nif.isEmpty()) {
            if (Validador.validarNif(nif)) {
                c.setNif(nif);
            } else {
                System.out.println(Validador.getMensagemErroNif() + " Valor mantido.");
            }
        }

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

        String email = lerStringOpcional("Novo Email [" + c.getEmail() + "]: ");
        if (!email.isEmpty()) {
            if (Validador.validarEmail(email)) {
                c.setEmail(email);
            } else {
                System.out.println(Validador.getMensagemErroEmail() + " Valor mantido.");
            }
        }

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



    private void menuRelStats() {
        int opcao;
        do {
            limparEcra();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║             MENU DE RELATÓRIOS E ESTATÍSTICAS                ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Apresentar a lista de clientes de uma viatura            ║");
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