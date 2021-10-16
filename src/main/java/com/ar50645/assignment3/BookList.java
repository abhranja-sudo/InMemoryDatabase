package com.ar50645.assignment3;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    private List<Book> bookList;

    public BookList() {
        this.bookList = new ArrayList<>();
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public boolean add(Book newBook) {
        return bookList.add(newBook);
    }

    public boolean contains(Book book) {
        return !bookList.contains(book) ? false : true;
    }

    public void remove(Book book) {
        bookList.remove(book);
    }

    public BookListState saveMemento() {
        List<Book> bookInventoryCopy = new ArrayList<>();
        for(Book book : bookList) {
            try {
                bookInventoryCopy.add(book.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return new BookListState(bookInventoryCopy);
    }

    public void restore(BookListState InventoryMemento) {
        if(InventoryMemento != null) {
            List<Book> bookInventory = new ArrayList<>();
            for(Book book : InventoryMemento.getBookInventoryCollection()) {
                try {
                    bookInventory.add(book.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            bookList = bookInventory;
        }
    }

    @Override
    public String toString() {
        return "BookList{" +
                "bookList=" + bookList +
                '}';
    }
}
