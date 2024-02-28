package be.vdab.taken.taak3BierVerwijderd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BierRepository extends AbstractRepository {

    int deleteIfAlcoholNull() throws SQLException {
        String sql = """
                delete from bieren
                where alcohol is null
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = getConnection().prepareStatement(sql)) {
            return statement.executeUpdate();
        }
    }
}
