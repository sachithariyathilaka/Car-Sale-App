package com.carSale.Repository;

import com.carSale.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User save(User user);
    User findById(int id);

}
