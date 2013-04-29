/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.List;
import java.util.Scanner;
import olutopas.domain.Pub;
import olutopas.service.BeerService;

/**
 *
 * @author Kati
 */
public class ListPubs implements Komento {

    private BeerService beerService;
    private Scanner scanner;

    public ListPubs(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

    
    @Override
    public void suorita() {
        listPubs();
    }

    private void listPubs() {
        List<Pub> pubs = beerService.listPubs();
        for (Pub pub : pubs) {
            System.out.println(pub);
        }
    }
}
