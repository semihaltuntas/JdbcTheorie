package be.vdab.taken.taak8ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BrouwersRepository extends AbstractRepository {

    Optional<Brouwers> findByID(long id) throws SQLException {
        String sql = """
                select id,naam,adres,postcode,gemeente,omzet
                from brouwers
                where id = ?
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            return result.next() ? Optional.of(naarBrouwer(result)) : Optional.empty();
        }
    }

    public Brouwers naarBrouwer(ResultSet result) throws SQLException {
        return new Brouwers(result.getInt("id"), result.getString("naam"),
                result.getInt("postcode"), result.getString("adres"),
                result.getString("gemeente"), result.getLong("omzet"));
    }
}
