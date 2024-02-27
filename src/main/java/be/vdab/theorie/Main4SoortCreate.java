package be.vdab.theorie;

import java.sql.SQLException;
import java.util.Scanner;

public class Main4SoortCreate {
    public static void main(String[] args) {

        Soort4Repository repository = new Soort4Repository();

        System.out.println("Type een naam: ");
        Scanner scanner = new Scanner(System.in);
        String naam = scanner.nextLine();

        try {
            long nieuweId =repository.create(naam);
            System.out.println("Soort toegevoegd. Het nummer is " + nieuweId);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
