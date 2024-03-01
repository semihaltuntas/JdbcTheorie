package be.vdab.taken.taak18PapaEnMama;

import java.util.Optional;

record PersoonOptionelePapaEnMama(String voornaam, Optional<String> voornaamPapa,
                                  Optional<String> voornaamMama) {
}
