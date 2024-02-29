package be.vdab.theorie;

import be.vdab.taken.taak4SoortNamen.SoortRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main4SoortCreate {
    public static void main(String[] args) {

        Soort4Repository repository = new Soort4Repository();
//
//        System.out.println("Type een naam: ");
//        Scanner scanner = new Scanner(System.in);
//        String naam = scanner.nextLine();
//
//        try {
//            long nieuweId =repository.create(naam);
//            System.out.println("Soort toegevoegd. Het nummer is " + nieuweId);
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.err);
//        }
//        System.out.println("--------Unieke Index---------");
//        System.out.print("Naam:");
//        var scanner = new Scanner(System.in);
//        var naam = scanner.nextLine();
//        try {
//            repository.create(naam);
//            System.out.println("Soort toegevoegd.");
//        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.err);
//        }


        System.out.println("----Batch update----");
        List<String> namen = new ArrayList<>();

        System.out.println("Type naam - Stop met (STOP)");
        Scanner scanner1 = new Scanner(System.in);
        String name = scanner1.nextLine();

        while (!name.equals("STOP")) {
            namen.add(name);
            name = scanner1.nextLine();
        }
        try {
            repository.create3(namen);
            System.out.println(" Namen Toegevoegd");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
