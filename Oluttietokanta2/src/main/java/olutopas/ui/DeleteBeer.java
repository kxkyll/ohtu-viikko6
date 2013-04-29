/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.Scanner;
import olutopas.domain.Beer;
import olutopas.service.BeerService;

/**
 *
 * @author Kati
 */
public class DeleteBeer implements Komento {

    private BeerService beerService;
    private Scanner scanner;

    public DeleteBeer(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

    @Override
    public void suorita() {
        deleteBeer();
    }

    private void deleteBeer() {
        System.out.print("beer to delete: ");
        String beerName = scanner.nextLine();
        Beer beerToDelete = beerService.readBeer(beerName);
        if (beerToDelete == null) {
            System.out.println(beerName + " not found");
            return;
        }

        beerService.deleteBeer(beerToDelete);
        System.out.println("deleted: " + beerToDelete);

    }
}
