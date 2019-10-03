package seedu.address.model.events;

import java.time.Instant;

/**
 * Represents a date and time.
 * Internally stored as seconds from epoch, without any timezone information.
 */
public class Time {

    private final Instant dateTime;

    public Time(Instant dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return dateTime.toString();
    }
}
