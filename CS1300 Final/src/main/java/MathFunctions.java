public class MathFunctions {

    public static double coordinatesToDistanceMiles(double _lat1, double _lon1, double _lat2, double _lon2){

        double lat1Rad = Math.toRadians(_lat1);
        double lon1Rad = Math.toRadians(_lon1);
        double lat2Rad = Math.toRadians(_lat2);
        double lon2Rad = Math.toRadians(_lon2);

        double s = Math.sin(lat1Rad) * Math.sin(lat2Rad);
        double c = Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.cos(lon1Rad - lon2Rad);
        double r = 3958.8f;

        return Math.acos(s + c) * r;
    }

    public static double distanceToPrice(Double _d){

        return _d * Config.pricePerMile * (1 + (Config.milePriceMultiplier * _d));
    }

    public static double distanceToTime(Double _d){
        return _d * Config.timePerMile;
    }
}
