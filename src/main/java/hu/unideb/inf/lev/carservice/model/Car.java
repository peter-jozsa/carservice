package hu.unideb.inf.lev.carservice.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

/**
 * An entity model which describes a car.
 */
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String VIN;

    @ManyToOne()
    private Person owner;

    @OneToMany
    private List<Worksheet> worksheets;

    /**
     * Paramter-less constructor.
     */
    public Car() {
    }

    /**
     * Initializes a new instance.
     * @param id The unique identifier of the car.
     * @param registrationNumber The registration plate number of the car.
     * @param brand The brand name of the car.
     * @param type The type of the car.
     * @param VIN The VIN of the car.
     * @param owner The owner of the car.
     */
    public Car(Long id, String registrationNumber, String brand, String type, String VIN, Person owner) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.type = type;
        this.VIN = VIN;
        this.owner = owner;
    }

    /**
     * Initializes a new instance.
     * @param registrationNumber The registration plate number of the car.
     * @param brand The brand name of the car.
     * @param type The type of the car.
     * @param VIN The VIN of the car.
     * @param owner The owner of the car.
     */
    public Car(String registrationNumber, String brand, String type, String VIN, Person owner) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.type = type;
        this.VIN = VIN;
        this.owner = owner;
    }


    /**
     * Initializes a new instance.
     * @param registrationNumber The registration plate number of the car.
     * @param brand The brand name of the car.
     * @param type The type of the car.
     * @param VIN The VIN of the car.
     */
    public Car(String registrationNumber, String brand, String type, String VIN) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.type = type;
        this.VIN = VIN;
    }

    /**
     * Gets the unique identifier of the car.
     * @return The unique identifier of the car.
     */
    @XmlAttribute(name = "id")
    public Long getId() {
        return id;
    }

    /**
     * Gets the registration plate number of the car.
     * @return The registration plate number of the car.
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Gets the brand of the car.
     * @return The name of the brand.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the type of the car.
     * @return The type of the car.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the VIN of the car.
     * @return The VIN of the car.
     */
    public String getVIN() {
        return VIN;
    }

    /**
     * Gets the owner of the car.
     * @return The owner of the car.
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Gets all worksheet associated to this car.
     * @return A list of {@link Worksheet} entities associated to this car.
     */
    public List<Worksheet> getWorksheets() {
        return worksheets;
    }

    /**
     * Sets the unique identifier of the car.
     * @param id The unique identifier value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the registration plate number.
     * @param registrationNumber The registration plate number.
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Sets the brand of the car.
     * @param brand The name of the brand.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Sets the type of the car.
     * @param type The type of the car.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the VIN of the car.
     * @param vin The VIN
     */
    public void setVIN(String vin) {
        this.VIN = vin;
    }

    /**
     * Sets the owner of the car.
     * @param owner The owner person
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * Converts the instance to a string representation.
     * @return A string representation of a <code>Car</code> instance.
     */
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", VIN='" + VIN + '\'' +
                ", owner=" + owner +
                '}';
    }
}
