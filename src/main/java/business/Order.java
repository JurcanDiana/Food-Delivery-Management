package business;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * Order Class
 */
public class Order implements Serializable, Comparable<Order> {
    
    private int clientID;
    private int orderID;
    private Date orderDate;

    /**
     * Constructor
     * @param clientID client ID
     * @param orderID order ID
     */
    public Order(int clientID, int orderID) {
        this.clientID = clientID;
        this.orderID = orderID;
        this.orderDate = new Date();
    }

    /**
     * Getters
     */
    public int getClientID() {
        return clientID;
    }
    
    public int getOrderID() {
        return orderID;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Determines order hour
     * @return hour the order was placed
     */
    public int getOrderHour() {
        return this.orderDate.getHours();
    }

    /**
     * Conversion to format LocalDate
     * @return data in format LocalDate
     */
    public LocalDate convertDateToLocalDate() {
        return this.orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Determines order day
     * @return day the order was placed
     */
    public int getOrderDay() {
        return convertDateToLocalDate().getDayOfMonth();
    }

    /**
     * Determines order month
     * @return month the order was placed
     */
    public int getOrderMonth() {
        return convertDateToLocalDate().getMonthValue();
    }

    /**
     * Determines order year
     * @return year the order was placed
     */
    public int getOrderYear() {
        return convertDateToLocalDate().getYear();
    }

    /**
     * Creating a date pattern
     * @return pattern for date
     */
    public String stringOrderDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(this.orderDate);
        return strDate;
    }

    /**
     * Overriding equals()
     * @param o object used to compare with
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return clientID == order.clientID && orderID == order.orderID && orderDate.equals(order.orderDate);
    }

    /**
     * Suprasciere hashCode()
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(clientID, orderID, orderDate);
    }

    /**
     * Overriding toString()
     * @return string containing order info
     */
    @Override
    public String toString() {
        return "Order {" +
                "clientID=" + clientID +
                ", orderID=" + orderID +
                ", orderDate=" + orderDate +
                "}\n";
    }

    /**
     * Overriding compareTO() by orderID
     * @param o object used to compare with
     * @return -1, 0, 1
     */
    @Override
    public int compareTo(Order o) {
        Integer id = orderID;
        Integer idO = o.orderID;
        return id.compareTo(idO);
    }
}
