package Classes;

/** Classe que representa um condutor da empresa TVDE. */
public class Condutor {
    /** Contador para gerar ID's unicos*/
    private static int contadorId = 1;
    /** Identificador unico do condutor. */
    private int id;
    /** Nome do condutor */
    private String nome;
    /** Número do cartão de cidadão do condutor */
    private String numeroIdentificacao;
    /** Carta de condução do condutor */
    private String cartaConducao;
    /** Número da segurança social do condutor. */
    private String numeroSegurancaSocial;
    /** Número de identificação fiscal do condutor. */
    private String nif;
    /** Número de telemovel do condutor */
    private String telemovel;
    /** Morada do condutor */
    private String morada;


    /**
     * Construtor da Classe condutor.
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
     * Construtor com ID especifico
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

    /**
     * Obtem o ID do Condutor.
     * @return ID do Condutor
     */
    public int getId() {
        return id;
    }

    /**
     * Define ID do Condutor.
     * @param id Novo ID do condutor
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtem o nome do condutor.
     * @return nome do condutor.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do condutor.
     * @param nome Novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtem número do cartão de Cidadão do condutor.
     * @return número do cartão de Cidadão do condutor
     */
    public String getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    /**
     * Define o número de identificaão fiscal do condutor.
     * @param numeroIdentificacao Novo número de identificaão fiscal
     */
    public void setNumeroIdentificacao(String numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    /**
     * Obtem carta de condução do condutor.
     * @return carta de condução do condutor.
     */
    public String getCartaConducao() {
        return cartaConducao;
    }

    /**
     * Define carta de condução do condutor.
     * @param cartaConducao Nova carta de condução
     */
    public void setCartaConducao(String cartaConducao) {
        this.cartaConducao = cartaConducao;
    }

    /**
     * Obtem número de segurança social do condutor.
     * @return número de segurança social do condutor.
     */
    public String getNumeroSegurancaSocial() {
        return numeroSegurancaSocial;
    }

    /**
     * Define número de segurança social do condutor.
     * @param numeroSegurancaSocial Novo número de segurança social
     */
    public void setNumeroSegurancaSocial(String numeroSegurancaSocial) {
        this.numeroSegurancaSocial = numeroSegurancaSocial;
    }

    /**
     * Obtem número de identificação fiscal do condutor.
     * @return número de identificação fiscal do condutor.
     */
    public String getNif() {
        return nif;
    }

    /**
     * Define número de identificação fiscal do condutor.
     * @param nif Novo número de identificação fiscal
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Obtem número de telemovel do condutor.
     * @return número de telemovel do condutor.
     */
    public String getTelemovel() {
        return telemovel;
    }

    /**
     * Define número de telemovel do condutor.
     * @param telemovel Novo número de telemovel
     */
    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    /**
     * Obtem a morada do condutor.
     * @return morada do condutor.
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Define morada do condutor.
     * @param morada Nova modada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Reinicia o contador de IDs.
     * Usado ao carregar dados de ficheiro.
     */
    public static void reiniciarContador() {
        contadorId = 1;
    }

    /**
     * Define o valor do contador de IDs.
     * @param valor novo valor do contador
     */
    public static void setContadorId(int valor) {
        contadorId = valor;
    }

    /**
     * Obtem o valor do contador de IDs.
     * @return valor do contador
     */
    public static int getContadorId() {
        return contadorId;
    }

    /**
     * Converte o objeto para formato de ficheiro.
     * @return String formatada para gravação em ficheiro
     */
    public String paraFicheiro() {
        return id + ";" + nome + ";" + numeroIdentificacao + ";" + cartaConducao + ";" +
                numeroSegurancaSocial + ";" + nif + ";" + telemovel + ";" + morada;
    }

    /**
     * Representação da informação do condutor.
     * @return String com informação do condutor
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | NIF: " + nif + " | Telemovel: " + telemovel;
    }

    /**
     * Representação da informação detalhada do condutor.
     * @return String com toda a informação do condutor
     */
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