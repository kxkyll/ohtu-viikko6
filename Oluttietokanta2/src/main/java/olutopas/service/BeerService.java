/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.service;

import java.util.List;
import olutopas.database.BeerRepository;
import olutopas.database.BeerRepositoryInterface;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Pub;
import olutopas.domain.Rating;

/**
 *
 * @author kxkyllon
 */
public class BeerService {
     private BeerRepositoryInterface beerRepository;

    public BeerService(BeerRepositoryInterface beerRepository) {
        this.beerRepository = beerRepository;
    }

    public List<Pub> listPubs() {
        return beerRepository.listPubs();
    }
    
    public List<Beer> listBeers() {
        return beerRepository.listBeers();
    }

    public Beer readBeer(String name) {
        return beerRepository.readBeer(name);
    }

    public Brewery readBrewery(String name) {
        return beerRepository.readBrewery(name);
    }

    public void createBrewery(Brewery brewery) {
        beerRepository.createBrewery(brewery);
    }

    public Pub readPub(String name) {
        return beerRepository.readPub(name);
    }

    public void deleteBeer(Beer beer) {
        beerRepository.deleteBeer(beer);
    }

    public void createPub(Pub pub) {
        beerRepository.createPub(pub);
    }

    public List<Brewery> listBreweries() {
       return beerRepository.listBreweries();
    }

    public void deleteBrewery(Brewery breweryToDelete) {
        beerRepository.deleteBrewery(breweryToDelete);
    }

    public void saveRating(Rating rating) {
        beerRepository.saveRating(rating);
    }

    public List<Rating> fingRating(String who) {
        return beerRepository.fingRating(who);
    }

    public List<Rating> getRatings(String name) {
        return beerRepository.getRatings(name);
    }
     
    
    
}
