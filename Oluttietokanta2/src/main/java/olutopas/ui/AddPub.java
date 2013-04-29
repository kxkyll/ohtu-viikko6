/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.Scanner;
import olutopas.domain.Pub;
import olutopas.service.BeerService;

/**
 *
 * @author Kati
 */
public class AddPub implements Komento {

    private BeerService beerService;
    private Scanner scanner;

    public AddPub(BeerService beerService, Scanner scanner) {
        this.beerService = beerService;
        this.scanner = scanner;
    }

    @Override
    public void suorita() {
        addPub();
    }

    private void addPub() {
        System.out.print("pub to add: ");

        String name = scanner.nextLine();

        Pub exists = beerService.readPub(name);
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        beerService.createPub(new Pub(name));
    }
}
