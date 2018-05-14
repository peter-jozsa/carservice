package hu.unideb.inf.lev.carservice.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An entity model which describes a job type.
 */
@Entity
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Long price;

    /**
     * Parameter-less constructor.
     */
    public JobType() {
    }

    /**
     * Initializes a new instance with the given values.
     * @param name The name of the job type.
     * @param price The cost of the job type.
     */
    public JobType(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Initializes a new instance with the given values.
     * @param id The unique identifier of the job type.
     * @param name The name of the job type.
     * @param price The price of the job type.
     */
    public JobType(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the unique identifier of the job type.
     * @return The unique identifier of the job type.
     */
    @XmlAttribute(name = "id")
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the job type.
     * @param id The unique identifier value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the job type.
     * @return The name of the job type.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the job type.
     * @param name The name of the job type.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the job type.
     * @return The cost of the job type.
     */
    public Long getPrice() {
        return price;
    }

    /**
     * Sets the price of the job type.
     * @param price The cost of the job type.
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * Converts the instance to a string representation.
     * @return A string representation of an <code>JobType</code> instance.
     */
    @Override
    public String toString() {
        return "JobType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
