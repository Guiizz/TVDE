package Classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa uma reserva de viagem na empresa TVDE.
 */
public class Reserva {
    private static int contadorId = 1;
    private int id;
    private int idCliente;
    private int idViatura;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String moradaOrigem;
    private String moradaDestino;
    private double kms;
    private boolean ativa;

    /**
     * Construtor da Classe Reserva.
     *
     * @param idCliente ID do cliente que faz a reserva
     * @param idViatura ID da viatura reservada
     * @param dataHoraInicio Data e hora de início
     * @param dataHoraFim Data e hora de fim
     * @param moradaOrigem Morada de origem
     * @param moradaDestino Morada de destino
     * @param kms Distância estimada em Kms
     */
    public Reserva(int idCliente, int idViatura, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
                   String moradaOrigem, String moradaDestino, double kms) {
        this.id = contadorId++;
        this.idCliente = idCliente;
        this.idViatura = idViatura;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.kms = kms;
        this.ativa = true;
    }

    /**
     * Construtor com ID específico (usado para leitura de ficheiros).
     *
     * @param id Identificador da reserva
     * @param idCliente ID do cliente
     * @param idViatura ID da viatura
     * @param dataHoraInicio Data e hora de início
     * @param dataHoraFim Data e hora de fim
     * @param moradaOrigem Morada de origem
     * @param moradaDestino Morada de destino
     * @param kms Distância estimada
     * @param ativa Estado da reserva (ativa ou não)
     */
    public Reserva(int id, int idCliente, int idViatura, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
                   String moradaOrigem, String moradaDestino, double kms, boolean ativa) {
        this.id = id;
        if (id >= contadorId) contadorId = id + 1;
        this.idCliente = idCliente;
        this.idViatura = idViatura;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.kms = kms;
        this.ativa = ativa;
    }

    // --- GETTERS E SETTERS ---

    /**
     * Obtem a data e hora de fim da reserva.
     * @return Data e hora de fim
     */
    public LocalDateTime getDataHoraFim() { return dataHoraFim; }

    /**
     * Define a data e hora de fim da reserva.
     * @param dataHoraFim Nova data e hora de fim
     */
    public void setDataHoraFim(LocalDateTime dataHoraFim) { this.dataHoraFim = dataHoraFim; }

    /**
     * Obtem o ID da reserva.
     * @return ID da reserva
     */
    public int getId() { return id; }

    /**
     * Define o ID da reserva.
     * @param id Novo ID
     */
    public void setId(int id) { this.id = id; }

    /**
     * Obtem o ID do cliente associado.
     * @return ID do cliente
     */
    public int getIdCliente() { return idCliente; }

    /**
     * Define o ID do cliente associado.
     * @param idCliente Novo ID de cliente
     */
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    /**
     * Obtem o ID da viatura associada.
     * @return ID da viatura
     */
    public int getIdViatura() { return idViatura; }

    /**
     * Define o ID da viatura associada.
     * @param idViatura Novo ID de viatura
     */
    public void setIdViatura(int idViatura) { this.idViatura = idViatura; }

    /**
     * Obtem a data e hora de início.
     * @return Data e hora de início
     */
    public LocalDateTime getDataHoraInicio() { return dataHoraInicio; }

    /**
     * Define a data e hora de início.
     * @param dataHoraInicio Nova data e hora de início
     */
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) { this.dataHoraInicio = dataHoraInicio; }

    /**
     * Obtem a morada de origem.
     * @return Morada de origem
     */
    public String getMoradaOrigem() { return moradaOrigem; }

    /**
     * Define a morada de origem.
     * @param moradaOrigem Nova morada de origem
     */
    public void setMoradaOrigem(String moradaOrigem) { this.moradaOrigem = moradaOrigem; }

    /**
     * Obtem a morada de destino.
     * @return Morada de destino
     */
    public String getMoradaDestino() { return moradaDestino; }

    /**
     * Define a morada de destino.
     * @param moradaDestino Nova morada de destino
     */
    public void setMoradaDestino(String moradaDestino) { this.moradaDestino = moradaDestino; }

    /**
     * Obtem a distância em Kms.
     * @return Distância em Kms
     */
    public double getKms() { return kms; }

    /**
     * Define a distância em Kms.
     * @param kms Nova distância
     */
    public void setKms(double kms) { this.kms = kms; }

    /**
     * Verifica se a reserva está ativa.
     * @return true se ativa, false se cancelada/concluída
     */
    public boolean isAtiva() { return ativa; }

    /**
     * Define o estado da reserva.
     * @param ativa Novo estado (true=ativa, false=cancelada)
     */
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    // --- MÉTODOS ESTÁTICOS ---

    /**
     * Reinicia o contador de IDs.
     */
    public static void reiniciarContador() { contadorId = 1; }

    /**
     * Define o valor do contador de IDs.
     * @param valor Novo valor do contador
     */
    public static void setContadorId(int valor) { contadorId = valor; }

    /**
     * Obtem o valor atual do contador de IDs.
     * @return Valor do contador
     */
    public static int getContadorId() { return contadorId; }

    // --- OUTROS MÉTODOS ---

    /**
     * Converte o objeto para formato de ficheiro.
     * @return String formatada para gravação em ficheiro
     */
    public String paraFicheiro() {
        // Adicionada a dataHoraFim na string
        return id + ";" + idCliente + ";" + idViatura + ";" +
                dataHoraInicio + ";" + dataHoraFim + ";" +
                moradaOrigem + ";" + moradaDestino + ";" + kms + ";" + ativa;
    }

    /**
     * Representação simplificada da reserva.
     * @return String com resumo da reserva
     */
    @Override
    public String toString() {
        DateTimeFormatter data = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String estado = ativa ? "Ativa" : "Cancelada";
        return "Reserva ID: " + id + " | Data: " + dataHoraInicio.format(data) + " ate " + dataHoraFim.format(data) + " | Estado: " + estado;
    }

    /**
     * Representação detalhada da reserva.
     * @return String com toda a informação da reserva
     */
    public String toStringDetalhado() {
        DateTimeFormatter data = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String estado = ativa ? "Ativa" : "Cancelada";
        String viatura = idViatura > 0 ? String.valueOf(idViatura) : "Nao especificada";
        return "=== RESERVA ===" +
                "\nID: " + id +
                "\nID Cliente: " + idCliente +
                "\nID Viatura: " + viatura +
                "\nInicio: " + dataHoraInicio.format(data) +
                "\nFim:    " + dataHoraFim.format(data) +
                "\nOrigem: " + moradaOrigem +
                "\nDestino: " + moradaDestino +
                "\nKms: " + String.format("%.2f", kms) +
                "\nEstado: " + estado;
    }
}