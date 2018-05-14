package hu.unideb.inf.lev.carservice.utility.converter;

import hu.unideb.inf.lev.carservice.model.Worksheet;
import hu.unideb.inf.lev.carservice.viewmodel.WorksheetViewModel;

/**
 * A converter class which manages the conversions between {@link Worksheet} entities
 * and {@link WorksheetViewModel} view models.
 */
public interface WorksheetConverter extends GenericConverter<Worksheet, WorksheetViewModel> {
}
