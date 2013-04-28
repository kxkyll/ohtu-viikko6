/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.database;

import java.util.List;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Pub;

/**
 *
 * @author Kati
 */
public interface BeerRepositoryInterface {

    void createBrewery(Brewery brewery);

    void createPub(Pub pub);

    void deleteBeer(String beerName);

    List<Beer> listBeers();

    List<Brewery> listBreweries();

    List<Pub> listPubs();

    Beer readBeer(String name);

    Brewery readBrewery(String name);

    Pub readPub(String name);
    
}
