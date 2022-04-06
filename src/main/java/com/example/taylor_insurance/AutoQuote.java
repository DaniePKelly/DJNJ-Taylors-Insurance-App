package com.example.taylor_insurance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity(name = "autoquote")
public class AutoQuote extends Quote {

    @Id
    @GeneratedValue
    private Integer autoQuoteId;

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

    public Integer getAutoQuoteId() {
        return autoQuoteId;
    }

    public void setAutoQuoteId(Integer autoQuoteId) {
        this.autoQuoteId = autoQuoteId;
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

}
