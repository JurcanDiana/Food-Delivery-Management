# Food-Delivery-Management

This project is a GUI application developed using Java Swing, simulating a food delivery management system with three types of users: administrator, employee and client. The users can log in using a username and a password.

The administrator will import the first set of products from a .csv file to populate the menu. Manage of the products is done using add, delete, modify of a product, or create new composed products from existing base products. The Composite Design Pattern is used to model the classes MenuItem, BaseProduct, and CompositeProduct. Reports are created on completed orders using lambda expressions and stream processing.

The client can view the list of products, search for them based on one or more criteria and can create an order of several products.

The employee will get notified when a new order is added by the client, done using the Observer Pattern.

Menu items, completed orders, and user information are serialized and made available to future system executions via deserialization.
