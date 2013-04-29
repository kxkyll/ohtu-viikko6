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
public class DeleteBrewery implements Komento {

    BeerService beerService;
    Scanner scanner;

    public DeleteBrewery(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

    @Override
    public void suorita() {
        deleteBrewery();
    }

    private void deleteBrewery() {
        System.out.print("brewery to delete: ");
        String name = scanner.nextLine();
        Brewery breweryToDelete = beerService.readBrewery(name);

        if (breweryToDelete == null) {
            System.out.println(name + " not found");
            return;
        }

        beerService.deleteBrewery(breweryToDelete);
        System.out.println("deleted: " + breweryToDelete);
    }
}
