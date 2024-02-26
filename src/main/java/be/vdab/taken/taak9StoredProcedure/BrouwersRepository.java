package be.vdab.taken.taak9StoredProcedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrouwersRepository extends AbstractRepository {
    List<Brouwer> findByOmzetTussen(int van, int tot) throws SQLException {
        List<Brouwer> brouwersList = new ArrayList<>();

        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareCall("{call BrouwersMetOmzetTussen(?,?)}")) {
            statement.setInt(1, van);
            statement.setInt(2, tot);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                brouwersList.add(naarBrouwers(result));
            }
            return brouwersList;
        }
    }

    private Brouwer naarBrouwers(ResultSet result) throws SQLException {
        return new Brouwer(result.getInt("id"), result.getString("naam"),
                result.getInt("omzet"));
    }
}
