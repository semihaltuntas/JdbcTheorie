package be.vdab.taken.taak4SoortNamen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SoortRepository extends AbstractRepository {
    List<String> SoortAllNamen() throws SQLException {
        String sql = """
                select id,naam
                from soorten
                order by naam 
                """;
        List<String> namen = new ArrayList<>();
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                namen.add(result.getLong("id") + " -> "
                        + result.getString("naam"));
            }
            return namen;
        }
    }


}
