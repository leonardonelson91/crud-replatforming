package org.apache.struts.crud.dao;

import org.apache.struts.crud.model.Country;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author bruce phillips
 * @author antonio sanchez
 */
@Repository
public class MemoryPersonSupportDao implements PersonSupportDao, ApplicationListener<ApplicationReadyEvent> {
    private static final String[] genders = {"male", "female"};
    private static final String[] sports = {"football", "baseball", "basketball", "mtb" };
    private static final String [] carModelsAvailable = {"Ford", "Chrysler", "Toyota", "Nissan", "Seat"};

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String[] getCarModels() {
        return carModelsAvailable;
    }

    @Override
    public String[] getSports() {
        return sports;
    }

    @Override
    public String[] getGenders() {
        return genders;
    }

    @Override
    public Country[] getCountries() {
        List<Country> countries = entityManager.createQuery("from Country").getResultList();
        Country[] countryArr = new Country[countries.size()];
        return countries.toArray(countryArr);
    }

    @Transactional
    @Override
    public Country getCountry(String countryId) {
        return entityManager.find(Country.class, countryId);
    }

    @Transactional
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        final Country[] countries = new Country[] {new Country("ES", "Spain"),
                new Country("IE", "Ireland"),
                new Country("IT", "Italy"),
                new Country("PL", "Poland"),
                new Country("US", "Usa") };

        for (Country c : countries) {
            entityManager.persist(c);
        }
    }
}
