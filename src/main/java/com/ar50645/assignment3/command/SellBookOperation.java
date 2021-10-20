package com.ar50645.assignment3.command;

import com.ar50645.assignment3.inventory.Book;
import com.ar50645.assignment3.inventory.Inventory;

import java.io.Serializable;
import java.util.Objects;

public class SellBookOperation implements InventoryOperation, Serializable {

    private Book book;

    public SellBookOperation(Book book) {
        this.book = book;
    }

    @Override
    public boolean execute(Inventory inventory) {
        return inventory.sellBook(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellBookOperation that = (SellBookOperation) o;
        return book.equals(that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }

    @Override
    public String toString() {
        return "SellBookOperation{" +
                "book=" + book +
                '}';
    }
}
