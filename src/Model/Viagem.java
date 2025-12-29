package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Viagem {


    private static int contadorId = 1;

    private int id;

    private int idCondutor;

    private int idCliente;

    private int idViatura;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    private String moradaOrigem;

    private String moradaDestino;

    private double kms;

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
     * Construtor com ID especifico (usado para leitura de ficheiros).
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

    public int getId() {
        return id;
    }

    public int getIdCondutor() {
        return idCondutor;
    }

    public void setIdCondutor(int idCondutor) {
        this.idCondutor = idCondutor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdViatura() {
        return idViatura;
    }

    public void setIdViatura(int idViatura) {
        this.idViatura = idViatura;
    }


    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }


    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }


    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }


    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }


    public String getMoradaOrigem() {
        return moradaOrigem;
    }

    public void setMoradaOrigem(String moradaOrigem) {
        this.moradaOrigem = moradaOrigem;
    }

    public String getMoradaDestino() {
        return moradaDestino;
    }

    public void setMoradaDestino(String moradaDestino) {
        this.moradaDestino = moradaDestino;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public static void reiniciarContador() {
        contadorId = 1;
    }

    public static void setContadorId(int valor) {
        contadorId = valor;
    }

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
     * Representacao textual da viagem.
     * @return String com informacao da viagem
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



