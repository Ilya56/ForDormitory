package models;

import sample.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilya on 16.09.2017 20:32 21:22, ForDormitory.
 */
public class Item {
    private String name;
    private double cost;
    private List<PersonList> persons;

    public Item(String name, double cost) {
        this.name = name;
        this.cost = cost;
        persons = new ArrayList<>();
        Main.getPeople().forEach(person -> persons.add(new PersonList(person)));
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public List<PersonList> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons.clear();
        persons.forEach(person -> this.persons.add(new PersonList(person)));
    }

    @Override
    public String toString() {
        return name + ", " + cost;
    }
}
