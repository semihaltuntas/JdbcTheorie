package be.vdab.taken.taak2Repository;

import java.sql.Connection;
import java.sql.SQLException;

public class MyRepository extends AbstractRepository {
    public static void main(String[] args) {
        try (Connection connection = new MyRepository().getConnection()) {
            System.out.println("Connectie geopend");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

    }
}
