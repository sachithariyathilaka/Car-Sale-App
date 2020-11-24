package com.carSale.Service;
import com.carSale.Model.User;


public interface UserService {

    User userRegister(User user);

    boolean userLogin(String Username, String Password) throws Exception;

    User getUserByName(String username);

    User getUserById(int id);

}
