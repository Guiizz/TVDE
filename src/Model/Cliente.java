package Model;

public class Cliente {

    private String nome, morada;
    private int nif, telemovel;

    /**
     *
     * @param nome
     * @param morada
     * @param nif
     * @param telemovel
     */

    public Cliente(String nome, String morada, int nif, int telemovel) {
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.telemovel = telemovel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    @Override
    public String toString() {
        return "========== Cliente ==========\n" +
                "Nome      : " + nome + "\n" +
                "NIF       : " + nif + "\n" +
                "Telemóvel : " + telemovel + "\n" +
                "Morada    : " + morada + "\n" +
                "=============================";
    }
    // O "\n" serve para aparecer uma nova linha
}
