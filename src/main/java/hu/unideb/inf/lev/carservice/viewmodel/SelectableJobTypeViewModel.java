package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.property.*;

/**
 * View model which represents a {@link hu.unideb.inf.lev.carservice.model.JobType} entity but extends
 * it with a selectable property.
 */
public class SelectableJobTypeViewModel extends JobTypeViewModel {
    BooleanProperty selected = new SimpleBooleanProperty(false);

    /**
     * Initializes a new instance based on the provided job type.
     * @param jobType Base job type
     */
    public SelectableJobTypeViewModel(JobTypeViewModel jobType) {
        super(
                jobType.getId(),
                new SimpleStringProperty(jobType.getName()),
                new SimpleLongProperty(jobType.getPrice())
        );
    }

    /**
     * Gets if this job type is selected or not.
     * @return <code>true</code> if job type is selected, <code>false</code> otherwise.
     */
    public boolean isSelected() {
        return selected.get();
    }

    /**
     * Gets the observable selected property of the job type.
     * @return An observable property.
     */
    public BooleanProperty selectedProperty() {
        return selected;
    }

    /**
     * Sets the selected state of the job type.
     * @param selected Selected state
     */
    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}
