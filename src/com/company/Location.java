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

    public void reinit()
    {
        if (this.distance != Double.POSITIVE_INFINITY) {
            this.distance = Double.POSITIVE_INFINITY;
            for (int i = 0; i < this.neighbors.length; i++) {
                this.neighbors[i].reinit();
            }
        }
    }

    public void findPathTo(Location to)
    {
        LocationSet set = new LocationSet();
        this.distance = 0;
        Location cur = this;

        while (cur != null && cur != to) {
            cur.proceedNode(set);

            cur = set.removeMin();
        }

        if (cur == null) {
            System.out.println("Impossible d'atteindre " + to.name + " depuis " + this.name);
        } else if (cur == to) {
            System.out.println(to.name + " est a une distance de " + this.name + " de " + to.distance + "km");
            System.out.println("Itinéraire en partant de " + to.name);
        }

        this.reinit();
    }

    public void proceedNode(LocationSet set)
    {
        // On parcours les voisins de la location
        for (int i = 0; i < this.neighbors.length; i++)
        {
            Location n = this.neighbors[i];
            // Si le voisin a une distance infini, c'est qu'il n'a pas encore été atteint
            if (n.distance == Double.POSITIVE_INFINITY) {
                // On l'ajoute au set
                set.add(n);
            }

            // On calcule la distance entre this et son voisin parcouru
            this.distance += this.distanceTo(n);

            // Si la distance de this est plus petite que la distance de N
            if (this.distance < n.distance) {
                // On modifie la distance de N avec la distance de this
                n.distance = this.distance;
                // On alloue N.from avec this (pour se rappeler qu'on a atteint cette distance en partant de this)
                n.from = this;
            }
        }
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
