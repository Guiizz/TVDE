package Gestão;
import Classes.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestaoTVDE {
    /** Nome da empresa */
    private String nomeEmpresa;
    /** Lista de condutores */
    private ArrayList<Condutor> condutores;
    /** Lista de viaturas */
    private ArrayList<Viatura> viaturas;
    /** Lista de clientes */
    private ArrayList<Cliente> clientes;
    /** Lista de reservas */
    private ArrayList<Reserva> reservas;
    /** Lista de viagens */
    private ArrayList<Viagem> viagens;
    /** Contador de condutores */
    private int numCondutores;
    /** Contador de viaturas */
    private int numViaturas;
    /** Contador de clientes */
    private int numClientes;
    /** Contador de reservas */
    private int numReservas;
    /** Contador de viagens */
    private int numViagens;
    /** Preço por km em euros */
    private double precoPorKm;
    /** Taxa base por viagem em euros */
    private double taxaBase;

    /**
     * Construtor da classe GestaoTVDE.
     */
    public GestaoTVDE () {
        this.nomeEmpresa = "TVDE";
        this.condutores = new ArrayList<Condutor>();
        this.viaturas = new ArrayList<Viatura>();
        this.clientes = new ArrayList<Cliente>();
        this.reservas = new ArrayList<Reserva>();
        this.viagens = new ArrayList<Viagem>();
        this.numCondutores = 0;
        this.numViaturas = 0;
        this.numClientes = 0;
        this.numReservas = 0;
        this.numViagens = 0;
        this.precoPorKm = 0.50;
        this.taxaBase = 3.50;
    }

    // ==================== GETTERS E SETTERS ====================

    /**
     * Obtem o nome da empresa.
     * @return nome da empresa
     */
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    /**
     * Define o nome da empresa.
     * @param nomeEmpresa novo nome da empresa
     */
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    /**
     * Obtem o preço por km
     * @return preço por km
     */
    public double getPrecoPorKm() {
        return precoPorKm;
    }

    /**
     * Define o preço por km.
     * @param precoPorKm novo preço por km
     */
    public void setPrecoPorKm(double precoPorKm) {
        this.precoPorKm = precoPorKm;
    }

    /**
     * Obtem a taxa base.
     * @return taxa base
     */
    public double getTaxaBase() {
        return taxaBase;
    }

    /**
     * Define a taxa base.
     * @param taxaBase nova taxa base
     */
    public void setTaxaBase(double taxaBase) {
        this.taxaBase = taxaBase;
    }

    /**
     * Obtem a lista de condutores.
     * @return Arraylist de condutores
     */
    public ArrayList<Condutor> getCondutores() {
        return condutores;
    }

    /**
     * Obtem a lista de viaturas.
     * @return Arraylist de viaturas
     */
    public ArrayList<Viatura> getViaturas() {
        return viaturas;
    }
    /**
     * Obtem a lista de clientes.
     * @return Arraylist de clientes
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    /**
     * Obtem a lista de reservas.
     * @return Arraylist de reservas
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    /**
     * Obtem a lista de viagens.
     * @return Arraylist de viagens
     */
    public ArrayList<Viagem> getViagens() {
        return viagens;
    }

    // ==================== OPERACOES CRUD CONDUTORES ====================

    /**
     * Adiciona um novo condutor
     * @param condutor Condutor a adicionar
     * @return true se for adicionado com sucesso
     */
    public boolean adicionarCondutor(Condutor condutor) {
        if (condutor == null) {
            return false;
        }
        condutores.add(condutor);
        numCondutores++;
        return true;
    }

    /**
     * Procura um condutor pelo ID.
     * @param id ID do condutor
     * @return Condutor encontrado ou null
     */
    public Condutor procurarCondutorPorId(int id) {
        for (int i = 0; i < numCondutores; i++) {
            Condutor c = condutores.get(i);
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Procura o condutor por NIF.
     * @param nif NIF do condutor
     * @return Condutor encontrado ou null
     */
    public Condutor procurarCondutorPorNif(String nif) {
        for (int i = 0; i < numCondutores; i++) {
            Condutor c = condutores.get(i);
            if (c.getNif().equals(nif)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Procurar condutor por número do cartão de cidadão.
     * @param numId Número do cartão de cidadão
     * @return Condutor encontrado ou null
     */
    public Condutor procurarCondutorPorNumeroIdentificacao(String numId) {
        for (int i = 0; i < numCondutores; i++) {
            Condutor c = condutores.get(i);
            if (c.getNumeroIdentificacao().equals(numId)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Procurar condutor por carta de condução.
     * @param carta Carta de condução
     * @return Condutor encontrado ou null
     */
    public Condutor procurarCondutorPorCartaConducao(String carta) {
        for (int i = 0; i < numCondutores; i++) {
            Condutor c = condutores.get(i);
            if (c.getCartaConducao().equals(carta)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Procurar condutor por número da segurança social.
     * @param nss Número da segurança social
     * @return Condutor encontrado ou null
     */
    public Condutor procurarCondutorPorNss(String nss) {
        for (int i = 0; i < numCondutores; i++) {
            Condutor c = condutores.get(i);
            if (c.getNumeroSegurancaSocial().equals(nss)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Remove um condutor
     * Verifica se não tem viagens associadas.
     * @param id ID do condutor a remover
     * @return 0 se removido, -1 se não encontrado, -2 se tem dependencias
     */
    public int removerCondutor(int id) {
        Condutor condutor = procurarCondutorPorId(id);
        if (condutor == null) {
            return -1;
        }
        // Verificar dependencias (viagens)
        if (condutorTemViagens(id)) {
            return -2;
        }
        condutores.remove(condutor);
        numCondutores--;
        return 0;
    }

    /**
     * Verifica se um condutor tem viagens associadas.
     * @param idCondutor ID do condutor
     * @return True se tem viagens
     */
    public boolean condutorTemViagens(int idCondutor) {
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getIdCondutor() == idCondutor) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtem o número de condutores.
     * @return Número de condutores
     */
    public int getNumeroCondutores() {
        return numCondutores;
    }


    // ==================== OPERACOES CRUD VIATURAS ====================

    /**
     * Adiciona um nova viatura.
     * @param viatura Viatura a adicionar
     * @return true se for adicionada com sucesso.
     */
    public boolean adicionarViatura(Viatura viatura) {
        if (viatura == null) {
            return false;
        }
        // Verificar se matricula ja existe
        if (procurarViaturaPorMatricula(viatura.getMatricula()) != null) {
            return false;
        }
        viaturas.add(viatura);
        numViaturas++;
        return true;
    }

    /**
     * Procura uma viatura por ID.
     * @param id ID da viatura
     * @return Viatura encontrada ou null
     */
    public Viatura procurarViaturaPorId(int id) {
        for (int i = 0; i < numViaturas; i++) {
            Viatura v = viaturas.get(i);
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    /**
     * Procura viatura por matricula.
     * @param matricula Matricula da viatura
     * @return Viatura encontrada ou null
     */
    public Viatura procurarViaturaPorMatricula(String matricula) {
        for (int i = 0; i < numViaturas; i++) {
            Viatura v = viaturas.get(i);
            if (v.getMatricula().equalsIgnoreCase(matricula)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Remove uma viatura.
     * Verifica se não tem viagens ou reservas associados.
     * @param id ID da viatura a remover
     * @return 0 se removida, -1 se não for encontrada, -2 se tem dependencias
     */
    public int removerViatura(int id) {
        Viatura viatura = procurarViaturaPorId(id);
        if (viatura == null) {
            return -1;
        }
        // Verificar dependencias
        if (viaturaTemViagens(id) || viaturaTemReservas(id)) {
            return -2;
        }
        viaturas.remove(viatura);
        numViaturas--;
        return 0;
    }

    /**
     * Verifica se uma viatura tem viagens associadas.
     * @param idViatura ID da viatura
     * @return True se tem viagens
     */
    public boolean viaturaTemViagens(int idViatura) {
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getIdViatura() == idViatura) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se uma viatura tem reservas associadas.
     * @param idViatura ID da viatura
     * @return True se tem reservas
     */
    public boolean viaturaTemReservas(int idViatura) {
        for (int i = 0; i < numReservas; i++) {
            Reserva r = reservas.get(i);
            if (r.getIdViatura() == idViatura && r.isAtiva()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtem o número de viaturas.
     * @return número de viaturas
     */
    public int getNumeroViaturas() {
        return numViaturas;
    }



    // ==================== OPERACOES CRUD CLIENTES ====================

    /**
     * Adiciona um novo cliente.
     * @param cliente Cliente a adicionar
     * @return True se adicionado com sucesso
     */
    public boolean adicionarCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        clientes.add(cliente);
        numClientes++;
        return true;
    }

    /**
     * Procura um cliente por ID.
     * @param id ID do cliente
     * @return Cliente encontrado ou null
     */
    public Cliente procurarClientePorId(int id) {
        for (int i = 0; i < numClientes; i++) {
            Cliente c = clientes.get(i);
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Procura um cliente por NIF.
     * @param nif NIF do cliente
     * @return Cliente encontrado ou null
     */
    public Cliente procurarClientePorNif(String nif) {
        for (int i = 0; i < numClientes; i++) {
            Cliente c = clientes.get(i);
            if (c.getNif().equals(nif)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Procura um cliente pelo Email.
     * @param email Email a procurar
     * @return Cliente encontrado ou null
     */
    public Cliente procurarClientePorEmail(String email) {
        for (int i = 0; i < numClientes; i++) {
            Cliente c = clientes.get(i);
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Remove um cliente
     * @param id ID do cliente a remover
     * @return 0 se removido, -1 se não foi encontrado, -2 se tem dependencias
     */
    public int removerCliente(int id) {
        Cliente cliente = procurarClientePorId(id);
        if (cliente == null) {
            return -1;
        }
        // Verificar dependencias
        if (clienteTemViagens(id) || clienteTemReservas(id)) {
            return -2;
        }
        clientes.remove(cliente);
        numClientes--;
        return 0;
    }

    /**
     * Verifica se o cliente tem viagens associadas
     * @param idCliente ID do cliente
     * @return True se tem viagens
     */
    public boolean clienteTemViagens(int idCliente) {
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getIdCliente() == idCliente) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o cliente tem reservas associadas.
     * @param idCliente ID do cliente
     * @return True se tem reservas ativas
     */
    public boolean clienteTemReservas(int idCliente) {
        for (int i = 0; i < numReservas; i++) {
            Reserva r = reservas.get(i);
            if (r.getIdCliente() == idCliente && r.isAtiva()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtem o número de clientes.
     * @return número de clientes
     */
    public int getNumeroClientes() {
        return numClientes;
    }

    // ==================== OPERACOES CRUD RESERVAS ====================

    /**
     * Adiciona uma nova reserva.
     * Verifica se não há sobreposição.
     * @param reserva Reserva a adicionar
     * @return True se adicionada com sucesso, False se houver sobreposições
     */
    public boolean adicionarReserva(Reserva reserva) {
        if (reserva == null) return false;

        if (existeSobreposicao(reserva.getIdViatura(), reserva.getDataHoraInicio(), reserva.getDataHoraFim(), 0)) {
            return false;
        }
        reservas.add(reserva);
        numReservas++;
        return true;
    }

    /**
     * Procura uma reserva por ID.
     * @param id ID da reserva
     * @return Reserva encontrada ou null
     */
    public Reserva procurarReservaPorId(int id) {
        for (int i = 0; i < numReservas; i++) {
            Reserva r = reservas.get(i);
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    /**
     * Remove uma reserva.
     * @param id ID da reserva a remover
     * @return True se removida, False se não encontrada
     */
    public boolean removerReserva(int id) {
        Reserva reserva = procurarReservaPorId(id);
        if (reserva == null) {
            return false;
        }
        reservas.remove(reserva);
        numReservas--;
        return true;
    }

    /**
     * Obtem as reservas de um cliente.
     * @param idCliente ID do cliente
     * @param resultado Número de reservas
     * @return Arraylist de reservas do cliente
     */
    public int getReservasCliente(int idCliente, ArrayList<Reserva> resultado) {
        int numResultados = 0;
        for (int i = 0; i < numReservas; i++) {
            Reserva r = reservas.get(i);
            if (r.getIdCliente() == idCliente) {
                resultado.add(r);
                numResultados++;
            }
        }
        return numResultados;
    }

    /**
     * Obtem as reservas ativas de um cliente
     * @param idCliente ID do cliente
     * @param resultado Número de reservas
     * @return Arraylist de reservas ativas do cliente
     */
    public int getReservasAtivasCliente(int idCliente, ArrayList<Reserva> resultado) {
        int numResultados = 0;
        for (int i = 0; i < numReservas; i++) {
            Reserva r = reservas.get(i);
            if (r.getIdCliente() == idCliente && r.isAtiva()) {
                resultado.add(r);
                numResultados++;
            }
        }
        return numResultados;
    }

    /**
     * Obtem o número de reservas.
     * @return número de reservas
     */
    public int getNumeroReservas() {
        return numReservas;
    }

    /**
     * Verifica todas as reservas ativas. Se a data de fim já passou,
     * tenta converter em viagem automaticamente.
     */
    public int processarReservasExpiradas() {
        int convertidas = 0;
        LocalDateTime agora = LocalDateTime.now();

        for (Reserva r : reservas) {
            // Se a reserva está ativa E a hora de fim já passou
            if (r.isAtiva() && agora.isAfter(r.getDataHoraFim())) {

                // 1. Tentar encontrar um condutor para esta reserva
                int idCondutor = obterCondutorDisponivel(r.getDataHoraInicio(), r.getDataHoraFim());

                if (idCondutor != -1) {
                    // 2. Se encontrou condutor, converte em viagem
                    converterReservaEmViagem(r.getId(), idCondutor);
                    convertidas++;
                } else {
                    // (Opcional) Podes fazer um print aqui se quiseres saber que falhou
                    // System.out.println("Reserva " + r.getId() + " expirada mas sem condutores disponíveis.");
                }
            }
        }
        return convertidas;
    }

    public void converterReservaEmViagem(int idReserva, int idCondutor) {
        Reserva r = procurarReservaPorId(idReserva);
        if (r == null || !r.isAtiva()) return;

        // Calcula custo (ex: 0.70€ por Km)
        double custo = r.getKms() * 0.70;

        // Cria a Viagem
        Viagem novaViagem = new Viagem(
                idCondutor, r.getIdCliente(), r.getIdViatura(),
                r.getDataHoraInicio(), r.getDataHoraFim(),
                r.getMoradaOrigem(), r.getMoradaDestino(),
                r.getKms(), custo
        );

        viagens.add(novaViagem);
        numViagens++;

        // Desativa a reserva para não ser processada novamente
        r.setAtiva(false);
    }
    // ==================== OPERACOES CRUD VIAGENS ====================

    /**
     * Adiciona uma nova viagem.
     * Verifica se não há sobreposição
     * @param viagem Viagem a adicionar
     * @return True se adicionada com sucesso, False se houver sobreposição
     */
    public boolean adicionarViagem(Viagem viagem) {
        if (viagem == null) {
            return false;
        }
        // Verificar sobreposicao
        if (existeSobreposicaoViagem(viagem.getIdViatura(), viagem.getIdCondutor(),
                viagem.getDataHoraInicio(), viagem.getDataHoraFim(), 0)) {
            return false;
        }
        viagens.add(viagem);
        numViagens++;
        return true;
    }

    /**
     * Procura uma viagem pelo ID.
     * @param id ID da viagem
     * @return Viagem encontrada ou null
     */
    public Viagem procurarViagemPorId(int id) {
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    /**
     * Remove uma Viagem.
     * @param id ID da viagem a remover
     * @return True se removida, False se não for encontrada.
     */
    public boolean removerViagem(int id) {
        Viagem viagem = procurarViagemPorId(id);
        if (viagem == null) {
            return false;
        }
        viagens.remove(viagem);
        numViagens--;
        return true;
    }

    /**
     * Obtem as viagens de um cliente.
     * @param idCliente ID do cliente
     * @param resultado Número de viagens
     * @return Arraylist de viagens do cliente
     */
    public int getViagensCliente(int idCliente, ArrayList<Viagem> resultado) {
        int numResultados = 0;
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getIdCliente() == idCliente) {
                resultado.add(v);
                numResultados++;
            }
        }
        return numResultados;
    }

    /**
     * Obtem as viagens de um cliente num intervalo de datas.
     * @param idCliente ID do cliente
     * @param dataInicio Data de inicio
     * @param dataFim Data de fim
     * @param resultado Número de viagens
     * @return Arraylist de viagens do cliente no intervalo.
     */
    public int getViagensClienteEntreDatas(int idCliente, LocalDateTime dataInicio, LocalDateTime dataFim, ArrayList<Viagem> resultado) {
        int numResultados = 0;

        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);

            // Vamos buscar a data da viagem para uma variável
            LocalDateTime dataViagem = v.getDataHoraInicio();

            boolean dentroDoInicio = dataViagem.isEqual(dataInicio) || dataViagem.isAfter(dataInicio);
            boolean dentroDoFim = dataViagem.isEqual(dataFim) || dataViagem.isBefore(dataFim);

            if (v.getIdCliente() == idCliente && dentroDoInicio && dentroDoFim) {
                resultado.add(v);
                numResultados++;
            }
        }
        return numResultados;
    }

    /**
     * Obtem as viagens de um condutor.
     * @param idCondutor ID do condutor
     * @return ArrayList de viagens do condutor
     */
    public ArrayList<Viagem> getViagensPorCondutor(int idCondutor) {
        ArrayList<Viagem> resultado = new ArrayList<>();
        for (Viagem v : viagens) {
            if (v.getIdCondutor() == idCondutor) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    /**
     * Obtem as viagens de uma viatura.
     * @param idViatura ID da viatura
     * @return ArrayList de viagens da viatura
     */
    public ArrayList<Viagem> getViagensPorViatura(int idViatura) {
        ArrayList<Viagem> resultado = new ArrayList<>();
        for (Viagem v : viagens) {
            if (v.getIdViatura() == idViatura) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    /**
     * Obtem o número de viagens.
     * @return número de viagens
     */
    public int getNumeroViagens() {
        return numViagens;
    }
// ==================== VERIFICACAO DE SOBREPOSICAO ====================

    /**
     * Verifica se existe sobreposição para uma VIATURA.
     *
     * @param idViatura ID da viatura a verificar
     * @param inicio Data/hora de início
     * @param fim Data/hora de fim (se for null, assume-se 2 horas - padrão para Reservas)
     * @param excluirId ID da reserva/viagem a excluir da verificação
     * @return true se houver conflito
     */
    public boolean existeSobreposicao(int idViatura, LocalDateTime inicio, LocalDateTime fim, int excluirId) {
        LocalDateTime fimEfetivo = (fim == null) ? inicio.plusHours(2) : fim;

        // 1. Verificar conflito com RESERVAS ATIVAS
        for (int i = 0; i < numReservas; i++) {
            Reserva r = reservas.get(i);

            if (r.getId() != excluirId && r.isAtiva() && r.getIdViatura() == idViatura) {
                // USAR A DATA DE FIM REAL DA RESERVA
                LocalDateTime rFim = r.getDataHoraFim();

                if (verificarConflitoTempo(inicio, fimEfetivo, r.getDataHoraInicio(), rFim)) {
                    return true;
                }
            }
        }

        // 2. Verificar conflito com VIAGENS (mantém-se igual)
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getId() != excluirId && v.getIdViatura() == idViatura) {
                if (verificarConflitoTempo(inicio, fimEfetivo, v.getDataHoraInicio(), v.getDataHoraFim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica se existe sobreposição para uma VIAGEM
     *
     * @param idViatura ID da viatura
     * @param idCondutor ID do condutor
     * @param inicio Data/hora de início
     * @param fim Data/hora de fim
     * @param excluirId ID a excluir (0 se for criação nova)
     * @return true se houver conflito
     */
    public boolean existeSobreposicaoViagem(int idViatura, int idCondutor, LocalDateTime inicio, LocalDateTime fim, int excluirId) {
        // Garantir que não há datas nulas
        LocalDateTime fimEfetivo = (fim == null) ? inicio.plusHours(2) : fim;

        // 1. Verificar se a VIATURA está livre (reutiliza o metodo acima)
        if (existeSobreposicao(idViatura, inicio, fimEfetivo, excluirId)) {
            return true;
        }

        // 2. Verificar se o CONDUTOR está livre (apenas em Viagens, pois Reservas não alocam condutor no início)
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getId() != excluirId && v.getIdCondutor() == idCondutor) {
                if (verificarConflitoTempo(inicio, fimEfetivo, v.getDataHoraInicio(), v.getDataHoraFim())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Metodo auxiliar para comparar dois intervalos de tempo.
     * Lógica: (InicioA < FimB) E (FimA > InicioB) detecta qualquer sobreposição.
     */
    private boolean verificarConflitoTempo(LocalDateTime inicioA, LocalDateTime fimA, LocalDateTime inicioB, LocalDateTime fimB) {
        // Tratamento de segurança para nulos (assume +2h se o fim for nulo no B)
        LocalDateTime finalB = (fimB == null) ? inicioB.plusHours(2) : fimB;

        return inicioA.isBefore(finalB) && fimA.isAfter(inicioB);
    }

    /**
     * Procura um condutor que não tenha viagens marcadas no horário da reserva.
     */
    public int obterCondutorDisponivel(LocalDateTime inicio, LocalDateTime fim) {
        // Percorre todos os condutores da empresa
        for (Condutor c : condutores) {
            boolean ocupado = false;

            // Verifica se este condutor tem alguma viagem que colida com o horário
            for (Viagem v : viagens) {
                if (v.getIdCondutor() == c.getId()) {
                    // Se houver conflito de horário, este condutor não serve
                    if (verificarConflitoTempo(inicio, fim, v.getDataHoraInicio(), v.getDataHoraFim())) {
                        ocupado = true;
                        break;
                    }
                }
            }

            // Se não estiver ocupado, devolve o ID deste condutor
            if (!ocupado) {
                return c.getId();
            }
        }
        return -1; // Nenhum condutor disponível
    }
// ==================== ESTATÍSTICAS E RELATÓRIOS ====================

    /**
     * 1. Obtem a lista de clientes que ja usaram uma determinada viatura (em viagens ou reservas).
     * @param matricula Matricula da viatura
     * @return Lista de clientes unicos
     */
    public ArrayList<Cliente> getClientesPorViatura(String matricula) {
        ArrayList<Cliente> resultado = new ArrayList<>();
        Viatura viatura = procurarViaturaPorMatricula(matricula);

        if (viatura == null) {
            return resultado; // Retorna lista vazia se viatura nao existir
        }

        int idViatura = viatura.getId();

        // 1. Verificar nas Reservas
        for (Reserva r : reservas) {
            if (r.getIdViatura() == idViatura) {
                Cliente c = procurarClientePorId(r.getIdCliente());
                // Adiciona apenas se o cliente existir e AINDA NAO estiver na lista
                if (c != null && !resultado.contains(c)) {
                    resultado.add(c);
                }
            }
        }

        // 2. Verificar nas Viagens
        for (Viagem v : viagens) {
            if (v.getIdViatura() == idViatura) {
                Cliente c = procurarClientePorId(v.getIdCliente());
                if (c != null && !resultado.contains(c)) {
                    resultado.add(c);
                }
            }
        }
        return resultado;
    }

    /**
     * 2. Calcula o valor total faturado por um condutor num intervalo de datas.
     * @param idCondutor ID do condutor
     * @param inicio Data de inicio
     * @param fim Data de fim
     * @return Valor total faturado
     */
    public double calcularFaturacaoCondutor(int idCondutor, LocalDateTime inicio, LocalDateTime fim) {
        double totalFaturado = 0.0;

        for (Viagem v : viagens) {
            // Verifica se a viagem e deste condutor
            if (v.getIdCondutor() == idCondutor) {
                LocalDateTime dataViagem = v.getDataHoraInicio();

                // Verifica se a data da viagem esta dentro do intervalo [inicio, fim]
                // (Data >= Inicio) E (Data <= Fim)
                boolean depoisDoInicio = dataViagem.isEqual(inicio) || dataViagem.isAfter(inicio);
                boolean antesDoFim = dataViagem.isEqual(fim) || dataViagem.isBefore(fim);

                if (depoisDoInicio && antesDoFim) {
                    totalFaturado += v.getCusto();
                }
            }
        }
        return totalFaturado;
    }

    /**
     * 3.Calcula a distância média das viagens num intervalo de datas.
     * @param inicio Data de inicio
     * @param fim Data de fim
     * @return Média de KMs (ou 0 se não houver viagens)
     */
    public double getDistanciaMediaViagens(LocalDateTime inicio, LocalDateTime fim) {
        double totalKms = 0.0;
        int contadorViagens = 0;

        for (Viagem v : viagens) {
            LocalDateTime data = v.getDataHoraInicio();
            // Verifica se a viagem está dentro do intervalo
            if ((data.isEqual(inicio) || data.isAfter(inicio)) &&
                    (data.isEqual(fim) || data.isBefore(fim))) {

                totalKms += v.getKms();
                contadorViagens++;
            }
        }

        if (contadorViagens == 0) return 0.0;

        return totalKms / contadorViagens;
    }

    /**
     * Auxiliar: Calcula os KMs totais de uma viatura num intervalo.
     */
    public double getKmsViaturaEntreDatas(int idViatura, LocalDateTime inicio, LocalDateTime fim) {
        double totalKms = 0.0;
        for (Viagem v : viagens) {
            if (v.getIdViatura() == idViatura) {
                // Verificar datas
                LocalDateTime data = v.getDataHoraInicio();
                if ((data.isEqual(inicio) || data.isAfter(inicio)) &&
                        (data.isEqual(fim) || data.isBefore(fim))) {
                    totalKms += v.getKms();
                }
            }
        }
        return totalKms;
    }


    /**
     * 4. Encontra o destino mais solicitado (em Reservas e Viagens) num intervalo.
     * @param inicio Data de inicio
     * @param fim Data de fim
     * @return O nome do destino mais popular (ou null se nao houver dados)
     */
    public String getDestinoMaisSolicitado(LocalDateTime inicio, LocalDateTime fim) {
        ArrayList<String> nomesDestinos = new ArrayList<>();
        ArrayList<Integer> contadores = new ArrayList<>();

        // 1. Processar Reservas
        for (Reserva r : reservas) {
            // Verificar Datas (Reservas ativas ou passadas contam)
            LocalDateTime data = r.getDataHoraInicio();
            if ((data.isEqual(inicio) || data.isAfter(inicio)) &&
                    (data.isEqual(fim) || data.isBefore(fim))) {

                contabilizarDestino(r.getMoradaDestino(), nomesDestinos, contadores);
            }
        }

        // 2. Processar Viagens
        for (Viagem v : viagens) {
            LocalDateTime data = v.getDataHoraInicio();
            if ((data.isEqual(inicio) || data.isAfter(inicio)) &&
                    (data.isEqual(fim) || data.isBefore(fim))) {

                contabilizarDestino(v.getMoradaDestino(), nomesDestinos, contadores);
            }
        }

        // 3. Descobrir o vencedor
        String vencedor = null;
        int maxVezes = -1;

        for (int i = 0; i < nomesDestinos.size(); i++) {
            if (contadores.get(i) > maxVezes) {
                maxVezes = contadores.get(i);
                vencedor = nomesDestinos.get(i);
            }
        }

        if (vencedor != null) {
            return vencedor + " (" + maxVezes + " vezes)";
        }
        return null;
    }

    /**
     * Metodo auxiliar privado para ajudar na contagem
     * @param destino Destino
     * @param nomes Nomes
     * @param counts
     */
    private void contabilizarDestino(String destino, ArrayList<String> nomes, ArrayList<Integer> counts) {
        if (destino == null || destino.isEmpty()) return;

        // Normalizar (ignorar maiusculas/minusculas)
        String dest = destino.trim().toUpperCase();

        int index = nomes.indexOf(dest);

        if (index == -1) {
            // Se ainda nao existe, adiciona com contagem 1
            nomes.add(dest);
            counts.add(1);
        } else {
            // Se ja existe, incrementa o contador dessa posicao
            int valorAtual = counts.get(index);
            counts.set(index, valorAtual + 1);
        }
    }

    /**
     * 5. Obtem a lista de clientes que fizeram viagens com distancia dentro de um intervalo.
     * @param minKms Distancia minima
     * @param maxKms Distancia maxima
     * @return Lista de clientes unicos
     */
    public ArrayList<Cliente> getClientesComViagensEntreKms(double minKms, double maxKms) {
        ArrayList<Cliente> resultado = new ArrayList<>();

        for (Viagem v : viagens) {
            double kms = v.getKms();

            // Verifica se a distancia da viagem esta no intervalo
            if (kms >= minKms && kms <= maxKms) {
                // Vai buscar o cliente pelo ID
                Cliente c = procurarClientePorId(v.getIdCliente());

                // Se o cliente existe e ainda nao esta na lista, adiciona
                if (c != null && !resultado.contains(c)) {
                    resultado.add(c);
                }
            }
        }
        return resultado;
    }

    /**
     * Obtem a lista de clientes que fizeram viagens num intervalo de datas.
     * @param inicio Data de inicio
     * @param fim Data de fim
     * @return Lista de clientes unicos
     */
    public ArrayList<Cliente> getClientesComViagensEntreDatas(LocalDateTime inicio, LocalDateTime fim) {
        ArrayList<Cliente> resultado = new ArrayList<>();

        for (Viagem v : viagens) {
            LocalDateTime data = v.getDataHoraInicio();

            // Verifica se a data da viagem está dentro do intervalo
            boolean dentroDoPrazo = (data.isEqual(inicio) || data.isAfter(inicio)) &&
                    (data.isEqual(fim) || data.isBefore(fim));

            if (dentroDoPrazo) {
                // Vai buscar o cliente pelo ID
                Cliente c = procurarClientePorId(v.getIdCliente());

                // Se o cliente existe e ainda nao esta na lista, adiciona
                if (c != null && !resultado.contains(c)) {
                    resultado.add(c);
                }
            }
        }
        return resultado;
    }

    /**

     Converte uma reserva numa viagem.
     @param idReserva ID da reserva
     @param idCondutor ID do condutor
     @param dataHoraFim Data/hora de fim
     @param kmsReais Kms reais percorridos
     @return Viagem criada ou null se erro*/
    public Viagem converterReservaEmViagem(int idReserva, int idCondutor, LocalDateTime dataHoraFim, double kmsReais) {
        Reserva reserva = procurarReservaPorId(idReserva);
        if (reserva == null || !reserva.isAtiva()) {
            return null;}
        Condutor condutor = procurarCondutorPorId(idCondutor);
        if (condutor == null) {
            return null;}

        // Calcular custo
        double custo = calcularCustoViagem(kmsReais);

        // Criar viagem
        Viagem viagem = new Viagem(
                idCondutor,
                reserva.getIdCliente(),
                reserva.getIdViatura(),
                reserva.getDataHoraInicio(),
                dataHoraFim,
                reserva.getMoradaOrigem(),
                reserva.getMoradaDestino(),
                kmsReais,
                custo
        );

        // Desativar reserva
        reserva.setAtiva(false);

        // Adicionar viagem
        viagens.add(viagem);
        numViagens++;

        return viagem;
    }
    /**
     * Calcula o custo de uma viagem.
     * @param kms Distancia em km
     * @return Custo em euros
     */
    public double calcularCustoViagem(double kms) {
        return taxaBase + (kms * precoPorKm);
    }
}

