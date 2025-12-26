package Gestão;
import Controller.GestãoTVDE;
import Model.*;

public class TVDE {
    public static void main (String[] args) {

       GestãoTVDE minhaGestao = new GestãoTVDE();
        System.out.println("Empresa criada");
        Cliente i = new Cliente("Nuno","rua da bela",123456799,912345678);
        Cliente p = new Cliente("Nuno","rua da bela",123456790,912345678);
        //Condutor c = new Condutor("Gilberto","Rua Da A",123456789,"AA-000000 1",9876512,987654321,987654321,true);
        //Condutor d = new Condutor("António","Rua Da B",123456788,"AA-000000 2",9876513,987654322,987654322,true);
        //Condutor e = new Condutor("Carlos","Rua Da C",123456787,"AA-000000 3",9876512,987654321,987654321,true);

        minhaGestao.adicionarClientes(i);
        minhaGestao.adicionarClientes(p);

        System.out.println("---- Verificação ----");

        minhaGestao.listarClientes();



        //continuar com os adds as listagens e o menu

    }
}
