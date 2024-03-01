package be.vdab.taken.taak18PapaEnMama;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PersoonRepository extends AbstractRepository {

    Optional<PersoonOptionelePapaEnMama> findByIdMetPapaEnMama(long id) throws SQLException {
        String sql = """ 
                select kinderen.voornaam as kindVoornaam,
                papa.voornaam as papaVoornaam,
                mama.voornaam as mamaVoornaam
                from personen kinderen
                left outer join personen papa on papa.id = kinderen.papaId
                left outer join personen mama on mama.id = kinderen.mamaId
                where kinderen.id = ?;
        """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            return result.next() ? Optional.of(new PersoonOptionelePapaEnMama(
                    result.getString("kindVoornaam"),
                    Optional.ofNullable(result.getString("papaVoornaam")),//eger nesne null sa empty doner null degılse o nesneyı olusturur
                    Optional.ofNullable(result.getString("mamaVoornaam"))))
                    : Optional.empty();
        }
    }
}
