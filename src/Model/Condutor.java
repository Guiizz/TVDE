package Model;

public class Condutor {
    private String nome, morada,cartaconducao;
    private int id, segsocial, nif, telemovel;
    private boolean disponivel;

    /**
     * javadocs
     * @param nome
     * @param morada
     * @param id
     * @param cartaconducao
     * @param segsocial
     * @param nif
     * @param telemovel
     * @param disponivel
     */

    public Condutor(String nome, String morada, int id, String cartaconducao, int segsocial, int nif, int telemovel, boolean disponivel) {
        this.nome = nome;
        this.morada = morada;
        this.id = id;
        this.cartaconducao = cartaconducao;
        this.segsocial = segsocial;
        this.nif = nif;
        this.telemovel = telemovel;
        this.disponivel = true;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCartaconducao() {
        return cartaconducao;
    }

    public void setCartaconducao(String cartaconducao) {
        this.cartaconducao = cartaconducao;
    }

    public int getSegsocial() {
        return segsocial;
    }

    public void setSegsocial(int segsocial) {
        this.segsocial = segsocial;
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

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
      return "========== Condutor =========\n" +
              "Nome        : " + nome +"\n" +
              "NIF         : " + nif + "\n" +
              "Telemóvel   : " + telemovel + "\n" +
              "Disponível  : " + (disponivel ? "sim" : "não") +"\n" +
              "=============================";
    }
}
