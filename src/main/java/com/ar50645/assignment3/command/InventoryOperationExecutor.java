package com.ar50645.assignment3.command;

public class InventoryOperationExecutor {

    public boolean executeOperation(InventoryOperation inventoryOperation) {
        return inventoryOperation.execute();
    }
}
