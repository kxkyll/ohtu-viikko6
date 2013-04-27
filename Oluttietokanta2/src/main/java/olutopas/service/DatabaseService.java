/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.service;

import com.avaje.ebean.EbeanServer;
import javax.persistence.OptimisticLockException;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Pub;
import olutopas.domain.User;

/**
 *
 * @author kxkyllon
 */
public class DatabaseService {

    private EbeanServer server;

    public DatabaseService(EbeanServer server) {
        this.server = server;
    }

    public void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);

        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);

        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());
        brewery = server.find(Brewery.class, brewery.getId());
        brewery.addBeer(b);
        server.save(brewery);

        server.save(new Brewery("Paulaner"));

        server.save(new Pub("Pikkulintu"));

        server.save(new User("mluukkai"));
    }
}
