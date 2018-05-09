package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

public class PersonViewModel {
    private LongProperty id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty phone;
    private AddressViewModel address;

    public PersonViewModel(LongProperty id, StringProperty firstName, StringProperty lastName, StringProperty phone, AddressViewModel address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
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

    public void setId(long id) {
        this.id.set(id);
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
                "id=" + getId() +
                ", firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", phone=" + getPhone() +
                ", address=" + getAddress() +
                '}';
    }
}
