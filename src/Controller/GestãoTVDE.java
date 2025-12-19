package Controller;

import Model.*;

import java.util.ArrayList;

public class GestãoTVDE {
    private ArrayList<Condutor> condutores = new ArrayList<Condutor>();
    private ArrayList<Viatura> viaturas = new ArrayList<Viatura>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private ArrayList<Viagem> viagens = new ArrayList<Viagem>();

    public boolean adicionarCondutores(Condutor novoCondutor) {
        for (Condutor c : condutores){
            if (c.getNif() == (novoCondutor.getNif())) {
                System.out.println("Erro: já existe um condutor com este NIF " + novoCondutor.getNif());
                return false;
            }
        }
        condutores.add(novoCondutor);
        System.out.println("Condutor adicionado com sucesso.");
        return true;
    }

    public void listarCondutores () {
        if (condutores.isEmpty()) {
            System.out.println("A Lista dos condutores está vazia.");
        }else{
                for (Condutor c: condutores) {
                    System.out.println(c);
                }
        }
    }
    public Condutor procurarCondutores (int nif) {
       for (Condutor c : condutores){
           if (c.getNif() == nif){
               return c;
           }
       }
       return null;
    }

    public boolean atualizarNomeCondutores (int nif, String novoNome){
        Condutor c =procurarCondutores(nif);
        if (c != null) {
            c.setNome(novoNome);
            System.out.println("Nome atualizado com sucesso.");
            return true;
        }
        System.out.println("Condutor não encontrado.");
        return false;
    }

    public boolean atualizarTelemovelCondutores (int nif, int novoTelemovel){
        Condutor c =procurarCondutores(nif);
        if (c != null) {
            c.setTelemovel(novoTelemovel);
            System.out.println("Telemóvel atualizado com sucesso.");
            return true;
        }
        System.out.println("Condutor não encontrado.");
        return false;
    }

    public boolean eliminarCondutores (int nif){
        Condutor c = procurarCondutores(nif);
        if (c == null) {
            System.out.println("Condutor não encontrado.");
            return false;
        }
        for (Viagem v : viagens) {
            if (v.getCondutor() == c) {
                System.out.println("Erro: não pode eliminar este condutor pois ele está numa viagem.");
                return false;
            }
        }
        condutores.remove(c);
        System.out.println("Condutor foi removido com sucesso.");
        return true;
    }

   // acabar o resto dos cruds e pensar no menu
}
