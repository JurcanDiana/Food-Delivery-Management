package data;

import business.BaseProduct;
import business.MenuItem;
import business.Order;
import business.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for Reading and Writing to file
 */
public class FileReaderWriter {

    /**
     * Importing the set of products from csv file
     * @param name name of csv file
     * @return set of products
     * @throws IOException exception
     */
    public static Set<MenuItem> readFromCSV(String name) throws IOException {
        return Files.lines(Paths.get(name))
                .map(x -> x.split(","))
                .map(x -> new BaseProduct(x[0],
                        Float.parseFloat(x[1]),
                        Integer.parseInt(x[2]),
                        Integer.parseInt(x[3]),
                        Integer.parseInt(x[4]),
                        Integer.parseInt(x[5]),
                        Integer.parseInt(x[6])))
                .collect(Collectors.toSet());
    }

    /**
     * Creating bill
     * @param order order
     * @param products list of products from order
     */
    public static void createBill(Order order, List<MenuItem> products) {
        int total = 0;
        for (MenuItem item :
             products) {
            total += item.computePrice();
        }
        try {
            FileWriter fileWriter = new FileWriter("Order-" + order.getOrderID() + ".txt");
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            String toWrite = "Order no." + order.getOrderID() + " from: " + order.getOrderDate() + "\n" + "Client ID: " + order.getClientID() + "\n";
            int index = 1;
            for (MenuItem item :
                 products) {
                toWrite += index + ". " + item.getTitle() + ": " + item.computePrice() + "\n";
                index++;
            }
            toWrite += "\n----------------------------------------------------------------------------------------------------------------------------------------------------------\nTotal price: " + total;
            buffer.write(toWrite);
            toWrite = "";
            buffer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Generating string for report of type 1
     * @param from start hour
     * @param to end hour
     * @param orders orders list
     * @return string which will be written in file
     */
    public static String generateStringReport1(int from, int to, List<Order> orders) {
        String toRreturn = "Orders between " + from + " and " + to + ", regardless the date:\n\n";
        for(Order o : orders) {
            toRreturn += "Order ID: " + o.getOrderID() + " Client ID: " + o.getClientID() + " Date And Time: " + o.getOrderDate() + "\n";
        }
        return toRreturn;
    }

    /**
     * Generating string for report of type 2
     * @param noOfTimes minimum no of ordered time
     * @param menuItems products list
     * @return string which will be written in file
     */
    public static String generateStringReport2(int noOfTimes, List<MenuItem> menuItems) {
        String toRreturn = "Products ordered more than " + noOfTimes + " times so far:\n\n";
        for(MenuItem i : menuItems) {
            toRreturn += "Name: " + i.getTitle() + " Price: " + i.computePrice() + "\n";
        }
        return toRreturn;
    }

    /**
     * Generating string for report of type 3
     * @param noOfTimes no of ordered time
     * @param amount minimum value of order
     * @param users list of users (clients)
     * @return string which will be written in file
     */
    public static String generateStringReport3(int noOfTimes, int amount, List<User> users) {
        String toReturn = "Clients who have ordered more than " + noOfTimes
                + " times and the order value was higher than " + amount + ":\n\n";
        for(User u : users) {
            toReturn += "ID: " + u.getId() + " Username: " + u.getUsername() + "\n";
        }
        return toReturn;
    }

    /**
     * Generating string for report of type 4
     * @param date order date
     * @param map pairs of (ordered times, product)
     * @return string which will be written in file
     */
    public static String generateStringReport4(LocalDate date, HashMap<Long, MenuItem> map) {
        String toReturn = "Products ordered on " + date + ":\n\n";
        for(Long i : map.keySet()) {
            toReturn += "Name: " + map.get(i) + " (" + i + " times)\n";
        }
        return toReturn;
    }

    /**
     * Generating reports .txt
     * @param toWrite string which will be written in file
     * @param type type of report
     */
    public static void generateReport(String toWrite, int type) {
        try {
            String pattern = "MM-dd-yyy-HH-mm-ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            FileWriter fileWriter = new FileWriter("Report-type-" + type + "-" + date + ".txt");
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            buffer.write(toWrite);
            buffer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
