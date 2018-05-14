package hu.unideb.inf.lev.carservice.datasource;

import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.model.Worksheet;

import java.util.List;

/**
 * An interface which describes the look of a data source.
 */
public interface CarserviceDataSource {
    /**
     * Gets all cars coming from the datasource.
     * @return A list of car entities.
     */
    List<Car> getCars();

    /**
     * Gets all person entities from the datasource.
     * @return A list of person entities.
     */
    List<Person> getPersons();

    /**
     * Gets all worksheet entities from the datasource.
     * @return A list of worksheet entities.
     */
    List<Worksheet> getWorksheets();

    /**
     * Gets all job type entities from the datasource.
     * @return A list of job type entities.
     */
    List<JobType> getJobTypes();
}
