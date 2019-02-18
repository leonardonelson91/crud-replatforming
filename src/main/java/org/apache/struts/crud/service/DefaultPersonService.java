package org.apache.struts.crud.service;

import org.apache.struts.crud.dao.PersonDao;
import org.apache.struts.crud.dao.PersonSupportDao;
import org.apache.struts.crud.model.Country;
import org.apache.struts.crud.model.Person;
import org.springframework.stereotype.Service;

/**
 * Implement Services needed to edit and save
 * a Person object's state.  
 * 
 * @author bruce phillips
 * @author antonio sanchez
 */

@Service
public class DefaultPersonService implements PersonService {
    private PersonDao personDao;
    private PersonSupportDao personSupportDao;

    public DefaultPersonService(PersonDao personDao, PersonSupportDao personSupportDao) {
        this.personDao = personDao;
        this.personSupportDao = personSupportDao;
    }

    @Override
    public Person getPerson(Integer id) {
        return personDao.getPerson(id);
    }

    @Override
    public Person[] getAllPersons() {
        return personDao.getAllPersons();
    }

    @Override
    public void updatePerson(Person personBean) {
        personDao.updatePerson(personBean);
    }

    @Override
    public void insertPerson(Person personBean) {
        personDao.insertPerson(personBean);
    }

    @Override
    public void deletePerson(Integer id) {
        personDao.deletePerson(id);
    }

    @Override
    public Country[] getCountries() {
        return personSupportDao.getCountries();
    }

    @Override
    public String[] getCarModels() {
        return personSupportDao.getCarModels();
    }

    @Override
    public String[] getSports() {
        return personSupportDao.getSports();
    }

    @Override
    public String[] getGenders() {
        return personSupportDao.getGenders();
    }
}
