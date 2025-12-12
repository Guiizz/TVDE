package Model;

import java.time.LocalDateTime;

/**
 * Dados de uma reserva de um cliente.
 */
public class Reserva {
    private String cliente;
    private LocalDateTime inicio;
    private String origem;
    private String destino;
    private double kms;

    /**
     * Construtor da reserva.
     * @param cliente O cliente que fez a reserva.
     * @param inicio A data e hora de inicio.
     * @param origem A morada de origem.
     * @param destino A morada de destino.
     * @param kms A distancia em Km.
     */
    public Reserva(String cliente, LocalDateTime inicio, String origem, String destino, double kms) {
        this.cliente = cliente;
        this.inicio = inicio;
        this.origem = origem;
        this.destino = destino;
        this.kms = kms;
    }

    // Getters e Setters
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    /**
     * Representação em String de uma reserva
     * @return String formatada com a informação
     */
    @Override
    public String toString () {
        return "Reserva de: " + cliente + " | Data: " + inicio;
    }
}