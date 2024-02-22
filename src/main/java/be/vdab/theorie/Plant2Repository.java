package be.vdab.theorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Plant2Repository extends AbstractRepository {
    int verhoogPrijzenMet10Procent() throws SQLException {
        String sql = """
                   update planten
                   set prijs = prijs * 1.1
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate();
        }
    }
}
