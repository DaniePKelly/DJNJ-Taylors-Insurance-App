package com.example.taylor_insurance;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface HomeRepository extends CrudRepository <Home, Integer> {

    //No queries beyond CrudRepository Needed most likely

}
