package org.example.model;

public class Toy implements StoreItem{
    private int id;
    private String name;
    private int quantity;
    private long dropoutRate;

    public Toy(int id, String name, int quantity, long dropoutRate){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.dropoutRate = dropoutRate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public long getDropoutRate() {
        return dropoutRate;
    }

    public void setDropoutRate(long dropoutRate){
        this.dropoutRate = dropoutRate;
    }

    @Override
    public String toString() {
        return getInfo();
    }

    private String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ")
                .append(id)
                .append(", name: ")
                .append(name)
                .append(", quantity: ")
                .append(quantity)
                .append(", dropout rate: ")
                .append(dropoutRate);
        return sb.toString();
    }
}
