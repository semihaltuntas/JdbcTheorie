package be.vdab.taken.taak19Erfenis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PersoonRepository extends AbstractRepository {

    void erfenis(long id) throws SQLException {

        try (var connection = super.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            var optionalVermogen = zoekVermogen(id, connection);
            if (optionalVermogen.isEmpty()) {
                connection.rollback();
                throw new IllegalArgumentException("Niet gevonden");
            }
            var vermogen = optionalVermogen.get();
            if (vermogen.compareTo(BigDecimal.ZERO) == 0) {
                connection.rollback();
                throw new IllegalArgumentException("Geen vermogen");
            }
            var aantalKinderen = zoekAantalKinderen(id, connection);
            if (aantalKinderen == 0) {
                connection.rollback();
                throw new IllegalArgumentException("Geen kinderen");
            }
            var erfenisPerKind = vermogen.divide(BigDecimal.valueOf(aantalKinderen), 2, RoundingMode.HALF_UP);
            verHoogVermogen(id, connection, erfenisPerKind);
            zetVermogenOpNul(id, connection);
            connection.commit();
        }
    }

    private Optional<BigDecimal> zoekVermogen(long id, Connection connection) throws SQLException {
        {
            // lock met for update zodat een andere gebruiker
            // die dit ook wil doen met dezelfde person even moet wachten:
            String sql = """
                    select vermogen
                    from personen
                    where id = ?
                    for update
                    """;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet result = statement.executeQuery();
                return result.next() ?
                        Optional.of(result.getBigDecimal("vermogen")) :
                        Optional.empty();
            }
        }
    }

    private int zoekAantalKinderen(long id, Connection connection) throws SQLException {
        var sql = """
                select count(*) as aantalKinderen 
                from personen 
                where papaId= ? or mamaId = ?
                 """;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.setLong(2, id);
            var result = statement.executeQuery();
            result.next();
            return result.getInt("aantalKinderen");
        }
    }

    private void verHoogVermogen(long id, Connection connection, BigDecimal erfenis) throws SQLException {
        var sql = """
                update personen 
                set vermogen = vermogen + ? 
                where papaid = ? or mamaid = ?
                 """;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setBigDecimal(1, erfenis);
            statement.setLong(2, id);
            statement.setLong(3, id);
            statement.executeUpdate();
        }
    }

    private void zetVermogenOpNul(long id, Connection connection) throws SQLException {
        var sql = """ 
                update personen 
                set vermogen = 0 
                where id = ? 
                """;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
