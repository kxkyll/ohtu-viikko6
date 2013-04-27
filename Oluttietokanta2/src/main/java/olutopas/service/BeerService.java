/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.service;

import java.util.List;
import olutopas.database.BeerRepository;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Pub;

/**
 *
 * @author kxkyllon
 */
public class BeerService {
     private BeerRepository beerRepository;

    public BeerService(BeerRepository beerRepository) {
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

    public void deleteBeer(String beerName) {
        beerRepository.deleteBeer(beerName);
    }

    public void createPub(Pub pub) {
        beerRepository.createPub(pub);
    }

    public List<Brewery> listBreweries() {
       return beerRepository.listBreweries();
    }
     
    
    
}
