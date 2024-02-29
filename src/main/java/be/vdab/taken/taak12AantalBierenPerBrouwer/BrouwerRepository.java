package be.vdab.taken.taak12AantalBierenPerBrouwer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrouwerRepository extends AbstractRepository{
    List<BrouwerNaamEnAantalBieren> findBrouwerNamenEnAantalBieren ()throws SQLException{
        List<BrouwerNaamEnAantalBieren> list = new ArrayList<>();
        String sql = """
                select brouwers.naam as brouwersnaam, count(*) as aantalbieren
                from brouwers inner join bieren
                on brouwers.id = bieren.brouwerId
                group by brouwersnaam
                order by brouwersnaam;
                """;
        try(Connection connection = super.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                list.add(new BrouwerNaamEnAantalBieren(result.getString("brouwersnaam"),
                        result.getInt("aantalbieren")));
            }
            connection.commit();
            return list;

        }
    }
}
