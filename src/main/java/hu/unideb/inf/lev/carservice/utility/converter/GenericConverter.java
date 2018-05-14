package hu.unideb.inf.lev.carservice.utility.converter;

/**
 * A generic converter which manages the conversions between {@link M} entities and {@link VM} viewModels.
 * @param <M> An entity model
 * @param <VM> A view model
 */
public interface GenericConverter<M, VM> {
    /**
     * Converts an entity to a view model.
     * @param model An entity.
     * @return The view model representation of the passed entity.
     */
    VM fromModel(M model);

    /**
     * Converts a view model to an entity model.
     * @param viewModel A view model.
     * @return The entity model.
     */
    M toModel(VM viewModel);
}
