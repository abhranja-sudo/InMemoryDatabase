package com.ar50645.assignment3.command;

import com.ar50645.assignment3.Book;
import com.ar50645.assignment3.Inventory;

public class SellBookOperation implements InventoryOperation {

    private Inventory inventory;
    private Book book;

    public SellBookOperation(Inventory inventory, Book book) {
        this.inventory = inventory;
        this.book = book;
    }

    @Override
    public boolean execute() {
        return inventory.sellBook(book);
    }
}
