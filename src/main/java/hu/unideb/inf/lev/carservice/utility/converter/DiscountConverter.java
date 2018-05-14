package hu.unideb.inf.lev.carservice.utility.converter;

import hu.unideb.inf.lev.carservice.model.Discount;
import hu.unideb.inf.lev.carservice.viewmodel.DiscountViewModel;

/**
 * A converter which manages the conversions between {@link Discount} entities
 * and {@link DiscountViewModel} view models.
 */
public interface DiscountConverter extends GenericConverter<Discount, DiscountViewModel> {
}
