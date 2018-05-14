package hu.unideb.inf.lev.carservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Worksheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToMany
    private List<JobType> jobs;

    private Long mileage;

    private Long total;

    private Integer discount;

    private LocalDateTime creationDate;

    public Worksheet() {
    }

    public Worksheet(Car car, List<JobType> jobs, Long mileage, Long total, Integer discount) {
        this.car = car;
        this.jobs = jobs;
        this.mileage = mileage;
        this.total = total;
        this.discount = discount;
    }

    public Worksheet(Long id, Car car, List<JobType> jobs, Long mileage, Long total, Integer discount) {
        this.id = id;
        this.car = car;
        this.jobs = jobs;
        this.mileage = mileage;
        this.total = total;
        this.discount = discount;
    }


    public Long getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public List<JobType> getJobs() {
        return jobs;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Long getMileage() {
        return mileage;
    }

    public Long getTotal() {
        return total;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setJobs(List<JobType> jobs) {
        this.jobs = jobs;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

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
