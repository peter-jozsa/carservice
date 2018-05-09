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

    private LocalDateTime creationDate;

    public Worksheet() {
    }

    public Worksheet(Car car, List<JobType> jobs, LocalDateTime creationDate) {
        this.car = car;
        this.jobs = jobs;
        this.creationDate = creationDate;
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
