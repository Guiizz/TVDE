package Ficheiros;

import Gestão.GestaoTVDE;
import Classes.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Trata de guardar e carregar os dados do programa em ficheiros.
 * A ideia é simples: o que está em memória vai para ficheiro e depois volta para memória.
 */
public class GestorFicheiros {

    /** Pasta base onde ficam os ficheiros (por defeito "dados"). */
    private String diretoriaBase;

    /** Separador usado nas linhas dos ficheiros. */
    private static final String SEPARADOR = ";";

    /** Nomes dos ficheiros (um por "tabela"). */
    private static final String FicheiroEmpresa    = "empresa.txt";
    private static final String FicheiroCondutores = "condutores.txt";
    private static final String FicheiroClientes   = "clientes.txt";
    private static final String FicheiroViaturas   = "viaturas.txt";
    private static final String FicheiroReservas   = "reservas.txt";
    private static final String FicheiroViagens    = "viagens.txt";

    /** Construtor default: usa "dados" como pasta base. */
    public GestorFicheiros() {
        this.diretoriaBase = "dados";
    }

    /**
     * Construtor com pasta base escolhida.
     * @param diretoriaBase pasta onde vamos guardar as cenas
     */
    public GestorFicheiros(String diretoriaBase) {
        this.diretoriaBase = diretoriaBase;
    }

    /** @return pasta base atual */
    public String getDiretoriaBase() {
        return diretoriaBase;
    }

    /** @param diretoriaBase nova pasta base */
    public void setDiretoriaBase(String diretoriaBase) {
        this.diretoriaBase = diretoriaBase;
    }

    /**
     * Garante que a pasta base existe (cria se não existir).
     * @return true se está tudo ok
     */
    public boolean criarDiretoriaBase() {
        File pasta = new File(diretoriaBase);
        if (!pasta.exists()) return pasta.mkdirs();
        return pasta.isDirectory();
    }

    /**
     * Guarda TUDO o que está no GestaoTVDE para ficheiros.
     * Cria a pasta dados/<empresa>/ e escreve os txt lá dentro.
     *
     * @param gestao objeto com tudo em memória
     * @throws IOException se falhar a criar pastas ou escrever ficheiros
     */
    public void guardarTudo(GestaoTVDE gestao) throws IOException {

        String nomeEmpresa = gestao.getNomeEmpresa();
        if (nomeEmpresa == null || nomeEmpresa.trim().isEmpty()) {
            nomeEmpresa = "empresa";
        }

        if (!criarDiretoriaBase()) {
            throw new IOException("Diretoria base inválida: " + diretoriaBase);
        }

        File pastaEmpresa = new File(diretoriaBase, limparParaEmpresa(nomeEmpresa));
        if (!pastaEmpresa.exists() && !pastaEmpresa.mkdirs()) {
            throw new IOException("Não foi possível criar pasta: " + pastaEmpresa.getPath());
        }

        // config (preços)
        gravarEmpresa(new File(pastaEmpresa, FicheiroEmpresa), gestao);

        // listas
        escreverCondutores(new File(pastaEmpresa, FicheiroCondutores), gestao.getCondutores());
        escreverClientes(new File(pastaEmpresa, FicheiroClientes), gestao.getClientes());
        escreverViaturas(new File(pastaEmpresa, FicheiroViaturas), gestao.getViaturas());
        escreverReservas(new File(pastaEmpresa, FicheiroReservas), gestao.getReservas());
        escreverViagens(new File(pastaEmpresa, FicheiroViagens), gestao.getViagens());
    }

    /**
     * Guarda os valores "fixos" (preço por km e taxa base).
     * Formato: precoPorKm;taxaBase
     */
    private void gravarEmpresa(File ficheiro, GestaoTVDE gestao) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheiro))) {
            writer.write(gestao.getPrecoPorKm() + SEPARADOR + gestao.getTaxaBase());
            writer.write(System.lineSeparator());
        }
    }

    /**
     * Lista os nomes das empresas (pastas) existentes na diretoria base.
     * @return Lista de nomes de empresas encontradas.
     */
    public ArrayList<String> listarEmpresasExistentes() {
        ArrayList<String> empresas = new ArrayList<>();
        File pastaBase = new File(diretoriaBase);

        if (pastaBase.exists() && pastaBase.isDirectory()) {
            File[] ficheiros = pastaBase.listFiles();
            if (ficheiros != null) {
                for (File f : ficheiros) {
                    // Se for uma diretoria, assumimos que é uma empresa
                    if (f.isDirectory()) {
                        empresas.add(f.getName());
                    }
                }
            }
        }
        return empresas;
    }
    /**
     * Remove a pasta de uma empresa e o seu conteúdo.
     * @param nomeEmpresa Nome da empresa a remover
     * @return true se removido com sucesso, false caso contrário
     */
    public boolean removerEmpresa(String nomeEmpresa) {
        // Proteção simples
        if (nomeEmpresa == null || nomeEmpresa.trim().isEmpty()) return false;

        File pastaEmpresa = new File(diretoriaBase, limparParaEmpresa(nomeEmpresa));

        // Chama a função auxiliar que apaga tudo lá dentro
        return apagarDiretoriaRecursivamente(pastaEmpresa);
    }

    /**
     * Metodo auxiliar para apagar pastas com conteúdo.
     */
    private boolean apagarDiretoriaRecursivamente(File pasta) {
        if (!pasta.exists()) return false;

        // Se for diretoria, listar e apagar o conteúdo primeiro
        File[] ficheiros = pasta.listFiles();
        if (ficheiros != null) {
            for (File f : ficheiros) {
                if (f.isDirectory()) {
                    apagarDiretoriaRecursivamente(f);
                } else {
                    f.delete();
                }
            }
        }
        // Finalmente, apaga a pasta vazia
        return pasta.delete();
    }

    /** Guarda condutores (1 linha por condutor). */
    private void escreverCondutores(File ficheiro, ArrayList<Condutor> lista) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheiro))) {
            for (Condutor c : lista) {
                String linha =
                        c.getId() + SEPARADOR +
                                c.getNome() + SEPARADOR +
                                c.getNumeroIdentificacao() + SEPARADOR +
                                c.getCartaConducao() + SEPARADOR +
                                c.getNumeroSegurancaSocial() + SEPARADOR +
                                c.getNif() + SEPARADOR +
                                c.getTelemovel() + SEPARADOR +
                                c.getMorada();

                writer.write(linha);
                writer.write(System.lineSeparator());
            }
        }
    }

    /** Guarda clientes (1 linha por cliente). */
    private void escreverClientes(File ficheiro, ArrayList<Cliente> lista) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheiro))) {
            for (Cliente c : lista) {
                String linha =
                        c.getId() + SEPARADOR +
                                c.getNome() + SEPARADOR +
                                c.getNif() + SEPARADOR +
                                c.getTelemovel() + SEPARADOR +
                                c.getMorada() + SEPARADOR +
                                c.getEmail();

                writer.write(linha);
                writer.write(System.lineSeparator());
            }
        }
    }

    /** Guarda viaturas (1 linha por viatura). */
    private void escreverViaturas(File ficheiro, ArrayList<Viatura> lista) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheiro))) {
            for (Viatura v : lista) {
                String linha =
                        v.getId() + SEPARADOR +
                                v.getMatricula() + SEPARADOR +
                                v.getMarca() + SEPARADOR +
                                v.getModelo() + SEPARADOR +
                                v.getAnoFabrico() + SEPARADOR +
                                v.getCor() + SEPARADOR +
                                v.getLugares();

                writer.write(linha);
                writer.write(System.lineSeparator());
            }
        }
    }

    /** Guarda reservas (1 linha por reserva). */
    private void escreverReservas(File ficheiro, ArrayList<Reserva> lista) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheiro))) {
            for (Reserva r : lista) {
                String linha =
                        r.getId() + SEPARADOR +
                                r.getIdCliente() + SEPARADOR +
                                r.getIdViatura() + SEPARADOR +
                                r.getDataHoraInicio().toString() + SEPARADOR +
                                r.getMoradaOrigem() + SEPARADOR +
                                r.getMoradaDestino() + SEPARADOR +
                                r.getKms() + SEPARADOR +
                                r.isAtiva();

                writer.write(linha);
                writer.write(System.lineSeparator());
            }
        }
    }

    /** Guarda viagens (1 linha por viagem). */
    private void escreverViagens(File ficheiro, ArrayList<Viagem> lista) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheiro))) {
            for (Viagem v : lista) {
                String linha =
                        v.getId() + SEPARADOR +
                                v.getIdCondutor() + SEPARADOR +
                                v.getIdCliente() + SEPARADOR +
                                v.getIdViatura() + SEPARADOR +
                                v.getDataHoraInicio().toString() + SEPARADOR +
                                v.getDataHoraFim().toString() + SEPARADOR +
                                v.getMoradaOrigem() + SEPARADOR +
                                v.getMoradaDestino() + SEPARADOR +
                                v.getKms() + SEPARADOR +
                                v.getCusto();

                writer.write(linha);
                writer.write(System.lineSeparator());
            }
        }
    }

    /**
     * Lê os ficheiros e devolve uma GestaoTVDE já carregada.
     * @param nomeEmpresa nome da pasta da empresa dentro de "dados"
     * @return GestaoTVDE com tudo carregado
     * @throws IOException se não encontrar a pasta/ficheiros ou se o parsing falhar
     */
    public GestaoTVDE lerTudo(String nomeEmpresa) throws IOException {

        if (nomeEmpresa == null || nomeEmpresa.trim().isEmpty()) {
            nomeEmpresa = "empresa";
        }

        File pastaEmpresa = new File(diretoriaBase, limparParaEmpresa(nomeEmpresa));
        if (!pastaEmpresa.exists() || !pastaEmpresa.isDirectory()) {
            throw new FileNotFoundException("Pasta da empresa não existe: " + pastaEmpresa.getPath());
        }

        // Reset dos contadores (para os IDs baterem certo ao carregar)
        Cliente.reiniciarContador();
        Condutor.reiniciarContador();
        Viatura.reiniciarContador();
        Viagem.reiniciarContador();
        Reserva.setContadorId(1);

        GestaoTVDE gestao = new GestaoTVDE();
        gestao.setNomeEmpresa(nomeEmpresa);

        // preços (se não houver ficheiro, fica default)
        lerEmpresa(new File(pastaEmpresa, FicheiroEmpresa), gestao);

        // ordem importante (evita stress com validações)
        for (Condutor c : lerCondutores(new File(pastaEmpresa, FicheiroCondutores))) {
            if (!gestao.adicionarCondutor(c)) {
                throw new IOException("Erro ao carregar condutor ID=" + c.getId());
            }
        }

        for (Viatura v : lerViaturas(new File(pastaEmpresa, FicheiroViaturas))) {
            if (!gestao.adicionarViatura(v)) {
                throw new IOException("Erro ao carregar viatura ID=" + v.getId() + " matrícula=" + v.getMatricula());
            }
        }

        for (Cliente c : lerClientes(new File(pastaEmpresa, FicheiroClientes))) {
            if (!gestao.adicionarCliente(c)) {
                throw new IOException("Erro ao carregar cliente ID=" + c.getId());
            }
        }

        for (Reserva r : lerReservas(new File(pastaEmpresa, FicheiroReservas))) {
            if (!gestao.adicionarReserva(r)) {
                throw new IOException("Erro ao carregar reserva ID=" + r.getId() + " (possível sobreposição)");
            }
        }

        for (Viagem v : lerViagens(new File(pastaEmpresa, FicheiroViagens))) {
            if (!gestao.adicionarViagem(v)) {
                throw new IOException("Erro ao carregar viagem ID=" + v.getId() + " (possível sobreposição)");
            }
        }

        return gestao;
    }

    /** Lê o ficheiro empresa.txt e mete preço/km e taxa base. */
    private void lerEmpresa(File ficheiro, GestaoTVDE gestao) throws IOException {
        if (!ficheiro.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha = br.readLine();
            if (linha == null) return;

            String[] c = linha.trim().split(SEPARADOR, -1);
            if (c.length >= 2) {
                gestao.setPrecoPorKm(Double.parseDouble(c[0]));
                gestao.setTaxaBase(Double.parseDouble(c[1]));
            }
        }
    }

    /** Lê condutores do ficheiro e devolve lista. */
    private ArrayList<Condutor> lerCondutores(File ficheiro) throws IOException {
        ArrayList<Condutor> res = new ArrayList<>();
        if (!ficheiro.exists()) return res;

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha;
            int nlinha = 0;

            while ((linha = br.readLine()) != null) {
                nlinha++;
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] c = linha.split(SEPARADOR, -1);
                if (c.length < 8) throw new IOException("condutores.txt linha " + nlinha + " inválida");

                int id = Integer.parseInt(c[0]);
                res.add(new Condutor(id, c[1], c[2], c[3], c[4], c[5], c[6], c[7]));
            }
        }
        return res;
    }

    /** Lê clientes do ficheiro e devolve lista. */
    private ArrayList<Cliente> lerClientes(File ficheiro) throws IOException {
        ArrayList<Cliente> res = new ArrayList<>();
        if (!ficheiro.exists()) return res;

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha;
            int nlinha = 0;

            while ((linha = br.readLine()) != null) {
                nlinha++;
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] c = linha.split(SEPARADOR, -1);
                if (c.length < 6) throw new IOException("clientes.txt linha " + nlinha + " inválida");

                int id = Integer.parseInt(c[0]);
                res.add(new Cliente(id, c[1], c[2], c[3], c[4], c[5]));
            }
        }
        return res;
    }

    /** Lê viaturas do ficheiro e devolve lista. */
    private ArrayList<Viatura> lerViaturas(File ficheiro) throws IOException {
        ArrayList<Viatura> res = new ArrayList<>();
        if (!ficheiro.exists()) return res;

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha;
            int nlinha = 0;

            while ((linha = br.readLine()) != null) {
                nlinha++;
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] c = linha.split(SEPARADOR, -1);
                if (c.length < 7) throw new IOException("viaturas.txt linha " + nlinha + " inválida");

                int id = Integer.parseInt(c[0]);
                res.add(new Viatura(id, c[1], c[2], c[3],
                        Integer.parseInt(c[4]), c[5], Integer.parseInt(c[6])));
            }
        }
        return res;
    }

    /** Lê reservas do ficheiro e devolve lista. */
    private ArrayList<Reserva> lerReservas(File ficheiro) throws IOException {
        ArrayList<Reserva> res = new ArrayList<>();
        if (!ficheiro.exists()) return res;

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha;
            int nlinha = 0;

            while ((linha = br.readLine()) != null) {
                nlinha++;
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] c = linha.split(SEPARADOR, -1);
                if (c.length < 8) throw new IOException("reservas.txt linha " + nlinha + " inválida");

                int id = Integer.parseInt(c[0]);
                int idCliente = Integer.parseInt(c[1]);
                int idViatura = Integer.parseInt(c[2]);
                LocalDateTime inicio = LocalDateTime.parse(c[3]);
                double kms = Double.parseDouble(c[6]);
                boolean ativa = Boolean.parseBoolean(c[7]);

                res.add(new Reserva(id, idCliente, idViatura, inicio, c[4], c[5], kms, ativa));
            }
        }
        return res;
    }

    /** Lê viagens do ficheiro e devolve lista. */
    private ArrayList<Viagem> lerViagens(File ficheiro) throws IOException {
        ArrayList<Viagem> res = new ArrayList<>();
        if (!ficheiro.exists()) return res;

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha;
            int nlinha = 0;

            while ((linha = br.readLine()) != null) {
                nlinha++;
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] c = linha.split(SEPARADOR, -1);
                if (c.length < 10) throw new IOException("viagens.txt linha " + nlinha + " inválida");

                int id = Integer.parseInt(c[0]);
                int idCondutor = Integer.parseInt(c[1]);
                int idCliente = Integer.parseInt(c[2]);
                int idViatura = Integer.parseInt(c[3]);
                LocalDateTime inicio = LocalDateTime.parse(c[4]);
                LocalDateTime fim = LocalDateTime.parse(c[5]);
                double kms = Double.parseDouble(c[8]);
                double custo = Double.parseDouble(c[9]);

                res.add(new Viagem(id, idCondutor, idCliente, idViatura, inicio, fim, c[6], c[7], kms, custo));
            }
        }
        return res;
    }

    /** Só para o nome da pasta não dar problemas. */
    private String limparParaEmpresa(String nome) {
        if (nome == null || nome.trim().isEmpty()) return "empresa";
        return nome.trim().replaceAll("[\\\\/:*?\"<>|]", "_");
    }
}