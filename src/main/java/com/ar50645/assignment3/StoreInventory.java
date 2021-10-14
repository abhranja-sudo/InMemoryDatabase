package com.ar50645.assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreInventory implements Inventory {

    List<Book> bookInventoryCollection = new ArrayList<>();

    @Override
    public boolean addNewBook(Book newBook) {
        boolean isAlreadyPresent = bookInventoryCollection
                .stream()
                .anyMatch(book -> book.equals(newBook));

        if(isAlreadyPresent){
            return false;
        }

        bookInventoryCollection.add(newBook);
        return true;
    }

    @Override
    public boolean sellBook(Book bookToSell) throws EntityNotFoundException {
        for (Book book : bookInventoryCollection) {
            if(book.equals(bookToSell)) {
                book.decrementQuantity();
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
}
