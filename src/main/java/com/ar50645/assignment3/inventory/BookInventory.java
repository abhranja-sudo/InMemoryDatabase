package com.ar50645.assignment3.inventory;

import com.ar50645.assignment3.exception.EntityNotFoundException;
import com.ar50645.assignment3.fileio.FileOperation;


public class BookInventory implements Inventory {

    private static final int intervalForBackup = 10;
    private int counterForBackUp = 0;
    private static final String BOOK_LIST_MEMENTO_FILENAME = "BookListMemento.ser";
    private static final String BOOK_LIST_MEMENTO_TEMP_FILE = "BookListMementoTemp.ser";

    private final String COMMAND_OUT_FILE =  "Command.ser";
    private BookList bookList = new BookList();

    //load BookList from memento if available
    {
        FileOperation fileOperation = new FileOperation(BOOK_LIST_MEMENTO_FILENAME);
        BookListState state = (BookListState) fileOperation.readNext();
        if(state != null) {
            bookList.restore(state);
        }
        else {
            bookList = new BookList();
        }
    }


    /**
     * If it's time to back up the Memento,
     * 1. copy the original memento to temporary file
     * 2. copy the new memento to BOOK_LIST_MEMENTO_FILENAME
     * 3. once copied, we can clear the temporary and command file
     *
     * The temporary file is used to keep backup until the updated data is safely copied to original file.
     */
    private void checkBackup() {
        counterForBackUp++;
        if(counterForBackUp == intervalForBackup) {

            FileOperation.copyFile(BOOK_LIST_MEMENTO_FILENAME, BOOK_LIST_MEMENTO_TEMP_FILE);
            FileOperation.clearFile(BOOK_LIST_MEMENTO_FILENAME);

            FileOperation fileOperation = new FileOperation(BOOK_LIST_MEMENTO_FILENAME);
            BookListState state = bookList.saveMemento();
            fileOperation.writeObject(state);

            //clear file
            FileOperation.clearFile(BOOK_LIST_MEMENTO_TEMP_FILE);
            FileOperation.clearFile(COMMAND_OUT_FILE);

            //resets counter
            counterForBackUp = 0;
        }
    }

    @Override
    public boolean addNewBook(Book newBook) {

        if(newBook.getQuantity() < 1)
            throw new IllegalArgumentException("Can't insert book with quantity less than one ");

        if(bookList.contains(newBook)) {
            return false;
        }

        bookList.add(newBook);
        checkBackup();
        return true;
    }

    @Override
    public boolean sellBook(Book bookToSell) throws EntityNotFoundException {
        for (Book book : bookList.getBookList()) {
            if(book.equals(bookToSell)) {
                book.decrementQuantity();
                if(book.getQuantity() < 1) {
                    bookList.remove(book);
                }
                checkBackup();
                return true;
            }
        }
        throw new EntityNotFoundException("Book not found");
    }

    @Override
    public boolean addCopy(Book bookToAddCopy, int quantity) throws EntityNotFoundException {
        for (Book book : bookList.getBookList()) {
            if(book.equals(bookToAddCopy)) {
                book.increaseQuantity(quantity);
                checkBackup();
                return true;
            }
        }
        throw new EntityNotFoundException("Book not found");
    }

    @Override
    public boolean changePrice(Book bookToChangePrice, double newPrice) throws EntityNotFoundException {
        for (Book book : bookList.getBookList()) {
            if(book.equals(bookToChangePrice)) {
                book.setPrice(newPrice);
                checkBackup();
                return true;
            }
        }
        throw new EntityNotFoundException("Book not found");
    }

    @Override
    public boolean isBookAvailable(Book book) {
        return bookList.contains(book);
    }

    @Override
    public double findPriceByName(String bookName) throws EntityNotFoundException {
        for (Book book : bookList.getBookList()) {
            if(book.getName().equals(bookName)) {
                return book.getPrice();
            }
        }
        throw new EntityNotFoundException("Book not found");
    }

    @Override
    public double findPriceByID(int id) throws EntityNotFoundException {
        for (Book book : bookList.getBookList()) {
            if(book.getId() == id) {
                return book.getPrice();
            }
        }
        throw new EntityNotFoundException("Book not found");
    }

    @Override
    public double findQuantityByName(String bookName) throws EntityNotFoundException {
        for (Book book : bookList.getBookList()) {
            if(book.getName().equals(bookName)) {
                return book.getQuantity();
            }
        }
        throw new EntityNotFoundException("Book not found");
    }

    @Override
    public double findQuantityByID(int id) throws EntityNotFoundException {
        for (Book book : bookList.getBookList()) {
            if(book.getId() == id) {
                return book.getId();
            }
        }
        throw new EntityNotFoundException("Book not found");
    }

    public BookList getBookList() {
        return bookList;
    }

}
