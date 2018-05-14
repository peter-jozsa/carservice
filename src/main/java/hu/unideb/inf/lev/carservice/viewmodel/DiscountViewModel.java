package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

import java.time.LocalDateTime;

public class DiscountViewModel {
    private IntegerProperty value = new SimpleIntegerProperty(0);
    private LocalDateTime validUntil;

    public DiscountViewModel() {
    }

    public DiscountViewModel(IntegerProperty value, LocalDateTime validUntil) {
        this.value = value;
        this.validUntil = validUntil;
    }

    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public StringExpression formattedValueProperty() {
        return Bindings.concat(valueProperty(), "%");
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
