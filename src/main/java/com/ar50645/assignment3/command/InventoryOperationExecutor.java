package com.ar50645.assignment3.command;

import com.ar50645.assignment3.BookInventory;
import com.ar50645.assignment3.Inventory;

public class InventoryOperationExecutor {

    private Inventory bookInventory = BookInventory.getBookInventory();

    public boolean executeOperation(InventoryOperation inventoryOperation) {
        return inventoryOperation.execute(bookInventory);
    }
}
