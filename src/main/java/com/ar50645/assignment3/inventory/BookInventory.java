package com.ar50645.assignment3.inventory;

import com.ar50645.assignment3.exception.EntityNotFoundException;
import com.ar50645.assignment3.fileio.FileOperation;


public class BookInventory implements Inventory {

    private static final int intervalForBackup = 3;
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


    public static Inventory getBookInventory() {
        return new BookInventory();
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
            throw new IllegalArgumentException("Book should have at least one quantity");

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

    public BookList getBookList() {
        return bookList;
    }

}
