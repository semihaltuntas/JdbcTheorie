package be.vdab.taken.taak14OmzetLeegMaken;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static Set<Long> vraagIds() {
        Set<Long> ids = new LinkedHashSet<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("id (stop met o):");
        for (long id; (id = scanner.nextLong()) != 0; ) {
            System.out.println("id (stop met 0): ");
            if (id < 0) {
                System.out.println("nummer moet positief zijn,opnieuw probeer :");
            } else {
                if (!ids.add(id)) {
                    System.out.println(id + " reeds getypt,probeer opnieuw:");
                }
            }
        }
        return ids;
    }

    public static void main(String[] args) {
        var ids = vraagIds();
        if (!ids.isEmpty()) {
            var repository = new BrouwerRepository();
            try {
                if (repository.maakOmzetLeeg(ids) != ids.size()) {
                    System.out.println("Niet gevonden ids:");
                    var gevondenIds = repository.findIds(ids);
                    ids.stream().filter(id -> !gevondenIds.contains(id)).forEach(System.out::println);
                }
            } catch (
                    SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }
}
