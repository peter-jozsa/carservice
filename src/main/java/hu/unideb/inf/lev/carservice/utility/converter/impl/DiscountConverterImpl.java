package hu.unideb.inf.lev.carservice.utility.converter.impl;

import hu.unideb.inf.lev.carservice.model.Discount;
import hu.unideb.inf.lev.carservice.utility.converter.DiscountConverter;
import hu.unideb.inf.lev.carservice.viewmodel.DiscountViewModel;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A converter which manages the conversions between {@link Discount} entities
 * and {@link DiscountViewModel} view models.
 */
public class DiscountConverterImpl implements DiscountConverter {
    @Override
    public DiscountViewModel fromModel(Discount model) {
        if (model == null) {
            return null;
        }

        return new DiscountViewModel(
                new SimpleIntegerProperty(model.getValue()),
                model.getValidUntil()
        );
    }

    @Override
    public Discount toModel(DiscountViewModel viewModel) {
        if (viewModel == null) {
            return null;
        }

        return new Discount(
                viewModel.getValue(),
                viewModel.getValidUntil()
        );
    }
}
