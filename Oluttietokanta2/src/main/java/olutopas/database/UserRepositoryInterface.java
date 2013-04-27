/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.database;

import java.util.List;
import olutopas.domain.User;

/**
 *
 * @author kxkyllon
 */
public interface UserRepositoryInterface {

    void createUser(User user);

    List<User> listUsers();

    User readUser(String name);
    
}
