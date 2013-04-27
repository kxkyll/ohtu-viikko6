/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.database;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
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

    public Brewery readBrewery(String name) {
        return server.find(Brewery.class).where().like("name", name).findUnique();
    }

    public List<Beer> listBeers() {
        return server.find(Beer.class).orderBy("brewery").findList();
    }

    public void createBrewery(Brewery brewery) {
        server.save(new Brewery(brewery.getName()));
    }

    public Pub readPub(String name) {
        return server.find(Pub.class).where().like("name", name).findUnique();
    }

    public void deleteBeer(String beerName) {
        server.delete(beerName);
    }

    public void createPub(Pub pub) {
        server.save(pub);
    }
    
    
}
