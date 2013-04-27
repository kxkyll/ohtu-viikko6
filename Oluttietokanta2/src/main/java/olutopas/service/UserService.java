/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.service;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.database.UserRepositoryInterface;
import olutopas.domain.User;

/**
 *
 * @author kxkyllon
 */
public class UserService {

   
    private UserRepositoryInterface userRepository;
    
    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    public User readUser(String name) {
        return userRepository.readUser(name);
//        User exists = server.find(User.class).where().like("name", name).findUnique();
//        return exists;
    }

    public List<User> listUsers() {
        return userRepository.listUsers();
        //return server.find(User.class).orderBy("name").findList();
    }

    public void createUser(User user) {
        userRepository.createUser(user);
        //server.save(user);
    }
}
