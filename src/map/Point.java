/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import graphADT.Vertex;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;

/**
 * Geographic point, specified by its coordinates.
 *
 * @author Christophe Jacquet
 *
 */
public class Point extends Vertex{
    
    private  double easting;
    private  double northing;

    // We need to use a single "reference meridian" for all points, for the
    // projection to be meaningful. We use the first point's longitude for
    // this. UTM ensures that distortion is below 1/1000 for a 6° longitude
    // span around lambda0.
    // Alternatively, we could use an explicit UTM zone and project within
    // this zone.
    private static double lambda0 = Double.NaN;

    private static final int N0_NORTH = 0;			// northern hemisphere(km)
    private static final int N0_SOUTH = 10000;		// southern hemisphere(km)
    //E0 = 500km 
    //f = 1/298.257223563.
    private static double k0 = .9996;   //                      // initial point scale factor
    private static double a = 6378.137;				// Earth's radius (km)
    private static double e = .0818192;                         //eccentricity

    /**
     * Creates a new point, given its coordinates according to the WGS84 datum.
     *
     * @param latitude
     * @param longitude
     */
    public Point(String label, double latitude, double longitude) {
        super(label, latitude, longitude);

        // lambda0 is the reference meridian of the projection
        // we use the first point's longitude as lambda0
        if (Double.isNaN(lambda0)) {
            lambda0 = longitude * PI / 180;

            // Note: another approach would be to use an explicit UTM zone, and
            // then deduce lambda0 as the zone's "middle meridian":
            // lambda0 = ((UTM_ZONE - 1) * 6 -180 + 3) * PI/180;
        }

        // project this point
        // see http://en.wikipedia.org/wiki/Universal_Transverse_Mercator_coordinate_system
        int n0 = this.getLat() >= 0 ? N0_NORTH : N0_SOUTH;

        // determine easting and northing
        double phi = latitude * PI / 180;
        double lambda = longitude * PI / 180;

        double e2 = e * e;
        double e4 = e2 * e2;
        double e6 = e2 * e4;

        double nu = 1 / sqrt(1 - e2 * pow(sin(phi), 2));
        double A = (lambda - lambda0) * cos(phi);
        double A2 = A * A;
        double A3 = A * A2;
        double A4 = A2 * A2;
        double A5 = A3 * A2;
        double A6 = A3 * A3;
        double s = (1 - e2 / 4 - 3 * e4 / 64 - 5 * e6 / 256) * phi - (3 * e2 / 8 + 3 * e4 / 32 + 45 * e6 / 1024) * sin(2 * phi) + (15 * e4 / 256 + 45 * e6 / 1024) * sin(4 * phi) - 35 * e6 / 3072 * sin(6 * phi);

        double T = Math.pow(tan(phi), 2);
        double C = e2 / (1 - e * e) * pow(cos(phi), 2);

        easting = 500 + k0 * a * nu * (A + (1 - T + C) * A3 / 6 + (5 - 18 * T + T * T) * A5 / 120);
        northing = n0 + k0 * a * (s + nu * tan(phi) * (A2 / 2 + (5 - T + 9 * C + 4 * C * C) * A4 / 24 + (61 - 58 * T + T * T) * A6 / 720));
    }

    public Point(String label, Point from, Point to, double fraction) {
        this(label, from.getLat() + fraction * (to.getLat() - from.getLat()), from.getLon() + fraction * (to.getLon() - from.getLon()));
    }

    /**
     * Returns the easting of the point.
     *
     * @return the easting
     */
    public double getEasting() {
        return easting;
    }

    /**
     * Returns the northing of the point.
     *
     * @return the northing
     */
    public double getNorthing() {
        return northing;
    }



    /**
     * Returns the distance between this point and another point, in kilometers.
     *
     * @param other another point
     * @return the distance between the points
     */
    public double distanceTo(Point other) {
        double ratio = Math.PI / 180;
        double deltaLat = ratio * (other.getLat() - this.getLat());
        double deltaLon = ratio * (other.getLon() - this.getLon());

        double angle = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(deltaLat / 2), 2)
                + Math.cos(ratio * this.getLat()) * Math.cos(ratio * other.getLat())
                * Math.pow(Math.sin(deltaLon / 2), 2)));

        return a * angle;
    }
}
