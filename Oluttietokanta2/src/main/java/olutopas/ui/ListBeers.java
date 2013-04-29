/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.List;
import java.util.Scanner;
import olutopas.domain.Beer;
import olutopas.service.BeerService;

/**
 *
 * @author Kati
 */
public class ListBeers implements Komento {

    private BeerService beerService;
    private Scanner scanner;

    public ListBeers(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

    @Override
    public void suorita() {
        listBeers();
    }

    private void listBeers() {
        List<Beer> beers = beerService.listBeers();

        if (beers.isEmpty() || beers == null) {
            System.out.println("no beers available");
        } else {
            for (Beer beer : beers) {
                System.out.println(beer);
            }
        }
    }
}
