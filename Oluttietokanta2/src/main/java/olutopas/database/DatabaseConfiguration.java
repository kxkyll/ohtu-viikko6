/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.database;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Pub;
import olutopas.domain.Rating;
import olutopas.domain.User;

/**
 *
 * @author kxkyllon
 */
public class DatabaseConfiguration {

    public DatabaseConfiguration() {
    }
    
    public static EbeanServer initializeDatabase(boolean dropAndCreateDatabase, DBSelection db) {
        ServerConfig config = new ServerConfig();
        config.setName("beerDb");
        configH2(db, config);
        configSQLite(db, config);
        configCommon(config);
        configAddEntities(config);

        if (dropAndCreateDatabase) {
            configDropAndCreate(config);
        }

        return EbeanServerFactory.create(config);
    }

    private static void configH2(DBSelection db, ServerConfig config) {
        if (db == DBSelection.H2) {
            DataSourceConfig hdDB = new DataSourceConfig();
            hdDB.setDriver("org.h2.Driver");
            hdDB.setUsername("test");
            hdDB.setPassword("test");
            hdDB.setUrl("jdbc:h2:mem:tests;DB_CLOSE_DELAY=-1");
            hdDB.setHeartbeatSql("select 1 ");
            config.setDataSourceConfig(hdDB);
        }
    }

    private static void configSQLite(DBSelection db, ServerConfig config) {
        if (db == DBSelection.SQLite) {
            DataSourceConfig sqLite = new DataSourceConfig();
            sqLite.setDriver("org.sqlite.JDBC");
            sqLite.setUsername("mluukkai");
            sqLite.setPassword("mluukkai");
            sqLite.setUrl("jdbc:sqlite:beer.db");
            config.setDataSourceConfig(sqLite);
            config.setDatabasePlatform(new SQLitePlatform());
            config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);
        }
    }

    private static void configCommon(ServerConfig config) {
        config.setDefaultServer(false);
        config.setRegister(false);
    }

    private static void configAddEntities(ServerConfig config) {
        config.addClass(Beer.class);
        config.addClass(Brewery.class);
        config.addClass(Pub.class);
        config.addClass(User.class);
        config.addClass(Rating.class);
    }

    private static void configDropAndCreate(ServerConfig config) {
        config.setDdlGenerate(true);
        config.setDdlRun(true);
        //config.setDebugSql(true);
    }
    
}
