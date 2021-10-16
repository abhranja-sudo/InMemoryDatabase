package com.ar50645.assignment3;

/**
 * Singleton implementation for BookInventory.
 * Also act as CareTaker for BookList Memento
 */
public class BookInventory implements Inventory {

    private int intervalForBackup = 3;
    private int counterForBackUp = 0;
    private static final String BOOK_LIST_MEMENTO_FILENAME = "BookListMemento.ser";
    private BookList bookList = new BookList();

    //load BookList memento if available
    {
        ObjectReadWrite objectReadWrite = new ObjectReadWrite(BOOK_LIST_MEMENTO_FILENAME);
        BookListState state = (BookListState) objectReadWrite.readNext();
        if(state != null) {
            bookList.restore(state);
        }
        else {
            bookList = new BookList();
        }
    }

    private static Inventory bookInventory;

    private BookInventory() {
    }

    public static Inventory getBookInventory() {
        if(bookInventory == null) {
            bookInventory = new BookInventory();
        }
        return bookInventory;
    }

    private void checkBackup() {
        counterForBackUp++;
        if(counterForBackUp == intervalForBackup) {
            ObjectReadWrite.clearFile(BOOK_LIST_MEMENTO_FILENAME);
            ObjectReadWrite objectReadWrite = new ObjectReadWrite(BOOK_LIST_MEMENTO_FILENAME);
            BookListState state = bookList.saveMemento();
            objectReadWrite.writeObject(state);

            //resets counter
            counterForBackUp = 0;
        }
    }

    @Override
    public boolean addNewBook(Book newBook) {
        if(bookList.contains(newBook)) {
            return false;
        }
        if(newBook.getQuantity() < 1)
            throw new IllegalArgumentException("Book should have at least one quantity");

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

}
