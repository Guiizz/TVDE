package Model;

public class Cliente {

    private String nome, sexo, nacionalidade;
    private int idade, id, telemovel;

        /**
         *
         * @param nome
         * @param sexo
         * @param nacionalidade
         * @param id
         * @param idade
         * @param telemovel
         */

    public Cliente(String nome, String sexo, String nacionalidade, int id, int idade, int telemovel) {
            this.nome = nome;
            this.sexo = sexo;
            this.nacionalidade = nacionalidade;
            this.id = id;
            this.idade = idade;
            this.telemovel = telemovel;
    }

    public String getNome() {
            return nome;
    }

    public void setNome(String nome) {
            this.nome = nome;
    }

    public String getSexo() {
            return sexo;
    }

    public void setSexo(String sexo) {
            this.sexo = sexo;
    }

    public String getNacionalidade() {
            return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
            this.nacionalidade = nacionalidade;
    }

    public int getId() {
            return id;
    }

    public void setId(int id) {
            this.id = id;
    }

    public int getIdade() {
            return idade;
    }

    public void setIdade(int idade) {
            this.idade = idade;
    }

    public int getTelemovel() {
            return telemovel;
    }

    public void setTelemovel(int telemovel) {
            this.telemovel = telemovel;
    }

    @Override
    public String toString(){
        return "Nome:" + nome + " Idade:" + idade + " Sexo:" + sexo;
    }
}
