package unicam.cs.ids.dtos;

import unicam.cs.ids.models.tempo.OrarioInizioFine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Coppia(String inizio, String fine) {

    public OrarioInizioFine serializzazione() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return new OrarioInizioFine(LocalDateTime.parse(inizio, dateTimeFormatter), LocalDateTime.parse(fine, dateTimeFormatter));
    }

}
