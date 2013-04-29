/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.domain.Beer;
import olutopas.domain.Pub;
import olutopas.domain.Rating;
import olutopas.domain.User;
import olutopas.service.BeerService;

/**
 *
 * @author kxkyllon
 */
public class FindBeer implements Komento {

    private BeerService beerService;
    private Scanner scanner;
    private User userLoggedIn;

    public FindBeer(BeerService beerService, Scanner scanner, User userLoggedIn) {
        this.beerService = beerService;
        this.scanner = scanner;
        this.userLoggedIn = userLoggedIn;
    }

    private void findBeer() {
        System.out.print("beer to find: ");
        String beerName = scanner.nextLine();
        Beer foundBeer = beerService.readBeer(beerName); //server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(beerName + " not found");
            return;
        }

        System.out.println(foundBeer);


        if (foundBeer.getPubs() == null || foundBeer.getPubs().isEmpty()) {
            System.out.println("  not available currently in any pub!");

        } else {
            System.out.println("  available now in:");
            for (Pub pub : foundBeer.getPubs()) {
                System.out.println("   " + pub);
            }
        }
        getDoneRatings(foundBeer.getName());
        askRating(foundBeer);
    }

    private void getDoneRatings(String name) {
        List<Rating> ratings = beerService.getRatings(name); //server.find(Rating.class).where().eq("beer.name", name).findList();
        double keskiarvo = 0;
        for (Rating rating : ratings) {
            System.out.println(rating);
            keskiarvo += rating.getValue();
        }
        if (!ratings.isEmpty()) {
            keskiarvo = keskiarvo / ratings.size();
            System.out.println("number of ratings: " + ratings.size() + " average " + keskiarvo);
            return;
        }
        System.out.println("no ratings");
    }

    private void askRating(Beer foundBeer) throws NumberFormatException, OptimisticLockException {
        System.out.println("give rating (leave emtpy if not): ");
        String givenRate = scanner.nextLine();
        if (!givenRate.isEmpty()) {
            int rate = Integer.parseInt(givenRate);
            Rating rating = new Rating(foundBeer, userLoggedIn, rate);
            beerService.saveRating(rating); // server.save(rating);
        }
    }

    @Override
    public void suorita() {
        findBeer();
    }
}
