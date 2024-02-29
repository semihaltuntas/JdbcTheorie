package be.vdab.taken.taak13BierenVanEenSoort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BierenRepository extends AbstracRepository {

    List<String> findBySoortID(String soort) throws SQLException {
        List<String> namen = new ArrayList<>();
        String sql = """
                select naam from bieren
                where soortId = (select id naam from soorten where naam = ?)
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,soort);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                namen.add(result.getString("naam"));
            }
            connection.commit();
            return namen;
        }
    }
}
