package com.example.taylor_insurance;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public abstract class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Integer policyID;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate startDate;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate endDate;

    private boolean status = true;

    public Integer getPolicyID() {
        return policyID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isActive(){
        if(this.endDate == LocalDate.now()){
            return false;
        }else{
            return true;
        }
    }

    public abstract double premium();
}
