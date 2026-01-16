package Classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    // Construtor para leitura de ficheiros
    public Reserva(int id, int idCliente, int idViatura, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
                   String moradaOrigem, String moradaDestino, double kms, boolean ativa) {
        this.id = id;
        if (id >= contadorId) contadorId = id + 1;
        this.idCliente = idCliente;
        this.idViatura = idViatura;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim; // <--- GUARDAR DATA FIM
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.kms = kms;
        this.ativa = ativa;
    }

    // --- GETTERS E SETTERS ---

    public LocalDateTime getDataHoraFim() { return dataHoraFim; }
    public void setDataHoraFim(LocalDateTime dataHoraFim) { this.dataHoraFim = dataHoraFim; }

    // (Mantém os outros Getters e Setters existentes para id, cliente, viatura, inicio, etc...)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdViatura() { return idViatura; }
    public void setIdViatura(int idViatura) { this.idViatura = idViatura; }
    public LocalDateTime getDataHoraInicio() { return dataHoraInicio; }
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) { this.dataHoraInicio = dataHoraInicio; }
    public String getMoradaOrigem() { return moradaOrigem; }
    public void setMoradaOrigem(String moradaOrigem) { this.moradaOrigem = moradaOrigem; }
    public String getMoradaDestino() { return moradaDestino; }
    public void setMoradaDestino(String moradaDestino) { this.moradaDestino = moradaDestino; }
    public double getKms() { return kms; }
    public void setKms(double kms) { this.kms = kms; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    // --- MÉTODOS ESTÁTICOS ---
    public static void reiniciarContador() { contadorId = 1; }
    public static void setContadorId(int valor) { contadorId = valor; }
    public static int getContadorId() { return contadorId; }

    // --- PARA FICHEIRO (Atualizado) ---
    public String paraFicheiro() {
        // Adicionada a dataHoraFim na string
        return id + ";" + idCliente + ";" + idViatura + ";" +
                dataHoraInicio + ";" + dataHoraFim + ";" +
                moradaOrigem + ";" + moradaDestino + ";" + kms + ";" + ativa;
    }

    @Override
    public String toString() {
        DateTimeFormatter data = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String estado = ativa ? "Ativa" : "Cancelada";
        return "Reserva ID: " + id + " | Data: " + dataHoraInicio.format(data) + " ate " + dataHoraFim.format(data) + " | Estado: " + estado;
    }

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