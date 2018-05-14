package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

import java.time.LocalDateTime;

/**
 * View model which represents a {@link hu.unideb.inf.lev.carservice.model.Discount} entity.
 */
public class DiscountViewModel {
    private IntegerProperty value = new SimpleIntegerProperty(0);
    private LocalDateTime validUntil;

    /**
     * Parameter-less constructor.
     */
    public DiscountViewModel() {
    }

    /**
     * Initializes a new instance with the provided values.
     * @param value A percentage value(0-100)
     * @param validUntil Validity timestamp
     */
    public DiscountViewModel(IntegerProperty value, LocalDateTime validUntil) {
        this.value = value;
        this.validUntil = validUntil;
    }

    /**
     * Gets the percentage value of the discount.
     * @return A percentage value
     */
    public int getValue() {
        return value.get();
    }

    /**
     * Gets the observable percentage property.
     * @return An observable property
     */
    public IntegerProperty valueProperty() {
        return value;
    }

    /**
     * Gets the formatted observable percentage property(with a % mark at the end).
     * @return An observable string expression.
     */
    public StringExpression formattedValueProperty() {
        return Bindings.concat(valueProperty(), "%");
    }

    /**
     * Sets the percentage value of the discount.
     * @param value A percentage value (0-100)
     */
    public void setValue(int value) {
        this.value.set(value);
    }

    /**
     * Gets the validity timestamp.
     * @return A timestamp
     */
    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    /**
     * Sets the validity timestamp.
     * @param validUntil A timestamp
     */
    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
