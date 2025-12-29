package Model;

public class Condutor {

    private static int contadorId = 1;

    private int id;

    private String nome;

    private String numeroIdentificacao;

    private String cartaConducao;

    private String numeroSegurancaSocial;

    private String nif;

    private String telemovel;

    private String morada;


    /**
     * Construtor com parametros.
     *
     * @param nome Nome do condutor
     * @param numeroIdentificacao Numero de identificacao civil
     * @param cartaConducao Numero da carta de conducao
     * @param numeroSegurancaSocial Numero de seguranca social
     * @param nif Numero de identificacao fiscal
     * @param telemovel Numero de telemovel
     * @param morada Morada completa
     */
    public Condutor(String nome, String numeroIdentificacao, String cartaConducao,
                    String numeroSegurancaSocial, String nif, String telemovel, String morada) {
        this.id = contadorId++;
        this.nome = nome;
        this.numeroIdentificacao = numeroIdentificacao;
        this.cartaConducao = cartaConducao;
        this.numeroSegurancaSocial = numeroSegurancaSocial;
        this.nif = nif;
        this.telemovel = telemovel;
        this.morada = morada;
    }

    /**
     * Construtor com ID especifico (usado para leitura de ficheiros).
     *
     * @param id Identificador do condutor
     * @param nome Nome do condutor
     * @param numeroIdentificacao Numero de identificacao civil
     * @param cartaConducao Numero da carta de conducao
     * @param numeroSegurancaSocial Numero de seguranca social
     * @param nif Numero de identificacao fiscal
     * @param telemovel Numero de telemovel
     * @param morada Morada completa
     */
    public Condutor(int id, String nome, String numeroIdentificacao, String cartaConducao,
                    String numeroSegurancaSocial, String nif, String telemovel, String morada) {
        this.id = id;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
        this.nome = nome;
        this.numeroIdentificacao = numeroIdentificacao;
        this.cartaConducao = cartaConducao;
        this.numeroSegurancaSocial = numeroSegurancaSocial;
        this.nif = nif;
        this.telemovel = telemovel;
        this.morada = morada;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    public void setNumeroIdentificacao(String numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public String getCartaConducao() {
        return cartaConducao;
    }

    public void setCartaConducao(String cartaConducao) {
        this.cartaConducao = cartaConducao;
    }

    public String getNumeroSegurancaSocial() {
        return numeroSegurancaSocial;
    }

    public void setNumeroSegurancaSocial(String numeroSegurancaSocial) {
        this.numeroSegurancaSocial = numeroSegurancaSocial;
    }


    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }


    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
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



    public String paraFicheiro() {
        return id + ";" + nome + ";" + numeroIdentificacao + ";" + cartaConducao + ";" +
                numeroSegurancaSocial + ";" + nif + ";" + telemovel + ";" + morada;
    }


    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | NIF: " + nif + " | Telemovel: " + telemovel;
    }


    public String toStringDetalhado() {
        return "=== CONDUTOR ===" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nN. Identificacao: " + numeroIdentificacao +
                "\nCarta de Conducao: " + cartaConducao +
                "\nN. Seguranca Social: " + numeroSegurancaSocial +
                "\nNIF: " + nif +
                "\nTelemovel: " + telemovel +
                "\nMorada: " + morada;
    }
}