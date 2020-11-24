package com.carSale.Service.Impl;

import com.carSale.Model.Car;
import com.carSale.Model.User;
import com.carSale.Repository.CarRepository;
import com.carSale.Repository.UserRepository;
import com.carSale.Service.UserService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger= Logger.getLogger(String.valueOf(Application.class));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.info(getClass().toString()+" << User not found with username: " + username + ">>");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    @Override
    public User userRegister(User user) {
        logger.info(getClass().toString() +" << User Register Done >>");
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        return userRepository.save(newUser);
    }


    @Override
    public boolean userLogin(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            logger.info(getClass().toString()+" << User Authenticated >>");
            return true;
        } catch (BadCredentialsException e) {
            logger.info(getClass().toString()+" << Bad Credentials >>");
            return false;
        }
    }

    @Override
    public User getUserByName(String username) {
        logger.info(getClass().toString() +" << Get User By Name >>");
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car findCar(String Name) {
        return carRepository.findByName(Name);
    }

    @Override
    public ArrayList<Car> getAllCarList() {
        return carRepository.getAll();
    }
}
