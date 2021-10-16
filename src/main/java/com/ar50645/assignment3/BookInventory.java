package com.ar50645.assignment3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookInventory implements Inventory {

    //Singleton
    private static Inventory bookInventory;

    private BookInventory() {
    }

    public static Inventory getBookInventory() {
        if(bookInventory == null) {
            bookInventory = new BookInventory();
        }
        return bookInventory;
    }

    List<Book> bookInventoryCollection = new ArrayList<>();

    @Override
    public boolean addNewBook(Book newBook) {
        boolean isAlreadyPresent = bookInventoryCollection
                .stream()
                .anyMatch(book -> book.equals(newBook));

        if(isAlreadyPresent){
            return false;
        }

        if(newBook.getQuantity() < 1)
            throw new IllegalArgumentException("Book should have at least one quantity");

        bookInventoryCollection.add(newBook);
        return true;
    }

    @Override
    public boolean sellBook(Book bookToSell) throws EntityNotFoundException {
        for (Book book : bookInventoryCollection) {
            if(book.equals(bookToSell)) {
                book.decrementQuantity();
                if(book.getQuantity() < 1) {
                    bookInventoryCollection.remove(book);
                }
                return true;
            }
        }
        throw new EntityNotFoundException("Entity not found");
    }

    @Override
    public boolean addCopy(Book book) throws EntityNotFoundException {
        return false;
    }

    @Override
    public boolean changePrice(Book book) throws EntityNotFoundException {
        return false;
    }

    @Override
    public double findPriceByName(String bookName) throws EntityNotFoundException {
        return 0;
    }

    @Override
    public double findPriceByID(int id) throws EntityNotFoundException {
        return 0;
    }

    @Override
    public double findQuantityByName(String bookName) throws EntityNotFoundException {
        return 0;
    }

    @Override
    public double findQuantityByID(int id) throws EntityNotFoundException {
        return 0;
    }

    public void restore(BookInventoryState InventoryMemento) {
        if(InventoryMemento != null) {
            List<Book> bookInventory = new ArrayList<>();
            for(Book book : InventoryMemento.getBookInventoryCollection()) {
                try {
                    bookInventory.add(book.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            bookInventoryCollection = bookInventory;
        }
    }

    public BookInventoryState saveMemento() {
        List<Book> bookInventoryCopy = new ArrayList<>();
        for(Book book : bookInventoryCollection) {
            try {
                bookInventoryCopy.add(book.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return new BookInventoryState(bookInventoryCopy);
    }
}
