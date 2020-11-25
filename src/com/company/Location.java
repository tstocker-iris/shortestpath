package com.company;

/**
 * Classe Location
 */
public class Location {
    /**
     * Nom de la ville
     */
    private String name;
    /**
     * Latitude
     */
    private double latitude;
    /**
     * Longitude
     */
    private double longitude;

    private double distance;

    /**
     * Villes adjacentes
     */
    private Location[] neighbors;

    private Location from;

    /**
     * CONSTANTE 180 Degrés
     */
    final static double DEG = 180;
    /**
     * CONSTANTE Rayon terrestre
     */
    final static double R = 6378;

    /**
     * Constructor
     *
     * @param pName
     * @param pLatitude
     * @param pLongitude
     */
    Location(String pName, double pLatitude, double pLongitude) {
        this.name = pName;
        this.latitude = degToRad(pLatitude);
        this.longitude = degToRad(pLongitude);
        this.distance = Double.POSITIVE_INFINITY;
    }

    public void findPathTo(Location to)
    {
        LocationSet set = new LocationSet();
        this.distance = 0;
        Location cur = this;


    }

    public Location getFrom()
    {
        return this.from;
    }

    public void setFrom(Location pFrom) {
        this.from = pFrom;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double pDistance) {
        this.distance = pDistance;
    }

    public void setNeighbors(Location... pNeighbors)
    {
        this.neighbors = pNeighbors;
    }

    public double distanceTo(Location to) {
        return R * (Math.PI / 2 - Math.asin(Math.sin(to.latitude) *
                Math.sin(this.latitude) + Math.cos(to.longitude - this.longitude) *
                Math.cos(to.latitude) * Math.cos(this.latitude)));
    }

    public static double degToRad(double pDeg) {
        return (Math.PI * pDeg) / DEG;
    }

    public static double radToDeg(double pRad) {
        return pRad * DEG / Math.PI;
    }

    public void display() {
        System.out.println(this);
    }

    public String toString() {
        return this.name + " - " + this.latitude + ", " + this.longitude;
    }
}
