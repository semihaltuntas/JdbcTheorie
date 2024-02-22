package be.vdab.theorie;

import java.sql.SQLException;

public class Main3ResultSet extends AbstractRepository {
    public static void main(String[] args) {
        Leverancier3Repository leverancierRepository = new Leverancier3Repository();
        try {
            leverancierRepository.findAllNamen().forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
