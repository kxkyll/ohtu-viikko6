package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.database.BeerRepository;
import olutopas.database.BeerRepositoryInterface;
import olutopas.database.UserRepository;
import olutopas.database.UserRepositoryInterface;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Pub;
import olutopas.domain.Rating;
import olutopas.domain.User;
import olutopas.service.BeerService;
import olutopas.service.DatabaseService;
import olutopas.service.UserService;
import olutopas.ui.Komento;
import olutopas.ui.Komentotehdas;

public class Application {

    private Komentotehdas komennot;
    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);
    private User userLoggedIn;
    private DatabaseService databaseService;
    private UserService userService;
    private UserRepositoryInterface userRepository;
    private BeerService beerService;
    private BeerRepositoryInterface beerRepository;
    

    public Application(UserRepositoryInterface userRepo, BeerRepositoryInterface beerRepo) {
        //this.server = server;
        //this.databaseService = new DatabaseService(server);
        //this.userRepository = new UserRepository(server);
        this.userService = new UserService(userRepo);
        //this.beerRepository = new BeerRepository(server);
        this.beerService = new BeerService(beerRepo);
        

    }

    //public void run(boolean newDatabase) {
        public void run() {
//        if (newDatabase) {
//            databaseService.seedDatabase();
//        }
        userLoggedIn = login();

        if (userLoggedIn == null) {
            System.out.println("Unauthorized user");
            System.out.println("Exiting...");
            return;
        }

        this.komennot = new Komentotehdas(beerService, scanner, userLoggedIn);
        
        System.out.println("");

        System.out.println("\nWelcome to Ratebeer " + userLoggedIn.getName());

        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("0")) {
                break;
            } else if (command.equals("1")) {
                //findBrewery();
                Komento komento = komennot.hae("1");
                komento.suorita();
            } else if (command.equals("2")) {
                Komento komento = komennot.hae("2");
                komento.suorita();
                //findBeer();
            } else if (command.equals("3")) {
                addBeer();
            } else if (command.equals("4")) {
                listBreweries();
            } else if (command.equals("5")) {
                deleteBeer();
            } else if (command.equals("6")) {
                addPub();
            } else if (command.equals("7")) {
                addBeerToPub();
            } else if (command.equals("8")) {
                showBeersInPub();
            } else if (command.equals("9")) {
                listPubs();
            } else if (command.equals("10")) {
                removeBeerFromPub();
            } else if (command.equals("11")) {
                listBeers();
            } else if (command.equals("12")) {
                addBrewery();
            } else if (command.equals("13")) {
                deleteBrewery();
            } else if (command.equals("14")) {
                listMyRatings();
            } else if (command.equals("l")) {
                listUsers();
            } else {
                System.out.println("unknown command");
            }

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

        System.out.println("bye");
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find/rate beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   delete beer");// tää pois
        System.out.println("6   add pub"); // tää pois
        System.out.println("7   add beer to pub"); // tää pois
        System.out.println("8   show beers in pub"); // tää pois
        System.out.println("9   list pubs"); // tää pois
        System.out.println("10  remove beer from pub"); // tää pois
        System.out.println("11  list beers");
        System.out.println("12  add brewery");
        System.out.println("13  delete brewery");// tää pois
        System.out.println("14  show my ratings");
        System.out.println("l   list users");
        System.out.println("0   quit");
        System.out.println("");
    }

//    private void findBrewery() {
//        System.out.print("brewery to find: ");
//        String name = scanner.nextLine();
//        Brewery foundBrewery = beerService.readBrewery(name);//server.find(Brewery.class).where().like("name", n).findUnique();
//
//        if (foundBrewery == null) {
//            System.out.println(name + " not found");
//            return;
//        }
//
//        System.out.println(foundBrewery);
//        for (Beer bier : foundBrewery.getBeers()) {
//            System.out.println("   " + bier.getName());
//        }
//    }

    private void listBreweries() {
        List<Brewery> breweries = beerService.listBreweries();//server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }

    private void addPub() {
        System.out.print("pub to add: ");

        String name = scanner.nextLine();

        Pub exists = beerService.readPub(name);// server.find(Pub.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        beerService.createPub(exists); //server.save(new Pub(name));
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

    private void deleteBeer() {
        System.out.print("beer to delete: ");
        String beerName = scanner.nextLine();
        Beer beerToDelete = beerService.readBeer(beerName);
        //server.find(Beer.class).where().like("name", n).findUnique();

        if (beerToDelete == null) {
            System.out.println(beerName + " not found");
            return;
        }


        beerService.deleteBeer(beerToDelete.getName());//server.delete(beerToDelete);
        System.out.println("deleted: " + beerToDelete);

    }

    private void addBeerToPub() {
        System.out.print("beer: ");
        String name = scanner.nextLine();
        Beer beer = beerService.readBeer(name);//server.find(Beer.class).where().like("name", name).findUnique();

        if (beer == null) {
            System.out.println("does not exist");
            return;
        }

        System.out.print("pub: ");
        name = scanner.nextLine();
        Pub pub = beerService.readPub(name);//server.find(Pub.class).where().like("name", name).findUnique();

        if (pub == null) {
            System.out.println("does not exist");
            return;
        }

        pub.addBeer(beer);
        beerService.createPub(pub);//server.save(pub)
    }

    private void showBeersInPub() {
        System.out.print("pub: ");
        String name = scanner.nextLine();
        Pub pub = beerService.readPub(name); //server.find(Pub.class).where().like("name", name).findUnique();
        if (pub == null) {
            System.out.println("No such pub " + name);
            return;
        }
        if (pub.getBeers().isEmpty()) {
            System.out.println("No beers in pub " + name);
            return;
        }
        for (Beer beer : pub.getBeers()) {
            System.out.println(beer);
        }

    }

    private void listPubs() {
        List<Pub> pubs = beerService.listPubs();
        for (Pub pub : pubs) {
            System.out.println(pub);
        }
    }

    private void removeBeerFromPub() {
        System.out.print("beer: ");
        String name = scanner.nextLine();
        Beer beer = beerService.readBeer(name);//server.find(Beer.class).where().like("name", name).findUnique();

        if (beer == null) {
            System.out.println("does not exist");
            return;
        }

        System.out.print("pub: ");
        name = scanner.nextLine();
        Pub pub = beerService.readPub(name); //server.find(Pub.class).where().like("name", name).findUnique();

        if (pub == null) {
            System.out.println("does not exist");
            return;
        }

        if (pub.getBeers().contains(beer)) {
            pub.removeBeer(beer);
        }


        beerService.createPub(pub); //server.save(pub);

        System.out.println("Beer " + beer.getName() + " removed from pub " + pub.getName());
    }

    private User login() {

        String userName = askUsername();
        while (userName.equals("?")) {
            createNewUser();
            userName = askUsername();
        }

        User userToLogin = userService.readUser(userName);
        if (userToLogin == null) {
            System.out.println(userName + " not found");
            return null;
        }

        return userToLogin;

    }

    private String askUsername() {
        System.out.println("Login (give ? to register a new user)");
        System.out.print("username: ");
        String command = scanner.nextLine();
        return command;
    }

    private String createNewUser() {
        System.out.println("Register a new user");
        System.out.println("give username:");
        String name = scanner.nextLine();
        User exists = userService.readUser(name);
        if (exists != null) {
            System.out.println(name + " exists already");
            return "";
        }

        userService.createUser(new User(name));
        //server.save(new User(name));
        System.out.println("Registration of user " + name + " was successfull");
        System.out.println("=====================");
        return name;

    }

    private void listUsers() {
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void listBeers() {
        List<Beer> beers = beerService.listBeers();//server.find(Beer.class).orderBy("brewery").findList();

        if (beers.isEmpty() || beers == null) {
            System.out.println("no beers available");
        } else {
            for (Beer beer : beers) {
                System.out.println(beer);
            }
        }
    }

    private void addBrewery() {
        System.out.print("brewery to add: ");

        String name = scanner.nextLine();

        Brewery exists = beerService.readBrewery(name);//server.find(Brewery.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        beerService.createBrewery(new Brewery(name)); // server.save(new Brewery(name));
        System.out.println("Brewery " + name + " added");
    }

    private void deleteBrewery() {
        System.out.print("brewery to delete: ");
        String name = scanner.nextLine();
        Brewery breweryToDelete = beerService.readBrewery(name);//server.find(Brewery.class).where().like("name", n).findUnique();

        if (breweryToDelete == null) {
            System.out.println(name + " not found");
            return;
        }

        beerService.deleteBrewery(breweryToDelete);//server.delete(breweryToDelete);
        System.out.println("deleted: " + breweryToDelete);
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
