package com.ar50645.assignment3.inventory;

import com.ar50645.assignment3.exception.EntityNotFoundException;

public interface Inventory {

    boolean addNewBook(Book newBook);

    boolean sellBook(Book bookToSell) throws EntityNotFoundException;

    boolean addCopy(Book book, int quantity) throws EntityNotFoundException;

    boolean changePrice(Book book, double newPrice) throws EntityNotFoundException;

    boolean isBookAvailable(Book book);

    double findPriceByName(String bookName) throws EntityNotFoundException;

    double findPriceByID(int id) throws EntityNotFoundException;

    double findQuantityByName(String bookName) throws EntityNotFoundException;

    double findQuantityByID(int id) throws EntityNotFoundException;



}
