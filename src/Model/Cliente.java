package Model;


public class Cliente {


    private static int contadorId = 1;
    private int id;
    private String nome;
    private String nif;
    private String telemovel;
    private String morada;
    private String email;


    /**
     * Construtor com parametros.
     *
     * @param nome Nome do cliente
     * @param nif Numero de identificacao fiscal
     * @param telemovel Numero de telemovel
     * @param morada Morada completa
     * @param email Email do cliente
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
     * Construtor com ID especifico (leitura de ficheiros do ficheiro).
     *
     * @param id Identificador do cliente
     * @param nome Nome do cliente
     * @param nif Numero de identificacao fiscal
     * @param telemovel Numero de telemovel
     * @param morada Morada completa
     * @param email Email do cliente
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

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
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
        return id + ";" + nome + ";" + nif + ";" + telemovel + ";" + morada + ";" + email;
    }


    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | NIF: " + nif + " | Telemovel: " + telemovel;
    }


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