package presentation;

import business.*;
import business.MenuItem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import java.util.List;

/**
 * Client Window
 */
public class ClientGUI extends JFrame {
   
    private JTabbedPane contentPanel =  new JTabbedPane();
    
    private JPanel welcomePanel = new JPanel();
    private JPanel viewAndOrderPanel = new JPanel();
    private JPanel searchPanel = new JPanel();
    private JLabel imageIconLabel = new JLabel("");
    
    private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src\\icon.png").getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
    
    private JLabel titleLabel = new JLabel("Welcome to Client's Page");
    
    private JButton logOutButton = new JButton("        Log Out        ");
    private JButton addProductButton = new JButton("Add to order");
    private JButton placeOrderButton = new JButton("Place order");
    private JButton searchButton = new JButton("Search");

    private JTable productsTableForView;
    private JTable productsTableForSearch;

    private JTextField productNameTxtField = new JTextField("");
    private JTextField productRatingTxtField = new JTextField("");
    private JTextField productCaloriesTxtField = new JTextField("");
    private JTextField productProteinTxtField = new JTextField("");
    private JTextField productFatTxtField = new JTextField("");
    private JTextField productSodiumTxtField = new JTextField("");
    private JTextField productPriceTxtField = new JTextField("");
    
    JScrollPane scrollPaneSearch;
    
    private DeliveryService deliveryService;
    private List<MenuItem> orderedProducts;
    
    private User client;

    public ClientGUI(DeliveryService deliveryService, User user) {
        this.setTitle("Client");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(1000, 800));

        this.welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        this.welcomePanel.setAlignmentY(0.5f);
        this.titleLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 30));
        for (int i = 0; i < 5; i++) {
            JLabel emptyLabel0 = new JLabel("              ");
            this.welcomePanel.add(emptyLabel0);
        }
        this.titleLabel.setAlignmentX(0.5f);
        this.titleLabel.setAlignmentY(0.5f);
        this.logOutButton.setAlignmentX(0.5f);
        this.imageIconLabel.setAlignmentX(0.5f);
        this.imageIconLabel.setIcon(imageIcon);

        JPanel welcomeButtonsPanel = new JPanel();
        JLabel emptyLabel2 = new JLabel("          ");
        welcomeButtonsPanel.add(emptyLabel2);
        welcomeButtonsPanel.setLayout(new BoxLayout(welcomeButtonsPanel, BoxLayout.Y_AXIS));
        welcomeButtonsPanel.add(logOutButton);

        this.welcomePanel.add(titleLabel);
        JLabel emptyLabel4 = new JLabel("              ");
        this.welcomePanel.add(emptyLabel4);
        this.welcomePanel.add(imageIconLabel);
        this.welcomePanel.add(welcomeButtonsPanel);


        this.deliveryService = deliveryService;
        productsTableForView = MenuItemsTable.generateTable(this.deliveryService.getMenuItemSet());
        productsTableForView.setVisible(true);
        JScrollPane scrollPaneView = new JScrollPane(productsTableForView);
        JLabel placeOrderLabel = new JLabel("Place an Order");
        placeOrderLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 20));
        placeOrderLabel.setAlignmentX(0.5f);
        JPanel orderButtonsPanel = new JPanel();
        orderButtonsPanel.setLayout(new BoxLayout(orderButtonsPanel, BoxLayout.X_AXIS));
        orderButtonsPanel.add(addProductButton);
        JLabel emptyLabel6 = new JLabel("     ");
        orderButtonsPanel.add(emptyLabel6);
        orderButtonsPanel.add(placeOrderButton);
        this.viewAndOrderPanel.setLayout(new BoxLayout(this.viewAndOrderPanel, BoxLayout.Y_AXIS));
        this.viewAndOrderPanel.add(placeOrderLabel);
        this.viewAndOrderPanel.add(scrollPaneView);
        this.viewAndOrderPanel.add(orderButtonsPanel);

        this.searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        JLabel searchLabel = new JLabel("Search products");
        searchLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 20));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setAlignmentX(0.5f);
        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setAlignmentX(0.5f);
        JLabel caloriesLabel = new JLabel("Calories:");
        caloriesLabel.setAlignmentX(0.5f);
        JLabel proteinLabel = new JLabel("Proteins:");
        proteinLabel.setAlignmentX(0.5f);
        JLabel fatLabel = new JLabel("Fat:");
        fatLabel.setAlignmentX(0.5f);
        JLabel sodiumLabel = new JLabel("Sodium:");
        sodiumLabel.setAlignmentX(0.5f);
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setAlignmentX(0.5f);

        searchLabel.setAlignmentX(0.5f);
        this.searchPanel.add(searchLabel);
        this.searchPanel.add(nameLabel);
        this.searchPanel.add(this.productNameTxtField);
        this.searchPanel.add(ratingLabel);
        this.searchPanel.add(this.productRatingTxtField);
        this.searchPanel.add(caloriesLabel);
        this.searchPanel.add(this.productCaloriesTxtField);
        this.searchPanel.add(proteinLabel);
        this.searchPanel.add(this.productProteinTxtField);
        this.searchPanel.add(fatLabel);
        this.searchPanel.add(this.productFatTxtField);
        this.searchPanel.add(sodiumLabel);
        this.searchPanel.add(this.productSodiumTxtField);
        this.searchPanel.add(priceLabel);
        this.searchPanel.add(this.productPriceTxtField);
        JLabel emptyLabel5 = new JLabel("            ");
        this.searchPanel.add(emptyLabel5);
        this.searchButton.setAlignmentX(0.5f);
        this.searchPanel.add(searchButton);
        JLabel emptyLabel7 = new JLabel("            ");
        this.searchPanel.add(emptyLabel7);
        productsTableForSearch = MenuItemsTable.generateTable(this.deliveryService.getMenuItemSet());
        productsTableForSearch.setVisible(true);
        scrollPaneSearch = new JScrollPane(productsTableForSearch);
        this.searchPanel.add(scrollPaneSearch);

        this.orderedProducts = new ArrayList<MenuItem>();
        this.client = user;

        this.contentPanel.add("Welcome", welcomePanel);
        this.contentPanel.add("Place orders", viewAndOrderPanel);
        this.contentPanel.add("Search products", searchPanel);
        this.setContentPane(contentPanel);
        this.setLocationRelativeTo(null);
        //this.setVisible(true);

        this.addLogOutListener();
        this.addAddListener();
        this.addPlaceOrderListener();
        this.addExitListener();
        this.addSearchListener();
    }

    /**
     * Determines selected product from table
     * @return selected product
     */
    public BaseProduct getSelectedBaseProduct() {
        int row = this.productsTableForView.getSelectedRow();
        if (row == -1) {
            return null;
        }
        String name = (String)this.productsTableForView.getValueAt(row, 0);
        float rating = Float.parseFloat((String)this.productsTableForView.getValueAt(row, 1));
        int calories = Integer.parseInt((String)this.productsTableForView.getValueAt(row, 2));
        int protein = Integer.parseInt((String)this.productsTableForView.getValueAt(row, 3));
        int fat = Integer.parseInt((String)this.productsTableForView.getValueAt(row, 4));
        int sodium = Integer.parseInt((String)this.productsTableForView.getValueAt(row, 5));
        int price = Integer.parseInt((String)this.productsTableForView.getValueAt(row, 6));
        if (this.deliveryService.findByName(name) instanceof BaseProduct) {
            return new BaseProduct(name, rating, calories, protein, fat, sodium, price);
        }
        return null;
    }

    /**
     * Determines selected product from composite table
     * @return selected product
     */
    public CompositeProduct getSelectedCompositeProduct() {
        int row = this.productsTableForView.getSelectedRow();
        if (row == -1) {
            return null;
        }
        String name = (String)this.productsTableForView.getValueAt(row, 0);
        float rating = Float.parseFloat((String)this.productsTableForView.getValueAt(row, 1));
        int calories = Integer.parseInt((String)this.productsTableForView.getValueAt(row, 2));
        int protein = Integer.parseInt((String)this.productsTableForView.getValueAt(row, 3));
        int fat = Integer.parseInt((String)this.productsTableForView.getValueAt(row, 4));
        int sodium = Integer.parseInt((String)this.productsTableForView.getValueAt(row, 5));
        int price = Integer.parseInt((String)this.productsTableForView.getValueAt(row, 6));
        if (this.deliveryService.findByName(name) instanceof CompositeProduct) {
            return new CompositeProduct(name);
        }
        return null;
    }

    /**
     * Show message
     * @param message message
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Updating GUI data
     * @param setToDisplay products to show in table
     */
    public void updateGUI(Set<MenuItem> setToDisplay) {
        this.searchPanel.removeAll();
        this.searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        JLabel searchLabel = new JLabel("Search products");
        searchLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 20));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setAlignmentX(0.5f);
        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setAlignmentX(0.5f);
        JLabel caloriesLabel = new JLabel("Calories:");
        caloriesLabel.setAlignmentX(0.5f);
        JLabel proteinLabel = new JLabel("Proteins:");
        proteinLabel.setAlignmentX(0.5f);
        JLabel fatLabel = new JLabel("Fat:");
        fatLabel.setAlignmentX(0.5f);
        JLabel sodiumLabel = new JLabel("Sodium:");
        sodiumLabel.setAlignmentX(0.5f);
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setAlignmentX(0.5f);

        searchLabel.setAlignmentX(0.5f);
        this.searchPanel.add(searchLabel);
        this.searchPanel.add(nameLabel);
        this.searchPanel.add(this.productNameTxtField);
        this.searchPanel.add(ratingLabel);
        this.searchPanel.add(this.productRatingTxtField);
        this.searchPanel.add(caloriesLabel);
        this.searchPanel.add(this.productCaloriesTxtField);
        this.searchPanel.add(proteinLabel);
        this.searchPanel.add(this.productProteinTxtField);
        this.searchPanel.add(fatLabel);
        this.searchPanel.add(this.productFatTxtField);
        this.searchPanel.add(sodiumLabel);
        this.searchPanel.add(this.productSodiumTxtField);
        this.searchPanel.add(priceLabel);
        this.searchPanel.add(this.productPriceTxtField);
        JLabel emptyLabel5 = new JLabel("            ");
        this.searchPanel.add(emptyLabel5);
        this.searchButton.setAlignmentX(0.5f);
        this.searchPanel.add(searchButton);
        JLabel emptyLabel7 = new JLabel("            ");
        this.searchPanel.add(emptyLabel7);
        productsTableForSearch = MenuItemsTable.generateTable(setToDisplay);
        productsTableForSearch.setVisible(true);
        scrollPaneSearch = new JScrollPane(productsTableForSearch);
        this.searchPanel.add(scrollPaneSearch);
    }

    /**
     * Add product to order
     */
    public void addSelectedProductToOrder() {
        if (getSelectedBaseProduct() != null) {
            this.orderedProducts.add(getSelectedBaseProduct());
        } else if (getSelectedCompositeProduct() != null) {
                this.orderedProducts.add(getSelectedCompositeProduct());
        }
    }

    /**
     * Places order
     */
    public void placeOrder() {
        List<MenuItem> listForCreate = new ArrayList<>(orderedProducts);
        this.deliveryService.newOrder(this.client, listForCreate);
        orderedProducts.clear();
    }

    /**
     * Add listener
     */
    public void addLogOutListener() {
        ClientGUI copyGUI = this;
        this.logOutButton.addActionListener(e -> {
            copyGUI.setVisible(false);
            new LogInGUI(deliveryService).setVisible(true);
        });
    }

    /**
     * Add listener
     */
    public void addAddListener() {
        this.addProductButton.addActionListener(e -> {
            if (getSelectedBaseProduct() == null && getSelectedCompositeProduct() == null) {
                showMessage("No selected product!");
                return;
            }
            addSelectedProductToOrder();
        });
    }

    /**
     * Add listener
     */
    public void addPlaceOrderListener() {
        this.placeOrderButton.addActionListener(e -> {
            if (orderedProducts.isEmpty()) {
                showMessage("Empty order!");
                return;
            }
            placeOrder();
            deliveryService.serializeAll();
            deliveryService = new DeliveryService();
            showMessage("Order placed!");
        });
    }

    /**
     * Add listener
     */
    public void addSearchListener() {
        this.searchButton.addActionListener(e -> {
            String name = productNameTxtField.getText();
            float rating = 0.0f;
            int calories = 0;
            int fat = 0;
            int sodium = 0;
            int protein = 0;
            int price = 0;
            try {
                if (!productCaloriesTxtField.getText().equals("")) {
                    rating = Float.parseFloat(productRatingTxtField.getText());
                }
                if (!productCaloriesTxtField.getText().equals("")) {
                    calories = Integer.parseInt(productCaloriesTxtField.getText());
                }
                if (!productFatTxtField.getText().equals("")) {
                    fat = Integer.parseInt(productFatTxtField.getText());
                }
                if (!productSodiumTxtField.getText().equals("")) {
                    sodium = Integer.parseInt(productSodiumTxtField.getText());
                }
                if (!productProteinTxtField.getText().equals("")) {
                    protein = Integer.parseInt(productProteinTxtField.getText());
                }
                if (!productPriceTxtField.getText().equals("")) {
                    price = Integer.parseInt(productPriceTxtField.getText());
                }
                if (rating < 0 || calories < 0 || fat < 0 || sodium < 0 || protein < 0 || price < 0) {
                    showMessage("Bad inputs!");
                    return;
                }
                Set <MenuItem> toDisplay = deliveryService.filterProducts(name, rating, calories, protein, fat, sodium, price);
                updateGUI(toDisplay);

            } catch (NumberFormatException exception) {
                showMessage("Bad input!");
            }
        });
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
