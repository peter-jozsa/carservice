package hu.unideb.inf.lev.carservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Car() {
    }

    public Car(Long id, String registrationNumber, String brand, String type, String VIN, Person owner) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.type = type;
        this.VIN = VIN;
        this.owner = owner;
    }

    public Car(String registrationNumber, String brand, String type, String VIN, Person owner) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.type = type;
        this.VIN = VIN;
        this.owner = owner;
    }

    public Car(String registrationNumber, String brand, String type, String VIN) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.type = type;
        this.VIN = VIN;
    }

    public Long getId() {
        return id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public String getVIN() {
        return VIN;
    }

    public Person getOwner() {
        return owner;
    }

    public List<Worksheet> getWorksheets() {
        return worksheets;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVIN(String vin) {
        this.VIN = vin;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setWorksheets(List<Worksheet> worksheets) {
        this.worksheets = worksheets;
    }

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
