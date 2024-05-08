import java.util.ArrayList;
import java.util.List;

public class Route {

    public String navPref;

    public List<Airport> airports = new ArrayList<Airport>();
    public List<Double> flightDistances = new ArrayList<Double>();
    public List<Double> flightCosts = new ArrayList<Double>();
    public List<Double> flightTimes = new ArrayList<Double>();

    public double totalDistance = 0f;
    public double totalDollars = 0f;
    public double totalSeconds = 0f;

    public static List<Completion> completions = new ArrayList<Completion>();

    Route(String _originID, String _destinationID, String _navPref) {

        navPref = _navPref;

        if(navPref.equals("time")){
            calculateRouteByTime(_originID, _destinationID);
            return;
        }

        calculateRouteByPrice(_originID, _destinationID);
    }

    private void calculateRouteByTime(String _originID, String _destinationID){
        List<String> initPath = new ArrayList<>();
        initPath.add(_originID);

        new Checkpoint(_destinationID, initPath, 0f, 0f, 0f);

        int fastestCompletionIndex = -1;
        double fastestCompletionTime = Double.MAX_VALUE;

        for (int i = 0; i < completions.size(); i++)
        {
            if(completions.get(i).totalTime < fastestCompletionTime){
                fastestCompletionIndex = i;
                fastestCompletionTime = completions.get(i).totalTime;
            }
        }

        Completion targetCompletion = completions.get(fastestCompletionIndex);

        totalDistance = targetCompletion.totalDistance;
        totalDollars = targetCompletion.totalPrice;
        totalSeconds = targetCompletion.totalTime;

        for(int i = 0; i < targetCompletion.path.size(); i++){

            Airport ap = Config.airportMap.get(targetCompletion.path.get(i));

            airports.add(ap);

            if(i < targetCompletion.path.size() - 1){

                Airport nap = Config.airportMap.get(targetCompletion.path.get(i + 1));

                Double distance = MathFunctions.coordinatesToDistanceMiles(ap.lat, ap.lon, nap.lat, nap.lon);
                Double price = MathFunctions.distanceToPrice(distance);
                Double time = MathFunctions.distanceToTime(distance);

                flightDistances.add(distance);
                flightCosts.add(price);
                flightTimes.add(time);
            }
        }
    }

    private void calculateRouteByPrice(String _originID, String _destinationID){

        List<String> initPath = new ArrayList<>();
        initPath.add(_originID);

        new Checkpoint(_destinationID, initPath, 0f, 0f, 0f);

        int cheapestCompletionIndex = -1;
        double cheapestCompletionPrice = Double.MAX_VALUE;

        for (int i = 0; i < completions.size(); i++)
        {
            if(completions.get(i).totalPrice < cheapestCompletionPrice){
                cheapestCompletionIndex = i;
                cheapestCompletionPrice = completions.get(i).totalPrice;
            }
        }

        Completion targetCompletion = completions.get(cheapestCompletionIndex);

        totalDistance = targetCompletion.totalDistance;
        totalDollars = targetCompletion.totalPrice;
        totalSeconds = targetCompletion.totalTime;

        for(int i = 0; i < targetCompletion.path.size(); i++){

            Airport ap = Config.airportMap.get(targetCompletion.path.get(i));

            airports.add(ap);

            if(i < targetCompletion.path.size() - 1){

                Airport nap = Config.airportMap.get(targetCompletion.path.get(i + 1));

                Double distance = MathFunctions.coordinatesToDistanceMiles(ap.lat, ap.lon, nap.lat, nap.lon);
                Double price = MathFunctions.distanceToPrice(distance);
                Double time = MathFunctions.distanceToTime(distance);

                flightDistances.add(distance);
                flightCosts.add(price);
                flightTimes.add(time);
            }
        }
    }
}
