/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.List;
import java.util.Scanner;
import olutopas.domain.Brewery;
import olutopas.service.BeerService;

/**
 *
 * @author kxkyllon
 */
public class ListBreweries implements Komento{
    private BeerService beerService;
    private Scanner scanner;

    public ListBreweries(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

    
    
    @Override
    public void suorita() {
     listBreweries();
    }
    
     private void listBreweries() {
        List<Brewery> breweries = beerService.listBreweries();//server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }
    
}
