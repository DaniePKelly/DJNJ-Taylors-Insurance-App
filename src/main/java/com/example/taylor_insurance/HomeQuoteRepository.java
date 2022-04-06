package com.example.taylor_insurance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface HomeQuoteRepository extends CrudRepository <HomeQuote, Integer> {

    //If we need specialized queries beyond those included in CrudRepository we can engage here or in the Repository interface

}
