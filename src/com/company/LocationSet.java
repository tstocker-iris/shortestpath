package com.company;

public class LocationSet {
    private Location[] locations;
    private int nbLocations;

    LocationSet() {
        this.locations = new Location[4];
        this.nbLocations = 0;
    }


    public Location removeMin() {
        // On initialise une variable tampon à null
        Location min = null;
        int indexMin = 0;

        // S'il n'y a pas
        if (this.nbLocations > 0) {
            min = this.locations[0];

            for (int i = 0; i < this.nbLocations; i++) {
                Location tmp = this.locations[i];
                if (tmp != null && tmp.getDistance() < min.getDistance()) {
                    min = tmp;
                    indexMin = i;
                }
            }

            this.locations[indexMin] = this.locations[this.nbLocations-1];
            this.nbLocations--;
        }


        return min;
    }

    public void add(Location pLocation) {
        // Si le nombre d'élément est supérieur ou égal à la taille de notre tableau
        if (this.nbLocations >= this.locations.length) {
            System.out.println("On réalloue le tableau avec " + (this.nbLocations + 1) + " éléments");
            // On stocke le tableau courant dans une variable tampon
            Location[] old = this.locations;
            // On réalloue un nouveau tableau de taille length + 1;
            this.locations = new Location[this.nbLocations + 1];
            // On parcours notre ancien tableau pour allouer les éléments dans le nouveau;
            for(int i = 0; i < old.length; i++) {
                this.locations[i] = old[i];
            }
        }
        // On ajoute l'élément passé en paramètre au tableau
        this.locations[this.nbLocations] = pLocation;
        // On augmente le nombre d'élément présent;
        this.nbLocations++;
    }
}
