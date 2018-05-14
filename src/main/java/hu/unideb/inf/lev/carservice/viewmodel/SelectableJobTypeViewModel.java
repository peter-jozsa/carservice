package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.property.*;

public class SelectableJobTypeViewModel extends JobTypeViewModel {
    BooleanProperty selected = new SimpleBooleanProperty(false);

    public SelectableJobTypeViewModel(JobTypeViewModel jobType) {
        super(
                jobType.getId(),
                new SimpleStringProperty(jobType.getName()),
                new SimpleLongProperty(jobType.getPrice())
        );
    }

    public SelectableJobTypeViewModel(BooleanProperty selected) {
        this.selected = selected;
    }

    public SelectableJobTypeViewModel(Long id, StringProperty name, LongProperty price, BooleanProperty selected) {
        super(id, name, price);
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}
