package hu.unideb.inf.lev.carservice.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 * An entity model which describes a person.
 */
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phone;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Car> cars = new ArrayList<>();

    private Address address;

    private Discount discount;

    /**
     * Parameter-less constructor.
     */
    public Person() {
    }

    /**
     * Initializes a new instance with the provided values.
     * @param id Unique identifier of the person.
     * @param firstName First name of the person.
     * @param lastName Last name of the person.
     * @param phone Phone number of the person.
     * @param address The address of the person.
     */
    public Person(Long id, String firstName, String lastName, String phone, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Initializes a new instance with the provided values.
     * @param id Unique identifier of the person.
     * @param firstName First name of the person.
     * @param lastName Last name of the person.
     * @param phone Phone number of the person.
     * @param address The address of the person.
     * @param discount The discount of the person.
     */
    public Person(Long id, String firstName, String lastName, String phone, Address address, Discount discount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.discount = discount;
    }

    /**
     * Initializes a new instance with the provided values.
     * @param firstName First name of the person.
     * @param lastName Last name of the person.
     * @param phone Phone number of the person.
     * @param address The address of the person.
     */
    public Person(String firstName, String lastName, String phone, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }


    /**
     * Initializes a new instance with the provided values.
     * @param firstName First name of the person.
     * @param lastName Last name of the person.
     * @param phone Phone number of the person.
     * @param cars List of cars this person owns.
     * @param address The address of the person.
     */
    public Person(String firstName, String lastName, String phone, List<Car> cars, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.cars = cars;
        this.address = address;
    }

    /**
     * Gets the unique identifier of the person.
     * @return The unique identifier of the person.
     */
    @XmlAttribute(name = "id")
    public Long getId() {
        return id;
    }

    /**
     * Gets the first name of the person.
     * @return The first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the person.
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the phone number of the person.
     * @return The phone number of the person.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the address of the person.
     * @return The address of the person.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the unique identifier of the person.
     * @param id Unique identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the first name of the person.
     * @param firstName A first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets tha last name of the person.
     * @param lastName A last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the phone number of the person.
     * @param phone A phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the address of the person.
     * @param address An address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the discount of the person.
     * @return A discount
     */
    public Discount getDiscount() {
        return discount;
    }

    /**
     * Sets the discount of the person.
     * @param discount A discount
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Gets the cars owned by the person.
     * @return A list of cars owned by this person.
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * Sets the list of cars this person owns.
     * @param cars List of cars
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * Adds a car to the list of cars owned by this person and also sets the owner
     * of the car to this person.
     * @param car A car to be owned by this person.
     */
    public void addCar(Car car) {
        car.setOwner(this);
        this.cars.add(car);
    }

    /**
     * Converts the instance to a string representation.
     * @return A string representation of an <code>Person</code> instance.
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}
