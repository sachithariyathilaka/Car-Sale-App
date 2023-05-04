package com.carSale.Service;
import com.carSale.Model.Car;
import com.carSale.Model.User;

import java.util.ArrayList;


public interface UserService {

    User userRegister(User user);

    boolean userLogin(String Username, String Password) throws Exception;

    User getUserByName(String username);

    User getUserById(int id);

    Car saveCar(Car car);

    Car findCar(String Name);

    ArrayList<Car> getAllCarList();
}
