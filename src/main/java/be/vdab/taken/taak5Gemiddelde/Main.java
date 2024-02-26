package be.vdab.taken.taak5Gemiddelde;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        BrouwerRepository repository = new BrouwerRepository();
        try {
            System.out.println(repository.findGemiddeldeOmzet());
            repository.findOmzetAlsGroterDanGemiddelde().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
