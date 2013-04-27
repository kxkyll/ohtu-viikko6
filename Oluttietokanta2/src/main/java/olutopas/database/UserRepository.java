/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.database;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.domain.User;

/**
 *
 * @author kxkyllon
 */
public class UserRepository implements UserRepositoryInterface {
    
    private EbeanServer server;

    public UserRepository(EbeanServer server) {
        this.server = server;
    }

    @Override
    public User readUser(String name) {
        return server.find(User.class).where().like("name", name).findUnique();
        
    }

    @Override
    public List<User> listUsers() {
        return server.find(User.class).orderBy("name").findList();
    }

    @Override
    public void createUser(User user) {
        server.save(user);
    }
    
}
