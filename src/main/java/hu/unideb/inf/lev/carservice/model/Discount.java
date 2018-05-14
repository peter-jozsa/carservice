package hu.unideb.inf.lev.carservice.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Access(AccessType.FIELD)
public class Discount {
    private Integer value = 0;
    private LocalDateTime validUntil;

    public Discount() {
    }

    public Discount(Integer value) {
        this.value = value;
    }

    public Discount(Integer value, LocalDateTime validUntil) {
        this.value = value;
        this.validUntil = validUntil;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
