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
public class AddBeerToPub implements Komento {

    private BeerService beerService;
    private Scanner scanner;

    public AddBeerToPub(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

    @Override
    public void suorita() {
        addBeerToPub();
    }

    private void addBeerToPub() {
        System.out.print("beer: ");
        String name = scanner.nextLine();
        Beer beer = beerService.readBeer(name);//server.find(Beer.class).where().like("name", name).findUnique();

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

        pub.addBeer(beer);
        beerService.createPub(pub);
    }
}
