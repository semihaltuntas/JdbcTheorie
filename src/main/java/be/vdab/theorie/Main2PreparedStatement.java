package be.vdab.theorie;

import java.sql.SQLException;

public class Main2PreparedStatement extends AbstractRepository {

    public static void main(String[] args) {
        var repository = new Plant2Repository();
        try {
            System.out.println(repository.verhoogPrijzenMet10Procent());
            System.out.println(" planten aangepast.");

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
