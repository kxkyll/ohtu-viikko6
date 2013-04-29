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
public class ShowBeersInPub implements Komento{

    private BeerService beerService;
    private Scanner scanner;

    public ShowBeersInPub(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

         
    
    @Override
    public void suorita() {
        showBeersInPub();
    }
    
        private void showBeersInPub() {
        System.out.print("pub: ");
        String name = scanner.nextLine();
        Pub pub = beerService.readPub(name); 
        if (pub == null) {
            System.out.println("No such pub " + name);
            return;
        }
        if (pub.getBeers().isEmpty()) {
            System.out.println("No beers in pub " + name);
            return;
        }
        for (Beer beer : pub.getBeers()) {
            System.out.println(beer);
        }

    }
    
}
