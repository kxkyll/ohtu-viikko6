/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.database;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.domain.Beer;
import olutopas.domain.Pub;

/**
 *
 * @author kxkyllon
 */
public class BeerRepository {
    private EbeanServer server;

    public BeerRepository(EbeanServer server) {
        this.server = server;
    }

    public List<Pub> listPubs() {
         return server.find(Pub.class).findList();
    }

    public Beer readBeer(String name) {
        return server.find(Beer.class).where().like("name", name).findUnique();
    }
    
    
}
