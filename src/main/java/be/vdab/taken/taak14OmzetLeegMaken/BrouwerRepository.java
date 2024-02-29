package be.vdab.taken.taak14OmzetLeegMaken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BrouwerRepository extends AbstractRepository {
    int maakOmzetLeeg(Set<Long> ids) throws SQLException {
        if (ids.isEmpty()) {
            return 0;
        }
        String sql = """
                update brouwers 
                set omzet = null
                where id in(
                """
                + "? ".repeat(ids.size() - 1)
                + "?)";
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            int index = 1;
            for (long id : ids) {
                statement.setLong(index++, id);
            }
            int aantalAangepast = statement.executeUpdate();
            connection.commit();
            return aantalAangepast;
        }
    }

    Set<Long> findIds(Set<Long> ids) throws SQLException {
        if (ids.isEmpty()) {
            return Set.of();
        }
        var gevondenIds = new HashSet<Long>();
        String sql = """
                select id 
                from brouwers 
                where id in(
                """
                + "? ".repeat(ids.size() - 1)
                + "?)";
        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            var index = 1;
            for (var id : ids) {
                statement.setLong(index++, id);
            }
            for (var result = statement.executeQuery(); result.next(); ) {
                gevondenIds.add(result.getLong("id"));
            }
            return gevondenIds;
        }
    }
}
