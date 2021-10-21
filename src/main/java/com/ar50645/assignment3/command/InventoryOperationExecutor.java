package com.ar50645.assignment3.command;

import com.ar50645.assignment3.inventory.Inventory;

public class InventoryOperationExecutor {

    public boolean executeOperation( Inventory bookInventory, InventoryOperation inventoryOperation) {
        return inventoryOperation.execute(bookInventory);
    }
}
