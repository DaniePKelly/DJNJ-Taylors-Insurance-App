package com.example.taylor_insurance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Migrate this from a POJO to a DAO
 *
 */
@Entity(name = "home")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer homeId;

    private int value;              //Should use BidDecimal but who cares right now
    private String description;     //Description of the house / addresss

    @Enumerated(EnumType.ORDINAL)
    private HeatingType heatingType;

    @Enumerated(EnumType.ORDINAL)
    private Location location;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate dateBuilt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference              //This will stop an infinite recurrsion in api get
    private Customer customer;

    //Enum Definitions
    public enum HeatingType { OIL_HEATING, WOOD_HEATING, OTHER_HEATING }
    public enum Location { URBAN, RURAL }

    public Integer getHomeId() {
        return homeId;
    }

    public void setHomeId(Integer id) {
        this.homeId = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HeatingType getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(HeatingType heatingType) {
        this.heatingType = heatingType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDateBuilt() {
        return dateBuilt;
    }

    public void setDateBuilt(LocalDate yearBuilt) {
        this.dateBuilt = yearBuilt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getAge(){
        return LocalDate.now().getYear() - dateBuilt.getYear();
    }

}
