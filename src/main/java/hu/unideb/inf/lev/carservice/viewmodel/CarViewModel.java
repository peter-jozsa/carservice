package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

public class CarViewModel {
    private Long id;
    private StringProperty registrationNumber = new SimpleStringProperty();
    private StringProperty brand = new SimpleStringProperty();
    private StringProperty type = new SimpleStringProperty();
    private StringProperty VIN = new SimpleStringProperty();
    private ObjectProperty<PersonViewModel> owner = new SimpleObjectProperty<>();

    public CarViewModel() {
    }

    public CarViewModel(Long id, StringProperty registrationNumber, StringProperty brand, StringProperty type, StringProperty VIN, ObjectProperty<PersonViewModel> owner) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.type = type;
        this.VIN = VIN;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber.get();
    }

    public StringProperty registrationNumberProperty() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber.set(registrationNumber);
    }

    public String getBrand() {
        return brand.get();
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public String getVIN() {
        return VIN.get();
    }

    public StringProperty VINProperty() {
        return VIN;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public void setVIN(String VIN) {
        this.VIN.set(VIN);
    }

    public PersonViewModel getOwner() {
        return owner.get();
    }

    public ObjectProperty<PersonViewModel> ownerProperty() {
        return owner;
    }

    public void setOwner(PersonViewModel owner) {
        this.owner.setValue(owner);
    }

    public ObservableStringValue fullNameProperty() {
        return Bindings.concat(registrationNumberProperty(), " (", brandProperty(), " ", typeProperty(), ")");
    }

    public String getFullName() {
        return fullNameProperty().get();
    }


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
