import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Checkpoint {

    Checkpoint(String _dest, List<String> _path, double _price, double _time, double _dist){

        Airport lastAirport = Config.airportMap.get(_path.getLast());

        for (Map.Entry<String,Airport> entry : Config.airportMap.entrySet()){

            if(_path.contains(entry.getKey())){
                continue;
            }

            Airport currentAirport = entry.getValue();

            List<String> newPath = new ArrayList<String>(_path);
            newPath.add(entry.getKey());

            double cDist = MathFunctions.coordinatesToDistanceMiles(currentAirport.lat, currentAirport.lon, lastAirport.lat, lastAirport.lon);
            double cPrice = MathFunctions.distanceToPrice(cDist);
            double cTime = MathFunctions.distanceToTime(cDist);

            if(entry.getKey().equals(_dest)){
                Completion comp = new Completion();

                comp.path.addAll(newPath);
                comp.totalDistance = _dist + cDist;
                comp.totalPrice = _price + cPrice;
                comp.totalTime = _time + cTime;

                Route.completions.add(comp);

                continue;
            }

            new Checkpoint(_dest, newPath, _price + cPrice, _time + cTime, _dist + cDist);
        }
    }
}
