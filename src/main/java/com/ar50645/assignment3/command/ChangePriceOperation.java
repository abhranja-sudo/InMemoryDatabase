package com.ar50645.assignment3.command;

import com.ar50645.assignment3.inventory.Book;
import com.ar50645.assignment3.inventory.Inventory;

import java.io.Serializable;
import java.util.Objects;

public class ChangePriceOperation implements InventoryOperation, Serializable {

    private Book book;
    private double newPrice;

    public ChangePriceOperation(Book book, double newPrice) {
        this.book = book;
        this.newPrice = newPrice;
    }

    @Override
    public boolean execute(Inventory inventory) {
        return inventory.changePrice(book, newPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangePriceOperation that = (ChangePriceOperation) o;
        return Double.compare(that.newPrice, newPrice) == 0 && book.equals(that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, newPrice);
    }

    @Override
    public String toString() {
        return "ChangePriceOperation{" +
                "book=" + book +
                ", newPrice=" + newPrice +
                '}';
    }
}
