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

    Route(String _originID, String _destinationID, String _navPref) {

        navPref = _navPref;

        if(navPref.equals("time")){
            calculateRouteByTime(_originID, _destinationID);
            return;
        }

        calculateRouteByPrice(_originID, _destinationID);
    }

    private void calculateRouteByTime(String _originID, String _destinationID){

    }

    private void calculateRouteByPrice(String _originID, String _destinationID){

        Airport origin = Config.airportMap.get(_originID);
        Airport destination = Config.airportMap.get(_destinationID);


    }
}
