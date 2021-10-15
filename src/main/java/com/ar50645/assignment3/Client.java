package com.ar50645.assignment3;

import com.ar50645.assignment3.Decorator.InventoryDecorator;
import com.ar50645.assignment3.command.InventoryOperation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Inventory inventory = new InventoryDecorator();
        Book b1 = new Book("book1", 88,3);
        Book b2 = new Book("book2", 98,4);
        Book b3 = new Book("book3", 108,6);

//        inventory.addNewBook(b1);
//        inventory.addNewBook(b2);
//        inventory.addNewBook(b3);

//        inventory.sellBook(b1);
//        inventory.sellBook(b1);
//        inventory.sellBook(b2);

        ObjectReadWrite is = new ObjectReadWrite("Command.ser");
//        is.clearFile();


//        read file

        InventoryOperation o = (InventoryOperation) is.readNext();
        while (o != null) {
            System.out.println(o);
            o = (InventoryOperation) is.readNext();
        }

    }
}
