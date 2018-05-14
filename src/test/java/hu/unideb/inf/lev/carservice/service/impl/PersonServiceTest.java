package hu.unideb.inf.lev.carservice.service.impl;

import hu.unideb.inf.lev.carservice.dao.PersonDAO;
import hu.unideb.inf.lev.carservice.model.Address;
import hu.unideb.inf.lev.carservice.model.Discount;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.exception.EmptyFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;
import hu.unideb.inf.lev.carservice.utility.EntityManagerFactoryHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class PersonServiceTest {
    PersonServiceImpl personService;

    PersonDAO personDAO;

    @Before
    public void setUp() {
        personService = new PersonServiceImpl();

        personDAO = Mockito.mock(PersonDAO.class);

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

        Mockito.when(personDAO.findById((long) 1)).thenReturn(person1);
        Mockito.when(personDAO.findById((long) 2)).thenReturn(person2);
        Mockito.when(personDAO.findById((long) 3)).thenReturn(person3);
    }

    @Test(expected = EmptyFieldValueException.class)
    public void failValidationWithEmptyFirstName() throws ValidationException {
        personService.validatePerson(new Person(
                "",
                "Teszt",
                "06301234567",
                new Address(
                        "Magyarország",
                        4031,
                        "Debrecen",
                        "Kishegyesi út 156."
                ),
                new Discount(40, LocalDateTime.now().minusDays(1))
        ));
    }
    @Test(expected = EmptyFieldValueException.class)
    public void failValidationWithEmptyLastName() throws ValidationException {
        personService.validatePerson(new Person(
                "Gábor",
                "",
                "06301234567",
                new Address(
                        "Magyarország",
                        4031,
                        "Debrecen",
                        "Kishegyesi út 156."
                ),
                new Discount(40, LocalDateTime.now().minusDays(1))
        ));
    }

    @Test(expected = EmptyFieldValueException.class)
    public void failValidationWithEmptyAddress() throws ValidationException {
        personService.validatePerson(new Person(
                "Gábor",
                "Teszt",
                "06301234567",
                null,
                new Discount(40, LocalDateTime.now().minusDays(1))
        ));
    }

    @Test
    public void testIncreasingDiscountWithoutDiscount() {
        personService.increaseDiscountOfPerson(personDAO.findById((long) 1));

        Discount discount = personDAO.findById((long) 1).getDiscount();
        Assert.assertNotNull(discount);
        Assert.assertNotNull(discount.getValue());
        Assert.assertEquals(5, discount.getValue().intValue());
    }

    @Test
    public void testIncreasingDiscountNormal() {
        personService.increaseDiscountOfPerson(personDAO.findById((long) 2));

        Discount discount = personDAO.findById((long) 2).getDiscount();
        Assert.assertNotNull(discount);
        Assert.assertNotNull(discount.getValue());
        Assert.assertEquals(35, discount.getValue().intValue());
    }

    @Test
    public void testIncreasingDiscountMax() {
        Discount discount = personDAO.findById((long) 3).getDiscount();
        Assert.assertNotNull(discount);
        Assert.assertNotNull(discount.getValue());
        Assert.assertEquals(40, discount.getValue().intValue());

        personService.increaseDiscountOfPerson(personDAO.findById((long) 3));

        Assert.assertEquals(40, discount.getValue().intValue());
    }

    @Test
    public void testExpiredDiscount() {
        Person person = personDAO.findById((long) 3);

        Assert.assertNotNull(person.getDiscount());
        Assert.assertTrue(LocalDateTime.now().isAfter(person.getDiscount().getValidUntil()));

        personService.removeExpiredDiscount(person);

        Assert.assertNull(person.getDiscount());
    }

    @After
    public void tearDown() {
        EntityManagerFactoryHelper.close();
    }
}
