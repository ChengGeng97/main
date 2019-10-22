package seedu.address.ui.panel.calendar;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.RowConstraints;

import seedu.address.model.events.EventSource;
import seedu.address.ui.EventCard;
import seedu.address.ui.EventCardHolder;
import seedu.address.ui.UiParser;

/**
 * An UI component that displays the feedback to the user.
 */
public class TimelineDayView extends TimelineView {

    private static final String FXML = "TimelineDayView.fxml";

    private Integer day;
    private Integer month;
    private Integer year;


    /**
     * Constructor for TimelineDayView for a particular day.
     *
     * @param day Represents the day of the timeline.
     * @param month Represents the month of the timeline.
     * @param year Represents the year of the timeline.
     * @param uiParser Represents a parser to convert certain types of objects into other types of objects.
     */
    public TimelineDayView(Integer day,
                           Integer month,
                           Integer year,
                           List<EventSource> eventList,
                           UiParser uiParser) {
        super(uiParser, FXML);
        this.day = day;
        this.month = month;
        this.year = year;
        this.totalRows = 24;

        this.timelineTitle.setText("Timeline: " + uiParser.getEnglishDate(day, month, year));
        addGrids();
        addLabels(getLabels());
        addEventCardHolders();
        eventChange(eventList);
    }

    /**
     * Returns true if the event is the same day as the current timeline.
     * @param event The given event.
     * @return True if the event is the same day as the current timeline.
     */
    private boolean sameDate(EventSource event) {
        Integer[] dayMonthYear = uiParser.getDateToNumbers(event.getStartDateTime().toInstant());
        return dayMonthYear[0].equals(day) && dayMonthYear[1].equals(month) && dayMonthYear[2].equals(year);
    }

    /**
     * Adds an event to the timeline.
     * @param event Represents the given event.
     */
    private void addEventCard(EventSource event) {
        // Gets the event Hour
        Integer eventHour = uiParser.getHour(event.getStartDateTime().toInstant());

        EventCard eventCard = new EventCard(event, uiParser);
        EventCardHolder eventCardHolder = eventCardHolders.get(eventHour);
        eventCardHolder.addEvent(eventCard);

        // Set Constraints for the grid pane
        RowConstraints rowConstraints = timelineGrid.getRowConstraints().get(eventHour);
        updateSizeDelay(rowConstraints, eventCardHolder);
    }

    private String[] getLabels() {
        String[] labels = new String[24];
        for(int time = 0; time < 10; time++) {
            labels[time] = "0" + time + ":00";
        }
        for(int time = 10; time < 24; time++) {
            labels[time] = time + ":00";
        }
        return labels;
    }

    @Override
    public void eventChange(List<EventSource> eventList) {
        resetTimeline();
        for (EventSource event : eventList) {
            if (sameDate(event)) {
                addEventCard(event);
            }
        }
    }
}
