/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.Scanner;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.service.BeerService;

/**
 *
 * @author kxkyllon
 */
public class FindBrewery implements Komento{
    private Scanner scanner;
    private BeerService beerService;

    public FindBrewery(BeerService beerService, Scanner scanner) {
        this.scanner = scanner;
        this.beerService = beerService;
    }
  
    

    @Override
    public void suorita() {
     findBrewery();   
    }
    
    private void findBrewery() {
        System.out.print("brewery to find: ");
        String name = scanner.nextLine();
 
        Brewery foundBrewery = beerService.readBrewery(name);//server.find(Brewery.class).where().like("name", n).findUnique();

        if (foundBrewery == null) {
            System.out.println(name + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }
    
}
