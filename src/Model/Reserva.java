package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private static int contador = 1;
    private int id;
    private Cliente cliente;
    private Viatura viatura;
    private LocalDateTime dataInicio;
    private String origem;
    private String destino;
    private double kms;

    public Reserva(Cliente cliente, Viatura viatura, LocalDateTime dataInicio, String origem, String destino, double kms) {
        this.id = contador++;
        this.cliente = cliente;
        this.viatura = viatura;
        this.dataInicio = dataInicio;
        this.origem = origem;
        this.destino = destino;
        this.kms = kms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Viatura getViatura() {
        return viatura;
    }

    public void setViatura(Viatura viatura) {
        this.viatura = viatura;
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
        return "Reserva: " + id + " | Cliente: " + cliente.getNome() + " | Data: " + dataInicio.format(data) + " | Origem: " + origem + " | Destino: " + destino;
    }
}