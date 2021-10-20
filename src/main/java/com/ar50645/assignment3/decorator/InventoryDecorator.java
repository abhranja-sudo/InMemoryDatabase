package com.ar50645.assignment3.decorator;

import com.ar50645.assignment3.command.AddBookOperation;
import com.ar50645.assignment3.command.AddCopyOperation;
import com.ar50645.assignment3.command.ChangePriceOperation;
import com.ar50645.assignment3.command.InventoryOperation;
import com.ar50645.assignment3.command.InventoryOperationExecutor;
import com.ar50645.assignment3.command.SellBookOperation;
import com.ar50645.assignment3.inventory.Book;
import com.ar50645.assignment3.exception.EntityNotFoundException;
import com.ar50645.assignment3.inventory.Inventory;
import com.ar50645.assignment3.fileio.FileOperation;

public class InventoryDecorator implements Inventory {

    private final String COMMAND_OUT_FILE =  "Command.ser";
    private final InventoryOperationExecutor inventoryOperationExecutor;
    private final FileOperation fileOperation;
    private final Inventory inventory;

    public InventoryDecorator(Inventory inventory)  {
        this.inventory = inventory;
        inventoryOperationExecutor = new InventoryOperationExecutor(inventory);
        fileOperation = new FileOperation(COMMAND_OUT_FILE);
    }

    @Override
    public boolean addNewBook(Book newBook) {

        // Create Command
        InventoryOperation addBookOperation = new AddBookOperation(newBook);

        // Execute Command
        inventoryOperationExecutor.executeOperation(inventory, addBookOperation);

        // Save Command
        fileOperation.writeObject(addBookOperation);

        return true;
    }

    @Override
    public boolean sellBook(Book bookToSell) throws EntityNotFoundException {

        // Create Command
        InventoryOperation sellBookOperation = new SellBookOperation(bookToSell);

        // Execute Command
        inventoryOperationExecutor.executeOperation(inventory, sellBookOperation);

        // Save Command
        fileOperation.writeObject(sellBookOperation);

        return true;
    }

    @Override
    public boolean addCopy(Book book, int quantity) throws EntityNotFoundException {

        // Create Command
        InventoryOperation addCopyOperation = new AddCopyOperation(book, quantity);

        // Execute Command
        inventoryOperationExecutor.executeOperation(inventory, addCopyOperation);

        // Save Command
        fileOperation.writeObject(addCopyOperation);

        return true;
    }

    @Override
    public boolean changePrice(Book book, double newPrice) throws EntityNotFoundException {

        // Create Command
        InventoryOperation changePriceOperation = new ChangePriceOperation(book, newPrice);

        // Execute Command
        inventoryOperationExecutor.executeOperation(inventory, changePriceOperation);

        // Save Command
        fileOperation.writeObject(changePriceOperation);

        return true;
    }

    @Override
    public boolean isBookAvailable(Book book) {
        return inventory.isBookAvailable(book);
    }

    @Override
    public double findPriceByName(String bookName) throws EntityNotFoundException {
        return inventory.findPriceByName(bookName);
    }

    @Override
    public double findPriceByID(int id) throws EntityNotFoundException {
        return inventory.findPriceByID(id);
    }

    @Override
    public double findQuantityByName(String bookName) throws EntityNotFoundException {
        return inventory.findQuantityByName(bookName);
    }

    @Override
    public double findQuantityByID(int id) throws EntityNotFoundException {
        return inventory.findQuantityByID(id);
    }
}
