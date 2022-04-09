package com.example.taylor_insurance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "homepolicy")
public class HomePolicy{

    @Id
    @GeneratedValue
    private Integer homePolicyId;

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

    @OneToOne
    @JoinColumn(name = "homequote_homeQuoteId")
    @JsonBackReference
    private HomeQuote homeQuote;


    public Integer getHomePolicyId() {
        return homePolicyId;
    }

    public void setHomePolicyId(Integer homePolicyId) {
        this.homePolicyId = homePolicyId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPremium() {
        return premium;
    }

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

    public HomeQuote getHomeQuote() {
        return homeQuote;
    }

    public void setHomeQuote(HomeQuote homeQuote) {
        this.homeQuote = homeQuote;
    }
}
