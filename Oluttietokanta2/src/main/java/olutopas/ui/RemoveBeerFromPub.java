/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.Scanner;
import olutopas.domain.Beer;
import olutopas.domain.Pub;
import olutopas.service.BeerService;

/**
 *
 * @author Kati
 */
public class RemoveBeerFromPub implements Komento {

    BeerService beerService;
    Scanner scanner;

    public RemoveBeerFromPub(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

    @Override
    public void suorita() {
        removeBeerFromPub();
    }

    private void removeBeerFromPub() {
        System.out.print("beer: ");
        String name = scanner.nextLine();
        Beer beer = beerService.readBeer(name);

        if (beer == null) {
            System.out.println("does not exist");
            return;
        }

        System.out.print("pub: ");
        name = scanner.nextLine();
        Pub pub = beerService.readPub(name); 

        if (pub == null) {
            System.out.println("does not exist");
            return;
        }

        if (pub.getBeers().contains(beer)) {
            pub.removeBeer(beer);
        }


        beerService.createPub(pub); 

        System.out.println("Beer " + beer.getName() + " removed from pub " + pub.getName());
    }

}
