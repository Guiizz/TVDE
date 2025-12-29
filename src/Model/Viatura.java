package Model;

public class Viatura {

    private static int contadorId = 1;

    private int id;

    private String matricula;

    private String marca;

    private String modelo;

    private int anoFabrico;

    private String cor;

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


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getMatricula() {
        return matricula;
    }


    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabrico() {
        return anoFabrico;
    }

    public void setAnoFabrico(int anoFabrico) {
        this.anoFabrico = anoFabrico;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getLugares() {
        return lugares;
    }

    public void setLugares(int lugares) {
        this.lugares = lugares;
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
        return id + ";" + matricula + ";" + marca + ";" + modelo + ";" +
                anoFabrico + ";" + cor + ";" + lugares;
    }


    @Override
    public String toString() {
        return "ID: " + id + " | Matricula: " + matricula + " | " + marca + " " + modelo +
                " (" + anoFabrico + ")";
    }


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
