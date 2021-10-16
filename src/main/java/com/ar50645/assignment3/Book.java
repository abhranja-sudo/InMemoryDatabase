package com.ar50645.assignment3;

import java.io.Serializable;
import java.util.Objects;

/**
 * No two com.ar50645.assignment3.Book can have same name
 */
public class Book implements Serializable, Cloneable {

    private int id;
    private String name;
    private double price;
    private int quantity;

    //Used to assign uniqueId to Book
    private static int sequenceCounter = 0;

    public Book(String name, double price, int quantity) {
        initializeId();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Book(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    private void initializeId() {
        sequenceCounter++;
        this.id = sequenceCounter;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "com.ar50645.assignment3.Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    /**
     * Book instance variables includes only primitives and immutables, so deep copy not required
     * @return cloned copy
     */
    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }
}
