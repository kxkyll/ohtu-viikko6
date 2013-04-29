/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.HashMap;
import java.util.Scanner;
import olutopas.domain.User;
import olutopas.service.BeerService;
import olutopas.service.UserService;

/**
 *
 * @author Kati
 */
public class Komentotehdas {

    private HashMap<String, Komento> komennot;
    private BeerService beerService;
    private UserService userService;
    private Scanner scanner;
    private User user;

    public Komentotehdas(BeerService beerService, UserService userService, Scanner scanner) {
        komennot = new HashMap<String, Komento>();
        this.beerService = beerService;
        this.userService = userService;
        this.scanner = scanner;

    }

    /**
     *
     */
    public void lisaaKirjautuneenPalvelut(User user) {
        this.user = user;

        komennot.put("tuntematon", new Tuntematon(scanner));
        komennot.put("1", new FindBrewery(beerService, scanner));
        komennot.put("2", new FindBeer(beerService, scanner, user));
        komennot.put("3", new AddBeer(beerService, scanner));
        komennot.put("4", new ListBreweries(beerService, scanner));
        komennot.put("5", new DeleteBeer(beerService, scanner));
        komennot.put("6", new AddPub(beerService, scanner));
        komennot.put("7", new AddBeerToPub(beerService, scanner));
        komennot.put("8", new ShowBeersInPub(beerService, scanner));
        komennot.put("9", new ListPubs(beerService, scanner));
        komennot.put("10", new RemoveBeerFromPub(beerService, scanner));
        komennot.put("11", new ListBeers(beerService, scanner));
        komennot.put("12", new AddBrewery(beerService, scanner));
        komennot.put("13", new DeleteBrewery(beerService, scanner));
        komennot.put("14", new ListMyRatings(beerService, scanner, user));
        komennot.put("l", new ListUsers(userService));
    }

    public Komento hae(String operaatio) {
        Komento komento = komennot.get(operaatio);
        if (komento == null) {
            komento = komennot.get("tuntematon");
        }
        return komento;
    }
}
