public interface Inventory {

    boolean addNewBook(Book newBook);

    boolean sellBook(Book bookToSell) throws EntityNotFoundException;

    boolean addCopy(Book book) throws EntityNotFoundException;

    boolean changePrice(Book book) throws EntityNotFoundException;

    double findPriceByName(String bookName) throws EntityNotFoundException;

    double findPriceByID(int id) throws EntityNotFoundException;

    double findQuantityByName(String bookName) throws EntityNotFoundException;

    double findQuantityByID(int id) throws EntityNotFoundException;

}
