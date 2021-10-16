package com.ar50645.assignment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Memento for BookInventory
 */
public class BookInventoryState {

    private List<Book> bookInventoryCollection;

    public BookInventoryState(List<Book> bookInventoryCollection) {
        this.bookInventoryCollection = bookInventoryCollection;
    }

    public List<Book> getBookInventoryCollection() {
        return bookInventoryCollection;
    }
}
