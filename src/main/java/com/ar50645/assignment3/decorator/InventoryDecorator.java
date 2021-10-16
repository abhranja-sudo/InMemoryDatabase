package com.ar50645.assignment3.decorator;

import com.ar50645.assignment3.inventory.Book;
import com.ar50645.assignment3.exception.EntityNotFoundException;
import com.ar50645.assignment3.inventory.Inventory;
import com.ar50645.assignment3.fileio.ObjectReadWrite;
import com.ar50645.assignment3.command.AddBookOperation;
import com.ar50645.assignment3.command.InventoryOperation;
import com.ar50645.assignment3.command.InventoryOperationExecutor;
import com.ar50645.assignment3.command.SellBookOperation;

import java.io.FileOutputStream;

public class InventoryDecorator implements Inventory {

    private final String COMMAND_OUT_FILE =  "Command.ser";
    private InventoryOperationExecutor inventoryOperationExecutor;
    private ObjectReadWrite objectReadWrite;

    public InventoryDecorator()  {
        inventoryOperationExecutor = new InventoryOperationExecutor();
        objectReadWrite = new ObjectReadWrite(COMMAND_OUT_FILE);
    }

    @Override
    public boolean addNewBook(Book newBook) {

        FileOutputStream fileOut;

        // Create Command
        InventoryOperation addBookOperation = new AddBookOperation(newBook);

        // Execute Command
        inventoryOperationExecutor.executeOperation(addBookOperation);

        // Save Command
        objectReadWrite.writeObject(addBookOperation);

        return true;
    }

    @Override
    public boolean sellBook(Book bookToSell) throws EntityNotFoundException {

        // Create Command
        InventoryOperation sellBookOperation = new SellBookOperation(bookToSell);

        // Execute Command
        inventoryOperationExecutor.executeOperation(sellBookOperation);

        // Save Command
        objectReadWrite.writeObject(sellBookOperation);

        return true;
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
