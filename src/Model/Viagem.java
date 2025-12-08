package Model;

import java.time.LocalDateTime;

public class Viagem {
    private String condutor;
    private String cliente;
    private String viatura;
    private String origem;
    private String destino;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private double kms, custo;

    public Viagem(String condutor, String cliente, String viatura, String origem, String destino, LocalDateTime inicio, LocalDateTime fim, double kms, double custo) {
        this.condutor = condutor;
        this.cliente = cliente;
        this.viatura = viatura;
        this.origem = origem;
        this.destino = destino;
        this.inicio = inicio;
        this.fim = fim;
        this.kms = kms;
        this.custo = custo;
    }
    public Viagem (Reserva reserva, String condutor, String viatura, LocalDateTime fim, double custo){
        this.cliente = reserva.getCliente();
        this.origem = reserva.getOrigem();
        this.destino = reserva.getDestino();
        this.kms = reserva.getKms();
        this.inicio = reserva.getInicio();
        this.condutor = condutor;
        this.viatura = viatura;
        this.fim = fim;
        this.custo = custo;
    }

    public String getCondutor() {
        return condutor;
    }

    public void setCondutor(String condutor) {
        this.condutor = condutor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getViatura() {
        return viatura;
    }

    public void setViatura(String viatura) {
        this.viatura = viatura;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
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

    @Override
    public String toString() {
        return "Viagem [" + viatura + "] Condutor: " + condutor + " | Cliente: " + cliente;
    }
}





