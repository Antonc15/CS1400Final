import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        if(!Config.initialize()){
            return;
        }

        String originID = "none";
        String destinationID = "none";
        String navPref = "none";

        Scanner scanner = new Scanner(System.in);

        //<editor-fold desc="Origin">
        System.out.println("Enter your origin airport.");

        System.out.println("------------------------------");

        for (Map.Entry<String, Airport> _airport : Config.airportMap.entrySet()) {
            String id = _airport.getKey();
            String name = _airport.getValue().name;

            System.out.printf("* (%s) %s%n", id, name);
        }

        System.out.println("------------------------------");

        System.out.println("* use the 3-character-code *");

        while(originID.equals("none")){
            System.out.print("Origin: ");

            String input = scanner.next();
            originID = getAirportKey(input);
        }
        //</editor-fold>

        System.out.println();

        //<editor-fold desc="Destination">
        System.out.println("Enter your destination airport.");

        System.out.println("------------------------------");

        for (Map.Entry<String, Airport> _airport : Config.airportMap.entrySet()) {
            String id = _airport.getKey();
            String name = _airport.getValue().name;

            System.out.printf("* (%s) %s%n", id, name);
        }

        System.out.println("------------------------------");

        System.out.println("* use the 3-character-code *");

        while(destinationID.equals("none")){
            System.out.print("Destination: ");

            String input = scanner.next();
            destinationID = getAirportKey(input);
        }
        //</editor-fold>

        System.out.println();

        //<editor-fold desc="Navigation Pref">
        System.out.println("Select a navigation preference:");

        System.out.println("------------------------------");
        System.out.println("* Price");
        System.out.println("* Time");
        System.out.println("------------------------------");

        while(navPref.equals("none")){
            System.out.print("Preference: ");
            String input = scanner.next();

            navPref = getNavPref(input);
        }

        //</editor-fold>

        System.out.println();

        //<editor-fold desc="Calculate & Print Route">
        String originName = Config.airportMap.get(originID).name;
        String destinationName = Config.airportMap.get(destinationID).name;

        System.out.printf("Calculating route from [%s] to [%s] with best (%s).\n", originName, destinationName, navPref);

        Route route = new Route(originID, destinationID, navPref);

        printFlightLog(route);
        //</editor-fold>
    }

    private static String getAirportKey(String _input) {

        String inputUC = _input.toUpperCase();

        if(Config.airportMap.containsKey(inputUC)){
            return inputUC;
        }

        return "none";
    }

    private static String getNavPref(String _input) {
        String text = _input.toLowerCase();

        if(text.equals("price") || text.equals("time")){
            return text;
        }

        return "none";
    }

    private static void printFlightLog(Route _route) {

        System.out.println("------------------------------------------------------------------------------------------");

        for (int i = 0; i < _route.airports.size() - 1; i++) {
            System.out.printf("[%s] >>>> %.2f miles | $%.2f | %.2f hours >>>> [%s]\n", _route.airports.get(i).name, _route.flightDistances.get(i), _route.flightCosts.get(i), _route.flightTimes.get(i) / 3600, _route.airports.get(i + 1).name);
        }

        System.out.println("------------------------------------------------------------------------------------------");

        System.out.println();

        System.out.println("------------------------------");
        System.out.printf("[Total Distance: %.2f miles]\n", _route.totalDistance);
        System.out.printf("[Total Cost: $%.2f]\n", _route.totalDollars);
        System.out.printf("[Total Time: %.2f hours]\n", _route.totalSeconds / 3600);
        System.out.println("------------------------------");
    }

}