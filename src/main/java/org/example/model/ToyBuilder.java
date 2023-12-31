package org.example.model;

public class ToyBuilder {
    public Toy build(int id, String name, int quantity, long dropoutRate){
        String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return new Toy(id, capitalizedName, quantity, dropoutRate);
    }
}
