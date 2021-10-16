package com.ar50645.assignment3.inventory;

import java.io.Serializable;
import java.util.List;

/**
 * Memento for BookInventory
 */
public class BookListState implements Serializable {

    private final List<Book> bookInventoryCollection;

    public BookListState(List<Book> bookInventoryCollection) {
        this.bookInventoryCollection = bookInventoryCollection;
    }

    public List<Book> getBookInventoryCollection() {
        return bookInventoryCollection;
    }

    @Override
    public String toString() {
        return "BookListState{" +
                "bookInventoryCollection=" + bookInventoryCollection +
                '}';
    }
}
