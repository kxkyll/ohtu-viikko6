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
import olutopas.domain.Rating;

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
        server.save(brewery);
    }

    @Override
    public Pub readPub(String name) {
        return server.find(Pub.class).where().like("name", name).findUnique();
    }

    @Override
    public void deleteBeer(Beer beer) {
        server.delete(beer);
    }

    @Override
    public void createPub(Pub pub) {
        server.save(pub);
    }

    @Override
    public List<Brewery> listBreweries() {
        return server.find(Brewery.class).findList();
    }

    @Override
    public void deleteBrewery(Brewery breweryToDelete) {
        server.delete(breweryToDelete);
    }

    @Override
    public void saveRating(Rating rating) {
        server.save(rating);
    }

    @Override
    public List<Rating> fingRating(String who) {
        return server.find(Rating.class).where().eq("user.name", who).findList();
    }

    @Override
    public List<Rating> getRatings(String name) {
        return server.find(Rating.class).where().eq("beer.name", name).findList();
    }
}
