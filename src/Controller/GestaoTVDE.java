package Controller;
import Model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestaoTVDE {

    private String nome;

    private ArrayList<Condutor> condutores;
    private ArrayList<Viatura> viaturas;
    private ArrayList<Cliente> clientes;
    private ArrayList<Reserva> reservas;
    private ArrayList<Viagem> viagens;
    private int numCondutores;
    private int numViaturas;
    private int numClientes;
    private int numReservas;
    private int numViagens;
    private double precoPorKm;
    private double taxaBase;


    public GestaoTVDE () {
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoPorKm() {
        return precoPorKm;
    }

    public void setPrecoPorKm(double precoPorKm) {
        this.precoPorKm = precoPorKm;
    }

    public double getTaxaBase() {
        return taxaBase;
    }

    public void setTaxaBase(double taxaBase) {
        this.taxaBase = taxaBase;
    }

    public ArrayList<Condutor> getCondutores() {
        return condutores;
    }

    public ArrayList<Viatura> getViaturas() {
        return viaturas;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public ArrayList<Viagem> getViagens() {
        return viagens;
    }

    // ==================== OPERACOES CRUD CONDUTORES ====================

    public boolean adicionarCondutor(Condutor condutor) {
        if (condutor == null) {
            return false;
        }
        condutores.add(condutor);
        numCondutores++;
        return true;
    }

    public Condutor procurarCondutorPorId(int id) {
        for (int i = 0; i < numCondutores; i++) {
            Condutor c = condutores.get(i);
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public Condutor procurarCondutorPorNif(String nif) {
        for (int i = 0; i < numCondutores; i++) {
            Condutor c = condutores.get(i);
            if (c.getNif().equals(nif)) {
                return c;
            }
        }
        return null;
    }

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

    public boolean condutorTemViagens(int idCondutor) {
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getIdCondutor() == idCondutor) {
                return true;
            }
        }
        return false;
    }


    public int getNumeroCondutores() {
        return numCondutores;
    }


    // ==================== OPERACOES CRUD VIATURAS ====================


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


    public Viatura procurarViaturaPorId(int id) {
        for (int i = 0; i < numViaturas; i++) {
            Viatura v = viaturas.get(i);
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }


    public Viatura procurarViaturaPorMatricula(String matricula) {
        for (int i = 0; i < numViaturas; i++) {
            Viatura v = viaturas.get(i);
            if (v.getMatricula().equalsIgnoreCase(matricula)) {
                return v;
            }
        }
        return null;
    }


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


    public boolean viaturaTemViagens(int idViatura) {
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getIdViatura() == idViatura) {
                return true;
            }
        }
        return false;
    }


    public boolean viaturaTemReservas(int idViatura) {
        for (int i = 0; i < numReservas; i++) {
            Reserva r = reservas.get(i);
            if (r.getIdViatura() == idViatura && r.isAtiva()) {
                return true;
            }
        }
        return false;
    }


    public int getNumeroViaturas() {
        return numViaturas;
    }



    // ==================== OPERACOES CRUD CLIENTES ====================


    public boolean adicionarCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        clientes.add(cliente);
        numClientes++;
        return true;
    }


    public Cliente procurarClientePorId(int id) {
        for (int i = 0; i < numClientes; i++) {
            Cliente c = clientes.get(i);
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }


    public Cliente procurarClientePorNif(String nif) {
        for (int i = 0; i < numClientes; i++) {
            Cliente c = clientes.get(i);
            if (c.getNif().equals(nif)) {
                return c;
            }
        }
        return null;
    }


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


    public boolean clienteTemViagens(int idCliente) {
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getIdCliente() == idCliente) {
                return true;
            }
        }
        return false;
    }


    public boolean clienteTemReservas(int idCliente) {
        for (int i = 0; i < numReservas; i++) {
            Reserva r = reservas.get(i);
            if (r.getIdCliente() == idCliente && r.isAtiva()) {
                return true;
            }
        }
        return false;
    }


    public int getNumeroClientes() {
        return numClientes;
    }

    // ==================== OPERACOES CRUD RESERVAS ====================


    public boolean adicionarReserva(Reserva reserva) {
        if (reserva == null) {
            return false;
        }
        // Verificar sobreposicao
        if (existeSobreposicao(reserva.getIdViatura(), reserva.getDataHoraInicio(), null, 0)) {
            return false;
        }
        reservas.add(reserva);
        numReservas++;
        return true;
    }


    public Reserva procurarReservaPorId(int id) {
        for (int i = 0; i < numReservas; i++) {
            Reserva r = reservas.get(i);
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }


    public boolean removerReserva(int id) {
        Reserva reserva = procurarReservaPorId(id);
        if (reserva == null) {
            return false;
        }
        reservas.remove(reserva);
        numReservas--;
        return true;
    }


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

    public int getNumeroReservas() {
        return numReservas;
    }



    // ==================== OPERACOES CRUD VIAGENS ====================


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


    public Viagem procurarViagemPorId(int id) {
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }


    public boolean removerViagem(int id) {
        Viagem viagem = procurarViagemPorId(id);
        if (viagem == null) {
            return false;
        }
        viagens.remove(viagem);
        numViagens--;
        return true;
    }


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


    public int getViagensClienteEntreDatas(int idCliente, LocalDateTime dataInicio, LocalDateTime dataFim, ArrayList<Viagem> resultado) {
        int numResultados = 0;

        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);

            // Vamos buscar a data da viagem para uma variável para ficar mais legível
            // Nota: Verifica se no teu Model o método é getDataHoraInicio() ou getDataInicio()
            LocalDateTime dataViagem = v.getDataHoraInicio();

            // Lógica: (data >= inicio) && (data <= fim)
            // Em Java LocalDateTime traduz-se para:
            // (!data.isBefore(inicio)) && (!data.isAfter(fim))
            // OU usando isAfter/isEqual explicitamente:

            boolean dentroDoInicio = dataViagem.isEqual(dataInicio) || dataViagem.isAfter(dataInicio);
            boolean dentroDoFim = dataViagem.isEqual(dataFim) || dataViagem.isBefore(dataFim);

            if (v.getIdCliente() == idCliente && dentroDoInicio && dentroDoFim) {
                resultado.add(v);
                numResultados++;
            }
        }
        return numResultados;
    }


    public int getNumeroViagens() {
        return numViagens;
    }
// ==================== VERIFICACAO DE SOBREPOSICAO (Refatorado) ====================

    /**
     * Verifica se existe sobreposição para uma VIATURA (usado ao criar Reservas ou Viagens).
     *
     * @param idViatura ID da viatura a verificar
     * @param inicio Data/hora de início
     * @param fim Data/hora de fim (se for null, assume-se 2 horas - padrão para Reservas)
     * @param excluirId ID da reserva/viagem a excluir da verificação (usar 0 se for uma criação nova)
     * @return true se houver conflito
     */
    public boolean existeSobreposicao(int idViatura, LocalDateTime inicio, LocalDateTime fim, int excluirId) {
        // Define o fim efetivo: se vier null, adiciona 2 horas (estimativa padrão para reservas)
        LocalDateTime fimEfetivo = (fim == null) ? inicio.plusHours(2) : fim;

        // 1. Verificar conflito com RESERVAS ATIVAS
        for (int i = 0; i < numReservas; i++) {
            Reserva r = reservas.get(i);
            // Verifica se: não é a própria reserva, está ativa, e é a mesma viatura
            if (r.getId() != excluirId && r.isAtiva() && r.getIdViatura() == idViatura) {
                // Reservas guardadas não têm data de fim explícita, assumimos +2h
                LocalDateTime rFim = r.getDataHoraInicio().plusHours(2);

                if (verificarConflitoTempo(inicio, fimEfetivo, r.getDataHoraInicio(), rFim)) {
                    return true;
                }
            }
        }

        // 2. Verificar conflito com VIAGENS (histórico ou agendadas)
        for (int i = 0; i < numViagens; i++) {
            Viagem v = viagens.get(i);
            // Verifica se: não é a própria viagem e é a mesma viatura
            if (v.getId() != excluirId && v.getIdViatura() == idViatura) {
                if (verificarConflitoTempo(inicio, fimEfetivo, v.getDataHoraInicio(), v.getDataHoraFim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica se existe sobreposição para uma VIAGEM (que valida Viatura E Condutor).
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

        // 1. Verificar se a VIATURA está livre (reutiliza o método acima)
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
     * Método auxiliar para comparar dois intervalos de tempo.
     * Lógica: (InicioA < FimB) E (FimA > InicioB) detecta qualquer sobreposição.
     */
    private boolean verificarConflitoTempo(LocalDateTime inicioA, LocalDateTime fimA, LocalDateTime inicioB, LocalDateTime fimB) {
        // Tratamento de segurança para nulos (assume +2h se o fim for nulo no B)
        LocalDateTime finalB = (fimB == null) ? inicioB.plusHours(2) : fimB;

        return inicioA.isBefore(finalB) && fimA.isAfter(inicioB);
    }


}

