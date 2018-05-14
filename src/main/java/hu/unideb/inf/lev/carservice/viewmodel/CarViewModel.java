package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

/**
 * View model which represents a {@link hu.unideb.inf.lev.carservice.model.Car} entity.
 */
public class CarViewModel {
    private Long id;
    private StringProperty registrationNumber = new SimpleStringProperty();
    private StringProperty brand = new SimpleStringProperty();
    private StringProperty type = new SimpleStringProperty();
    private StringProperty VIN = new SimpleStringProperty();
    private ObjectProperty<PersonViewModel> owner = new SimpleObjectProperty<>();

    /**
     * Parameter-less constructor.
     */
    public CarViewModel() {
    }

    /**
     * Initializes a new instance with the provided values.
     * @param id Unique identifier of the car
     * @param registrationNumber Observable registration number
     * @param brand Observable brand name
     * @param type Observable type
     * @param VIN Observable VIN
     * @param owner Owner person
     */
    public CarViewModel(Long id, StringProperty registrationNumber, StringProperty brand, StringProperty type, StringProperty VIN, ObjectProperty<PersonViewModel> owner) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.type = type;
        this.VIN = VIN;
        this.owner = owner;
    }

    /**
     * Gets the unique identifier of the car.
     * @return A unique identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the car.
     * @param id A unique identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the registration number of the car.
     * @return Registration number of the car
     */
    public String getRegistrationNumber() {
        return registrationNumber.get();
    }

    /**
     * Gets the observable registration number property.
     * @return An observable property
     */
    public StringProperty registrationNumberProperty() {
        return registrationNumber;
    }

    /**
     * Sets the registration number of the car.
     * @param registrationNumber A car registration number
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber.set(registrationNumber);
    }

    /**
     * Gets the brand of the car.
     * @return A car brand name
     */
    public String getBrand() {
        return brand.get();
    }

    /**
     * Gets the observable brand name property.
     * @return An observable property.
     */
    public StringProperty brandProperty() {
        return brand;
    }

    /**
     * Gets the VIN of the car.
     * @return VIN of the car
     */
    public String getVIN() {
        return VIN.get();
    }

    /**
     * Gets the observable VIN property.
     * @return An observable property.
     */
    public StringProperty VINProperty() {
        return VIN;
    }

    /**
     * Sets brand name of the car.
     * @param brand A car brand name
     */
    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    /**
     * Gets the type of the car.
     * @return A car type
     */
    public String getType() {
        return type.get();
    }

    /**
     * Gets the observable type property.
     * @return An observable property.
     */
    public StringProperty typeProperty() {
        return type;
    }

    /**
     * Sets the type of the car.
     * @param type A car type
     */
    public void setType(String type) {
        this.type.set(type);
    }

    /**
     * Sets the VIN of the car.
     * @param VIN VIN
     */
    public void setVIN(String VIN) {
        this.VIN.set(VIN);
    }

    /**
     * Gets the owner of the car.
     * @return A person entity who owns the car.
     */
    public PersonViewModel getOwner() {
        return owner.get();
    }

    /**
     * Gets the observable owner of the car.
     * @return An observable property.
     */
    public ObjectProperty<PersonViewModel> ownerProperty() {
        return owner;
    }

    /**
     * Sets the owner of the car.
     * @param owner A person entity.
     */
    public void setOwner(PersonViewModel owner) {
        this.owner.setValue(owner);
    }

    /**
     * Gets the concatenated full name of the car as an observable.
     * @return An observable string value.
     */
    public ObservableStringValue fullNameProperty() {
        return Bindings.concat(registrationNumberProperty(), " (", brandProperty(), " ", typeProperty(), ")");
    }

    /**
     * Gets the full name of the car.
     * @return Full name of the car.
     */
    public String getFullName() {
        return fullNameProperty().get();
    }

    /**
     * Gets the string representation of the view model.
     * @return A string describing the car.
     */
    @Override
    public String toString() {
        return "CarViewModel{" +
                "id=" + id +
                ", registrationNumber=" + registrationNumber +
                ", brand=" + brand +
                ", type=" + type +
                ", VIN=" + VIN +
                ", owner=" + owner +
                '}';
    }
}
