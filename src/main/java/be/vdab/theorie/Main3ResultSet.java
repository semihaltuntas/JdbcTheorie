package be.vdab.theorie;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

public class Main3ResultSet extends AbstractRepository {
    public static void main(String[] args) throws SQLException {
        Leverancier3Repository leverancierRepository = new Leverancier3Repository();
        System.out.println("-----Find All Names with ID's------");
        try {
            leverancierRepository.findAllNamen().forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        System.out.println("----------------");
        try {
            System.out.println("Aantal leveranciers");
            System.out.println(leverancierRepository.findAantal());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------Find All elements----------");

        leverancierRepository.findAll().forEach(System.out::println);

        System.out.println("------Parameters in SQL statements---------");

        System.out.println("Find By Woonplaats: ");
        Scanner scanner = new Scanner(System.in);
        String woonplaats = scanner.nextLine();

        try {
            leverancierRepository.findByWoonPlaats(woonplaats)
                    .forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.println("-----Een record lezen aan de hand van zijn id met Optional----");
        System.out.println("Find by Id: ");
        Scanner scanner1 = new Scanner(System.in);
        int id = scanner1.nextInt();

        try {
            leverancierRepository
                    .findById(id)
                    .ifPresentOrElse(System.out::println,
                            () -> System.out.println("Niet gevonden!"));
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.println("-----Letterlijke datum of tijd in een SQL statement-----");

        try {
            leverancierRepository.findBySinds2000().forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.println("------Datum als parameter------");
        System.out.println("Type een datum vanaf (dag/maand/jaar)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y");
        Scanner scanner2 = new Scanner(System.in);
        LocalDate datum = LocalDate.parse(scanner2.nextLine(), formatter);

        try {
            leverancierRepository.findBySindsVanaf(datum).forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.println("------{fn naamVanDeFunctie(eventueleParameter)}------");
        try {
            leverancierRepository.findLeverancierGewordenInHetJaar2000().forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

    }
}
