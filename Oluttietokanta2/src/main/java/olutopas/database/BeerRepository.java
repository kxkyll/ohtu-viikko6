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
public class BeerRepository implements BeerRepositoryInterface {

    private EbeanServer server;

    public BeerRepository(EbeanServer server) {
        this.server = server;
    }

    @Override
    public List<Pub> listPubs() {
        return server.find(Pub.class).findList();
    }

    @Override
    public Beer readBeer(String name) {
        return server.find(Beer.class).where().like("name", name).findUnique();
    }

    @Override
    public Brewery readBrewery(String name) {
        return server.find(Brewery.class).where().like("name", name).findUnique();
    }

    @Override
    public List<Beer> listBeers() {
        return server.find(Beer.class).orderBy("brewery").findList();
    }

    @Override
    public void createBrewery(Brewery brewery) {
        server.save(new Brewery(brewery.getName()));
    }

    @Override
    public Pub readPub(String name) {
        return server.find(Pub.class).where().like("name", name).findUnique();
    }

    @Override
    public void deleteBeer(String beerName) {
        server.delete(beerName);
    }

    @Override
    public void createPub(Pub pub) {
        server.save(pub);
    }

    @Override
    public List<Brewery> listBreweries() {
        return server.find(Brewery.class).findList();
    }
}
