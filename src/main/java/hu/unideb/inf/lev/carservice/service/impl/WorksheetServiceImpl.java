package hu.unideb.inf.lev.carservice.service.impl;

import hu.unideb.inf.lev.carservice.dao.DAOFactory;
import hu.unideb.inf.lev.carservice.dao.WorksheetDAO;
import hu.unideb.inf.lev.carservice.model.Worksheet;
import hu.unideb.inf.lev.carservice.service.PersonService;
import hu.unideb.inf.lev.carservice.service.WorksheetService;
import hu.unideb.inf.lev.carservice.service.exception.EmptyFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.InvalidFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A service class implementation which manages {@link Worksheet} entities.
 */
public class WorksheetServiceImpl implements WorksheetService {
    private WorksheetDAO worksheetDAO = DAOFactory.createWorksheetDAO();

    protected PersonService personService = ServiceFactory.createPersonService();

    @Override
    public Worksheet getWorksheetById(Long id) {
        return worksheetDAO.findById(id);
    }

    @Override
    public void createWorksheet(Worksheet worksheet) throws ValidationException {
        if (validateWorksheet(worksheet)) {
            worksheet.setCreationDate(LocalDateTime.now());

            worksheetDAO.create(worksheet);
            try {
                personService.increaseDiscountOfPerson(worksheet.getCar().getOwner());
                personService.updatePerson(worksheet.getCar().getOwner());
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateWorksheet(Worksheet worksheet) throws ValidationException, EntityNotFoundException {
        if (validateWorksheet(worksheet)) {
            Worksheet entity = worksheetDAO.findById(worksheet.getId());
            if (entity == null) {
                throw new EntityNotFoundException("Worksheet entity (id=" + worksheet.getId() + ") does not exist!");
            }

            entity.setCar(worksheet.getCar());
            entity.setJobs(worksheet.getJobs());
            entity.setTotal(worksheet.getTotal());
            entity.setDiscount(worksheet.getDiscount());

            worksheetDAO.update(entity);
        }
    }

    @Override
    public void deleteWorksheetById(Long id) throws EntityNotFoundException {
        Worksheet entity = worksheetDAO.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException("Worksheet entity (id=" + id + ") does not exist!");
        }

        worksheetDAO.delete(entity);
    }

    @Override
    public boolean validateWorksheet(Worksheet worksheet) throws ValidationException {
        if (worksheet == null) {
            return false;
        }

        if (worksheet.getCar() == null) {
            throw new EmptyFieldValueException(Worksheet.class, "car");
        }

        if (worksheet.getDiscount() < 0 || worksheet.getDiscount() > DISCOUNT_MAX_VALUE) {
            throw new InvalidFieldValueException(Worksheet.class, "discount");
        }

        if (worksheet.getTotal() < 0) {
            throw new InvalidFieldValueException(Worksheet.class, "total");
        }

        return true;
    }

    @Override
    public List<Worksheet> getAllWorksheet() {
        return worksheetDAO.getAll();
    }

    @Override
    public long calculateJobSum(Worksheet worksheet) {
        return worksheet.getJobs()
                .stream()
                .mapToLong(jobType -> jobType.getPrice())
                .sum();
    }

    @Override
    public long calculateTotal(Worksheet worksheet) {
        return calculateJobSum(worksheet) * (100 - worksheet.getDiscount())/100;
    }
}
