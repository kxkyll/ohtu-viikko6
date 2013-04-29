/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.List;
import java.util.Scanner;
import olutopas.domain.Rating;
import olutopas.domain.User;
import olutopas.service.BeerService;

/**
 *
 * @author Kati
 */
public class ListMyRatings implements Komento{

    private BeerService beerService;
    private Scanner scanner;
    private User userLoggedIn;

    public ListMyRatings(BeerService beerService, Scanner scanner, User userLoggedIn) {
        this.beerService = beerService;
        this.scanner = scanner;
        this.userLoggedIn = userLoggedIn;
    }
    
        
    @Override
    public void suorita() {
     listMyRatings();
    }
    
    private void listMyRatings() {
        String who = userLoggedIn.getName();

        List<Rating> ratings;
        ratings = beerService.fingRating(who); //server.find(Rating.class).where().eq("user.name", who).findList();
        for (Rating rating : ratings) {
            System.out.println(rating);
        }
    }
    
}
