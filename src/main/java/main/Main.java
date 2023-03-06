package main;

import business.CompositeProduct;
import business.DeliveryService;
import presentation.LogInGUI;

import java.util.Arrays;

/**
 * Main Class
 */
public class Main {

    public static void main(String[] args) {

        DeliveryService deliveryService = new DeliveryService();

        System.out.println(deliveryService.getUsersSet() + "\n");

        CompositeProduct cp = new CompositeProduct("1");
        cp.addProduct(deliveryService.findByName("Italian Chicken Salad "));
        cp.addProduct(deliveryService.findByName("Mango and Red Onion Salsa "));

        System.out.println(cp);
        deliveryService.addProduct(cp);

        System.out.println(deliveryService.findByName("1"));

        LogInGUI logInGUI = new LogInGUI(deliveryService);

        System.out.println(deliveryService.getPlacedOrders());
    }
}
