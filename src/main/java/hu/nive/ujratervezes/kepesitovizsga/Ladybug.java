package hu.nive.ujratervezes.kepesitovizsga;

import java.util.Objects;

public class Ladybug {

    private String hungarian_name;
    private String latin_name;
    private String genus;
    private int number_of_points;

    public Ladybug(String hungarian_name, String latin_name, String genus, int number_of_points) {
        this.hungarian_name = hungarian_name;
        this.latin_name = latin_name;
        this.genus = genus;
        this.number_of_points = number_of_points;
    }

    public String getHungarian_name() {
        return hungarian_name;
    }

    public String getLatin_name() {
        return latin_name;
    }

    public String getGenus() {
        return genus;
    }

    public int getNumber_of_points() {
        return number_of_points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ladybug ladybug = (Ladybug) o;
        return Objects.equals(hungarian_name, ladybug.hungarian_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hungarian_name);
    }
}
