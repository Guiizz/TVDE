package Classes;

/**
 * Classe que representa um cliente da empresa TVDE.
 */

public class Cliente {

    /** Contador para gerar ID's unicos*/
    private static int contadorId = 1;
    /** Identificador unico do cliente. */
    private int id;
    /** Nome do cliente */
    private String nome;
    /** Número de identificação fiscal do cliente */
    private String nif;
    /** Número de telemovel do cliente */
    private String telemovel;
    /** Morada do cliente */
    private String morada;
    /** E-mail do cliente. */
    private String email;


    /**
     * Construtor da Classe Cliente.
     *
     * @param nome Nome do cliente
     * @param nif Número de identificação fiscal
     * @param telemovel Número de telemovel
     * @param morada Morada completa
     * @param email E-mail do cliente
     */
    public Cliente(String nome, String nif, String telemovel, String morada, String email) {
        this.id = contadorId++;
        this.nome = nome;
        this.nif = nif;
        this.telemovel = telemovel;
        this.morada = morada;
        this.email = email;
    }

    /**
     * Construtor com ID especifico (usado para leitura de ficheiros).
     *
     * @param id Identificador do cliente
     * @param nome Nome do cliente
     * @param nif Número de identificação fiscal
     * @param telemovel Número de telemovel
     * @param morada Morada completa
     * @param email E-mail do cliente
     */
    public Cliente(int id, String nome, String nif, String telemovel, String morada, String email) {
        this.id = id;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
        this.nome = nome;
        this.nif = nif;
        this.telemovel = telemovel;
        this.morada = morada;
        this.email = email;
    }

    /**
     * Obtem o ID do Cliente.
     * @return ID do Cliente
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do Cliente.
     * @param id Novo ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtem o Nome do Cliente.
     * @return Nome do Cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o Nome do Cliente.
     * @param nome Novo Nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtem NIF do Cliente.
     * @return NIF do cliente
     */
    public String getNif() {
        return nif;
    }

    /**
     * Define o NIF do Cliente
     * @param nif Novo NIF
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Obtem o número de telemovel.
     * @return número de telemovel
     */
    public String getTelemovel() {
        return telemovel;
    }

    /**
     * Define o número de telemovel.
     * @param telemovel Novo número de telemovel
     */
    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    /**
     * Obtem a morada do cliente.
     * @return morada do cliente
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Define a morada do cliente.
     * @param morada Nova morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Obtem o E-mail do cliente.
     * @return E-mail do cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o E-mail do cliente.
     * @param email Novo e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Reinicia o contador de IDs.
     * Usado ao carregar dados de ficheiro
     */
    public static void reiniciarContador() {
        contadorId = 1;
    }

    /**
     * Define o valor do contador de IDs.
     * @param valor Novo valor do contador
     */
    public static void setContadorId(int valor) {
        contadorId = valor;
    }
    /**
     * Obtem o valor atual do contador de IDs.
     * @return Valor do contador
     */
    public static int getContadorId() {
        return contadorId;
    }

    /**
     * Converte o objeto para formato de ficheiro.
     * @return String formatada para gravação em ficheiro
     */
    public String paraFicheiro() {
        return id + ";" + nome + ";" + nif + ";" + telemovel + ";" + morada + ";" + email;
    }

    /**
     * Representação da informação do cliente.
     * @return String com informação do cliente
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | NIF: " + nif + " | Telemovel: " + telemovel;
    }

    /**
     * Representação da informação detalhada do cliente.
     * @return String com toda a informação do cliente
     */
    public String toStringDetalhado() {
        return "=== CLIENTE ===" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nNIF: " + nif +
                "\nTelemovel: " + telemovel +
                "\nMorada: " + morada +
                "\nEmail: " + email;
    }
}