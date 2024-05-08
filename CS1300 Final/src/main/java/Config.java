import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Config {

    // Values
    public static double pricePerMile;
    public static double timePerMile;
    public static double milePriceMultiplier;

    // Airport
    public static Map<String, Airport> airportMap = new HashMap<String, Airport>();

    // Public Methods
    public static boolean initialize(){

        InputStream inputStream;

        try{
            inputStream = new FileInputStream("config.yml");
        }
        catch(FileNotFoundException _e){
            System.out.println("\"config.yml\" file not found. Make sure this file exists in the \"CS1300 Final\" folder.");
            return false;
        }

        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);

        Map<String, Object> settingsMap = (Map<String, Object>) data.get("settings");
        Map<String, Object> airportsMap = (Map<String, Object>) data.get("airports");

        assignValues(settingsMap);
        assignAirports(airportsMap);

        return true;
    }

    // Private Methods
    private static void assignValues(Map<String, Object> _settings){

        pricePerMile = (double) _settings.get("dollars-per-mile");
        timePerMile = (double) _settings.get("seconds-per-mile");
        milePriceMultiplier = (double) _settings.get("mile-price-multiplier");
    }

    private static void assignAirports(Map<String, Object> _airports){

        for (Map.Entry<String, Object> _airport : _airports.entrySet()){

            String id = _airport.getKey();
            Map<String, Object> data = (Map<String, Object>) _airport.getValue();

            Airport newAirport = new Airport();

            newAirport.name = (String) data.get("name");
            newAirport.lat = (double) data.get("lat");
            newAirport.lon = (double) data.get("lon");

            airportMap.put(id, newAirport);
        }
    }
}
