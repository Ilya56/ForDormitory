package models;

/**
 * Created by Ilya on 16.09.2017 21:04.
 */
public class Person {
    private String name;
    private double money;

    public Person(String name) {
        this.name = name;
        money = 0;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return name + " -- " + money;
    }
}
