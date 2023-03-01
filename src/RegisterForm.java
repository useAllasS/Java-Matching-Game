import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.sql.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class RegisterForm extends JFrame {
    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    public RegisterForm() {
      
        
// Set the title
        setTitle("Registration Form");
        

// Set the layout
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
       
        

// Add a JLabel to display the GIF image as the background
         
         ImageIcon imageIcon = new ImageIcon(getClass().getResource("icon/giphy.gif"));
         JLabel backgroundLabel = new JLabel(imageIcon);
         backgroundLabel.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
         setContentPane(backgroundLabel);
         getContentPane().setLayout(new GridBagLayout());
        

// Create the name label and field
        nameLabel = new JLabel("UserName:");
        nameLabel.setForeground(Color.BLUE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(nameLabel, constraints);
        nameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(nameField, constraints);
        // Create the password label and field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLUE); // Set foreground color
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(passwordLabel, constraints);
        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(passwordField, constraints);
        
        // Create the register button
        registerButton = new JButton("Register");
        registerButton.addActionListener((var e) -> {
            // Get the entered name, email, and password
            String username = nameField.getText();
            String password = new String(passwordField.getPassword());
            // Hash and salt the password
            try {
                byte[] salt = getSalt();
                String hashedPassword = getHashedPassword(password, salt);
                // Connect to the database and insert the user's information
                Connection con = Database.getConnection();
                PreparedStatement st;
                st = con.prepareStatement("INSERT INTO accounts (username, passwd, salt) VALUES (?,?,?)");
                st.setString(1, username);
                st.setString(2, hashedPassword);
                st.setBytes(3, salt);
                st.executeUpdate();
                JOptionPane.showMessageDialog(RegisterForm.this, "Successfully registered!");
                // Clear the fields
                nameField.setText("");
                passwordField.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(RegisterForm.this, "Error registering: " + ex.getMessage());
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                JOptionPane.showMessageDialog(RegisterForm.this, "Error hashing password: " + ex.getMessage());
            }
        });
        
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(registerButton, constraints);
        setSize(400, 200);
        // Set the size and center the form on the screen
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        setLocationRelativeTo(null);
        // Set the size and
        // center the form on the screen
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Call the login interface when the frame is closed
                login login = new login();
                login.setVisible(true);
            }
        });
pack();
setLocationRelativeTo(null);
}
 private byte[] getSalt() throws NoSuchAlgorithmException {
    SecureRandom sr = SecureRandom.getInstanceStrong();
    byte[] salt = new byte[16];
    sr.nextBytes(salt);
    return salt;
}

protected static String getHashedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
    SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] hash = f.generateSecret(spec).getEncoded();
    return Base64.getEncoder().encodeToString(hash);
}   

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new RegisterForm().setVisible(true);
    });
}
}