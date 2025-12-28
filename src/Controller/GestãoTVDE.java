package Controller;

import Model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestãoTVDE {

    private ArrayList<Condutor> condutores;
    private ArrayList<Viatura> viaturas;
    private ArrayList<Cliente> clientes;
    private ArrayList<Reserva> reservas;
    private ArrayList<Viagem> viagens;

    public GestãoTVDE() {
        this.condutores = new ArrayList<>();
        this.viaturas = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.viagens = new ArrayList<>();
    }

    //-----------------------------------------------------------------------------------------------
    // GESTÃO DE CONDUTORES
    //-----------------------------------------------------------------------------------------------

    public boolean adicionarCondutores(Condutor novoCondutor) {
        for (Condutor c : condutores) {
            if (c.getNif() == (novoCondutor.getNif())) {
                System.out.println("Erro: já existe um condutor com este NIF " + novoCondutor.getNif());
                return false;
            }
        }
        condutores.add(novoCondutor);
        System.out.println("Condutor adicionado com sucesso.");
        return true;
    }

    public void listarCondutores() {
        if (condutores.isEmpty()) {
            System.out.println("A Lista dos condutores está vazia.");
        } else {
            for (Condutor c : condutores) {
                System.out.println(c);
            }
        }
    }

    public Condutor procurarCondutores(int nif) {
        for (Condutor c : condutores) {
            if (c.getNif() == nif) {
                return c;
            }
        }
        return null;
    }

    public boolean atualizarCondutores(int nif, String novoNome, int novoTel, String novaMorada, String novaCartaCondicao) {
        Condutor c = procurarCondutores(nif);
        if (c == null) return false;

        c.setNome(novoNome);
        c.setTelemovel(novoTel);
        c.setMorada(novaMorada);
        c.setCartaconducao(novaCartaCondicao);
        System.out.println("Dados do condutor atualizados com sucesso!");
        return true;
    }

    public boolean removerCondutores(int nif) {
        Condutor c = procurarCondutores(nif);
        if (c == null)
            return false;
        //verificar se não existe viagens associadas.
        for (Viagem v : viagens) {
            if (v.getCondutor() == c)
                return false;
        }
        return condutores.remove(c);
    }

    public ArrayList<Condutor> getCondutores() { return new ArrayList<>(condutores);}



    //-----------------------------------------------------------------------------------------------
    // GESTÃO DE Clientes
    //-----------------------------------------------------------------------------------------------

    public boolean adicionarClientes(Cliente novoCliente) {
        for (Cliente c : clientes) {
            if (c.getNif() == (novoCliente.getNif())) {
                System.out.println("Erro: já existe um cliente com este NIF " + novoCliente.getNif());
                return false;
            }
        }
        clientes.add(novoCliente);
        System.out.println("Cliente adicionado com sucesso.");
        return true;
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("A Lista dos clientes está vazia.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    public Cliente procurarClientes(int nif) {
        for (Cliente c : clientes) {
            if (c.getNif() == nif) {
                return c;
            }
        }
        return null;
    }

    public boolean atualizarclientes(int nif, String novoNome, int novoTel, String novaMorada) {
        Cliente c = procurarClientes(nif);
        if (c == null) return false;

        c.setNome(novoNome);
        c.setTelemovel(novoTel);
        c.setMorada(novaMorada);
        System.out.println("Dados do cliente atualizados com sucesso!");
        return true;
    }

    public boolean removerClientes(int nif) {
        Cliente c = procurarClientes(nif);
        if (c == null) return false;

            //Verificar dependecias de Reservas e Viagens.
        for (Reserva r : reservas) {
            if (r.getCliente() == c) return false;
            }
        for (Viagem v : viagens) {
            if (v.getCliente() == c) return false;
            }

        return clientes.remove(c);
    }

    public ArrayList<Cliente> getClientes() {return new ArrayList<>(clientes);}


    //-----------------------------------------------------------------------------------------------
    // GESTÃO DE VIATURA
    //-----------------------------------------------------------------------------------------------

    public boolean adicionarViatura(Viatura novaViatura) {
        for (Viatura v : viaturas) {
            if (v.getMatricula() == (novaViatura.getMatricula())) {
                System.out.println("Erro: já existe uma viatura com esta matricula:  " + novaViatura.getMatricula());
                return false;
            }
        }
        viaturas.add(novaViatura);
        System.out.println("Viatura adicionada com sucesso.");
        return true;
    }

    public void listarViaturas() {
        if (viaturas.isEmpty()) {
            System.out.println("A Lista das viaturas está vazia.");
        } else {
            for (Viatura v : viaturas) {
                System.out.println(v);
            }
        }
    }

    public Viatura procurarViaturas(String matricula) {
        for (Viatura v : viaturas) {
            if (v.getMatricula() == matricula) {
                return v;
            }
        }
        return null;
    }

    public boolean atualizarCorViatura(String matricula, String novaCor) {
        Viatura v = procurarViaturas(matricula);
        if (v != null) {
            v.setCor(novaCor);
            System.out.println("Cor atualizada com sucesso.");
            return true;
        }
        System.out.println("Viatura não encontrada.");
        return false;
    }

    public boolean eliminarViatura(String matricula) {
        Viatura v = procurarViaturas(matricula);
        if (v == null) {
            System.out.println("Viatura não encontrada.");
            return false;
        }
        for (Viagem viagem : viagens) {
            if (viagem.getViatura() == v) {
                System.out.println("Erro: não pode eliminar esta viatura pois ela está associada a uma viagem.");
                return false;
            }
        }
        viaturas.remove(v);
        System.out.println("Viatura removida com sucesso.");
        return true;
    }

    public ArrayList<Viatura> getViaturas() { return new ArrayList<>(viaturas);}

    //-----------------------------------------------------------------------------------------------
    // GESTÃO DE RESERVAS
    //-----------------------------------------------------------------------------------------------

    // Métudo auxiliar para estimar tempo (50km/h velocidade media
    private LocalDateTime estimarFim (LocalDateTime inico, double kms){
        long minutos = (long)((kms / 50.0) * 60) + 10; // +10 mins margem
        return inico.plusMinutes(minutos);
    }
    // verificar se a viatura está livre num intervalo de tempo.
    //requesito: Não permitir sobreposição.

    public boolean viaturaDisponivel (Viatura v, LocalDateTime inicio, LocalDateTime fim){
        for (Reserva r : reservas ){
            if (r.getViatura() == v){
                LocalDateTime rFim = estimarFim(r.getDataInicio(),r.getKms());
                if (inicio.isBefore(rFim) && fim.isAfter(r.getDataInicio())){
                    return false;
                }
            }

        }
        //verificar conflitos com VIAGENS já registadas
        for (Viagem vg : viagens){
            if (vg.getViatura() == v ){
                if (inicio.isBefore(vg.getDataFim()) && fim.isAfter(vg.getDataInicio())) {
                    return false;
                }
            }
        }
        return true;
    }
    //Cria nova reserva depois de validar disponiblidade.

    public boolean criarReserva (Cliente cliente, Viatura viatura, LocalDateTime inicio,
                                 String origem, String destino, double kms) {
        LocalDateTime fimEstimado = estimarFim(inicio, kms);

        if (!viaturaDisponivel(viatura, inicio,fimEstimado)) return false;

        Reserva novaReserva = new Reserva(cliente, viatura, inicio, origem, destino, kms);
        return reservas.add(novaReserva);

    }
    // Procurar Reserva
    public Reserva procurarReserva (int id){
        for (Reserva r : reservas){
            if (r.getId() == id){
                return r;
            }
        }
        return null;
        }
     // Listar Reservas
    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("A Lista das reservas está vazia.");
        } else {
            for (Reserva r : reservas) {
                System.out.println(r);
            }
        }
    }

    // Atualizar Reserva (nao podemos alterar a data/viatura para nao mexer com a validação da
    // sobreposiçao.
    public boolean atualizarReserva (int id, String novaOrigem, String novoDestino, double novoskms){
        Reserva r = procurarReserva(id);
        if (r == null) return false;

        r.setOrigem(novaOrigem);
        r.setDestino(novoDestino);
        r.setKms(novoskms);
        return true;
    }

    // Remover Reserva
    public boolean removerReserva(int id){
        Reserva r = procurarReserva(id);
        if (r == null ) return false;
        return reservas.remove(r);

    }
    //consultar reservas de um cliente.

    public ArrayList<Reserva> getReservasCliente(int nif) {
        ArrayList<Reserva> lista = new ArrayList<>();
        for (Reserva r : reservas){
            if (r.getCliente().getNif() == nif)
                lista.add(r);
        }
        return lista;
    }

    //-----------------------------------------------------------------------------------------------
    // GESTÃO DE VIAGENS
    //-----------------------------------------------------------------------------------------------

    // Adicionar viagem manualmente, que nao veio de uma reserva.
    public boolean adicionarViagemManual(Viagem v){
        if (!viaturaDisponivel(v.getViatura(), v.getDataInicio(), v.getDataFim())) return false;

        return viagens.add(v);
    }

    // Procurar viagem
    public Viagem procurarViagem(int id){
        for (Viagem v : viagens){
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    // Atualizar Viagem realizada (apenas tem kms e custo para nao correr o risco de sobreposição)
    public boolean atualizarViagem (int id, double novoskms, double novoCusto){
       Viagem v = procurarViagem(id);
       if (v == null ) return false;
       v.setKms(novoskms);
       v.setCusto(novoCusto);
       return true;
    }

    // remover viagem do historico
    public boolean removerViagem (int id) {
        Viagem v = procurarViagem(id);
        if (v == null) return false;
        return viagens.remove(v);
    }
    public ArrayList<Reserva> getReservas() { return new ArrayList<>(reservas);}
    public ArrayList<Viagem> getViagens() { return new ArrayList<>(viagens);}

    // converte uma reserva numa viagem e remove a reserva da lista.

    public boolean converterReservaEmViagem (int idReserva, Condutor condutor,
                                             LocalDateTime fim, double custo){

        Reserva r = procurarReserva(idReserva);
        if (r == null) return false;

        // cria a viagem baseada na reserva
        Viagem novaViagem = new Viagem(r, condutor, fim, custo);
            viagens.add(novaViagem);
        //remove a reserva (já cumprida)
            reservas.remove(r);
            return true;
    }
    //-----------------------------------------------------------------------------------------------
    // ESTATISTICAS E RELATÓRIOS
    //-----------------------------------------------------------------------------------------------





}
