package hu.unideb.inf.lev.carservice.service.impl;

import hu.unideb.inf.lev.carservice.dao.CarDAO;
import hu.unideb.inf.lev.carservice.dao.JobTypeDAO;
import hu.unideb.inf.lev.carservice.dao.PersonDAO;
import hu.unideb.inf.lev.carservice.dao.impl.CarDAOImpl;
import hu.unideb.inf.lev.carservice.dao.impl.PersonDAOImpl;
import hu.unideb.inf.lev.carservice.model.*;
import hu.unideb.inf.lev.carservice.service.exception.EmptyFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.InvalidFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import hu.unideb.inf.lev.carservice.utility.EntityManagerFactoryHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorksheetServiceTest {
    private WorksheetServiceImpl worksheetService;
    private PersonDAO personDAO;
    private CarDAO carDAO;
    private JobTypeDAO jobTypeDAO;


    @Before
    public void setUp() {
        worksheetService = new WorksheetServiceImpl();

        personDAO = Mockito.mock(PersonDAOImpl.class);
        carDAO = Mockito.mock(CarDAOImpl.class);
        jobTypeDAO = Mockito.mock(JobTypeDAO.class);

        Person person1 = new Person(
                "Elek",
                "Teszt",
                "+36301234567",
                new Address(
                        "Magyarország",
                        4025,
                        "Debrecen",
                        "Piac utca 49-51."
                )
        );

        Person person2 = new Person(
                "Béla",
                "Teszt",
                "06301234567",
                new Address(
                        "Magyarország",
                        4031,
                        "Debrecen",
                        "Kishegyesi út 156."
                ),
                new Discount(30, LocalDateTime.now().plusDays(1))
        );

        Person person3 = new Person(
                "Gábor",
                "Teszt",
                "06301234567",
                new Address(
                        "Magyarország",
                        4031,
                        "Debrecen",
                        "Kishegyesi út 156."
                ),
                new Discount(40, LocalDateTime.now().minusDays(1))
        );

        JobType jobType1 = new JobType("Olajcsere", (long) 7000);
        JobType jobType2 = new JobType("Kerékcsere", (long) 4000);

        Car car1 = new Car("KKK-155", "Opel", "Astra G", "FA3123KJL31232", person1);
        Car car2 = new Car("LLK-999", "BMW", "320i", "KL323156KK53716", person2);
        Car car3 = new Car("AAA-001", "Ford", "Fiesta", "LLKAEV", person3);

        List<JobType> jobs = new ArrayList<JobType>();
        jobs.add(jobType1);
        jobs.add(jobType2);

        //Worksheet worksheet1 = new Worksheet(car1, jobs1, (long) 11000, 0);
        //Worksheet worksheet2 = new Worksheet(car2, jobs2, (long) 4900, 30);
        //Worksheet worksheet3 = new Worksheet(car3, jobs2, (long));

        Mockito.when(personDAO.findById((long) 1)).thenReturn(person1);
        Mockito.when(personDAO.findById((long) 2)).thenReturn(person2);
        Mockito.when(personDAO.findById((long) 3)).thenReturn(person3);

        Mockito.when(carDAO.findById((long) 1)).thenReturn(car1);
        Mockito.when(carDAO.findById((long) 2)).thenReturn(car2);
        Mockito.when(carDAO.findById((long) 3)).thenReturn(car3);

        Mockito.when(jobTypeDAO.findById((long) 1)).thenReturn(jobType1);
        Mockito.when(jobTypeDAO.findById((long) 2)).thenReturn(jobType2);
        Mockito.when(jobTypeDAO.getAll()).thenReturn(jobs);
    }

    @After
    public void tearDown() {
        EntityManagerFactoryHelper.close();
    }

    @Test(expected = EmptyFieldValueException.class)
    public void failedValidationWithoutCar() throws ValidationException {
        worksheetService.validateWorksheet(new Worksheet(null, jobTypeDAO.getAll(), (long) 0, 0));
    }

    @Test(expected = InvalidFieldValueException.class)
    public void failedValidationWithWrongDiscount() throws ValidationException {
        worksheetService.validateWorksheet(new Worksheet(carDAO.findById((long) 1), jobTypeDAO.getAll(), (long) 0, -10));
    }

    @Test
    public void testSuccessValidation() throws ValidationException {
        worksheetService.validateWorksheet(new Worksheet(carDAO.findById((long) 1), jobTypeDAO.getAll(), (long) 0, 0));
    }

    @Test
    public void checkJobSum() {
        Assert.assertEquals(
                11000,
                worksheetService.calculateJobSum(new Worksheet(
                        carDAO.findById((long) 1),
                        jobTypeDAO.getAll(),
                        (long) 0,
                        0
                ))
        );
    }

    @Test
    public void checkTotalCostWithoutDiscount() {
        Assert.assertEquals(
                11000,
                worksheetService.calculateTotal(new Worksheet(
                        carDAO.findById((long) 1),
                        jobTypeDAO.getAll(),
                        (long) 0,
                        0
                ))
        );
    }

    @Test
    public void checkTotalCostWithDiscount() {
        Car car = carDAO.findById((long) 2);

        Assert.assertEquals(
                7700,
                worksheetService.calculateTotal(new Worksheet(
                        car,
                        jobTypeDAO.getAll(),
                        (long) 0,
                        car.getOwner().getDiscount().getValue()
                ))
        );
    }
}
