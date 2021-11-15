**In-Memory Database with Persistence**

1. We created an in-memory database for a bookstore inventory. The bookstore story sells
books. Each book has a name, price, unique id and a quantity. The store uses sequential
integers for unique ids. We need to be able to
* Add new books.
* Sell a book in the inventory.
* Add new copies of existing books
* Change the price of a book
* Find the price and/or quantity of a book by either name or id.

The problem with Inventory class as a database is that it does not persist. Once your program
stops running all data is lost.

2.  Used the ***Memento Pattern*** to copy the data in an Inventory object. This will be helpful when we need to restore the state after program restarts.
Made the memento serializable so it can be saved in a file. Given an Inventory object and a memento we can
restore the Inventory object to a previous state. 

3. For each operation that changes the state of the Inventory object, we used ***Command Pattern*** to create, execute a command. Made
the commands serializable. we don't serialize the Inventory object each time we serialize a command. Now every time we perform an operation on an Inventory object,
we can create a command, perform the command and save the command to disk. This way we will have a history of all the
operations. If our program were to crash we can recover the last state by first loading the last
*memento* and then replaying all the *commands* done since the last memento was created. 
Since the commands will always be small, which is not the case with the Inventory object, saving it to disk each time will not be very expensive. 
After a while the number of commands may get very large. When this happens we are creating a new memento, save it to disk and remove
the old commands.

4. Used a ***Decorator Pattern*** for every operation that changes the Inventory object's state the decorator will create the command, perform the command and
save the command to a file.
However having to create those commands each time we want to perform an operation can be
annoying. The proxy is making this transparent to the client code.
