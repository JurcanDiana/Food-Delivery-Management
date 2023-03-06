package presentation;

import business.DeliveryService;
import business.MenuItem;
import business.Order;
import business.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Employee Window
 */
public class EmployeeGUI extends JFrame implements Observer {
    
    private JPanel contentPanel = new JPanel();
    
    private JTextArea ordersTxtArea = new JTextArea();

    private DeliveryService deliveryService;
    
    private User employee;

 
    public EmployeeGUI(DeliveryService deliveryService, User user) {
        this.setTitle("Employee");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(1000, 800));
        this.setLocationRelativeTo(null);
        this.deliveryService = deliveryService;
        this.employee = user;
        JLabel titleLabel = new JLabel("Waiting list of orders:");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 30));
        this.contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        this.contentPanel.setAlignmentY(0.5f);
        this.contentPanel.add(titleLabel);
        titleLabel.setAlignmentX(0.5f);
        titleLabel.setAlignmentY(0.5f);
        this.ordersTxtArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(this.ordersTxtArea);
        this.contentPanel.add(scrollPane);
        this.setContentPane(contentPanel);
        update(deliveryService, deliveryService.getPlacedOrders());
        this.addExitListener();
    }


    /**
     * Updating GUI data (overriding method from observer
     * @param o Observable object
     * @param arg argument used to update
     */
    @Override
    public void update(Observable o, Object arg) {
        Map<Order, Collection<MenuItem>> orders = (Map<Order, Collection<MenuItem>>)arg;
        String toWrite = "";
        for (Order order :
             orders.keySet()) {
            toWrite += "Order: " + order.getOrderID() + "\n";
            toWrite += "Client: " + order.getClientID() + "\n";
            toWrite += "Date: " + order.convertDateToLocalDate() + "\n";
            toWrite += "Products: ";
            int i = 0;
            for (MenuItem menuItem :
                 orders.get(order)) {
                toWrite += menuItem.getTitle();
                if (i != orders.get(order).size() - 1) {
                    toWrite += ", ";
                } else {
                    toWrite += "\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
                }
                i++;
            }
        }
        this.ordersTxtArea.setText(toWrite);
    }

    /**
     * Add listener
     */
    public void addExitListener() {
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                new LogInGUI(deliveryService).setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
