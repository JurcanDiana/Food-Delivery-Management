package presentation;

import business.DeliveryService;
import business.User;
import business.UserType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Log In and Sign Up Window
 */
public class LogInGUI extends JFrame {
   
    private JTabbedPane contentPanel =  new JTabbedPane();
    
    private JPanel registerPanel = new JPanel();

    private JButton logInButton = new JButton("Log In");
    private JButton signUpButton = new JButton("Sign Up");
    
    private JTextField userLogInTxtField = new JTextField();
    private JPasswordField passwordLogInTxtField = new JPasswordField();
    private JTextField userSignUpTxtField = new JTextField();
    private JPasswordField passwordSignUpTxtField = new JPasswordField();
    private JPasswordField confirmPasswordTxtField = new JPasswordField();

    private DeliveryService deliveryService;
    
    public LogInGUI(DeliveryService deliveryService) {
        this.setTitle("Log In");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(400, 160));
        this.deliveryService = deliveryService;

        JPanel logInPanel = new JPanel();
        logInPanel.setLayout(new BoxLayout(logInPanel, BoxLayout.Y_AXIS));
        JPanel usrLPanel = new JPanel();
        usrLPanel.setLayout(new BoxLayout(usrLPanel, BoxLayout.X_AXIS));
        JLabel usrLLabel = new JLabel("Username:          ");
        usrLPanel.add(usrLLabel);
        usrLPanel.add(this.userLogInTxtField);
        logInPanel.add(usrLPanel);
        JPanel passLPanel = new JPanel();
        passLPanel.setLayout(new BoxLayout(passLPanel, BoxLayout.X_AXIS));
        JLabel passLLabel = new JLabel("Password:           ");
        passLPanel.add(passLLabel);
        passLPanel.add(this.passwordLogInTxtField);
        logInPanel.add(passLPanel);
        this.logInButton.setAlignmentX(0.5f);
        logInPanel.add(logInButton);

        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
        JPanel usrPPanel = new JPanel();
        usrPPanel.setLayout(new BoxLayout(usrPPanel, BoxLayout.X_AXIS));
        JLabel usrPLabel = new JLabel("Username:                 ");
        usrPPanel.add(usrPLabel);
        usrPPanel.add(this.userSignUpTxtField);
        signUpPanel.add(usrPPanel);
        JPanel passPPanel = new JPanel();
        passPPanel.setLayout(new BoxLayout(passPPanel, BoxLayout.X_AXIS));
        JLabel passPLabel = new JLabel("Password:                  ");
        passPPanel.add(passPLabel);
        passPPanel.add(this.passwordSignUpTxtField);
        signUpPanel.add(passPPanel);
        JPanel confPPanel = new JPanel();
        confPPanel.setLayout(new BoxLayout(confPPanel, BoxLayout.X_AXIS));
        JLabel confPLabel = new JLabel("Confirm password:  ");
        confPPanel.add(confPLabel);
        confPPanel.add(this.confirmPasswordTxtField);
        signUpPanel.add(confPPanel);
        this.signUpButton.setAlignmentX(0.5f);
        signUpPanel.add(signUpButton);


        this.contentPanel.add("Log In", logInPanel);
        this.contentPanel.add("Sign Up", signUpPanel);
        this.setContentPane(contentPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addLogInListener();
        this.addSignUpListener();
    }

    /**
     * Show message
     * @param message message
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     *Add listener
     */
    public void addLogInListener() {
        LogInGUI copyGUI = this;
        this.logInButton.addActionListener(e -> {
            User u = deliveryService.logInUser(userLogInTxtField.getText());
            if (u == null) {
                showError("Invalid username!");
                return;
            }
            if (!u.getPassword().equals(passwordLogInTxtField.getText())) {
                showError("Invalid password!");
                return;
            }
            if(u.getUserType() == UserType.CLIENT) {
                new ClientGUI(deliveryService, u).setVisible(true);
            } else if (u.getUserType() == UserType.ADMINISTRATOR) {
                (new AdministratorGUI(deliveryService)).setVisible(true);
            } else {
                (new EmployeeGUI(deliveryService, u)).setVisible(true);
            }
            copyGUI.setVisible(false);
        });
    }

    /**
     *Add listener
     */
    public void addSignUpListener() {
        LogInGUI copyGUI = this;
        this.signUpButton.addActionListener(e -> {
            if (userSignUpTxtField.getText().equals("") || passwordSignUpTxtField.getText().equals("") || confirmPasswordTxtField.getText().equals("")) {
                showError("Empty field(s)!");
                return;
            }
            if (!passwordSignUpTxtField.getText().equals(confirmPasswordTxtField.getText())) {
                showError("Incorrect password confirmation!");
                return;
            }
            User u = deliveryService.registerNewClient(userSignUpTxtField.getText(), passwordSignUpTxtField.getText(), UserType.CLIENT);
            deliveryService.serializeAll();
            deliveryService = new DeliveryService();
            (new ClientGUI(deliveryService, u)).setVisible(true);
            copyGUI.setVisible(false);
        });

    }



}
