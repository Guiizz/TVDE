package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private Cliente cliente;
    private LocalDateTime dataInicio;
    private String origem;
    private String destino;
    private double kms;

    public Reserva(Cliente cliente, LocalDateTime dataInicio, String origem, String destino, double kms) {
        this.cliente = cliente;
        this.dataInicio = dataInicio;
        this.origem = origem;
        this.destino = destino;
        this.kms = kms;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
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
    @Override
    public String toString(){
        DateTimeFormatter data = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "========== Reserva ==========\n" +
                "Cliente   : " + cliente.getNome() + "\n" +
                "Data      : " + dataInicio.format(data) + "\n" +
                "De        : " + origem + " -> Para: " + destino + "\n" +
                "Distância : " + kms + " km" + "\n" +
                "=============================";
    }


}