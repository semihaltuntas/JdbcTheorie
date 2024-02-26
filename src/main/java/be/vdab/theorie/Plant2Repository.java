package be.vdab.theorie;

import jdk.dynalink.linker.LinkerServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Plant2Repository extends AbstractRepository {
    int verhoogPrijzenMet10Procent(String naam) throws SQLException {
        String sql = """
                   update planten
                   set prijs = prijs * 1.1
                   where naam = ?
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, naam);
            return statement.executeUpdate();
        }
    }

    List<String> findNamenByWoord(String woord) throws SQLException {
        List<String> namen = new ArrayList<>();
        try (Connection connection = super.getConnection();
             PreparedStatement statement =
                     connection.prepareCall("{call PlantNamenMetEenWoord(?)}")) {
            statement.setString(1, '%' + woord + '%');
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                namen.add(result.getString("naam"));
            }
            return namen;
        }

    }
}
