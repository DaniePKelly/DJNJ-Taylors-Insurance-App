package com.example.taylor_insurance;

public class HardCodedRates implements InsuranceRates{
    @Override
    public double tax() {
        return 1.15;
    }

    @Override
    public double getBasePremiumHome() {
        return 500;
    }

    @Override
    public double getHomeValueFactor(double homeValue) {
        if(homeValue >= 250000){
            return 1.2;
        }else{
            return 1.0;
        }
    }

    @Override
    public double getHeatingTypeFactor(Home.HeatingType heatingType) {
        switch (heatingType){
            case OIL_HEATING -> {
                return 2.0;
            }
            case WOOD_HEATING -> {
                return 1.25;
            }
            case OTHER_HEATING -> {
                return 1.0;
            }
        }
        return 1.0;
    }


    @Override
    public double getLocationFactor(Home.Location location) {
        switch (location){
            case URBAN  -> {
                return 1.0;
            }
            case RURAL -> {
                return 1.15;
            }
        }
        return 1.0;
    }


    @Override
    public double homeAgeFactor(int yearsOld) {
        if (yearsOld >= 50){
            return 1.5;
        }else if (yearsOld > 25){
            return 1.0;
        }else{
            return 1.0;
        }
    }

    @Override
    public double getBasePremiumAuto() {
        return 750;
    }

    @Override
    public double getDriverAgeFactor(int driverAge) {
        if(driverAge < 25){
            return 2.0;
        }else{
            return 1.0;
        }
    }

    @Override
    public double getNumberOfAccidentsFactor(int accidents) {
        if(accidents >= 2){
            return 2.5;
        }else if (accidents >= 1){
            return 1.25;
        }else{
            return 1.0;
        }
    }

    @Override
    public double vehicleAgeFactor(int vehicleAge) {
        if (vehicleAge >= 10){
            return 2.0;
        }else if (vehicleAge >=5){
            return 1.5;
        }else{
            return 1.0;
        }
    }
}
