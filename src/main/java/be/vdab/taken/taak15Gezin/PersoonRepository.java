package be.vdab.taken.taak15Gezin;

import java.lang.reflect.Type;
import java.sql.*;

public class PersoonRepository extends AbstractRepository {

    void create(Gezin gezin) throws SQLException {
        String sql = """
                insert into personen(voornaam,papaId,mamaId)
                values(?,?,?)
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            //papaToevoegen:
            statement.setString(1, gezin.getPapa());
            statement.setNull(2, Types.INTEGER);
            statement.setNull(3, Types.INTEGER);
            statement.addBatch();
            //mamaToevoegen:
            statement.setString(1, gezin.getMama());
            statement.addBatch();
            statement.executeBatch();
            //gegeneerde autoNumbers lezen:
            ResultSet result = statement.getGeneratedKeys();
            result.next();// gegenereerde id van papa lezen
            statement.setLong(2, result.getLong(1));//id van papa gebruiken als papaId
            result.next(); // gegenereerde id van mama lezen
            statement.setLong(3, result.getLong(1));//id van mama gebruiken als mamaId
            //kinderen toevoegen
            for (var kind : gezin.getKinderen()) {
                statement.setString(1, kind);
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        }
    }
}
