package Classes;

import java.time.LocalDateTime;

/** Classe que representa uma viagem realizada. */
public class Viagem {

    /** Contador para gerar ID's unicos*/
    private static int contadorId = 1;
    /** Identificador unico da viagem. */
    private int id;
    /** ID do condutor */
    private int idCondutor;
    /** ID do cliente */
    private int idCliente;
    /** ID da viatura */
    private int idViatura;
    /** Data e hora do inicio da viagem */
    private LocalDateTime dataHoraInicio;
    /** Data e hora do fim da viagem */
    private LocalDateTime dataHoraFim;
    /** morada de origem */
    private String moradaOrigem;
    /** morada de destino */
    private String moradaDestino;
    /** Disatancia estimada em KM */
    private double kms;
    /** Custo da viagem em euros. */
    private double custo;


    /**
     * Construtor com parametros.
     *
     * @param idCondutor ID do condutor
     * @param idCliente ID do cliente
     * @param idViatura ID da viatura
     * @param dataHoraInicio Data/hora de inicio
     * @param dataHoraFim Data/hora de fim
     * @param moradaOrigem Morada de origem
     * @param moradaDestino Morada de destino
     * @param kms Distancia em km
     * @param custo Custo da viagem
     */
    public Viagem(int idCondutor, int idCliente, int idViatura,
                  LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
                  String moradaOrigem, String moradaDestino, double kms, double custo) {
        this.id = contadorId++;
        this.idCondutor = idCondutor;
        this.idCliente = idCliente;
        this.idViatura = idViatura;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.kms = kms;
        this.custo = custo;
    }

    /**
     * Construtor com ID especifico
     *
     * @param id Identificador da viagem
     * @param idCondutor ID do condutor
     * @param idCliente ID do cliente
     * @param idViatura ID da viatura
     * @param dataHoraInicio Data/hora de inicio
     * @param dataHoraFim Data/hora de fim
     * @param moradaOrigem Morada de origem
     * @param moradaDestino Morada de destino
     * @param kms Distancia em km
     * @param custo Custo da viagem
     */
    public Viagem(int id, int idCondutor, int idCliente, int idViatura,
                  LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
                  String moradaOrigem, String moradaDestino, double kms, double custo) {
        this.id = id;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
        this.idCondutor = idCondutor;
        this.idCliente = idCliente;
        this.idViatura = idViatura;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.kms = kms;
        this.custo = custo;
    }

    // Getters e Setters

    /**
     * Obtem ID da viagem.
     * @return ID da viagem
     */
    public int getId() {
        return id;
    }

    /**
     * Obtem ID do condutor.
     * @return ID do condutor.
     */
    public int getIdCondutor() {
        return idCondutor;
    }

    /**
     * Define ID do condutor
     * @param idCondutor novo ID condutor
     */
    public void setIdCondutor(int idCondutor) {
        this.idCondutor = idCondutor;
    }

    /**
     * Obtem ID do cliente.
     * @return ID do cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Define ID do cliente
     * @param idCliente Novo ID cliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtem ID da viatura.
     * @return ID da viatura.
     */
    public int getIdViatura() {
        return idViatura;
    }

    /**
     * Define ID da viatura.
     * @param idViatura Novo ID da viatura
     */
    public void setIdViatura(int idViatura) {
        this.idViatura = idViatura;
    }

    /**
     * Obtem a data e a hora de inico da viagem.
     * @return data e a hora de inico da viagem
     */
    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    /**
     * Define data e a hora de inico da viagem.
     * @param dataHoraInicio Nova data e a hora de inico da viagem
     */
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    /**
     * Obtem data e a hora de fim da viagem.
     * @return data e a hora de fim da viagem
     */
    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    /**
     * Define data e a hora de fim da viagem.
     * @param dataHoraFim Nova data e a hora de fim da viagem
     */
    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    /**
     * Obtem morada de origem
     * @return morada de origem
     */
    public String getMoradaOrigem() {
        return moradaOrigem;
    }

    /**
     * Define morada de origem
     * @param moradaOrigem Nova morada de origem
     */
    public void setMoradaOrigem(String moradaOrigem) {
        this.moradaOrigem = moradaOrigem;
    }

    /**
     * Obtem a morada de destino.
     * @return morada de destino
     */
    public String getMoradaDestino() {
        return moradaDestino;
    }

    /**
     * Define a morada de destino
     * @param moradaDestino Nova morada de destino
     */
    public void setMoradaDestino(String moradaDestino) {
        this.moradaDestino = moradaDestino;
    }

    /**
     * Obtem a distancia estimada em KM.
     * @return distancia estimada em KM.
     */
    public double getKms() {
        return kms;
    }

    /**
     * Define a distancia estimada em KM.
     * @param kms Nova distancia estimada em KM
     */
    public void setKms(double kms) {
        this.kms = kms;
    }

    /**
     * Obtem Custo da viagem em euros
     * @return custo da viagem em euros
     */
    public double getCusto() {
        return custo;
    }

    /**
     * Define o custo da viagem em euros
     * @param custo Novo custo em euros
     */
    public void setCusto(double custo) {
        this.custo = custo;
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
     * @return String formatada para gravacao em ficheiro
     */
    public String paraFicheiro() {
        return id + ";" + idCondutor + ";" + idCliente + ";" + idViatura + ";" +
                dataHoraInicio + ";" + dataHoraFim + ";" +
                moradaOrigem + ";" + moradaDestino + ";" + kms + ";" + custo;
    }

    /**
     * Representação da informação da viagem.
     * @return String com informação da viagem
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Cliente ID: " + idCliente + " | " +
                dataHoraInicio + " | " + String.format("%.2f", custo) + " EUR";
    }

    /**
     * Representacao detalhada da viagem.
     * @return String com toda a informacao da viagem
     */
    public String toStringDetalhado() {
        return "=== VIAGEM ===" +
                "\nID: " + id +
                "\nID Condutor: " + idCondutor +
                "\nID Cliente: " + idCliente +
                "\nID Viatura: " + idViatura +
                "\nData/Hora Inicio: " + dataHoraInicio +
                "\nData/Hora Fim: " + dataHoraFim +
                "\nOrigem: " + moradaOrigem +
                "\nDestino: " + moradaDestino +
                "\nKms: " + String.format("%.2f", kms) +
                "\nCusto: " + String.format("%.2f", custo) + " EUR";
    }
}



