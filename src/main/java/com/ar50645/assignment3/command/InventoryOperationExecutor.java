package com.ar50645.assignment3.command;

import com.ar50645.assignment3.inventory.Book;
import com.ar50645.assignment3.inventory.BookInventory;
import com.ar50645.assignment3.inventory.Inventory;
import com.ar50645.assignment3.fileio.FileOperation;

public class InventoryOperationExecutor {

    private final static String COMMAND_FILE_NAME =  "Command.ser";
    private FileOperation fileOperation = new FileOperation(COMMAND_FILE_NAME);

    public InventoryOperationExecutor() {
        InventoryOperation commandHistory = (InventoryOperation) fileOperation.readNext();
        while (commandHistory != null) {
            executeOperation(BookInventory.getBookInventory(), commandHistory);
            commandHistory = (InventoryOperation) fileOperation.readNext();
        }
    }

    public boolean executeOperation( Inventory bookInventory, InventoryOperation inventoryOperation) {
        return inventoryOperation.execute(bookInventory);
    }
}
