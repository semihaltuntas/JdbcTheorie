package be.vdab.taken.taak15Gezin;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Papa: ");
        Scanner scanner = new Scanner(System.in);
        String papa = scanner.nextLine();
        System.out.println("Mama: ");
        String mama = scanner.nextLine();


        Gezin gezin = new Gezin(papa, mama);
        System.out.println("Kinderen (type STOP om te stoppen: )");
        String kind = scanner.nextLine();
        while (!kind.equals("STOP")) {
            gezin.addKinderen(kind);
            kind = scanner.nextLine();
        }
        PersoonRepository repository = new PersoonRepository();
        try {
            repository.create(gezin);
            System.out.println("Succesvol Toegevoegd ");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }


    }
}
