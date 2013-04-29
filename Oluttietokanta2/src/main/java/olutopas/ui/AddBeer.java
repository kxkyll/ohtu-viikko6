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
public class AddBeer implements Komento {

    private BeerService beerService;
    private Scanner scanner;

    public AddBeer(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

    @Override
    public void suorita() {
        addBeer();
    }

    private void addBeer() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = beerService.readBrewery(name);// server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = beerService.readBeer(name);//server.find(Beer.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        beerService.createBrewery(brewery); // server.save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }
}
