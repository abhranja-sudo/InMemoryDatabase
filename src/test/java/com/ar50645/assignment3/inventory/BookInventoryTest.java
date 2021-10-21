package com.ar50645.assignment3.inventory;

import com.ar50645.assignment3.exception.EntityNotFoundException;
import com.ar50645.assignment3.fileio.FileOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class BookInventoryTest {

    private Inventory bookInventory;
    private static final int intervalForBackup = 10;
    private static final String BOOK_LIST_MEMENTO_FILENAME = "BookListMemento.ser";
    private FileOperation mementoReadWrite;

    @BeforeEach
    void setUp() {
        FileOperation.clearFile(BOOK_LIST_MEMENTO_FILENAME);
        mementoReadWrite = new FileOperation(BOOK_LIST_MEMENTO_FILENAME);
        bookInventory = new BookInventory();
    }

    @Test
    void addNewBookWhenBookIsValid() {
        List<Book> validTestData = getValidTestData();
        for (Book book : validTestData) {
            Assertions.assertTrue(bookInventory.addNewBook(book));
        }

        //check if the added book is present in inventory
        for (Book book : getValidTestData()) {
            Assertions.assertTrue(bookInventory.isBookAvailable(book));
        }
    }

    @Test
    void addNewBookWhenBookAlreadyExists() {

        Book testData = new Book("testBook", 45, 2);
        bookInventory.addNewBook(testData);

        //Addition of Duplicate book in inventory should return false
        Assertions.assertFalse(bookInventory.addNewBook(testData));
    }

    @Test
    void addNewBookWhenQuantityLessThanOne() {

        Book testData = new Book("testBook", 45, 0);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bookInventory.addNewBook(testData));
    }

    @Test
    void sellBookWhenQuantityGreaterThanOne() {

        Book testData = new Book("testBook", 45, 2);
        double expectedQuantity = testData.getQuantity() - 1;

        bookInventory.addNewBook(testData);

        Assertions.assertTrue(bookInventory.sellBook(testData));

        double actualQuantity = bookInventory.findQuantityByName(testData.getName());
        Assertions.assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    void sellBookWhenQuantityEqualToOne() {

        Book testData = new Book("testBook", 45, 1);
        bookInventory.addNewBook(testData);

        Assertions.assertTrue(bookInventory.sellBook(testData));

        //sold book shouldn't be present in the inventory since the only copy is sold
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> bookInventory.findQuantityByName(testData.getName()));
    }

    @Test
    void sellBookWhenBookNotExist() {

        Book testData = new Book("testBook", 45.3, 3);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> bookInventory.sellBook(testData));
    }

    @Test
    void addCopyWhenBookExists() {

        Book testData = new Book("testBook", 45, 3);
        int quantityToAdd = 3;
        int expectedQuantity = testData.getQuantity() + quantityToAdd;

        bookInventory.addNewBook(testData);

        Assertions.assertTrue(bookInventory.addCopy(testData, quantityToAdd));

        double actualQuantity = bookInventory.findQuantityByName(testData.getName());
        Assertions.assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    void addCopyWhenBookNotExist() {

        int quantityToAdd = 3;
        Book testData = new Book("testBook", 45.3, 3);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> bookInventory.addCopy(testData, quantityToAdd));

    }

    @Test
    void changePriceWhenBookExists() {

        Book testData = new Book("testBook", 45.3, 3);
        double newPrice = 88.3;

        bookInventory.addNewBook(testData);
        Assertions.assertTrue(bookInventory.changePrice(testData, newPrice));

        double actualQuantity = bookInventory.findPriceByName(testData.getName());
        Assertions.assertEquals(newPrice, actualQuantity);
    }

    @Test
    void changePriceWhenBookNotExist() {

        Book testData = new Book("testBook", 45.3, 3);
        double newPrice = 88.3;

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> bookInventory.changePrice(testData, newPrice));

    }

    @Test
    void testMementoCreation() {
        List<Book> validTestData = getValidTestData();

        //Adding new book till it's time for creating memento
        for(int i = 0; i < intervalForBackup; i++) {
            bookInventory.addNewBook(validTestData.get(i));
        }

        //Memento should have saved on file
        BookListState bookListMementoFromFile = (BookListState) mementoReadWrite.readNext();
        List<Book> actualBookList = bookListMementoFromFile.getBookInventoryCollection();
        List<Book> expectedData = validTestData;

        for(int i = 0; i < intervalForBackup; i++) {
            Assertions.assertEquals(expectedData.get(i), actualBookList.get(i));
        }

        //test if the creation of new BookInventory object, loads correct state from backup if memento is available in file
        BookInventory newBookInventory = new BookInventory();
        actualBookList = newBookInventory.getBookList().getBookList();

        for(int i = 0; i < intervalForBackup; i++) {
            Assertions.assertEquals(expectedData.get(i), actualBookList.get(i));
        }
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