package be.vdab.taken.taak17Ouders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersoonRepository extends AbstractRepository {

    List<PersoonMetMamaEnPapa> findMetOuders() throws SQLException {
        List<PersoonMetMamaEnPapa> listVanNamen = new ArrayList<>();
        String sql = """
                select kinderen.voornaam as kindVoornaam,
                papas.voornaam as papaVoornaam,
                mamas.voornaam as mamaVoornaam
                from personen kinderen
                inner join personen papas on papas.id = kinderen.papaId
                inner join personen mamas on mamas.id = kinderen.mamaId
                order by kinderen.voornaam
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                listVanNamen.add(new PersoonMetMamaEnPapa(
                        result.getString("kindVoornaam"),
                        result.getString("papaVoornaam"),
                        result.getString("mamaVoornaam")));
            }
            connection.commit();
            return listVanNamen;
        }
    }
}

