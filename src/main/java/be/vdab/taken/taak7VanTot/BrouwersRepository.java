package be.vdab.taken.taak7VanTot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrouwersRepository extends AbstractRepository {

    List<Brouwer> findByOmzetTussen(int min, int max) throws SQLException {
        List<Brouwer> brouwers = new ArrayList<>();
        String sql = """
                SELECT id,naam,omzet
                FROM brouwers
                where omzet between ? and ?
                order by naam,omzet;
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, min);
            statement.setInt(2, max);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                brouwers.add(naarBrouwer(result));
            }
            return brouwers;
        }
    }

    private Brouwer naarBrouwer(ResultSet result) throws SQLException {
        return new Brouwer(result.getInt("id"), result.getString("naam"), result.getInt("omzet"));
    }
}
