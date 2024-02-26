package be.vdab.taken.taak6AlleSoorten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SoortenRepository extends AbstractRepository {

    public List<Soort> findAll() throws SQLException {
        List<Soort> soorts = new ArrayList<>();

        String sql = """
                select id,naam
                from soorten
                order by naam;
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                soorts.add(naarSoort(result));
            }
            return soorts;
        }
    }

    private Soort naarSoort(ResultSet result) throws SQLException {
        return new Soort(result.getInt("id"), result.getString("naam"));
    }
}
