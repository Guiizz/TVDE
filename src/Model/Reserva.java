package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Classe que representa uma reserva de viagem. */

public class Reserva {
    /** Contador para gerar ID's unicos*/
    private static int contadorId = 1;
    /** Identificador unico da reserva. */
    private int id;
    /** ID do cliente que fez a reserva */
    private int idCliente;
    /** ID da viatura reservada */
    private int idViatura;
    /** Data e hora do inicio da reserva. */
    private LocalDateTime dataHoraInicio;
    /** Morada de origem. */
    private String moradaOrigem;
    /** Morada do destino. */
    private String moradaDestino;
    /** Distancia estimada em km. */
    private double kms;
    /** Indica se a reserva esta ativa. */
    private boolean ativa;

    /**
     * Construtor da classe Reserva.
     * @param idCliente ID do cliente
     * @param idViatura ID da viatura
     * @param dataHoraInicio Dara e hora de inicio
     * @param moradaOrigem Morada de origem
     * @param moradaDestino Morada de destino
     * @param kms Distancia em KM
     */
    public Reserva(int idCliente, int idViatura, LocalDateTime dataHoraInicio,
                   String moradaOrigem, String moradaDestino, double kms) {
        this.id = contadorId++;
        this.idCliente = idCliente;
        this.idViatura = idViatura;
        this.dataHoraInicio = dataHoraInicio;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.kms = kms;
        this.ativa = true;
    }

    /**
     * Construtor com ID especifico (usado para leitura de ficheiros).
     *
     * @param id Identificador da reserva
     * @param idCliente ID do cliente
     * @param idViatura ID da viatura
     * @param dataHoraInicio Data/hora de inicio
     * @param moradaOrigem Morada de origem
     * @param moradaDestino Morada de destino
     * @param kms Distancia estimada em km
     * @param ativa Estado da reserva
     */
    public Reserva(int id, int idCliente, int idViatura, LocalDateTime dataHoraInicio,
                   String moradaOrigem, String moradaDestino, double kms, boolean ativa) {
        this.id = id;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
        this.idCliente = idCliente;
        this.idViatura = idViatura;
        this.dataHoraInicio = dataHoraInicio;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.kms = kms;
        this.ativa = ativa;
    }

    // Getters e Setters

    /**
     * Obtem o ID da reserva.
     * @return ID da reserva
     */

    public int getId() {
        return id;
    }
    /**
     * Define o ID da reserva.
     * @param id Novo ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtem ID do cliente.
     * @return ID do cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Define ID do cliente
     * @param idCliente novo ID cliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtem ID da viatura.
     * @return ID da viatura
     */
    public int getIdViatura() {
        return idViatura;
    }

    /**
     * Define ID da viatura
     * @param idViatura novo ID viatura
     */
    public void setIdViatura(int idViatura) {
        this.idViatura = idViatura;
    }

    /**
     * Obtem a data e a hora do inicio da reserva.
     * @return data e a hora do inicio da reserva
     */
    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    /**
     * Define data e a hora do inicio da reserva.
     * @param dataHoraInicio nova data e a hora do inicio da reserva
     */
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    /**
     * Obtem morada de origem.
     * @return morada de origem
     */
    public String getMoradaOrigem() {
        return moradaOrigem;
    }

    /**
     * Define morada de origem.
     * @param moradaOrigem Nova morada de origem
     */
    public void setMoradaOrigem(String moradaOrigem) {
        this.moradaOrigem = moradaOrigem;
    }

    /**
     * Obtem morada de destino.
     * @return morada de destino
     */
    public String getMoradaDestino() {
        return moradaDestino;
    }

    /**
     * Define morada de destino.
     * @param moradaDestino Nova morada de destino
     */
    public void setMoradaDestino(String moradaDestino) {
        this.moradaDestino = moradaDestino;
    }

    /**
     * Obtem distancia estimada em KM.
     * @return distancia estimada em KM
     */
    public double getKms() {
        return kms;
    }

    /**
     * Define distancia estimada em KM.
     * @param kms Nova distancia estimada em KM
     */
    public void setKms(double kms) {
        this.kms = kms;
    }

    /**
     * Verifica se a reserva esta ativa.
     * @return true se ativa, false caso contrario
     */
    public boolean isAtiva() {
        return ativa;
    }

    /**
     * Define o estado da reserva.
     * @param ativa Novo estado
     */
    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    /**
     * Reinicia o contador de IDs.
     * Usado ao carregar dados de ficheiro.
     */
    public static void reiniciarContador() {
        contadorId = 1;
    }

    /**
     * Define o valor do contador de IDs.
     * @param valor Novo valor do contador
     */
    public static void setContadorId(int valor) {
        contadorId = valor;
    }

    /**
     * Obtem o valor atual do contador de IDs.
     * @return Valor do contador
     */
    public static int getContadorId() {
        return contadorId;
    }

    /**
     * Converte o objeto para formato de ficheiro.
     * @return String formatada para gravação em ficheiro
     */
    public String paraFicheiro() {
        return id + ";" + idCliente + ";" + idViatura + ";" +
                dataHoraInicio + ";" +
                moradaOrigem + ";" + moradaDestino + ";" + kms + ";" + ativa;
    }

    /**
     * Representação da informação da reserva.
     * @return String com informação da reserva
     */
    @Override
    public String toString(){
        DateTimeFormatter data = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String estado = ativa ? "Ativa" : "Cancelada";
        return "Reserva ID: " + id + " | Cliente ID: " + idCliente + " | Data: " + dataHoraInicio.format(data) + " | Estado: " + estado;
    }
    /**
     * Representação da informação detalhada da reserva.
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
                "\nData/Hora Inicio: " + dataHoraInicio.format(data) +
                "\nOrigem: " + moradaOrigem +
                "\nDestino: " + moradaDestino +
                "\nKms: " + String.format("%.2f", kms) +
                "\nEstado: " + estado;
    }
}