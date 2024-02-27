package be.vdab.taken.taak10Failliet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BierenRepository extends AbstractRepository {

void faillietBrouwer1() throws SQLException {
    String sqlBrouwer2 = """
            update bieren
            set brouwerId=2
            where brouwerId = 1 and alcohol >= 8.5 
            """;
    String sqlBrouwer3 = """
            update bieren 
            set brouwerId = 3
            where brouwerId = 1 and alcohol < 8.5
            """;
    String sqlDeleteBrouwer1 = """
            delete from brouwers 
            where id = 1            
            """;

    try(Connection connection = super.getConnection();
        PreparedStatement statementTransectieNaarBrouwer2 = connection.prepareStatement(sqlBrouwer2);
        PreparedStatement statementTransectieNaarBrouwer3 = connection.prepareStatement(sqlBrouwer3);
        PreparedStatement statementDeleteBrouwerId1= connection.prepareStatement(sqlDeleteBrouwer1)){

        connection.setAutoCommit(false);
        statementTransectieNaarBrouwer2.executeUpdate();
        statementTransectieNaarBrouwer3.executeUpdate();
        statementDeleteBrouwerId1.executeUpdate();
        connection.commit();
    }
}
}
