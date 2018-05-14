package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

import java.util.Objects;

/**
 * View model which represents a {@link hu.unideb.inf.lev.carservice.model.Person} entity.
 */
public class PersonViewModel {
    private Long id;
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private AddressViewModel address = new AddressViewModel();
    private DiscountViewModel discount = new DiscountViewModel();

    /**
     * Parameter-less constructor.
     */
    public PersonViewModel() {
    }

    /**
     * Initializes a new instance with the provided values.
     * @param id Unique identifier of the person
     * @param firstName First name of the person
     * @param lastName Last name of the person
     * @param phone Phone number of the person
     * @param address Address of the person
     * @param discount Discount of the person
     */
    public PersonViewModel(Long id, StringProperty firstName, StringProperty lastName, StringProperty phone, AddressViewModel address, DiscountViewModel discount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.discount = discount;
    }

    /**
     * Gets the unique identifier of the person.
     * @return A unique identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the first name of the person.
     * @return A first name
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * Gets the observable first name property of the person.
     * @return An observable property
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * Gets the last name of the person.
     * @return A last name
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Gets the observable last name property of the person.
     * @return An observable property.
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * Gets the phone number of the person.
     * @return A phone number
     */
    public String getPhone() {
        return phone.get();
    }

    /**
     * Gets the observable phonen number property of the person.
     * @return An observable property.
     */
    public StringProperty phoneProperty() {
        return phone;
    }

    /**
     * Gets the concatenated full name of the person.
     * @return Full name
     */
    public String getFullName() {
        return fullNameProperty().get();
    }

    /**
     * Gets the address of the person.
     * @return An address
     */
    public AddressViewModel getAddress() {
        return address;
    }

    /**
     * Gets the observable full name property of the person.
     * @return An observable property.
     */
    public ObservableStringValue fullNameProperty() {
        return Bindings.concat(lastNameProperty(), " ", firstNameProperty());
    }

    /**
     * Sets the unique identifier of the person.
     * @param id A unique identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the first name of the person.
     * @param firstName A first name
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * Sets the last name of the person.
     * @param lastName A last name
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * Sets the phone number of the person.
     * @param phone A phone number
     */
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    /**
     * Sets the address of the person.
     * @param address An address
     */
    public void setAddress(AddressViewModel address) {
        this.address = address;
    }

    /**
     * Gets the discount of the person.
     * @return A discount
     */
    public DiscountViewModel getDiscount() {
        return discount;
    }

    /**
     * Sets the discount of the person.
     * @param discount A discount
     */
    public void setDiscount(DiscountViewModel discount) {
        this.discount = discount;
    }

    /**
     * Gets the string representation of the view model.
     * @return A string describing the person.
     */
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
