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

    /**
     * Villes adjacentes
     */
    private Location[] neighbors;

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
