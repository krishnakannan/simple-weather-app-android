package io.github.krishnakannan.simpleweatherapp.Model;

/**
 * Created by Krish on 20/07/16.
 */
public class Area {

    private String east;

    private String west;

    private String north;

    private String south;

    private String central;

    public Area(String east, String west, String north, String south, String central)  {
        this.east = east;
        this.west = west;
        this.north = north;
        this.south = south;
        this.central = central;
    }
}
