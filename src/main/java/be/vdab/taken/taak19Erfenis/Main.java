package be.vdab.taken.taak19Erfenis;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Id:");
        Long id = scanner.nextLong();
        PersoonRepository repository = new PersoonRepository();
        try {
            repository.erfenis(id);
            System.out.println("De erfenis werd in gelijke delen aan de kinderen overgedragen");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (
                SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
