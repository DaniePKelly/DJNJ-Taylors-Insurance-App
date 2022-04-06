package com.example.taylor_insurance;

/**
 * Interface for Insurance Rates Home and Auto
 *
 * @author DanKe
 */
public interface InsuranceRates {
    /**
     * Tax rate
     * @return double
     */
    public double tax();
    //Home Rates

    /**
     * Home Bas Premium
     * @return double
     */
    public double getBasePremiumHome();

    /**
     * Home value Factor
     * @param homeValue double
     * @return double
     */
    public double getHomeValueFactor(double homeValue);

    /**
     * Heating type factor
     * @param heatingType heatingType
     * @return double
     */
    public double getHeatingTypeFactor(Home.HeatingType heatingType);

    /**
     * Location Factor
     * @param location area
     * @return double
     */
    public double getLocationFactor(Home.Location location);

    /**
     * Home age Factor
     * @param yearsOld int
     * @return double
     */
    public double homeAgeFactor(int yearsOld);

    //Auto Rates

    /**
     * Base Premium Auto
     * @return double
     */
    public double getBasePremiumAuto();

    /**
     * Driver Age Factor
     * @param driverAge int
     * @return double
     */
    public double getDriverAgeFactor(int driverAge);

    /**
     * Number of Accident Factor
     * @param accidents int
     * @return double
     */
    public double getNumberOfAccidentsFactor(int accidents);

    /**
     * Vehicle Age Factor
     * @param vehicleAge int
     * @return double
     */
    public double vehicleAgeFactor(int vehicleAge);

}
