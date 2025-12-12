package Controller;

import Model.*;

import java.util.ArrayList;

public class GestãoTVDE {
    private ArrayList<Condutor> condutores = new ArrayList<Condutor>();
    private ArrayList<Viatura> viaturas = new ArrayList<Viatura>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private ArrayList<Viagem> viagens = new ArrayList<Viagem>();

    public void adicionarCondutores(Condutor c1) {
        condutores.add(c1);
    }

    public void listarCondutores () {
        if (condutores.isEmpty()) {
            System.out.println("A Lista dos condutores está vazia.");
        }else{
                for (Condutor c: condutores)
            System.out.println(c);
        }
    }

    //public void adicionarViaturas(Viatura v1){
    //    viaturas.add(v1);
    //}

    //public void adicionarClientes(Cliente c2){
    //    clientes.add(c2);
    //}

    //public void adicionarReservas(Reserva r){
    //    reservas.add(r);
    //}

    //public void adicionarViagens(Viagem v2){
    //    viagens.add(v2);
    //}
}
