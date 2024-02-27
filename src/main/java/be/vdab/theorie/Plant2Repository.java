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

    void verhoogPrijzenBovenEnOnder100Euro() throws SQLException {
        String sqlVanaf100 = """
                update planten 
                set prijs = prijs * 1.1
                where prijs >= 100
                """;
        String sqlTot100 = """
                update planten
                set prijs = prijs * 1.05
                where prijs < 100
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statementVanaf100 = connection.prepareStatement(sqlVanaf100);
             PreparedStatement statementTot100 = connection.prepareStatement(sqlTot100)) {
            /*auto commit false yapıyoruz cunku birbiryle iliskili ıkı durumu check etmemız gerekıyor.
            auto commiti kapatıp kontrol ettıkten sonra sıkıntı yoksa commietleyip onaylıyoruz*/
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            statementVanaf100.executeUpdate();
            statementTot100.executeUpdate();
            connection.commit();
        }
    }
}
