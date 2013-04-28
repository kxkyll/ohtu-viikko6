package olutopas;

import olutopas.database.DBSelection;
import com.avaje.ebean.EbeanServer;
import olutopas.database.BeerRepository;
import olutopas.database.BeerRepositoryInterface;
import olutopas.database.DatabaseConfiguration;
import olutopas.database.UserRepository;
import olutopas.database.UserRepositoryInterface;
import olutopas.service.BeerService;
import olutopas.service.DatabaseService;
import olutopas.service.UserService;

public class Main {

    public static void main(String[] args) {
   
    
      
        boolean dropAndCreateTables = true;
        EbeanServer server = new DatabaseConfiguration().initializeDatabase(dropAndCreateTables, DBSelection.SQLite);
        DatabaseService databaseService = new DatabaseService(server);
        if (dropAndCreateTables){
            databaseService.seedDatabase();
        }
        UserRepositoryInterface userRepository = new UserRepository(server);
        BeerRepositoryInterface beerRepository = new BeerRepository(server);
        //new Application(server).run(dropAndCreateTables);
        new Application(userRepository, beerRepository).run();
        
    }
}
