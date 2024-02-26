package be.vdab.taken.taak9StoredProcedure;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BrouwersRepository repository = new BrouwersRepository();

        System.out.println("geef min omzet :");
        Scanner scanner = new Scanner(System.in);
        int van = scanner.nextInt();
        System.out.println("geef max omzet :");
        Scanner scanner1 = new Scanner(System.in);
        int tot = scanner1.nextInt();


        try{
            repository.findByOmzetTussen(van,tot).forEach(System.out::println);
        }catch (SQLException ex){
            ex.printStackTrace(System.err);
        }
    }
}
