package com.ar50645.assignment3.command;

import com.ar50645.assignment3.inventory.BookInventory;
import com.ar50645.assignment3.inventory.Inventory;
import com.ar50645.assignment3.fileio.FileOperation;

public class InventoryOperationExecutor {

    private final static String COMMAND_FILE_NAME =  "Command.ser";
    private Inventory bookInventory = BookInventory.getBookInventory();
    private FileOperation fileOperation = new FileOperation(COMMAND_FILE_NAME);

    public InventoryOperationExecutor() {
        InventoryOperation commandHistory = (InventoryOperation) fileOperation.readNext();
        while (commandHistory != null) {
            executeOperation(commandHistory);
            commandHistory = (InventoryOperation) fileOperation.readNext();
        }
    }

    public boolean executeOperation(InventoryOperation inventoryOperation) {
        return inventoryOperation.execute(bookInventory);
    }
}
