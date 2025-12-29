package ficheiros;

import java.io.*;
import Model.*;
import Gestão.TVDE;
import java.util.ArrayList;

public class GestorFicheiros {

    /** Diretoria base para os ficheiros */
    private String diretoriaBase;

    /** Separador de campos nos ficheiros */
    private static final String SEPARADOR = ";";

    /**
     * Construtor por omissao.
     * Usa a diretoria "dados" como base.
     */
    public GestorFicheiros() {
        this.diretoriaBase = "dados";
    }

    /**
     * Construtor com diretoria especifica.
     *
     * @param diretoriaBase Diretoria base para os ficheiros
     */
    public GestorFicheiros(String diretoriaBase) {
        this.diretoriaBase = diretoriaBase;
    }

    /**
     * Obtem a diretoria base.
     * @return Diretoria base
     */
    public String getDiretoriaBase() {
        return diretoriaBase;
    }

    /**
     * Define a diretoria base.
     * @param diretoriaBase Nova diretoria base
     */
    public void setDiretoriaBase(String diretoriaBase) {
        this.diretoriaBase = diretoriaBase;
    }



    /**
     * Cria a diretoria base se nao existir.
     *
     * @return true se criada ou ja existia
     */
    public boolean criarDiretoriaBase() {
        File pasta = new File(diretoriaBase);
        if (!pasta.exists()) {
            return pasta.mkdirs();
        }
        return true;
    }
}
