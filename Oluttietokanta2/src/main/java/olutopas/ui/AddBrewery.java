/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.Scanner;
import olutopas.domain.Brewery;
import olutopas.service.BeerService;

/**
 *
 * @author Kati
 */
public class AddBrewery implements Komento {

    private BeerService beerService;
    private Scanner scanner;

    public AddBrewery(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

    @Override
    public void suorita() {
        addBrewery();
    }

    private void addBrewery() {
        System.out.print("brewery to add: ");

        String name = scanner.nextLine();

        Brewery exists = beerService.readBrewery(name);//server.find(Brewery.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        beerService.createBrewery(new Brewery(name)); // server.save(new Brewery(name));
        System.out.println("Brewery " + name + " added");
    }
}
