package com.ar50645.assignment3.command;

import com.ar50645.assignment3.inventory.Book;
import com.ar50645.assignment3.inventory.Inventory;

import java.io.Serializable;
import java.util.Objects;

public class AddCopyOperation implements InventoryOperation, Serializable {

    private final Book book;
    private final int quantity;

    public AddCopyOperation(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    @Override
    public boolean execute(Inventory inventory) {
        return inventory.addCopy(book, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddCopyOperation that = (AddCopyOperation) o;
        return quantity == that.quantity && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, quantity);
    }

    @Override
    public String toString() {
        return "AddCopyOperation{" +
                "book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
