package Controller;
import Model.*;

import java.time.LocalDateTime;

public class TVDE {
    public static void main (String[] args) {

       GestãoTVDE minhaGestao = new GestãoTVDE();
        System.out.println("Empresa criada");

        Condutor c = new Condutor("Gilberto","Rua Da A",123456789,"AA-000000 1",9876512,987654321,987654321,true);
        Condutor d = new Condutor("António","Rua Da B",123456788,"AA-000000 2",9876513,987654322,987654322,true);

        minhaGestao.adicionarCondutores(c);
        minhaGestao.adicionarCondutores(d);

        System.out.println("Condutor enviado para a lista.");

        System.out.println("---- Verificação ----");

        minhaGestao.listarCondutores();

        //continuar com os adds as listagens e o menu

    }
}
