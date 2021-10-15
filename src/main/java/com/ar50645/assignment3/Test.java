package com.ar50645.assignment3;

import com.ar50645.assignment3.command.InventoryOperation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Book b1 = new Book("book1", 88,3);
        Book b2 = new Book("book2", 98,4);
        Book b3 = new Book("book3", 99,2);
        Book b4 = new Book("book4", 78,3);
        Book b5 = new Book("book5", 77,4);
        Book b6 = new Book("book6", 79,2);

//
//        try {
//            FileOutputStream fileOut =
//                    new FileOutputStream("employee.ser");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(b1);
//            out.writeObject(b2);
//            out.writeObject(b3);
//
////            out.close();
////            fileOut.close();
//            System.out.printf("Serialized data is saved in /tmp/employee.ser");
//        } catch (IOException i) {
//            i.printStackTrace();
//        }



//        List<Book> en = null;


//        try {
//            FileInputStream fileIn = new FileInputStream("employee.ser");
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            Book b11 = (Book) in.readObject();
//            Book b12 = (Book) in.readObject();
//            Book b13 = (Book) in.readObject();
//
//            System.out.println(b11);
//            System.out.println(b12);
//            System.out.println(b13);
//
////            en = (ArrayList<Book>) in.readObject();
////            System.out.println(en);
////            in.close();
////            fileIn.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//            return;
//        } catch (ClassNotFoundException c) {
//            System.out.println("Employee class not found");
//            c.printStackTrace();
//            return;
//        }


//        try (ObjectOutputStream os1 = new ObjectOutputStream(new FileOutputStream("test"))) {
//            os1.writeObject(b1);
//            os1.close();
//        }
//
//        ObjectOutputStream os2 = new ObjectOutputStream(new FileOutputStream("test", true)) {
//            protected void writeStreamHeader() throws IOException {
//                reset();
//            }
//        };
//        os2.writeObject(b1);
//        os2.writeObject(b2);
//        os2.writeObject(b3);
//
//        os2.close();


//        ObjectReadWrite is = new ObjectReadWrite("test");
//        is.clearTheFile();

        //read file
        ObjectReadWrite is = new ObjectReadWrite("Command.ser");
        InventoryOperation o = (InventoryOperation) is.readNext();
        while (o != null) {
            System.out.println(o);
            o = (InventoryOperation) is.readNext();
        }
//        System.out.println(is.readObject());
//        System.out.println(is.readObject());
//        System.out.println(is.readObject());
//        System.out.println(is.readObject());
//        System.out.println(is.readObject());
//        System.out.println(is.readObject());
//        System.out.println(is.readObject());
//
//        System.out.println(is.readObject());
//        System.out.println(is.readObject());
//        System.out.println(is.readObject());
//        System.out.println(is.readObject());




//        ObjectReadWrite readWrite = new ObjectReadWrite("test");
////        readWrite.writeObject(b1);
//        readWrite.writeObject(b2);
//
//        readWrite.writeObject(b3);
//        readWrite.writeObject(b4);
//        readWrite.writeObject(b5);
//        readWrite.writeObject(b6);
//


    }
}
