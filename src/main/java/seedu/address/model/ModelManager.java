package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.events.EventSource;
import seedu.address.model.listeners.ModelListListener;
import seedu.address.model.tasks.TaskSource;

/**
 * Represents a class that manages the {@link ModelData} of Horo.
 */
public class ModelManager {
    private ModelData model;

    private final List<ModelListListener> modelListListeners;

    /**
     * Creates a ModelManager.
     */
    public ModelManager() {
        super();
        this.model = new ModelData();

        this.modelListListeners = new ArrayList<>();
    }

    public void addModelListListener(ModelListListener listener) {
        this.modelListListeners.add(listener);
    }

    /**
     * Replaces the current EventList and TaskList with a deep copy of a ModelLists provided.
     * @param lists a ModelLists to replace this Model
     */
    public void setModelData(ModelData lists) {
        // Deep copy and reassign model.
        this.model = new ModelData(lists);

        // Notify all listeners whenever either the EventList or TaskList is changed.
        this.modelListListeners
            .forEach(listener -> listener.onModelListChange(this.getModelData()));
    }

    /**
     * Returns an immutable, deep copy of Horo's events and tasks.
     *
     * @return a ModelLists
     */
    public ModelData getModelData() {
        return new ModelData(this.model);
    }

    /**
     * Returns an immutable, deep copy of this Horo's events.
     *
     * @return a copy of the Horo's events
     */
    public List<EventSource> getEvents() {
        return this.model.getEvents();
    }

    /* Tasks */

    /**
     * Returns an immutable, deep copy of Horo's tasks.
     *
     * @return a copy of Horo's tasks
     */
    public List<TaskSource> getTasks() {
        return this.model.getTasks();
    }
}
