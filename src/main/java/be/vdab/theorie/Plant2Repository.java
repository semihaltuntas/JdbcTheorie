package be.vdab.theorie;

import jdk.dynalink.linker.LinkerServices;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    void verlaagPrijs(long id, BigDecimal nieuwePrijs) throws SQLException {
        String sqlUpdate = """
                update planten
                set prijs = ?
                where id = ? and ? > prijs / 2
                 """;
        try (Connection connection = super.getConnection();
             PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdate)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statementUpdate.setBigDecimal(1, nieuwePrijs);
            statementUpdate.setLong(2, id);
            statementUpdate.setBigDecimal(3, nieuwePrijs);
            var aantalAangepasteRecords = statementUpdate.executeUpdate();
            System.out.println(aantalAangepasteRecords);
            if (aantalAangepasteRecords == 1) {
                connection.commit();
            } else {
                String sqlSelect = """
                        select id 
                        from planten
                        where id = ?
                        """;
                try (PreparedStatement statement = connection.prepareStatement(sqlSelect)) {
                    statement.setLong(1, id);
                    ResultSet result = statement.executeQuery();
                    if (result.next()) {
                        connection.rollback();
                        throw new IllegalArgumentException("Prijs te laag");
                    }
                    connection.rollback();
                    throw new IllegalArgumentException("Plant niet gevonden");
                }
            }
        }
    }

    List<String> findNamenByIds(Set<Long> ids) throws SQLException {
        if (ids.isEmpty()) {
            return List.of();
        }
        List<String> namen = new ArrayList<>();
        String sql = """
                select naam
                from planten
                where id in (
                """
                + "?,".repeat(ids.size() - 1)
                + "?)";
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            for (long id : ids) {
                statement.setLong(index++, id);
                //System.out.println(statement);
            }
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                namen.add(result.getString("naam"));
            }
            connection.commit();
            return namen;
        }
    }

    List<Plant2NaamEnLeveranciersNaam> findRodePlantenEnHunleveranciers() throws SQLException {
        List<Plant2NaamEnLeveranciersNaam> list = new ArrayList<>();
        String sql = """
                select planten.naam as plantnaam, leveranciers.naam as leveranciersnaam
                from planten inner join leveranciers
                on planten.leverancierId=leveranciers.id
                where kleur = 'rood'
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Plant2NaamEnLeveranciersNaam(result.getString("plantnaam"),
                        result.getString("leveranciersnaam")));
            }
            connection.commit();
            return list;
        }
    }
}
