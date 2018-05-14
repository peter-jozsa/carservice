package hu.unideb.inf.lev.carservice.datasource;

import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.model.Worksheet;
import hu.unideb.inf.lev.carservice.service.CarService;
import hu.unideb.inf.lev.carservice.service.JobTypeService;
import hu.unideb.inf.lev.carservice.service.PersonService;
import hu.unideb.inf.lev.carservice.service.WorksheetService;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import hu.unideb.inf.lev.carservice.service.impl.ServiceFactory;

import java.util.stream.Collectors;

/**
 * A class which can import a datasource into the database using the services.
 */
public class DataSourceImporter {
    /**
     * Imports the provided datasource into the database.
     * @param src A datasource
     * @throws ValidationException When an invalid entity is found.
     */
    public static void importDatasource(CarserviceDataSource src) throws ValidationException {
        CarService carService = ServiceFactory.createCarService();
        PersonService personService = ServiceFactory.createPersonService();
        JobTypeService jobTypeService = ServiceFactory.createJobTypeService();
        WorksheetService worksheetService = ServiceFactory.createWorksheetService();

        for (JobType jobType : src.getJobTypes()) {
            jobTypeService.createJobType(jobType);
        }

        for (Person person : src.getPersons()) {
            personService.createPerson(person);
        }

        System.out.println(personService.getAllPerson());

        for (Car car : src.getCars()) {
            car.setOwner(personService.getPersonsById(car.getOwner().getId()));
            carService.createCar(car);
        }

        for (Worksheet worksheet : src.getWorksheets()) {
            worksheet.setCar(carService.getCarById(worksheet.getCar().getId()));
            worksheet.setJobs(
                    worksheet.getJobs()
                            .stream()
                            .map(lazyJob -> jobTypeService.getJobTypeById(lazyJob.getId()))
                            .collect(Collectors.toList())
            );

            worksheetService.createWorksheet(worksheet);
        }
    }
}
