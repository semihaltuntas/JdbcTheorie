package be.vdab.theorie;

import java.sql.SQLException;

public class Main3ResultSet extends AbstractRepository {
    public static void main(String[] args) throws SQLException {
        Leverancier3Repository leverancierRepository = new Leverancier3Repository();
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
        System.out.println("------------------");

        leverancierRepository.findAll().forEach(System.out::println);
    }
}
