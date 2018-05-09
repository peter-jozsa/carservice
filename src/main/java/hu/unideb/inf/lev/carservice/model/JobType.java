package hu.unideb.inf.lev.carservice.model;

import javax.persistence.*;

@Entity
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    public JobType() {
    }
}
