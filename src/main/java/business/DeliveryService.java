package business;

import data.FileReaderWriter;
import data.Serializator;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Delivery Service Class for implementing the functionalities of IDeliveryServiceProcessing interface
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing {

    private Set<MenuItem> menuItemSet;
    private Map<Order, Collection<MenuItem>> placedOrders;
    private Set<User> usersSet;

    public DeliveryService() {
        try {
            deserializeAll();
            setChanged();
            notifyObservers(placedOrders);
        } catch (ClassNotFoundException | IOException e){
            this.menuItemSet = new HashSet<MenuItem>();
            this.placedOrders = new HashMap<>();
            this.usersSet = new HashSet<>();
            addInitialUsers();
            readProductsFromCSV("products.csv");
            System.out.println("deserialize failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Set<MenuItem> getMenuItemSet() {
        return menuItemSet;
    }

    public Map<Order, Collection<MenuItem>> getPlacedOrders() {
        return placedOrders;
    }

    public Set<User> getUsersSet() {
        return usersSet;
    }

    /**
     * Adding the initial set of users (admin and employee)
     */
    public void addInitialUsers() {
        User administrator = new User(0, "Administrator 1", "admin1", UserType.ADMINISTRATOR);
        User employee = new User(1, "Employee 1", "employee1", UserType.REGULAR_EMPLOYEE);
        usersSet.add(administrator);
        usersSet.add(employee);
    }

    @Override
    public User registerNewClient(String username, String password, UserType type) {
        assert isWellFormed() && username != null && password != null && type == UserType.CLIENT;
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();
        User u = new User(this.usersSet.size(), username, password, type);
        this.usersSet.add(u);
        assert usersSet.size() == uSizePre + 1 && menuItemSet.size() == mSizePre && placedOrders.size() == oSizePre && isWellFormed();
        return u;
    }

    /**
     * Searching of a product by name
     * @param name searched name
     * @return product that was found
     */
    public MenuItem findByName(String name) {

        MenuItem item = this.menuItemSet.stream()
                .filter(menuItem -> menuItem.getTitle().equals(name))
                .findAny()
                .orElse(null);

        return item;
    }

    @Override
    public User logInUser(String username) {
        assert isWellFormed() && username != null;
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();

        User user = this.usersSet.stream()
                .filter(user1 -> user1.getUsername().equals(username))
                .findAny()
                .orElse(null);

        assert usersSet.size() == uSizePre && menuItemSet.size() == mSizePre && placedOrders.size() == oSizePre && isWellFormed();
        return user;
    }

    /**
     * Serialization of all info
     */
    public void serializeAll() {
        Serializator<Set<MenuItem>> menuItemSerializator = new Serializator<Set<MenuItem>>();
        Serializator<Map<Order, Collection<MenuItem>>> orderSerializator = new Serializator<Map<Order, Collection<MenuItem>>>();
        Serializator<Set<User>> userSerializator = new Serializator<Set<User>>();
        try {
            menuItemSerializator.serializeObject("menu.txt", menuItemSet);
            orderSerializator.serializeObject("orders.txt", placedOrders);
            userSerializator.serializeObject("users.txt", usersSet);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Deserialization of all info
     * @throws ClassNotFoundException exception
     * @throws IOException exception
     */
    public void deserializeAll() throws ClassNotFoundException, IOException {
        Serializator<Set<MenuItem>> menuItemSerializator = new Serializator<Set<MenuItem>>();
        Serializator<Map<Order, Collection<MenuItem>>> orderSerializator = new Serializator<Map<Order, Collection<MenuItem>>>();
        Serializator<Set<User>> userSerializator = new Serializator<Set<User>>();
        this.menuItemSet = menuItemSerializator.deserializeObject("menu.txt");
        this.placedOrders = orderSerializator.deserializeObject("orders.txt");
        this.usersSet = userSerializator.deserializeObject("users.txt");
    }

    @Override
    public Set<MenuItem> filterProducts(String keyword, float rating, int calories, int protein, int fat, int sodium, int price) {
        assert isWellFormed() && !menuItemSet.isEmpty();
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();

        Set<MenuItem> setOfSelected = this.menuItemSet.stream()
                .filter(x1 -> x1.getTitle().toLowerCase().contains(keyword.toLowerCase()) || keyword.equals(""))
                .filter(x2 -> (x2 instanceof BaseProduct && (((BaseProduct) x2).getRating() == rating || rating == 0)) ||
                        (x2 instanceof CompositeProduct && (((CompositeProduct) x2).getRating() == rating || rating == 0)))
                .filter(x3 -> (x3 instanceof BaseProduct && (((BaseProduct) x3).getCalories() == calories || calories == 0)) ||
                        (x3 instanceof CompositeProduct && (((CompositeProduct) x3).getCalories() == calories || calories == 0)))
                .filter(x4 -> (x4 instanceof BaseProduct && (((BaseProduct) x4).getProtein() == protein || protein == 0)) ||
                        (x4 instanceof CompositeProduct && (((CompositeProduct) x4).getProtein() == protein || protein == 0)))
                .filter(x5 -> (x5 instanceof BaseProduct && (((BaseProduct) x5).getFat() == fat || fat == 0)) ||
                        (x5 instanceof CompositeProduct && (((CompositeProduct) x5).getFat() == fat || fat == 0)))
                .filter(x6 -> (x6 instanceof BaseProduct && (((BaseProduct) x6).getSodium() == sodium || sodium == 0)) ||
                        (x6 instanceof CompositeProduct && (((CompositeProduct) x6).getSodium() == sodium || sodium == 0)))
                .filter(x7 -> (x7 instanceof BaseProduct && (((BaseProduct) x7).computePrice() == price || price == 0)) ||
                        (x7 instanceof CompositeProduct && (((CompositeProduct) x7).computePrice() == price || price == 0)))
                .collect(Collectors.toSet());

        assert usersSet.size() == uSizePre && menuItemSet.size() == mSizePre && placedOrders.size() == oSizePre && isWellFormed();
        return setOfSelected;
    }

    /**
     * Searching for a user by username
     * @param username username
     * @return user with found username
     */
    public User findUser(String username) {
        for (User u : this.usersSet) {
            if (u.getUsername().equals(username))
                return u;
        }
        return null;
    }

    @Override
    public void newOrder(User user, List<MenuItem> products) {
        assert isWellFormed();
        assert user != null;
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();
        Order order = new Order(user.getId(), placedOrders.size());
        List<MenuItem> addedProducts = new ArrayList<>(products);
        placedOrders.put(order, addedProducts);
        setChanged();
        notifyObservers(placedOrders);
        FileReaderWriter.createBill(order, addedProducts);
        assert usersSet.size() == uSizePre && menuItemSet.size() == mSizePre && placedOrders.size() == oSizePre + 1 && isWellFormed();
    }

    @Override
    public void readProductsFromCSV(String name) {
        assert isWellFormed() && name != null;
        int uSizePre = usersSet.size();
        int oSizePre = placedOrders.size();
        try {
            this.menuItemSet = FileReaderWriter.readFromCSV(name);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        assert usersSet.size() == uSizePre && placedOrders.size() == oSizePre + 1 && isWellFormed();
    }

    @Override
    public void addProduct(MenuItem item) {
        assert isWellFormed() && item != null;
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();
        this.menuItemSet.add(item);
        assert usersSet.size() == uSizePre && menuItemSet.size() == mSizePre + 1 && placedOrders.size() == oSizePre && isWellFormed();
    }

    @Override
    public void deleteProduct(MenuItem item) {
        assert isWellFormed() && item != null && menuItemSet.size() != 0;
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();
        MenuItem toDelete = findByName(item.getTitle());
        if (toDelete == null) {
            return;
        }
        this.menuItemSet.remove(toDelete);
        assert usersSet.size() == uSizePre && menuItemSet.size() == mSizePre - 1 && placedOrders.size() == oSizePre && isWellFormed();
    }

    @Override
    public void updateProduct(MenuItem item) {
        assert isWellFormed() && item != null;
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();
        MenuItem toUpdate = findByName(item.getTitle());
        if (toUpdate == null) {
            return;
        }
        menuItemSet.remove(toUpdate);
        menuItemSet.add(item);
        assert usersSet.size() == uSizePre && menuItemSet.size() == mSizePre && placedOrders.size() == oSizePre && isWellFormed();
    }

    @Override
    public List<Order> reportOrders(int from, int to) {
        assert isWellFormed() && from <= to;
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();

        List<Order> betweenOrders = placedOrders.keySet().stream()
                .filter(x -> x.getOrderHour() >= from && x.getOrderHour() <= to)
                .collect(Collectors.toList());

        assert usersSet.size() == uSizePre && menuItemSet.size() == mSizePre && placedOrders.size() == oSizePre && isWellFormed();
        return betweenOrders;
    }

    @Override
    public List<MenuItem> reportProducts(int noOfTimes) {
        assert isWellFormed();
        assert noOfTimes > 0;
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();

        List<MenuItem> toReturn = menuItemSet.stream()
                .filter(x -> placedOrders.values().stream()
                        .flatMapToLong(list -> LongStream.of(list.stream()
                                .filter(item -> x.getTitle().equals(item.getTitle()))
                                .count()))
                        .sum() >= noOfTimes)
                .collect(Collectors.toList());

        assert usersSet.size() == uSizePre && menuItemSet.size() == mSizePre && placedOrders.size() == oSizePre && isWellFormed();
        return toReturn;
    }

    @Override
    public List<User> reportClients(int noOfTimes, int amount) {
        assert isWellFormed() && noOfTimes > 0 && amount > 0;
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();

        List<User> toReturn = this.usersSet.stream()
                .filter(user -> placedOrders.keySet().stream()
                        .filter(order -> order.getClientID() == user.getId())
                        .filter(order -> placedOrders.get(order).stream()
                            .flatMapToInt(menuItem -> IntStream.of(menuItem.computePrice()))
                        .sum() >= amount)
                .count() >= noOfTimes)
                .collect(Collectors.toList());

        assert usersSet.size() == uSizePre && menuItemSet.size() == mSizePre && placedOrders.size() == oSizePre && isWellFormed();
        return toReturn;
    }

    @Override
    public HashMap<Long, MenuItem> reportProductsDay(LocalDate date) {
        assert isWellFormed() && date != null;
        int uSizePre = usersSet.size(), mSizePre = menuItemSet.size(), oSizePre = placedOrders.size();

        List<Order> orderList = placedOrders.keySet().stream()
                .filter(order -> order.convertDateToLocalDate().equals(date))
                .collect(Collectors.toList());

        HashMap<Long, MenuItem> products = new HashMap<Long, MenuItem>();

        menuItemSet.forEach(menuItem -> {
            long cnt = orderList.stream()
                    .flatMapToLong(order -> LongStream.of(placedOrders.get(order).stream()
                            .filter(item -> menuItem.getTitle().equals(item.getTitle()))
                            .count()))
                    .sum();
            if (cnt > 0)
                products.put(cnt, menuItem);
        });

        assert usersSet.size() == uSizePre && menuItemSet.size() == mSizePre && placedOrders.size() == oSizePre && isWellFormed();
        return products;
    }

    /**
     * Method of type isWellFormed
     * @return boolean value
     */
    public boolean isWellFormed() {
        return (!this.usersSet.isEmpty()
                && this.usersSet.contains(new User(0, "Administrator 1", "admin1", UserType.ADMINISTRATOR))
                && this.usersSet.contains(new User(1, "Employee 1", "employee1", UserType.REGULAR_EMPLOYEE))
                && menuItemSet != null && usersSet.size() >= 2);
    }
}
