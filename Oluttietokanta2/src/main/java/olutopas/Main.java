package olutopas;

import olutopas.database.DBSelection;
import com.avaje.ebean.EbeanServer;
import olutopas.database.DatabaseConfiguration;

public class Main {

    public static void main(String[] args) {
        boolean dropAndCreateTables = true;
        EbeanServer server = new DatabaseConfiguration().initializeDatabase(dropAndCreateTables, DBSelection.SQLite);
        new Application(server).run(dropAndCreateTables);
    }
}
