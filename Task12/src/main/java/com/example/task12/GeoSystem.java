package com.example.task12;

import java.util.ArrayList;
import java.util.List;


interface GeographicComponent {
    void printInfo();
}


class City implements GeographicComponent {
    private String name;

    public City(String name) {
        this.name = name;
    }

    @Override
    public void printInfo() {
        System.out.println("Город: " + name);
    }
}


class Region implements GeographicComponent {
    private String name;
    private List<GeographicComponent> cities = new ArrayList<>();

    public Region(String name) {
        this.name = name;
    }

    public void addCity(GeographicComponent city) {
        cities.add(city);
    }

    @Override
    public void printInfo() {
        System.out.println("Регион: " + name);
        for (GeographicComponent city : cities) {
            city.printInfo();
        }
    }
}


class Country implements GeographicComponent {
    private String name;
    private List<GeographicComponent> regions = new ArrayList<>();

    public Country(String name) {
        this.name = name;
    }

    public void addRegion(GeographicComponent region) {
        regions.add(region);
    }

    @Override
    public void printInfo() {
        System.out.println("Страна: " + name);
        for (GeographicComponent region : regions) {
            region.printInfo();
        }
    }
}


public class GeoSystem {
    public static void main(String[] args) {

        GeographicComponent city1 = new City("Воронеж");
        GeographicComponent city2 = new City("Липецк");

        GeographicComponent region1 = new Region("Воронежская область");
        ((Region) region1).addCity(city1);
        ((Region) region1).addCity(city2);

        GeographicComponent city3 = new City("Волгоград");
        GeographicComponent city4 = new City("Калач-на-Дону");

        GeographicComponent region2 = new Region("Волгоградская область");
        ((Region) region2).addCity(city3);
        ((Region) region2).addCity(city4);

        GeographicComponent country = new Country("Россия");
        ((Country) country).addRegion(region1);
        ((Country) country).addRegion(region2);


        country.printInfo();
    }
}
