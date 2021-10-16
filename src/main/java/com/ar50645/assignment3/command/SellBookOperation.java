package com.ar50645.assignment3.command;

import com.ar50645.assignment3.inventory.Book;
import com.ar50645.assignment3.inventory.Inventory;

import java.io.Serializable;

public class SellBookOperation implements InventoryOperation, Serializable {

    private Book book;

    public SellBookOperation(Book book) {
        this.book = book;
    }

    @Override
    public boolean execute(Inventory inventory) {
        return inventory.sellBook(book);
    }
}
