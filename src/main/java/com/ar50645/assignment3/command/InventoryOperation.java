package com.ar50645.assignment3.command;

import com.ar50645.assignment3.inventory.Inventory;

public interface InventoryOperation {
    boolean execute(Inventory inventory);
}

