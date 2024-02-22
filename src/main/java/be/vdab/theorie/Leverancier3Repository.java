package be.vdab.theorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Leverancier3Repository extends AbstractRepository {
    List<String> findAllNamen() throws SQLException {
        var sql = """
                select id, naam
                from leveranciers
                """;
        List<String> namen = new ArrayList();
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                namen.add(result.getLong("id") + "-> " + result.getString("naam"));
            }
             /*Korte kod inplaats van boven

             for (var result = statement.executeQuery(); result.next(); ) {
              namen.add(result.getString("naam"));
              }
              */
        }
        return namen;
    }
}
