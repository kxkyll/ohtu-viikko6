package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import olutopas.database.BeerRepositoryInterface;
import olutopas.database.UserRepositoryInterface;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Pub;
import olutopas.domain.Rating;
import olutopas.domain.User;
import olutopas.service.BeerService;
import olutopas.service.UserService;
import olutopas.ui.Komento;
import olutopas.ui.Komentotehdas;

public class Application {

    private Komentotehdas komennot;
    private Scanner scanner = new Scanner(System.in);
    private User userLoggedIn;
    private UserService userService;
    private BeerService beerService;

    public Application(UserRepositoryInterface userRepo, BeerRepositoryInterface beerRepo) {
        
        this.userService = new UserService(userRepo);
        this.beerService = new BeerService(beerRepo);
        this.komennot = new Komentotehdas(beerService, userService, scanner);
    }

    public void run() {
        
        userLoggedIn =  login();       
        if (userLoggedIn == null) {
            System.out.println("Unauthorized user");
            System.out.println("Exiting...");
            return;
        }

        this.komennot.lisaaKirjautuneenPalvelut(userLoggedIn);

        System.out.println("");

        System.out.println("\nWelcome to Ratebeer " + userLoggedIn.getName());

        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();

            Komento komento = komennot.hae(command);
            if (komento != null) {
                komento.suorita();
            }

            if (command.equals("0")) {
                break;
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
}
