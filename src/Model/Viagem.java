package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Viagem extends Reserva {
    private Condutor condutor;
    private LocalDateTime dataFim;
    private double custo;

    public Viagem(Cliente cliente, Viatura viatura, LocalDateTime dataInicio, String origem, String destino, double kms, Condutor condutor, LocalDateTime dataFim, double custo) {
        super(cliente, viatura, dataInicio, origem, destino, kms);
        this.condutor = condutor;
        this.dataFim = dataFim;
        this.custo = custo;
    }

    public Viagem (Reserva r, Condutor condutor, LocalDateTime dataFim, double custo){
        super(r.getCliente(), r.getViatura(), r.getDataInicio(), r.getOrigem(), r.getDestino(), r.getKms());
        setId(r.getId());
        this.condutor = condutor;
        this.dataFim = dataFim;
        this.custo = custo;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
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
    public String toString(){
        return "Viagem: " + getId() + " | Cliente: " + getCliente().getNome() + " | Condutor: " + condutor.getNome() + " | Origem: " + getOrigem() + " | Destino: " + getDestino() + " | Custo: " + custo;
    }

}



