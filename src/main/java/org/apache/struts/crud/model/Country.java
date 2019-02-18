package org.apache.struts.crud.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;

/**
 * Model a country.
 * 
 * @author bruce phillips
 * @author antonio sánchez
 */
@Entity
public class Country implements Serializable {
    @Id
    private String countryId;
    private String countryName;
    @OneToMany(mappedBy = "country")
    private Collection<Person> persons;

    public Country(String countryId, String countryName) {
        setCountryId(countryId);
        setCountryName(countryName);
    }

    public Country() {}

    public void setCountryId(String countryId) {
        if (countryId == null)
            throw new IllegalArgumentException("Country ID must be non-null.");
        
        this.countryId = countryId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString() {
        return getCountryId();
    }
    
    @Override
    public boolean equals(Object o) {
        return (o!=null) 
                && (o instanceof Country) 
                && (((Country) o).getCountryId() != null) 
                && (countryId.equals(((Country) o).getCountryId()));
    }
}
