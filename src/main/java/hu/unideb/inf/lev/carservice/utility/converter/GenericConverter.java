package hu.unideb.inf.lev.carservice.utility.converter;

public interface GenericConverter<M, VM> {
    VM fromModel(M model);
    M toModel(VM viewModel);
}
