/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.HashMap;
import java.util.Scanner;
import olutopas.domain.User;
import olutopas.service.BeerService;

/**
 *
 * @author Kati
 */
public class Komentotehdas {
    private HashMap<String, Komento> komennot;
    
    
    public Komentotehdas(BeerService beerService, Scanner scanner, User user) {
        komennot = new HashMap<String, Komento>();
        komennot.put("1", new FindBrewery(beerService, scanner));
        komennot.put("2", new FindBeer(beerService, scanner, user));

    }
 
    public Komento hae(String operaatio) {
        Komento komento = komennot.get(operaatio);
        if (komento == null) {
            komento = komennot.get("tuntematon");
        }
        return komento;
    }
    
}
