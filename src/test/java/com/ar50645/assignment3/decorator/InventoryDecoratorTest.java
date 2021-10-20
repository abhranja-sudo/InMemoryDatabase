package com.ar50645.assignment3.decorator;

import com.ar50645.assignment3.command.*;
import com.ar50645.assignment3.fileio.FileOperation;
import com.ar50645.assignment3.inventory.Book;
import com.ar50645.assignment3.inventory.BookInventory;
import com.ar50645.assignment3.inventory.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class InventoryDecoratorTest {

    private final String COMMAND_OUT_FILE =  "Command.ser";
    private FileOperation commandReadWrite;
    private Inventory inventoryDecorator;

    @BeforeEach
    void setUp() {
        FileOperation.clearFile(COMMAND_OUT_FILE);
        commandReadWrite = new FileOperation(COMMAND_OUT_FILE);
        inventoryDecorator = new InventoryDecorator(new BookInventory());
    }

    @Test
    void testCommandPersistence() {

        Book testData = new Book("testBook", 45, 3);
        List<InventoryOperation> expectedList = new ArrayList<>() ;

        inventoryDecorator.addNewBook(testData);
        expectedList.add(new AddBookOperation(testData));

        inventoryDecorator.sellBook(testData);
        expectedList.add(new SellBookOperation(testData));

        int quantityToAdd = 4;
        inventoryDecorator.addCopy(testData, quantityToAdd);
        expectedList.add(new AddCopyOperation(testData, quantityToAdd));

        double newPrice = 33;
        inventoryDecorator.changePrice(testData, newPrice);
        expectedList.add(new ChangePriceOperation(testData, newPrice));

        List<InventoryOperation> actualList = new ArrayList<>();

        //get command List from file
        InventoryOperation command = (InventoryOperation) commandReadWrite.readNext();
        while (command != null) {
            actualList.add(command);
            command = (InventoryOperation) commandReadWrite.readNext();
        }

        Assertions.assertEquals(expectedList, actualList);

    }

    @Test
    void addNewBook() {
        List<Book> validTestData = getValidTestData();

        for (Book book : validTestData) {
            Assertions.assertTrue(inventoryDecorator.addNewBook(book));
        }

        //check if the command has been saved to COMMAND_OUT_FILE
        List<InventoryOperation> expectedList = validTestData.stream()
                .map(AddBookOperation::new)
                .collect(Collectors.toList());

        List<InventoryOperation> actualList = new ArrayList<>();

        //get command List from file
        InventoryOperation command = (InventoryOperation) commandReadWrite.readNext();
        while (command != null) {
            actualList.add(command);
            command = (InventoryOperation) commandReadWrite.readNext();
        }

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void sellBook() {

        Book testData = new Book("testBook", 45, 2);
        double expectedQuantity = testData.getQuantity() - 1;

        inventoryDecorator.addNewBook(testData);
        Assertions.assertTrue(inventoryDecorator.sellBook(testData));

        double actualQuantity = inventoryDecorator.findQuantityByName(testData.getName());
        Assertions.assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    void addCopy() {
        Book testData = new Book("testBook", 45, 3);
        int quantityToAdd = 3;
        int expectedQuantity = testData.getQuantity() + quantityToAdd;

        inventoryDecorator.addNewBook(testData);

        Assertions.assertTrue(inventoryDecorator.addCopy(testData, quantityToAdd));

        double actualQuantity = inventoryDecorator.findQuantityByName(testData.getName());
        Assertions.assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    void changePrice() {

        Book testData = new Book("testBook", 45.3, 3);
        double newPrice = 88.3;

        inventoryDecorator.addNewBook(testData);
        Assertions.assertTrue(inventoryDecorator.changePrice(testData, newPrice));

        double actualQuantity = inventoryDecorator.findPriceByName(testData.getName());
        Assertions.assertEquals(newPrice, actualQuantity);
    }

    private List<Book> getValidTestData() {

        List<Book> bookList = new ArrayList<>();

        bookList.add(new Book("book1", 88,3));
        bookList.add(new Book("book2", 87,2));
        bookList.add(new Book("book3", 188.3,1));
        bookList.add(new Book("book4", 818.3,4));
        bookList.add(new Book("book5", 882.3,5));
        bookList.add(new Book("book6", 234.3,9));
        bookList.add(new Book("book7", 831.3,11));
        bookList.add(new Book("book8", 238.3,2));
        bookList.add(new Book("book9", 342.3,93));
        bookList.add(new Book("book10", 322.3,2));
        bookList.add(new Book("book11", 441.3,12));
        bookList.add(new Book("book12", 42.3,12));

        return bookList;
    }

}