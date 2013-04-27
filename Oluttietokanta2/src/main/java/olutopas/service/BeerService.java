/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.service;

import java.util.List;
import olutopas.database.BeerRepository;
import olutopas.domain.Beer;
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

    public Beer readBeer(String name) {
        return beerRepository.readBeer(name);
    }
     
    
    
}
