package org.apache.struts.crud.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.crud.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * In memory data repository for Person objects.
 * 
 * @author bruce phillips
 * @author antonio sanchez
 */
@Repository
public class MemoryPersonDao implements PersonDao, ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOG = LogManager.getLogger(MemoryPersonDao.class.getName());

    private final static List<Person> persons;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    PersonSupportDao personSupportDao;

    static {
        persons = new ArrayList<>();
    }

    @Override
    public Person getPerson(Integer id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public Person[] getAllPersons() {
        List<Person> persons = entityManager.createQuery("from Person").getResultList();
        Person[] personArr = new Person[persons.size()];
        return persons.toArray(personArr);
    }

    @Transactional
    @Override
    public void updatePerson(Person person) {
        entityManager.merge(person);
    }

    @Transactional
    @Override
    public void insertPerson(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    @Override
    public void deletePerson(Integer id) {
        Person personToRemove = entityManager.find(Person.class, id);
        if(personToRemove != null) {
            entityManager.remove(personToRemove);
        } else {
            LOG.error("Person to remove couldn't be found.");
        }
    }

    @Transactional
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Person person1 = new Person("Bruce", "Phillips", "basketball",
                "male", personSupportDao.getCountry("US"),
                true, new String[]{"Ford", "Nissan"}, "bphillips@ku.edu", "123-456-9999");
        Person person2 = new Person("Antonio", "Sanchez", "mtb",
                "male", personSupportDao.getCountry("ES"),
                true, new String[]{"Toyota", "Seat"}, "asanchez@correo-e.es", "555-999-8888");
        insertPerson(person1);
        insertPerson(person2);
    }
}
