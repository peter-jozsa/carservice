package hu.unideb.inf.lev.carservice.service.impl;

import hu.unideb.inf.lev.carservice.service.CarService;
import hu.unideb.inf.lev.carservice.service.JobTypeService;
import hu.unideb.inf.lev.carservice.service.PersonService;
import hu.unideb.inf.lev.carservice.service.WorksheetService;

/**
 * A factory class which constructs new service instances.
 */
public class ServiceFactory {
    /**
     * Instantiates a new car service.
     * @return A car service instance.
     */
    public static CarService createCarService() {
        return new CarServiceImpl();
    }

    /**
     * Instantiates a new person service.
     * @return A person service instance.
     */
    public static PersonService createPersonService() {
        return new PersonServiceImpl();
    }

    /**
     * Instantiates a new job type service.
     * @return A job type service instance.
     */
    public static JobTypeService createJobTypeService() {
        return new JobTypeServiceImpl();
    }

    /**
     * Instantiates a new worksheet service.
     * @return A worksheet service instance.
     */
    public static WorksheetService createWorksheetService() {
        return new WorksheetServiceImpl();
    }
}
