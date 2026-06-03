package model.didattica;

import java.time.LocalTime;

public enum OraInizio {
    PAR_1("09:00", LocalTime.of(9, 0)),
    PAR_2("11:00", LocalTime.of(11, 0)),
    PAR_3("13:00", LocalTime.of(13, 0)),
    PAR_4("15:00", LocalTime.of(15, 0)),
    PAR_5("17:00", LocalTime.of(17, 0));

    private final String label;
    private final LocalTime time;

    OraInizio(String label, LocalTime time) {
        this.label = label;
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return label;
    }
}
