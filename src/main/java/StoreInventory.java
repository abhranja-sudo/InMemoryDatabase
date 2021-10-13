import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreInventory implements Inventory{

    List<Book> bookInventoryCollection = new ArrayList<>();

    @Override
    public boolean addNewBook(Book newBook) {
        long count = bookInventoryCollection
                .stream()
                .filter(book -> book.equals(newBook))
                .count();

        if(count > 1){
            return false;
        }

        bookInventoryCollection.add(newBook);
        return true;
    }

    @Override
    public boolean sellBook(Book bookToSell) throws EntityNotFoundException {

        Optional<Book> optionalBook = bookInventoryCollection
                .stream()
                .findAny();

        if( ! optionalBook.isPresent()) {
            throw new EntityNotFoundException("Entity not found");
        }

        optionalBook.ifPresent(book -> {
                book.decrementQuantity();
                if(book.getQuantity() == 0) {
                    bookInventoryCollection.remove(bookToSell);
                }
            });
        return true;
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
