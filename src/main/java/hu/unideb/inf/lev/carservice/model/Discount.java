package hu.unideb.inf.lev.carservice.model;

import hu.unideb.inf.lev.carservice.utility.LocalDateTimeAdapter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

/**
 * An entity model which describes a discount.
 */
@Embeddable
@Access(AccessType.FIELD)
public class Discount {
    private Integer value = 0;
    private LocalDateTime validUntil;

    /**
     * Parameter-less constructor.
     */
    public Discount() {
    }

    /**
     * Initializes a new instance.
     * @param value The value of the discount in percent(0-100)
     */
    public Discount(Integer value) {
        this.value = value;
    }

    /**
     * Initializes a new instance.
     * @param value The value of the discount in percent(0-100).
     * @param validUntil Discount will be valid until this date.
     */
    public Discount(Integer value, LocalDateTime validUntil) {
        this.value = value;
        this.validUntil = validUntil;
    }

    /**
     * Gets the value of the discount.
     * @return The percent value of the discount(0-100)
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets the value of the discount
     * @param value The value of the discount in percent(0-100)
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Gets the date until this discount is valid.
     * @return The date of validity.
     */
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    /**
     * Sets the date until this discount is valid
     * @param validUntil The validity date
     */
    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
