package be.vdab.taken.taak5Gemiddelde;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrouwerRepository extends AbstractRepository {

    BigDecimal findGemiddeldeOmzet() throws SQLException {
        String sql = """
                select avg(omzet) as gemiddelde 
                from brouwers
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getBigDecimal("gemiddelde");
        }
    }

    private Brouwers naarBrouwers(ResultSet result) throws SQLException {
        return new Brouwers(result.getInt("id"), result.getString("naam"),
                result.getString("adres"), result.getInt("postcode"),
                result.getString("gemeente"), result.getInt("omzet"));
    }

    List<Brouwers> findOmzetAlsGroterDanGemiddelde() throws SQLException {
        List<Brouwers> brouwers = new ArrayList<>();
        String sql = """
                SELECT id,naam,adres,postcode,gemeente,omzet,
                AVG(omzet) AS gemiddelde
                FROM brouwers
                GROUP BY id
                HAVING AVG(omzet) > (SELECT AVG(omzet) FROM brouwers)
                order by omzet;
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                brouwers.add(naarBrouwers(result));
            }
            return brouwers;
        }
    }
}
