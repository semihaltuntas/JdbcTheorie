package be.vdab.theorie;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    int findAantal() throws SQLException {
        String sql = """
                select count(*) as aantal
                from leveranciers
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt("aantal");
        }
    }

    List<Leverancier3> findAll() throws SQLException {
        List<Leverancier3> leveranciers = new ArrayList<>();
        String sql = """
                select id, naam, adres, postcode, woonplaats, sinds
                from leveranciers
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                leveranciers.add(naarLeverancier(result));
            }
            return leveranciers;
        }
    }


    List<Leverancier3> findByWoonPlaats(String woonplaats) throws SQLException {
        List<Leverancier3> leveranciers = new ArrayList<>();
        String sql = """
                select id,naam,adres,postcode,woonplaats,sinds
                from leveranciers
                where woonplaats = ?
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, woonplaats);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                leveranciers.add(naarLeverancier(result));
            }
            return leveranciers;
        }
    }

    Optional<Leverancier3> findById(long id) throws SQLException {
        String sql = """
                select id,naam,adres,postcode,woonplaats,sinds
                    from leveranciers
                    where id = ?
                    """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            return result.next() ? Optional.of(naarLeverancier(result)) : Optional.empty();

        }
    }

    List<Leverancier3> findBySinds2000() throws SQLException {
        List<Leverancier3> leveranciers = new ArrayList<>();
        String sql = """
                 select id,naam,adres,postcode,woonplaats,sinds
                from leveranciers
                where sinds >= {d '2000-01-01'}
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                leveranciers.add(naarLeverancier(result));
            }
            connection.commit();
            return leveranciers;
        }
    }
    /*
    * JDBC bevat datum en tijd functies die elk database merk correct verwerkt. De belangrijkste functies:
•
curdate() huidige datum
•
curtime() huidige tijd
•
now() huidige datum en tijd
•
dayofmonth(eenDatum) dag in de maand van eenDatum (getal tussen 1 en 31)
•
dayofweek(eenDatum) dag in de week van eenDatum (getal tussen 1: zondag en 7)
•
dayofyear(eenDatum) dag in het jaar van eenDatum (getal tussen 1 en 366)
•
month(eenDatum) maand in eenDatum (getal tussen 1 en 12)
•
week(eenDatum) week van eenDatum (getal tussen 1 en 53)
•
year(eenDatum) jaartal van eenDatum
•
hour(eenTijd) uur van eenTijd (getal tussen 0 en 23)
•
minute(eenTijd) minuten van eenTijd (getal tussen 0 en 59)
•
second(eenTijd) seconden van eenTijd (getal tussen 0 en 59)*/

    List<Leverancier3> findBySindsVanaf(LocalDate datum) throws SQLException {
        List<Leverancier3> leveranciers = new ArrayList<>();
        String sql = """
                  select id,naam,adres,postcode,woonplaats,sinds
                from leveranciers
                where sinds >= ?
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statement.setObject(1, datum);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                leveranciers.add(naarLeverancier(result));
            }
            connection.commit();
            return leveranciers;
        }
    }

    List<Leverancier3> findLeverancierGewordenInHetJaar2000() throws SQLException {
        List<Leverancier3> leveranciers = new ArrayList<>();
        String sql = """
                select id, naam, adres, postcode, woonplaats, sinds 
                from leveranciers 
                where {fn year(sinds)} = 2000
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                leveranciers.add(naarLeverancier(result));
            }
            connection.commit();
            return leveranciers;
        }
    }

    private Leverancier3 naarLeverancier(ResultSet result) throws SQLException {
        return new Leverancier3(result.getLong("id"), result.getString("naam"),
                result.getString("adres"), result.getInt("postcode"),
                result.getString("woonplaats"), result.getObject("sinds", LocalDate.class));
    }


}
