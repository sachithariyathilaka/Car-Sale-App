package com.carSale.Controller;

import com.carSale.Config.JwtRequest;
import com.carSale.Config.JwtResponse;
import com.carSale.Dto.UserReturn;
import com.carSale.Model.Car;
import com.carSale.Model.User;
import com.carSale.Service.Impl.UserServiceImpl;
import com.carSale.Utill.JwtTokenUtil;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin
public class UserController {

    private static final Logger logger= Logger.getLogger(String.valueOf(Application.class));

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest credentials) throws Exception {
        logger.info(getClass().toString() + " << User Login Controller >>");
            boolean auth = userServiceImpl.userLogin(credentials.getUsername(), credentials.getPassword());
            if(auth){
                final UserDetails userDetails = userServiceImpl.loadUserByUsername(credentials.getUsername());
                final User user = userServiceImpl.getUserByName(credentials.getUsername());
                final String token = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new UserReturn(token, user.getId()));
           } else{
                logger.info(getClass().toString()+" << User Credentials Invalid >>");
                return ResponseEntity.ok(new JwtResponse("Invalid"));
            }

    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        logger.info(getClass().toString()+" << User Register Controller >>");
        return ResponseEntity.ok(userServiceImpl.userRegister(user));
    }

    @RequestMapping(value = "/userProfile", method = RequestMethod.POST)
    public ResponseEntity<?> userProfile(@RequestBody String id) throws Exception {
        logger.info(getClass().toString()+" << Get User Profile Controller >>");
        return ResponseEntity.ok(userServiceImpl.getUserById(Integer.parseInt(id)));
    }

    @RequestMapping(value = "/registerCar", method = RequestMethod.POST)
    public ResponseEntity<?> registerCar(@RequestBody Car car) throws Exception {
        logger.info(getClass().toString()+" << Register Car Controller >>");
        return ResponseEntity.ok(userServiceImpl.saveCar(car));
    }

    @RequestMapping(value = "/findCar", method = RequestMethod.POST)
    public ResponseEntity<?> findCar(@RequestBody String name) throws Exception {
        logger.info(getClass().toString()+" << Find Car Controller >>");
        return ResponseEntity.ok(userServiceImpl.findCar(name));
    }

    @RequestMapping(value = "/getCarList", method = RequestMethod.GET)
    public ResponseEntity<?> getCarList() throws Exception {
        logger.info(getClass().toString()+" << Get All Car Controller >>");
        return ResponseEntity.ok(userServiceImpl.getAllCarList());
    }
}
