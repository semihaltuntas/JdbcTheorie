package be.vdab.theorie;

import java.sql.Connection;
import java.sql.SQLException;

public class Main1SqlConnectie extends AbstractRepository {

    public static void main(String[] args) {
        // Dat is check om database te verbinden.

        try (Connection connection = new Main3ResultSet().getConnection()) {
            System.out.println("Connection geopend");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
