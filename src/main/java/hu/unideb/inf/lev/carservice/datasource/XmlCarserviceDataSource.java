package hu.unideb.inf.lev.carservice.datasource;

import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.model.Worksheet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * A datasource class for parsing XML files.
 */
@XmlRootElement(name = "service")
public class XmlCarserviceDataSource implements CarserviceDataSource {
    private List<Car> cars;
    private List<Person> persons;
    private List<JobType> jobTypes;
    private List<Worksheet> worksheets;

    @XmlElementWrapper(name = "cars")
    @XmlElement(name = "car")
    @Override
    public List<Car> getCars() {
        return cars;
    }

    @XmlElementWrapper(name = "persons")
    @XmlElement(name = "person")
    @Override
    public List<Person> getPersons() {
        return persons;
    }

    @XmlElementWrapper(name = "jobTypes")
    @XmlElement(name = "jobType")
    @Override
    public List<JobType> getJobTypes() {
        return jobTypes;
    }

    @XmlElementWrapper(name = "worksheets")
    @XmlElement(name = "worksheet")
    @Override
    public List<Worksheet> getWorksheets() {
        return worksheets;
    }

    /**
     * Sets the list of car entities.
     * @param cars A list of car entities
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * Sets the list of person entities.
     * @param persons A list of person entities
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * Sets the list of job type entities.
     * @param jobTypes A list of job type entities.
     */
    public void setJobTypes(List<JobType> jobTypes) {
        this.jobTypes = jobTypes;
    }

    /**
     * Sets the list of worksheet entities.
     * @param worksheets A list of worksheet entities.
     */
    public void setWorksheets(List<Worksheet> worksheets) {
        this.worksheets = worksheets;
    }
}
