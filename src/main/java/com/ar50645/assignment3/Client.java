package com.ar50645.assignment3;

import com.ar50645.assignment3.decorator.InventoryDecorator;
import com.ar50645.assignment3.inventory.Book;
import com.ar50645.assignment3.inventory.Inventory;

public class Client {
    public static void main(String[] args) {

        Inventory inventory = new InventoryDecorator();
        Book b1 = new Book("book10", 88,3);
        Book b2 = new Book("book8", 98,4);
//        Book b3 = new Book("book6", 108,6);
////
        inventory.addNewBook(b1);
//        inventory.addNewBook(b2);
//        inventory.addNewBook(b3);

//        List<Book> bookList = new ArrayList<>();
//        bookList.add(b1);
//        bookList.add(b2);
//        bookList.add(b3);
//        ObjectReadWrite is1 = new ObjectReadWrite("BookListMemento.ser");
//        is1.writeObject(new BookListState(bookList));
//        System.out.println(is1.readNext());

//        Object o =  is1.readNext();
//        while (o != null) {
//            System.out.println(o);
//            o =  is1.readNext();
//        }

//        inventory.sellBook(b1);
//        inventory.sellBook(b1);
//        inventory.sellBook(b2);

//        ObjectReadWrite is = new ObjectReadWrite("Command.ser");
//        is.clearFile();


//        read InventoryOperation file

//        InventoryOperation o = (InventoryOperation) is.readNext();
//        while (o != null) {
//            System.out.println(o);
//            o = (InventoryOperation) is.readNext();
//        }

        //read Memento
//                    System.out.println(is1.readNext());

    }
}
