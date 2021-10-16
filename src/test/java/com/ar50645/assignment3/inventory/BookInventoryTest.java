package com.ar50645.assignment3.inventory;

import com.ar50645.assignment3.fileio.ObjectReadWrite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class BookInventoryTest {

    private Inventory bookInventory;
    private static final int intervalForBackup = 3;
    private int counter = 0;
    private static final String BOOK_LIST_MEMENTO_FILENAME = "BookListMemento.ser";
    private ObjectReadWrite mementoReadWrite;

    @BeforeEach
    void setUp() {
        ObjectReadWrite.clearFile(BOOK_LIST_MEMENTO_FILENAME);
        mementoReadWrite = new ObjectReadWrite(BOOK_LIST_MEMENTO_FILENAME);
        bookInventory = BookInventory.getBookInventory();
    }

    @Test
    void addNewBook() {

        List<Book> validTestData = getValidTestData();
        for(Book book: validTestData) {
            Assertions.assertTrue(bookInventory.addNewBook(book));
        }

        //Addition of Duplicate book in inventory will return false
        Assertions.assertFalse(bookInventory.addNewBook(validTestData.get(0)));

        //Duplicate Addition will throw IllegalArgumentException
        Book bookWithInvalidQuantity = new Book("Book", 88.8,0);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bookInventory.addNewBook(bookWithInvalidQuantity));
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