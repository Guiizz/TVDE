package Model;

public class Viatura {
    /**
     * atributos obrigatorios
     */
    private String marca, modelo, matricula, cor;
    private int ano, lugares;

    /**
     * java docs
     * @param marca
     * @param modelo
     * @param matricula
     * @param ano
     * @param lugares
     * @param cor
     */

    /**
     * construtor
     */
    public Viatura(String marca, String modelo, String matricula, String cor, int ano, int lugares) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.cor = cor;
        this.ano = ano;
        this.lugares = lugares;
    }

    /**
     * getters e setters
     */
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * toString
     * @return
     */

    public String toString(){
        return "Viatura [" + matricula + "] - " + marca + ", " + modelo + ", " + cor;
    }
}
