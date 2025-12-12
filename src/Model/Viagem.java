package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Viagem extends Reserva {

private Condutor condutor;
private Viatura viatura;
private LocalDateTime dataFim;
private double custo;


    public Viagem(Cliente cliente, LocalDateTime dataInicio, String origem, String destino, double kms) {
        super(cliente, dataInicio, origem, destino, kms);
        this.condutor = condutor;
        this.viatura = viatura;
        this.dataFim = dataFim;
        this.custo = custo;
    }

    public Viagem(Reserva reserva, LocalDateTime dataInicio, String origem, String destino, double kms) {
        super(reserva.getCliente(), reserva.getDataInicio(), reserva.getOrigem(), reserva.getDestino(), reserva.getKms());
        this.condutor = condutor;
        this.viatura = viatura;
        this.dataFim = dataFim;
        this.custo = custo;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Viatura getViatura() {
        return viatura;
    }

    public void setViatura(Viatura viatura) {
        this.viatura = viatura;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
    @Override
    public String toString() {
        DateTimeFormatter data = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return super.toString() +
                "\n Detalhes da Viagem: " +
                "Condutor: " + condutor +
                " | Viatura: " + viatura.getMatricula() +
                " | Fim: " + (dataFim != null ? dataFim.format(data) : "Em curso") +
                " | Custo: " + custo + "€";
    }
}





