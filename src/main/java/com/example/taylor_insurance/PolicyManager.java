package com.example.taylor_insurance;

import java.time.LocalDate;

public class PolicyManager {

    public static AutoPolicy getNewAutoPolicy(AutoQuote autoQuote){
        AutoPolicy autoPolicy = new AutoPolicy();

        autoPolicy.setStartDate(LocalDate.now());
        autoPolicy.setEndDate(LocalDate.now().plusYears(1));
        autoPolicy.setPremium(autoQuote.getPremium());
        autoPolicy.setAuto(autoQuote.getAuto());
        autoPolicy.setAutoQuote(autoQuote);
        autoPolicy.setAutoModel(autoQuote.getAutoModel());
        autoPolicy.setAutoYear(autoQuote.getAutoYear());
        return autoPolicy;
    }

    public static HomePolicy getNewHomePolicy(HomeQuote homeQuote){
        HomePolicy homePolicy = new HomePolicy();

        homePolicy.setStartDate(LocalDate.now());
        homePolicy.setEndDate(LocalDate.now().plusYears(1));
        homePolicy.setPremium(homeQuote.getPremium());
        homePolicy.setHome(homeQuote.getHome());
        homePolicy.setHomeQuote(homeQuote);
        return homePolicy;
    }



}
