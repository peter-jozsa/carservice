package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

/**
 * View model which represents a {@link hu.unideb.inf.lev.carservice.model.Worksheet} entity.
 */
public class WorksheetViewModel {
    private Long id;
    private ObjectProperty<CarViewModel> car = new SimpleObjectProperty<>();
    private List<JobTypeViewModel> jobs = new ArrayList<>();
    private LongProperty total = new SimpleLongProperty(0);
    private IntegerProperty discount = new SimpleIntegerProperty(0);

    /**
     * Parameter-less constructor.
     */
    public WorksheetViewModel() {
    }

    /**
     * Initializes a new instance with the provided values.
     * @param id Unique identifier of the worksheet.
     * @param car Observable car of the worksheet.
     * @param jobs List of job types.
     * @param total Total cost of servicing.
     * @param discount Discount percentage of the worksheet.
     */
    public WorksheetViewModel(Long id, ObjectProperty<CarViewModel> car, List<JobTypeViewModel> jobs, LongProperty total, IntegerProperty discount) {
        this.id = id;
        this.car = car;
        this.jobs = jobs;
        this.total = total;
        this.discount = discount;
    }

    /**
     * Gets the car of the worksheet.
     * @return A car view model
     */
    public CarViewModel getCar() {
        return car.get();
    }

    /**
     * Gets the observable car property of the worksheet.
     * @return An observable property
     */
    public ObjectProperty<CarViewModel> carProperty() {
        return car;
    }

    /**
     * Sets the car of the worksheet.
     * @param car A car view model
     */
    public void setCar(CarViewModel car) {
        this.car.set(car);
    }

    /**
     * Gets the list of jobs done during servicing.
     * @return A list of job type view models.
     */
    public List<JobTypeViewModel> getJobs() {
        return jobs;
    }

    /**
     * Sets the list of jobs done.
     * @param jobs A list of job types
     */
    public void setJobs(List<JobTypeViewModel> jobs) {
        this.jobs = jobs;
    }

    /**
     * Gets the total cost of servicing.
     * @return The total cost of the worksheet.
     */
    public long getTotal() {
        return total.get();
    }

    /**
     * Gets the observable total property of the worksheet.
     * @return An observable property
     */
    public LongProperty totalProperty() {
        return total;
    }

    /**
     * Gets the formatted observable total cost of the worksheet.
     * @return An observable string expression
     */
    public StringExpression formattedTotalProperty() {
        return Bindings.concat(totalProperty(), " Ft");
    }

    /**
     * Sets the total cost of the worksheet.
     * @param total The total cost of servicing
     */
    public void setTotal(long total) {
        this.total.set(total);
    }

    /**
     * Gets the discount percentage value of the worksheet.
     * @return A discount percentage value
     */
    public int getDiscount() {
        return discount.get();
    }

    /**
     * Gets the formatted observable discount value of the worksheet.
     * @return An observable string expression
     */
    public StringExpression formattedDiscountProperty() {
        return Bindings.concat(discountProperty(), "%");
    }

    /**
     * Gets the observable discount property of the worksheet.
     * @return An observable property.
     */
    public IntegerProperty discountProperty() {
        return discount;
    }

    /**
     * Sets the discount percentage value of the worksheet.
     * @param discount A percentage value
     */
    public void setDiscount(int discount) {
        this.discount.set(discount);
    }

    /**
     * Gets the unique identifier of the worksheet.
     * @return Unique identifier of the worksheet
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the worksheet.
     * @param id A unique identifier value
     */
    public void setId(Long id) {
        this.id = id;
    }
}
