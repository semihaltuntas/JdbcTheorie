package be.vdab.taken.taak16GrootsteVermogen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonenRepository extends AbstractRepository {

    List<String> grootsteVermogen() throws SQLException {

        List<String> list = new ArrayList<>();
        String sql = """
                select voornaam
                from personen
                where vermogen = (select max(vermogen) from personen)
                order by voornaam
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(result.getString("voornaam"));
            }
            connection.commit();
            return list;
        }
    }
}
