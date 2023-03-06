package presentation;

import business.*;
import business.MenuItem;
import data.FileReaderWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

/**
 * Administrator Window
 */
public class AdministratorGUI extends JFrame {

    private JTabbedPane contentPanel =  new JTabbedPane();

    private JPanel welcomePanel = new JPanel();
    private JPanel addNewProductPanel = new JPanel();
    private JPanel createCompositeProductPanel = new JPanel();
    private JPanel updateProductsPanel = new JPanel();
    private JPanel generateReportsPanel = new JPanel();
    private JLabel imageIconLabel = new JLabel("");

    private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src\\icon.png").getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));

    JScrollPane scrollPaneCompose;
    JScrollPane scrollPaneCreate;

    private JLabel titleLabel = new JLabel("Welcome to Administrator's Page");
    private JButton logOutButton = new JButton("        Log Out        ");
    private JButton importProductsButton = new JButton("Import products");
    private JButton registerProductButton = new JButton("Register");
    private JButton updateProductButton = new JButton("Update selected");
    private JButton deleteProductButton = new JButton("Delete selected");
    private JButton addToCompositeButton = new JButton("             Add product           ");
    private JButton createCompositeButton = new JButton("Create composite product");
    private JButton generateReportButton1 = new JButton("Generate report");
    private JButton generateReportButton2 = new JButton("Generate report");
    private JButton generateReportButton3 = new JButton("Generate report");
    private JButton generateReportButton4 = new JButton("Generate report");

    private JTextField baseProductNameTxtField = new JTextField("Type the name of the base product...");
    private JTextField baseProductRatingTxtField = new JTextField("Type a real positive number...");
    private JTextField baseProductCaloriesTxtField = new JTextField("Type a positive integer...");
    private JTextField baseProductProteinTxtField = new JTextField("Type a positive integer...");
    private JTextField baseProductFatTxtField = new JTextField("Type a positive integer...");
    private JTextField baseProductSodiumTxtField = new JTextField("Type a positive integer...");
    private JTextField baseProductPriceTxtField = new JTextField("Type a positive integer...");
    private JTextField startHourTxtField = new JTextField("Type the start hour...");
    private JTextField endHourTxtField = new JTextField("Type the end hour...");
    private JTextField moreOrderedTxtField = new JTextField("Type a positive integer...");
    private JTextField moreTimesTxtField = new JTextField("Type a positive integer...");
    private JTextField higherValueTxtField = new JTextField("Type a positive integer...");
    private JTextField dateTxtField = new JTextField("Type de date as yyyy-mm-dd...");
    private JTextField newCompositeProductNameTxtField = new JTextField("Type the name of the composite product...");

    private JTable productsTableForUpdate;
    private JTable productsTableForCompose;

    private List<MenuItem> productsForCompose;
    private DeliveryService deliveryService;


    public AdministratorGUI(DeliveryService deliveryService) {
        this.setTitle("Administrator");
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
        this.importProductsButton.setAlignmentX(0.5f);
        this.importProductsButton.setAlignmentY(0.5f);
        this.logOutButton.setAlignmentX(0.5f);
        this.imageIconLabel.setAlignmentX(0.5f);
        this.imageIconLabel.setIcon(imageIcon);

        JPanel welcomeButtonsPanel = new JPanel();
        JLabel emptyLabel2 = new JLabel("          ");
        welcomeButtonsPanel.add(emptyLabel2);
        welcomeButtonsPanel.setLayout(new BoxLayout(welcomeButtonsPanel, BoxLayout.Y_AXIS));
        JLabel emptyLabel3 = new JLabel("          ");
        welcomeButtonsPanel.add(importProductsButton);
        welcomeButtonsPanel.add(emptyLabel3);
        welcomeButtonsPanel.add(logOutButton);

        this.welcomePanel.add(titleLabel);
        JLabel emptyLabel4 = new JLabel("              ");
        this.welcomePanel.add(emptyLabel4);
        this.welcomePanel.add(imageIconLabel);
        this.welcomePanel.add(welcomeButtonsPanel);


        this.addNewProductPanel.setLayout(new BoxLayout(addNewProductPanel, BoxLayout.Y_AXIS));
        JLabel registerProductLabel = new JLabel("Register a new base product");
        registerProductLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 20));
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

        registerProductLabel.setAlignmentX(0.5f);
        this.addNewProductPanel.add(registerProductLabel);
        this.addNewProductPanel.add(nameLabel);
        this.addNewProductPanel.add(this.baseProductNameTxtField);
        this.addNewProductPanel.add(ratingLabel);
        this.addNewProductPanel.add(this.baseProductRatingTxtField);
        this.addNewProductPanel.add(caloriesLabel);
        this.addNewProductPanel.add(this.baseProductCaloriesTxtField);
        this.addNewProductPanel.add(proteinLabel);
        this.addNewProductPanel.add(this.baseProductProteinTxtField);
        this.addNewProductPanel.add(fatLabel);
        this.addNewProductPanel.add(this.baseProductFatTxtField);
        this.addNewProductPanel.add(sodiumLabel);
        this.addNewProductPanel.add(this.baseProductSodiumTxtField);
        this.addNewProductPanel.add(priceLabel);
        this.addNewProductPanel.add(this.baseProductPriceTxtField);
        JLabel emptyLabel5 = new JLabel("            ");
        this.addNewProductPanel.add(emptyLabel5);
        this.registerProductButton.setAlignmentX(0.5f);
        this.addNewProductPanel.add(registerProductButton);

        this.deliveryService = deliveryService;
        productsTableForUpdate = MenuItemsTable.generateTable(this.deliveryService.getMenuItemSet());
        productsTableForUpdate.setVisible(true);
        scrollPaneCreate = new JScrollPane(productsTableForUpdate);
        JLabel updateProductLabel = new JLabel("Update or Delete an Existing Product");
        updateProductLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 20));
        updateProductLabel.setAlignmentX(0.5f);
        JPanel updateButtonsPanel = new JPanel();
        updateButtonsPanel.setLayout(new BoxLayout(updateButtonsPanel, BoxLayout.X_AXIS));
        updateButtonsPanel.add(updateProductButton);
        JLabel emptyLabel6 = new JLabel("     ");
        updateButtonsPanel.add(emptyLabel6);
        updateButtonsPanel.add(deleteProductButton);
        this.updateProductsPanel.setLayout(new BoxLayout(this.updateProductsPanel, BoxLayout.Y_AXIS));
        this.updateProductsPanel.add(updateProductLabel);
        this.updateProductsPanel.add(scrollPaneCreate);
        this.updateProductsPanel.add(updateButtonsPanel);

        productsTableForCompose = MenuItemsTable.generateTable(this.deliveryService.getMenuItemSet());
        productsTableForCompose.setVisible(true);
        scrollPaneCompose = new JScrollPane(productsTableForCompose);
        JLabel composeProductLabel = new JLabel("Create a New Composite Product");
        composeProductLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 20));
        composeProductLabel.setAlignmentX(0.5f);
        JPanel composeButtonsPanel = new JPanel();
        composeButtonsPanel.setLayout(new BoxLayout(composeButtonsPanel, BoxLayout.X_AXIS));
        composeButtonsPanel.add(addToCompositeButton);
        JLabel emptyLabel7 = new JLabel("     ");
        composeButtonsPanel.add(emptyLabel7);
        composeButtonsPanel.add(createCompositeButton);
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        JLabel compositeNameLabel = new JLabel("Name:");
        namePanel.add(compositeNameLabel);
        namePanel.add(this.newCompositeProductNameTxtField);
        this.createCompositeProductPanel.setLayout(new BoxLayout(this.createCompositeProductPanel, BoxLayout.Y_AXIS));
        this.createCompositeProductPanel.add(composeProductLabel);
        this.createCompositeProductPanel.add(namePanel);
        this.createCompositeProductPanel.add(scrollPaneCompose);
        this.createCompositeProductPanel.add(composeButtonsPanel);

        JPanel reportPanel1 = new JPanel();
        JPanel reportPanel2 = new JPanel();
        JPanel reportPanel3 = new JPanel();
        JPanel reportPanel4 = new JPanel();
        reportPanel1.setLayout(new BoxLayout(reportPanel1, BoxLayout.Y_AXIS));
        reportPanel2.setLayout(new BoxLayout(reportPanel2, BoxLayout.Y_AXIS));
        reportPanel3.setLayout(new BoxLayout(reportPanel3, BoxLayout.Y_AXIS));
        reportPanel4.setLayout(new BoxLayout(reportPanel4, BoxLayout.Y_AXIS));
        generateReportsPanel.setLayout(new BoxLayout(generateReportsPanel, BoxLayout.Y_AXIS));
        JLabel generateReportsLabel = new JLabel("Generate Reports");
        generateReportsLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 20));
        generateReportsLabel.setAlignmentX(0.5f);

        JLabel timeIntervalLabel = new JLabel("Time interval of orders:");
        timeIntervalLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 15));
        timeIntervalLabel.setAlignmentX(0.5f);
        JLabel startHourLabel = new JLabel("Start Hour:");
        JLabel endHourLabel = new JLabel("End Hour:");
        reportPanel1.add(startHourLabel);
        reportPanel1.add(startHourTxtField);
        reportPanel1.add(endHourLabel);
        reportPanel1.add(endHourTxtField);
        reportPanel1.add(generateReportButton1);
        generateReportsPanel.add(generateReportsLabel);
        generateReportsPanel.add(timeIntervalLabel);
        generateReportsPanel.add(reportPanel1);

        JLabel moreOrderedLabel = new JLabel("The products ordered more than:");
        moreOrderedLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 15));
        moreOrderedLabel.setAlignmentX(0.5f);
        reportPanel2.add(moreOrderedTxtField);
        reportPanel2.add(generateReportButton2);
        generateReportsPanel.add(moreOrderedLabel);
        generateReportsPanel.add(reportPanel2);

        JLabel clientsOrderedLabel = new JLabel("The clients who ordered more than a number of times and higher than a value:");
        clientsOrderedLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 15));
        clientsOrderedLabel.setAlignmentX(0.5f);
        JLabel moreTimesLabel = new JLabel("Number of times:");
        JLabel higherValueLabel = new JLabel("Higher than value:");
        reportPanel3.add(moreTimesLabel);
        reportPanel3.add(moreTimesTxtField);
        reportPanel3.add(higherValueLabel);
        reportPanel3.add(higherValueTxtField);
        reportPanel3.add(generateReportButton3);
        generateReportsPanel.add(clientsOrderedLabel);
        generateReportsPanel.add(reportPanel3);

        JLabel dayOrderedLabel = new JLabel("The products ordered within a specified day:");
        dayOrderedLabel.setFont(new Font(this.titleLabel.getFont().getName(), Font.BOLD, 15));
        dayOrderedLabel.setAlignmentX(0.5f);
        JLabel dayLabel = new JLabel("Date:");
        reportPanel4.add(dayLabel);
        reportPanel4.add(dateTxtField);
        reportPanel4.add(generateReportButton4);
        generateReportsPanel.add(dayOrderedLabel);
        generateReportsPanel.add(reportPanel4);

        this.productsForCompose = new ArrayList<MenuItem>();

        this.contentPanel.add("Welcome", welcomePanel);
        this.contentPanel.add("Register product", addNewProductPanel);
        this.contentPanel.add("Update products", updateProductsPanel);
        this.contentPanel.add("Create product", createCompositeProductPanel);
        this.contentPanel.add("Generate reports", generateReportsPanel);
        this.setContentPane(contentPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addRegisterListener();
        this.addAddToCompositeListener();
        this.addComposeListener();
        this.addUpdateListener();
        this.addDeleteListener();
        this.addImportListener();
        this.addLogOutListener();
        this.addExitListener();
        this.addReport1Listener();
        this.addReport2Listener();
        this.addReport3Listener();
        this.addReport4Listener();
    }

    /**
     * Gets selected product from updated table
     * @return selected base product
     */
    public BaseProduct getSelectedBaseProductUpdate() {
        int row = this.productsTableForUpdate.getSelectedRow();

        if (row == -1) {
            return null;
        }

        String name = (String)this.productsTableForUpdate.getValueAt(row, 0);
        float rating = Float.parseFloat((String)this.productsTableForUpdate.getValueAt(row, 1));
        int calories = Integer.parseInt((String)this.productsTableForUpdate.getValueAt(row, 2));
        int protein = Integer.parseInt((String)this.productsTableForUpdate.getValueAt(row, 3));
        int fat = Integer.parseInt((String)this.productsTableForUpdate.getValueAt(row, 4));
        int sodium = Integer.parseInt((String)this.productsTableForUpdate.getValueAt(row, 5));
        int price = Integer.parseInt((String)this.productsTableForUpdate.getValueAt(row, 6));

        if (this.deliveryService.findByName(name) instanceof BaseProduct) {
            return new BaseProduct(name, rating, calories, protein, fat, sodium, price);
        }
        return null;
    }

    /**
     * Gets selected product from updated table
     * @return selected composite product
     */
    public CompositeProduct getSelectedCompositeProductUpdate() {
        int row = this.productsTableForUpdate.getSelectedRow();

        if (row == -1) {
            return null;
        }

        String name = (String)this.productsTableForUpdate.getValueAt(row, 0);
        float rating = Float.parseFloat((String)this.productsTableForUpdate.getValueAt(row, 1));
        int calories = Integer.parseInt((String)this.productsTableForUpdate.getValueAt(row, 2));
        int protein = Integer.parseInt((String)this.productsTableForUpdate.getValueAt(row, 3));
        int fat = Integer.parseInt((String)this.productsTableForUpdate.getValueAt(row, 4));
        int sodium = Integer.parseInt((String)this.productsTableForUpdate.getValueAt(row, 5));
        int price = Integer.parseInt((String)this.productsTableForUpdate.getValueAt(row, 6));

        if (this.deliveryService.findByName(name) instanceof CompositeProduct) {
            return new CompositeProduct(name);
        }
        return null;
    }

    /**
     * Gets selected base product from composite table
     * @return selected product
     */
    public BaseProduct getSelectedBaseProductCompose() {
        int row = this.productsTableForCompose.getSelectedRow();
        if (row == -1) {
            return null;
        }
        String name = (String)this.productsTableForCompose.getValueAt(row, 0);
        float rating = Float.parseFloat((String)this.productsTableForCompose.getValueAt(row, 1));
        int calories = Integer.parseInt((String)this.productsTableForCompose.getValueAt(row, 2));
        int protein = Integer.parseInt((String)this.productsTableForCompose.getValueAt(row, 3));
        int fat = Integer.parseInt((String)this.productsTableForCompose.getValueAt(row, 4));
        int sodium = Integer.parseInt((String)this.productsTableForCompose.getValueAt(row, 5));
        int price = Integer.parseInt((String)this.productsTableForCompose.getValueAt(row, 6));
        if (this.deliveryService.findByName(name) instanceof BaseProduct) {
            return new BaseProduct(name, rating, calories, protein, fat, sodium, price);
        }
        return null;
    }

    /**
     * Gets selected product from composite table
     * @return selected product
     */
    public CompositeProduct getSelectedCompositeProductCompose() {
        int row = this.productsTableForCompose.getSelectedRow();
        if (row == -1) {
            return null;
        }
        String name = (String)this.productsTableForCompose.getValueAt(row, 0);
        float rating = Float.parseFloat((String)this.productsTableForCompose.getValueAt(row, 1));
        int calories = Integer.parseInt((String)this.productsTableForCompose.getValueAt(row, 2));
        int protein = Integer.parseInt((String)this.productsTableForCompose.getValueAt(row, 3));
        int fat = Integer.parseInt((String)this.productsTableForCompose.getValueAt(row, 4));
        int sodium = Integer.parseInt((String)this.productsTableForCompose.getValueAt(row, 5));
        int price = Integer.parseInt((String)this.productsTableForCompose.getValueAt(row, 6));
        if (this.deliveryService.findByName(name) instanceof CompositeProduct) {
            return new CompositeProduct(name);
        }
        return null;
    }

    /**
     * Gets menu item from composite table
     * @return selected product
     */
    public MenuItem getSelectedProductCompose() {
        if (getSelectedCompositeProductCompose() != null) {
            return getSelectedCompositeProductCompose();
        } else {
            return getSelectedBaseProductCompose();
        }
    }

    /**
     * Gets menu item from updated table
     * @return selected product
     */
    public MenuItem getSelectedProductUpdate() {
        if (getSelectedCompositeProductUpdate() != null) {
            return getSelectedCompositeProductUpdate();
        } else {
            return getSelectedBaseProductUpdate();
        }
    }

    /**
     * Adds product to composite
     */
    public void addProductToComposite() {
        if (getSelectedBaseProductCompose() != null) {
            this.productsForCompose.add(getSelectedBaseProductCompose());
        } else if (getSelectedCompositeProductCompose() != null) {
            this.productsForCompose.add(getSelectedCompositeProductCompose());
        }
    }

    /**
     * Creates composite product
     */
    public void createCompositeProduct() {
        CompositeProduct compositeProduct = new CompositeProduct(this.newCompositeProductNameTxtField.getText());
        List<MenuItem> listForCompose = new ArrayList<>(productsForCompose);
        for (MenuItem item :
             listForCompose) {
            compositeProduct.addProduct(item);
        }
        this.deliveryService.addProduct(compositeProduct);
        productsForCompose.clear();
    }

    /**
     * Creates base product
     */
    public void createNewBaseProduct() {
        try {
            String name = this.baseProductNameTxtField.getText();
            float rating = Float.parseFloat(this.baseProductRatingTxtField.getText());
            int calories = Integer.parseInt(this.baseProductCaloriesTxtField.getText());
            int protein = Integer.parseInt(this.baseProductProteinTxtField.getText());
            int fat = Integer.parseInt(this.baseProductProteinTxtField.getText());
            int sodium = Integer.parseInt(this.baseProductSodiumTxtField.getText());
            int price = Integer.parseInt(this.baseProductPriceTxtField.getText());
            BaseProduct toInsert = new BaseProduct(name, rating, calories, protein, fat, sodium, price);
            this.deliveryService.addProduct(toInsert);
            System.out.println(this.deliveryService.getMenuItemSet().contains(toInsert));
            System.out.println(toInsert);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bad input");
        }
    }

    /**
     * Modifies base product
     */
    public void modifyProduct() {
        if (getSelectedBaseProductUpdate() != null) {
            BaseProduct baseProduct = getSelectedBaseProductUpdate();
            this.deliveryService.updateProduct(baseProduct);
        } else if (getSelectedCompositeProductUpdate() != null) {
            CompositeProduct compositeProduct = getSelectedCompositeProductUpdate();
            this.deliveryService.updateProduct(compositeProduct);
        } else {
            JOptionPane.showMessageDialog(this, "No product selected");
        }
    }

    /**
     * Deleted selected product
     */
    public void deleteProduct() {
        if (getSelectedBaseProductUpdate() != null) {
            BaseProduct baseProduct = getSelectedBaseProductUpdate();
            this.deliveryService.deleteProduct(baseProduct);
        } else if (getSelectedCompositeProductUpdate() != null) {
            CompositeProduct compositeProduct = getSelectedCompositeProductUpdate();
            this.deliveryService.deleteProduct(compositeProduct);
        } else {
            JOptionPane.showMessageDialog(this, "No product selected");
        }
    }

    /**
     * Imports products
     */
    public void importProducts() {
        this.deliveryService.readProductsFromCSV("products.csv");
    }

    /**
     * Updating of GUI data
     */
    public void updateGUIData() {
        updateProductsPanel.removeAll();
        productsTableForUpdate = MenuItemsTable.generateTable(deliveryService.getMenuItemSet());
        productsTableForUpdate.setVisible(true);
        scrollPaneCreate = new JScrollPane(productsTableForUpdate);
        JLabel updateProductLabel = new JLabel("Update or Delete an Existing Product");
        updateProductLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 20));
        updateProductLabel.setAlignmentX(0.5f);
        JPanel updateButtonsPanel = new JPanel();
        updateButtonsPanel.setLayout(new BoxLayout(updateButtonsPanel, BoxLayout.X_AXIS));
        updateButtonsPanel.add(updateProductButton);
        JLabel emptyLabel6 = new JLabel("     ");
        updateButtonsPanel.add(emptyLabel6);
        updateButtonsPanel.add(deleteProductButton);
        updateProductsPanel.setLayout(new BoxLayout(updateProductsPanel, BoxLayout.Y_AXIS));
        updateProductsPanel.add(updateProductLabel);
        updateProductsPanel.add(scrollPaneCreate);
        updateProductsPanel.add(updateButtonsPanel);

        createCompositeProductPanel.removeAll();
        productsTableForCompose = MenuItemsTable.generateTable(deliveryService.getMenuItemSet());
        productsTableForCompose.setVisible(true);
        scrollPaneCompose = new JScrollPane(productsTableForCompose);
        JLabel composeProductLabel = new JLabel("Create a New Composite Product");
        composeProductLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 20));
        composeProductLabel.setAlignmentX(0.5f);
        JPanel composeButtonsPanel = new JPanel();
        composeButtonsPanel.setLayout(new BoxLayout(composeButtonsPanel, BoxLayout.X_AXIS));
        composeButtonsPanel.add(addToCompositeButton);
        JLabel emptyLabel7 = new JLabel("     ");
        composeButtonsPanel.add(emptyLabel7);
        composeButtonsPanel.add(createCompositeButton);
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        JLabel compositeNameLabel = new JLabel("Name:");
        namePanel.add(compositeNameLabel);
        namePanel.add(newCompositeProductNameTxtField);
        createCompositeProductPanel.setLayout(new BoxLayout(createCompositeProductPanel, BoxLayout.Y_AXIS));
        createCompositeProductPanel.add(composeProductLabel);
        createCompositeProductPanel.add(namePanel);
        createCompositeProductPanel.add(scrollPaneCompose);
        createCompositeProductPanel.add(composeButtonsPanel);
    }

    /**
     * Add Listener
     */
    public void addRegisterListener() {
        this.registerProductButton.addActionListener(e -> {
            createNewBaseProduct();
            deliveryService.serializeAll();
            deliveryService = new DeliveryService();
            updateGUIData();
        });
    }

    /**
     * Add listener
     */
    public void addAddToCompositeListener() {
        this.addToCompositeButton.addActionListener(e -> addProductToComposite());
    }

    /**
     * Add listener
     */
    public void addComposeListener() {
        this.createCompositeButton.addActionListener(e -> {
            createCompositeProduct();
            deliveryService.serializeAll();
            deliveryService = new DeliveryService();
            updateGUIData();
        });
    }

    /**
     * Show message
     * @param message message
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Add listener
     */
    public void addUpdateListener() {
        this.updateProductButton.addActionListener(e -> {
            MenuItem selected = getSelectedBaseProductUpdate();
            if (selected == null) {
                showMessage("No selected product!");
                return;
            }
            MenuItem toUpdate = deliveryService.findByName(selected.getTitle());
            modifyProduct();
            deliveryService.serializeAll();
            deliveryService = new DeliveryService();
            updateGUIData();

        });
    }

    /**
     * Add listener
     */
    public void addDeleteListener() {
        this.deleteProductButton.addActionListener(e -> {
            MenuItem selected = getSelectedProductUpdate();
            if (selected == null) {
                showMessage("No selected product!");
                return;
            }
            deleteProduct();
            deliveryService.serializeAll();
            deliveryService = new DeliveryService();
            updateGUIData();
        });
    }

    /**
     * Add listener
     */
    public void addImportListener() {
        this.importProductsButton.addActionListener(e -> {
            if (deliveryService.getMenuItemSet().isEmpty()) {
                deliveryService.readProductsFromCSV("products.csv");
            } else {
                showMessage("Products already imported!");
            }
        });
    }

    /**
     * Add listener
     */
    public void addLogOutListener() {
        AdministratorGUI copyGUI = this;
        this.logOutButton.addActionListener(e -> {
            copyGUI.setVisible(false);
            new LogInGUI(deliveryService).setVisible(true);
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

    /**
     * Add listener
     */
    public void addReport1Listener() {
        this.generateReportButton1.addActionListener(e -> {
            try {
                int start = Integer.parseInt(startHourTxtField.getText());
                int end = Integer.parseInt(endHourTxtField.getText());
                if (start > end) {
                    showMessage("Start hour has to be smaller than or equal to end hour!");
                    return;
                }
                List<Order> orders = deliveryService.reportOrders(start, end);
                String toWrite = FileReaderWriter.generateStringReport1(start, end, orders);
                FileReaderWriter.generateReport(toWrite, 1);
                showMessage("Report generated!");
            } catch (NumberFormatException ex) {
                showMessage("Bad input for start or end hour!");
            }
        });
    }

    /**
     * Add listener
     */
    public void addReport2Listener() {
        this.generateReportButton2.addActionListener(e -> {
            try {
                int times = Integer.parseInt(moreOrderedTxtField.getText());
                if (times <= 0) {
                    showMessage("Type a positive integer!");
                    return;
                }
                List<MenuItem> menuItems = deliveryService.reportProducts(times);
                String toWrite = FileReaderWriter.generateStringReport2(times, menuItems);
                FileReaderWriter.generateReport(toWrite, 2);
                showMessage("Report generated!");
            } catch (NumberFormatException ex) {
                showMessage("Bad input for number of times!");
            }
        });
    }

    /**
     * Add listener
     */
    public void addReport3Listener() {
        this.generateReportButton3.addActionListener(e -> {
            try {
                int times = Integer.parseInt(moreTimesTxtField.getText());
                int val = Integer.parseInt(higherValueTxtField.getText());
                if (times <= 0 || val <= 0) {
                    showMessage("Type a positive integer!");
                    return;
                }
                List<User> users = deliveryService.reportClients(times, val);
                String toWrite = FileReaderWriter.generateStringReport3(times, val, users);
                FileReaderWriter.generateReport(toWrite, 3);
                showMessage("Report generated!");
            } catch (NumberFormatException ex) {
                showMessage("Bad input for number of times or amount!");
            }
        });
    }

    /**
     * Add listener
     */
    public void addReport4Listener() {
        this.generateReportButton4.addActionListener(e -> {
            try {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                Date date = simpleDateFormat.parse(dateTxtField.getText());
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                HashMap<Long, MenuItem> map = deliveryService.reportProductsDay(localDate);
                System.out.println(map);
                String toWrite = FileReaderWriter.generateStringReport4(localDate, map);
                FileReaderWriter.generateReport(toWrite, 4);
                showMessage("Report generated!");
            } catch (java.text.ParseException ex) {
                showMessage("Bad input for date!");
            }
        });
    }

}
