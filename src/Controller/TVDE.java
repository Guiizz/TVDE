package Controller;
import Model.Reserva;
import Model.Viagem;
import Model.Viatura;

import java.time.LocalDateTime;

public class TVDE {
    public static void main (String[] args) {
        LocalDateTime agora = LocalDateTime.now();
        Reserva r1 = new Reserva("Nuno",agora,"Porto","Gaia",10);
        System.out.println(">> Teste Reserva:");
        System.out.println(r1.toString());

        LocalDateTime fim = agora.plusHours(3);
        Viagem v1 = new Viagem(r1,"dinis","AA-00-BB",fim,3.10);

        System.out.println(">> Teste Viagem (Criada a partir da Reserva):");
        System.out.println(v1.toString());

        //Viatura viatura = new Viatura("Renault","ZOE","AA-00-BB","azul",2004,5);
        //System.out.println(viatura);
    }
}
