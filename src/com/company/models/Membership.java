package com.company.models;

public class Membership {
    private int id;
    private String name;
    private int durationMonths;
    private double price;

    public Membership() {
    }

    public Membership(String name, int durationMonths, double price) {
        setName(name);
        setDurationMonths(durationMonths);
        setPrice(price);
    }

    public Membership(int id, String name, int durationMonths, double price) {
        this(name, durationMonths, price);
        setId(id);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDurationMonths() {
        return durationMonths;
    }
    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", durationMonths=" + durationMonths +
                ", price=" + price +
                '}';
    }
}