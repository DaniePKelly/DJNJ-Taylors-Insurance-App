package com.example.taylor_insurance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "homequote")
public class HomeQuote extends Quote {

    @Id
    @GeneratedValue
    private Integer homeQuoteId;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate startDate;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate endDate;

    private BigDecimal premium;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference     //This will stop an infinite recurrsion in api get
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "home_homeId",nullable = false)
    @JsonBackReference     //This will stop an infinite recurrsion in api get
    private Home home;

    public Integer getHomeQuoteId() {
        return homeQuoteId;
    }

    public void setHomeQuoteId(Integer homeQuoteId) {
        this.homeQuoteId = homeQuoteId;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public BigDecimal getPremium() {
        return premium;
    }

    @Override
    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
