package hu.unideb.inf.lev.carservice.model;

import hu.unideb.inf.lev.carservice.utility.LocalDateTimeAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * An entity model which describes a Worksheet.
 */
@Entity
public class Worksheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToMany
    private List<JobType> jobs;

    private Long total;

    private Integer discount;

    private LocalDateTime creationDate;

    /**
     * Parameter-less constructor.
     */
    public Worksheet() {
    }

    /**
     * Initializes a new instance with the provided values.
     * @param car A car entity
     * @param jobs A list of job type entities
     * @param total The total cost of the worksheet
     * @param discount Amount of discount in percentage(0-100)
     */
    public Worksheet(Car car, List<JobType> jobs, Long total, Integer discount) {
        this.car = car;
        this.jobs = jobs;
        this.total = total;
        this.discount = discount;
    }

    /**
     * Initializes a new instance with the provided values.
     * @param id A unique identifier
     * @param car A car entity
     * @param jobs A list of job type entities
     * @param total The total cost of the worksheet
     * @param discount Amount of discount in percentage(0-100)
     */
    public Worksheet(Long id, Car car, List<JobType> jobs, Long total, Integer discount) {
        this.id = id;
        this.car = car;
        this.jobs = jobs;
        this.total = total;
        this.discount = discount;
    }

    /**
     * Gets the unique identifier of the worksheet.
     * @return The unique identifier of the worksheet.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the car of the worksheet.
     * @return A car entity.
     */
    public Car getCar() {
        return car;
    }

    /**
     * Gets the list of jobs done on the car of the worksheet.
     * @return A list of job type entities.
     */
    @XmlElementWrapper(name = "jobs")
    @XmlElement(name = "job")
    public List<JobType> getJobs() {
        return jobs;
    }

    /**
     * Gets the creation timestamp of the worksheet.
     * @return A timestamp
     */
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Gets the total cost of servicing.
     * @return The total cost of servicing.
     */
    public Long getTotal() {
        return total;
    }

    /**
     * Gets the discount percentage of this worksheet.
     * @return The discount value of the worksheet.
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * Sets the unique identifier of the worksheet.
     * @param id A unique identifier.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the car of the worksheet.
     * @param car A car entity.
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Sets the list of jobs done on the car of this worksheet.
     * @param jobs A list of job type entities.
     */
    public void setJobs(List<JobType> jobs) {
        this.jobs = jobs;
    }

    /**
     * Sets the creation timestamp of the worksheet.
     * @param creationDate A timestamp value.
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Sets the total cost of servicing.
     * @param total A cost of servicing.
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * Sets the discount percentage applied for the worksheet.
     * @param discount A discount percentage(0-100)
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * Converts the instance to a string representation.
     * @return A string representation of an <code>Worksheet</code> instance.
     */
    @Override
    public String toString() {
        return "Worksheet{" +
                "id=" + id +
                ", car=" + car +
                ", jobs=" + jobs +
                ", creationDate=" + creationDate +
                '}';
    }
}
