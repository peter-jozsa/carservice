package hu.unideb.inf.lev.carservice.controller.context;

import hu.unideb.inf.lev.carservice.model.Person;

public class PersonFormViewControllerContext extends ViewControllerContext {
    private Person person;

    public PersonFormViewControllerContext(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
