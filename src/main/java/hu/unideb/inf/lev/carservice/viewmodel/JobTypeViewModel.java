package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class JobTypeViewModel {
    private Long id;
    private StringProperty name = new SimpleStringProperty();
    private LongProperty price = new SimpleLongProperty();

    public JobTypeViewModel() {
    }

    public JobTypeViewModel(Long id, StringProperty name, LongProperty price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public long getPrice() {
        return price.get();
    }

    public LongProperty priceProperty() {
        return price;
    }

    public void setPrice(long price) {
        this.price.set(price);
    }

    @Override
    public String toString() {
        return "JobTypeViewModel{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobTypeViewModel)) return false;
        JobTypeViewModel that = (JobTypeViewModel) o;

        if (id == null) {
            return Objects.equals(name, that.name) && Objects.equals(price, that.price);
        }

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
