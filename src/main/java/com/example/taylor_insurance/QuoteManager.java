package com.example.taylor_insurance;

import java.math.BigDecimal;
import java.time.LocalDate;

public class QuoteManager {

    public static AutoQuote getNewAutoQuote(Auto auto, LocalDate customerDOB ){
        AutoQuote  autoQuote = new AutoQuote();
        autoQuote.setStartDate(LocalDate.now());
        autoQuote.setEndDate(LocalDate.now().plusDays(30));
        autoQuote.setAutoYear(auto.getAutoYear());
        autoQuote.setAutoModel(auto.getModel());
        autoQuote.setAuto(auto);
        //TODO Add the get premium step + find out how to pass auto into the add new quote
        autoQuote.setPremium(getAutoQuotePremium(auto, customerDOB));
        return autoQuote;
    }

    public static HomeQuote getNewHomeQuote(Home home){
        HomeQuote homeQuote = new HomeQuote();
        homeQuote.setStartDate(LocalDate.now());
        homeQuote.setEndDate(LocalDate.now().plusDays(30));
        //TODO Add the get premium step + find out how to pass auto into the add new quote
        homeQuote.setPremium(getHomeQuotePremium(home));
        return homeQuote;
    }

    private static BigDecimal getAutoQuotePremium(Auto auto,LocalDate customerDOB){
        InsuranceRates insuranceRates = new HardCodedRates();

        BigDecimal autoPremium = new BigDecimal(insuranceRates.getBasePremiumAuto());
        autoPremium = autoPremium.multiply(BigDecimal.valueOf(insuranceRates.getDriverAgeFactor(LocalDate.now().getYear() - customerDOB.getYear())));
        autoPremium = autoPremium.multiply(BigDecimal.valueOf(insuranceRates.getNumberOfAccidentsFactor(auto.getNumberOfAccidents())));
        autoPremium = autoPremium.multiply(BigDecimal.valueOf(insuranceRates.vehicleAgeFactor(auto.getAutoYear())));
        autoPremium = autoPremium.multiply(BigDecimal.valueOf(insuranceRates.tax()));

        return autoPremium;
    }

    private static BigDecimal getHomeQuotePremium(Home home){

        InsuranceRates insuranceRates = new HardCodedRates();

        BigDecimal homePremium = new BigDecimal(insuranceRates.getBasePremiumHome());
        homePremium = homePremium.multiply(BigDecimal.valueOf(insuranceRates.getHeatingTypeFactor(home.getHeatingType())));
        homePremium = homePremium.multiply(BigDecimal.valueOf(insuranceRates.getLocationFactor(home.getLocation())));
        homePremium = homePremium.multiply(BigDecimal.valueOf(insuranceRates.homeAgeFactor(home.getAge())));
        homePremium = homePremium.multiply(BigDecimal.valueOf(insuranceRates.getHomeValueFactor(home.getValue())));
        homePremium = homePremium.multiply(BigDecimal.valueOf(insuranceRates.tax()));

        return homePremium;
    }
}
