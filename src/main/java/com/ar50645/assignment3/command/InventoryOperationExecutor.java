package com.ar50645.assignment3.command;

import com.ar50645.assignment3.inventory.BookInventory;
import com.ar50645.assignment3.inventory.Inventory;
import com.ar50645.assignment3.fileio.ObjectReadWrite;

public class InventoryOperationExecutor {

    private final static String COMMAND_FILE_NAME =  "Command.ser";
    private Inventory bookInventory = BookInventory.getBookInventory();
    private ObjectReadWrite objectReadWrite = new ObjectReadWrite(COMMAND_FILE_NAME);

    public InventoryOperationExecutor() {
        InventoryOperation commandHistory = (InventoryOperation) objectReadWrite.readNext();
        while (commandHistory != null) {
            executeOperation(commandHistory);
            commandHistory = (InventoryOperation) objectReadWrite.readNext();
        }
    }

    public boolean executeOperation(InventoryOperation inventoryOperation) {
        return inventoryOperation.execute(bookInventory);
    }
}
