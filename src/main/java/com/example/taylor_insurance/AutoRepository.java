package com.example.taylor_insurance;

import org.springframework.data.repository.CrudRepository;

public interface AutoRepository extends CrudRepository <Auto, Integer> {

    //No queries beyond CrudRepository Needed most likely

}
