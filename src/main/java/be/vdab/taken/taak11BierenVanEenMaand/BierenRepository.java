package be.vdab.taken.taak11BierenVanEenMaand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BierenRepository extends AbstractRepository {

    List<Bieren> findByMaandNummerBieren(int month) throws SQLException {
        List<Bieren> bieren = new ArrayList<>();
        String sql = """
                select id,naam
                from bieren
                where {fn month(sinds)} = ?
                order by naam
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statement.setInt(1,month);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                bieren.add(naarBieren(result));
            }
            connection.commit();
            return bieren;
        }

    }

    Bieren naarBieren(ResultSet result) throws SQLException {
        return new Bieren(result.getInt("id"), result.getString("naam"));
    }
}
