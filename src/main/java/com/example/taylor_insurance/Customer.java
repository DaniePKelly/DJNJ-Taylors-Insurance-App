package com.example.taylor_insurance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * The core class for the Customer. All insured items, quotes and policies must be owned by a customer.
 *
 * //TODO implement the 1 to Many relationship for all other class
 *
 * @author Josh
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String firstName;

    private String lastName;

    //TODO make this unique
    private String email;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference               //Manages the reference to prevent infinite api reference
    private Set<Home> homes;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference               //Manages the reference to prevent infinite api reference
    private Set<Auto> autos;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference               //Manages the reference to prevent infinite api reference
    private Set<AutoQuote> autoQuotes;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference               //Manages the reference to prevent infinite api reference
    private Set<HomeQuote> homeQuotes;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference               //Manages the reference to prevent infinite api reference
    private Set<HomePolicy> homePolicies;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference               //Manages the reference to prevent infinite api reference
    private Set<AutoPolicy> autoPolicies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public void setHomes(Set<Home> homes) {
        this.homes = homes;
    }

    public Set<Auto> getAutos() {
        return autos;
    }

    public void setAutos(Set<Auto> autos) {
        this.autos = autos;
    }

    public Set<AutoQuote> getAutoQuotes() {
        return autoQuotes;
    }

    public void setAutoQuotes(Set<AutoQuote> autoQuotes) {
        this.autoQuotes = autoQuotes;
    }

    public Set<HomeQuote> getHomeQuotes() {
        return homeQuotes;
    }

    public void setHomeQuotes(Set<HomeQuote> homeQuotes) {
        this.homeQuotes = homeQuotes;
    }

    public Set<HomePolicy> getHomePolicies() {
        return homePolicies;
    }

    public void setHomePolicies(Set<HomePolicy> homePolicies) {
        this.homePolicies = homePolicies;
    }

    public Set<AutoPolicy> getAutoPolicies() {
        return autoPolicies;
    }

    public void setAutoPolicies(Set<AutoPolicy> autoPolicies) {
        this.autoPolicies = autoPolicies;
    }
}
