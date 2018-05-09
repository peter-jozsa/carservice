package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JobTypeViewModel {
    private Long id;
    private StringProperty name = new SimpleStringProperty();

    public JobTypeViewModel() {
    }

    public JobTypeViewModel(StringProperty name) {
        this.name = name;
    }

    public JobTypeViewModel(Long id, StringProperty name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return "JobTypeViewModel{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
