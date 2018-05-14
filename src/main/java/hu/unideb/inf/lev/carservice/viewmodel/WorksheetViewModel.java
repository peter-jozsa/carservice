package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class WorksheetViewModel {
    private Long id;
    private ObjectProperty<CarViewModel> car = new SimpleObjectProperty<>();
    private List<JobTypeViewModel> jobs = new ArrayList<>();
    private LongProperty mileage = new SimpleLongProperty(0);
    private LongProperty total = new SimpleLongProperty(0);
    private IntegerProperty discount = new SimpleIntegerProperty(0);

    public WorksheetViewModel() {
    }

    public WorksheetViewModel(Long id, ObjectProperty<CarViewModel> car, List<JobTypeViewModel> jobs, LongProperty mileage, LongProperty total, IntegerProperty discount) {
        this.id = id;
        this.car = car;
        this.jobs = jobs;
        this.mileage = mileage;
        this.total = total;
        this.discount = discount;
    }

    public WorksheetViewModel(ObjectProperty<CarViewModel> car, List<JobTypeViewModel> jobs, LongProperty mileage, LongProperty total, IntegerProperty discount) {
        this.car = car;
        this.jobs = jobs;
        this.mileage = mileage;
        this.total = total;
        this.discount = discount;
    }

    public CarViewModel getCar() {
        return car.get();
    }

    public ObjectProperty<CarViewModel> carProperty() {
        return car;
    }

    public void setCar(CarViewModel car) {
        this.car.set(car);
    }

    public List<JobTypeViewModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobTypeViewModel> jobs) {
        this.jobs = jobs;
    }

    public long getMileage() {
        return mileage.get();
    }

    public LongProperty mileageProperty() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage.set(mileage);
    }

    public long getTotal() {
        return total.get();
    }

    public LongProperty totalProperty() {
        return total;
    }

    public StringExpression formattedTotalProperty() {
        return Bindings.concat(totalProperty(), " Ft");
    }

    public void setTotal(long total) {
        this.total.set(total);
    }

    public int getDiscount() {
        return discount.get();
    }

    public StringExpression formattedDiscountProperty() {
        return Bindings.concat(discountProperty(), "%");
    }

    public IntegerProperty discountProperty() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount.set(discount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
