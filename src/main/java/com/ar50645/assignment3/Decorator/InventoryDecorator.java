package com.ar50645.assignment3.Decorator;

import com.ar50645.assignment3.Book;
import com.ar50645.assignment3.EntityNotFoundException;
import com.ar50645.assignment3.Inventory;
import com.ar50645.assignment3.BookInventory;
import com.ar50645.assignment3.command.AddBookOperation;
import com.ar50645.assignment3.command.InventoryOperation;
import com.ar50645.assignment3.command.InventoryOperationExecutor;

public class InventoryDecorator implements Inventory {

    private Inventory bookInventory = new BookInventory();
    private InventoryOperationExecutor inventoryOperationExecutor = new InventoryOperationExecutor();

    @Override
    public boolean addNewBook(Book newBook) {

        // Create Command
        InventoryOperation addBookOperation = new AddBookOperation(bookInventory, newBook);

        // Execute Command
        inventoryOperationExecutor.executeOperation(addBookOperation);

        // @@TODO Save Command

        return true;
    }

    @Override
    public boolean sellBook(Book bookToSell) throws EntityNotFoundException {
        return false;
    }

    @Override
    public boolean addCopy(Book book) throws EntityNotFoundException {
        return false;
    }

    @Override
    public boolean changePrice(Book book) throws EntityNotFoundException {
        return false;
    }

    @Override
    public double findPriceByName(String bookName) throws EntityNotFoundException {
        return 0;
    }

    @Override
    public double findPriceByID(int id) throws EntityNotFoundException {
        return 0;
    }

    @Override
    public double findQuantityByName(String bookName) throws EntityNotFoundException {
        return 0;
    }

    @Override
    public double findQuantityByID(int id) throws EntityNotFoundException {
        return 0;
    }
}
