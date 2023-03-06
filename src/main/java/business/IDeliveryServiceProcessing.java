package business;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Interface for describing the possible functionalities of users of type Admin and Client
 */
public interface IDeliveryServiceProcessing {
    /**
     * Reading products from csv file
     * @param name name of file
     * @pre name != null
     * @post usersSet.size() == usersSet.size()@pre and placedOrders.size() == placedOrders.size()@pre
     * @inv isWellFormed()
     */
    void readProductsFromCSV(String name);

    /**
     * Adds new product
     * @param item product to add
     * @pre item != null
     * @post menuItemSet.size() == menuItemSet.size()@pre + 1 and menuItemSet.contains(item) and placedOrders.size() == placedOrders.size()@pre and usersSet.size() == usersSet.size()@pre
     * @inv isWellFormed()
     */
    void addProduct(MenuItem item);

    /**
     * Deletes exiting product
     * @param item product to delete
     * @pre item != null and menuItemSet.size() != 0
     * @post menuItemSet.size() == menuItemSet.size()@pre - 1 and !menuItemSet.contains(item) and placedOrders.size() == placedOrders.size()@pre and usersSet.size() == usersSet.size()@pre
     * @inv isWellFormed()
     */
    void deleteProduct(MenuItem item);

    /**
     * Updating of an exiting product
     * @param item product to update
     * @pre item != null
     * @post menuItemSet.size() == menuItemSet.size()@pre and placedOrders.size() == placedOrders.size()@pre and usersSet.size() == usersSet.size()@pre
     * @inv isWellFormed()
     */
    void updateProduct(MenuItem item);

    /**
     * Filtering the products according to different criteria
     * @param keyword key word
     * @param rating rating
     * @param calories calorie
     * @param protein protein
     * @param fat fat
     * @param sodium sodium
     * @param price price
     * @return set of products that fulfill the criteria chosen
     * @pre !menuItemSet.isEmpty()
     * @post menuItemSet.size() == menuItemSet.size()@pre and placedOrders.size() == placedOrders.size()@pre and usersSet.size() == usersSet.size()@pre
     * @inv isWellFormed()
     */
    Set<MenuItem> filterProducts(String keyword, float rating, int calories, int protein, int fat, int sodium, int price);

    /**
     * Registering of a new client
     * @param username name of user
     * @param password password
     * @param type type of user
     * @return registered user
     * @pre username != null and password != null and type = UserType.CLIENT
     * @post menuItemSet.size() == menuItemSet.size()@pre and placedOrders.size() == placedOrders.size()@pre and usersSet.size() == usersSet.size()@pre + 1
     * @inv isWellFormed()
     */
    User registerNewClient(String username, String password, UserType type);

    /**
     * Login of the user
     * @param username name of user
     * @return logged in user
     * @pre username != null
     * @post menuItemSet.size() == menuItemSet.size()@pre and placedOrders.size() == placedOrders.size()@pre and usersSet.size() == usersSet.size()@pre
     * @inv isWellFormed()
     */
    User logInUser(String username);

    /**
     * Registering a new order
     * @param user user that creates the order
     * @param products list of ordered products
     * @pre user != null
     * @post menuItemSet.size() == menuItemSet.size()@pre and placedOrders.size() == placedOrders.size()@pre + 1 and usersSet.size() == usersSet.size()@pre
     * @inv isWellFormed()
     */
    void newOrder(User user, List<MenuItem> products);

    /**
     * Generating report with orders placed in a time interval
     * @param from start hour
     * @param to end hour
     * @return list of orders that satisfy the condition
     * @pre from less than or equal to
     * @post menuItemSet.size() == menuItemSet.size()@pre and placedOrders.size() == placedOrders.size()@pre and usersSet.size() == usersSet.size()@pre
     * @inv isWellFormed()
     */
    List<Order> reportOrders(int from, int to);

    /**
     * Generating report with products ordered more times than the specified times
     * @param noOfTimes minimum no of times
     * @return list of orders that satisfy the condition
     * @pre noOfTimes > 0
     * @post menuItemSet.size() == menuItemSet.size()@pre and placedOrders.size() == placedOrders.size()@pre and usersSet.size() == usersSet.size()@pre
     * @inv isWellFormed()
     */
    List<MenuItem> reportProducts(int noOfTimes);

    /**
     * Generating report with clients that ordered more than a specified nr of times, and the value of the order is greater than the specified value
     * @param noOfTimes minimum no of times
     * @param amount minimum amount of that order
     * @return list of orders that satisfy the condition
     * @pre noOfTimes > 0 and amount > 0
     * @post menuItemSet.size() == menuItemSet.size()@pre and placedOrders.size() == placedOrders.size()@pre and usersSet.size() == usersSet.size()@pre
     * @inv isWellFormed()
     */
    List<User> reportClients(int noOfTimes, int amount);

    /**
     * Generating report with ordered products from a given date and the number of requests
     * @param date data
     * @return pairs
     * @pre date != null
     * @post menuItemSet.size() == menuItemSet.size()@pre and placedOrders.size() == placedOrders.size()@pre and usersSet.size() == usersSet.size()@pre
     * @inv isWellFormed()
     */
    HashMap<Long, MenuItem> reportProductsDay(LocalDate date);
}
