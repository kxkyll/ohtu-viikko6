/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.List;
import olutopas.domain.User;
import olutopas.service.UserService;

/**
 *
 * @author Kati
 */
public class ListUsers implements Komento {

    private UserService userService;

    public ListUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void suorita() {
        listUsers();
    }

    private void listUsers() {
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

}
