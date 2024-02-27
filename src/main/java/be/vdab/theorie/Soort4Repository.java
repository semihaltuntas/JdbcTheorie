package be.vdab.theorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Soort4Repository extends AbstractRepository {

    long create(String naam) throws SQLException {
        String sql = """
                insert into soorten (naam)
                values (?)
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statementInsert = connection.prepareStatement(sql,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statementInsert.setString(1, naam);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statementInsert.executeUpdate();
            ResultSet result = statementInsert.getGeneratedKeys();
            result.next();
            long nieuweId = result.getLong(1);
            connection.commit();
            return nieuweId;
        }

    }
}
