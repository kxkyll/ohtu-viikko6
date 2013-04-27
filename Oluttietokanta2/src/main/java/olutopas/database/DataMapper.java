/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.database;

import java.util.List;
import olutopas.domain.User;

/**
 *
 * @author Kati
 */
public interface DataMapper {
    
    void createUser(User user);

    List<User> listUsers();

    User readUser(String name);
    
    User getCurrentUser();
    
    void setCurrentUser(User user);
    
}
