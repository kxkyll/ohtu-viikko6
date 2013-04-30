package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );
        System.out.println("has at least 10 goals and 10 assists");
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        
        Matcher jokotai = new Or( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );
        System.out.println("has at least 10 goals OR 10 assists");
        for (Player player : stats.matches(jokotai)) {
            System.out.println( player );
        }
        
        System.out.println("has fewer than 10 goals and 10 assists");
        Matcher less = new And( new HasFewerThan(10, "goals"),
                             new HasFewerThan(10, "assists"),
                             new PlaysIn("PHI")
        );
        
        for (Player player : stats.matches(less)) {
            System.out.println( player );
        }
        
        System.out.println("has not  10 goals nor 10 assists");
        Matcher not = new Not( new HasFewerThan(10, "goals"),
                             new HasFewerThan(10, "assists"),
                             new PlaysIn("PHI")
        );
        
        for (Player player : stats.matches(less)) {
            System.out.println( player );
        }
    }
}
