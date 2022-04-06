package com.example.taylor_insurance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "autopolicy")
public class AutoPolicy extends Policy{

    @Id
    @GeneratedValue
    private Integer autoPolicyId;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate startDate;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate endDate;

    private BigDecimal premium;
    private String autoModel;
    private int autoYear;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference     //This will stop an infinite recurrsion in api get
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "auto_autoId",nullable = false)
    @JsonBackReference     //This will stop an infinite recurrsion in api get
    private Auto auto;

    @OneToOne
    @JoinColumn(name = "autoquote_autoQuoteId")
    @JsonBackReference
    private AutoQuote autoQuote;


    public Integer getAutoPolicyId() {
        return autoPolicyId;
    }

    public void setAutoPolicyId(Integer autoPolicyId) {
        this.autoPolicyId = autoPolicyId;
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

    public String getAutoModel() {
        return autoModel;
    }

    public void setAutoModel(String autoModel) {
        this.autoModel = autoModel;
    }

    public int getAutoYear() {
        return autoYear;
    }

    public void setAutoYear(int autoYear) {
        this.autoYear = autoYear;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public AutoQuote getAutoQuote() {
        return autoQuote;
    }

    public void setAutoQuote(AutoQuote autoQuote) {
        this.autoQuote = autoQuote;
    }

    @Override
    public double premium() {
        return 0;
    }
}
