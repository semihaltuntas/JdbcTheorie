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
    Optional<Leverancier3> findById(long id) throws  SQLException{
        String sql = """
            select id,naam,adres,postcode,woonplaats,sinds
                from leveranciers
                where id = ?
                """;
        try(Connection connection = super.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
           return result.next() ? Optional.of(naarLeverancier(result)) : Optional.empty();

        }
    }
    private Leverancier3 naarLeverancier(ResultSet result) throws SQLException {
        return new Leverancier3(result.getLong("id"), result.getString("naam"),
                result.getString("adres"), result.getInt("postcode"),
                result.getString("woonplaats"), result.getObject("sinds", LocalDate.class));
    }


}
