package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

import java.util.Objects;

public class PersonViewModel {
    private Long id;
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private AddressViewModel address = new AddressViewModel();

    public PersonViewModel() {
    }

    public PersonViewModel(Long id, StringProperty firstName, StringProperty lastName, StringProperty phone, AddressViewModel address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public PersonViewModel(StringProperty firstName, StringProperty lastName, StringProperty phone, AddressViewModel address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getFullName() {
        return fullNameProperty().get();
    }

    public AddressViewModel getAddress() {
        return address;
    }

    public ObservableStringValue fullNameProperty() {
        return Bindings.concat(lastNameProperty(), " ", firstNameProperty());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setAddress(AddressViewModel address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonViewModel{" +
                "firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", phone=" + getPhone() +
                ", address=" + getAddress() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonViewModel that = (PersonViewModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, address);
    }
}
