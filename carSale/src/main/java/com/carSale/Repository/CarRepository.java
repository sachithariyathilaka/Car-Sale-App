package com.carSale.Repository;

import com.carSale.Model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
    Car findByName(String Name);

}
