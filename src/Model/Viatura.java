package Model;

/** Classe que representa uma viatura da empresa TVDE. */
public class Viatura {
    /** Contador para gerar ID's unicos*/
    private static int contadorId = 1;
    /** Identificador unico da viatura. */
    private int id;
    /** Matricula da viatura */
    private String matricula;
    /** Marca da viatura */
    private String marca;
    /** Modelo da viatura */
    private String modelo;
    /** Ano de fabrico da viatura */
    private int anoFabrico;
    /** Cor da viatura */
    private String cor;
    /** Número de lugares da viatura */
    private int lugares;


    /**
     * Construtor com parametros.
     *
     * @param matricula Matricula do veiculo
     * @param marca Marca do veiculo
     * @param modelo Modelo do veiculo
     * @param anoFabrico Ano de fabrico
     * @param cor Cor do veiculo
     * @param lugares Numero de lugares
     */
    public Viatura(String matricula, String marca, String modelo, int anoFabrico,
                   String cor, int lugares) {
        this.id = contadorId++;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabrico = anoFabrico;
        this.cor = cor;
        this.lugares = lugares;
    }

    /**
     * Construtor com ID especifico (usado para leitura de ficheiros).
     *
     * @param id Identificador da viatura
     * @param matricula Matricula do veiculo
     * @param marca Marca do veiculo
     * @param modelo Modelo do veiculo
     * @param anoFabrico Ano de fabrico
     * @param cor Cor do veiculo
     * @param lugares Numero de lugares
     */
    public Viatura(int id, String matricula, String marca, String modelo, int anoFabrico,
                   String cor, int lugares) {
        this.id = id;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabrico = anoFabrico;
        this.cor = cor;
        this.lugares = lugares;
    }

    // Getters e Setters

    /**
     * Obtem o ID da viatura.
     * @return ID da viatura
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID da viatura.
     * @return novo ID da viatura
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtem a matricula da viatura.
     * @return matricula da viatura
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Define matricula da viatura.
     * @param matricula nova matricula da viatura
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Obtem a marca da viatura
     * @return marca da viatura
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca da viatura
     * @param marca nova marca da viatura
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * obtem modelo da viatura
     * @return modelo da viatura
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define modelo da viatura
     * @param modelo novo modelo da viatura
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtem ano de fabrico da viatura.
     * @return ano de fabrico da viatura
     */
    public int getAnoFabrico() {
        return anoFabrico;
    }

    /**
     * Define ano de fabrico da viatura.
     * @param anoFabrico novo ano de fabrico
     */
    public void setAnoFabrico(int anoFabrico) {
        this.anoFabrico = anoFabrico;
    }

    /**
     * Obtem cor da viatura
     * @return cor da viatura
     */
    public String getCor() {
        return cor;
    }

    /**
     * Define cor da viatura
     * @param cor nova cor da viatura
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * Obtem número lugares da viatura
     * @return número lugares da viatura
     */
    public int getLugares() {
        return lugares;
    }

    /**
     * Define número lugares da viatura
     * @param lugares novo número de lugares da viatura
     */
    public void setLugares(int lugares) {
        this.lugares = lugares;
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
        return id + ";" + matricula + ";" + marca + ";" + modelo + ";" +
                anoFabrico + ";" + cor + ";" + lugares;
    }

    /**
     * Representação da informação da viatura.
     * @return String com informação da viatura
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Matricula: " + matricula + " | " + marca + " " + modelo +
                " (" + anoFabrico + ")";
    }

    /**
     * Representação da informação detalhada da viatura.
     * @return String com toda a informação da viatura
     */
    public String toStringDetalhado() {
        return "=== VIATURA ===" +
                "\nID: " + id +
                "\nMatricula: " + matricula +
                "\nMarca: " + marca +
                "\nModelo: " + modelo +
                "\nAno de Fabrico: " + anoFabrico +
                "\nCor: " + cor +
                "\nLugares: " + lugares;
    }
}
