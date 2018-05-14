package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

/**
 * View model which represents a {@link hu.unideb.inf.lev.carservice.model.JobType} entity.
 */
public class JobTypeViewModel {
    private Long id;
    private StringProperty name = new SimpleStringProperty();
    private LongProperty price = new SimpleLongProperty();

    /**
     * Parameter-less constructor.
     */
    public JobTypeViewModel() {
    }

    /**
     * Initializes a new instance with the provided values.
     * @param id Unique identifier
     * @param name An observable job name
     * @param price An observable price
     */
    public JobTypeViewModel(Long id, StringProperty name, LongProperty price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the unique identifier of hte job type.
     * @return Unique identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the job type.
     * @param id Unique identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the job type.
     * @return A job type name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Gets the observable name property.
     * @return An observable property.
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Sets the name of the job type.
     * @param name A job type name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Gets the price of the job type.
     * @return The price of the job type
     */
    public long getPrice() {
        return price.get();
    }

    /**
     * Gets an observable price property.
     * @return An observable property.
     */
    public LongProperty priceProperty() {
        return price;
    }

    /**
     * Sets the price property of the job type.
     * @param price The price value
     */
    public void setPrice(long price) {
        this.price.set(price);
    }

    /**
     * Gets the string representation of the view model.
     * @return A string describing the job type.
     */
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
