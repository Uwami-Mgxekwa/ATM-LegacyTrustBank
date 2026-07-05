package atm;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * ATM login screen — validates username + PIN against the shared database.
 */
public class AtmLogin extends javax.swing.JFrame {

    private final DatabaseService db;

    public AtmLogin() {
        initComponents();
        db = new DatabaseService();
        db.initializeDatabase();
    }

    // Holds the logged-in user's ID for the session
    private static int currentUserId = -1;

    public static int getCurrentUserId() {
        return currentUserId;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1      = new javax.swing.JPanel();
        jLabel1      = new javax.swing.JLabel();
        jLabel2      = new javax.swing.JLabel();
        jLabel3      = new javax.swing.JLabel();
        txtUsername  = new javax.swing.JTextField();
        txtPin       = new javax.swing.JPasswordField();
        btnLogin     = new javax.swing.JButton();
        btnClear     = new javax.swing.JButton();
        lblMessage   = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ATM Login");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 28));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LEGACY TRUST BANK ATM");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Username / Card Number :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 185, 250, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("PIN :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 235, -1, -1));

        jPanel1.add(txtPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 258, 250, 32));

        btnLogin.setBackground(new java.awt.Color(0, 153, 153));
        btnLogin.setFont(new java.awt.Font("Segoe UI Black", 1, 14));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("ENTER");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 120, 36));

        btnClear.setBackground(new java.awt.Color(80, 80, 80));
        btnClear.setFont(new java.awt.Font("Segoe UI Black", 1, 14));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsername.setText("");
                txtPin.setText("");
                lblMessage.setText("");
            }
        });
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 120, 36));

        lblMessage.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMessage.setForeground(Color.RED);
        jPanel1.add(lblMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 320, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String username = txtUsername.getText().trim();
        String pin      = new String(txtPin.getPassword());

        if (username.isEmpty() || pin.isEmpty()) {
            lblMessage.setForeground(Color.RED);
            lblMessage.setText("Please enter your username and PIN.");
            return;
        }

        int userId = db.validatePin(username, pin);

        if (userId != -1) {
            currentUserId = userId;
            lblMessage.setForeground(Color.GREEN);
            lblMessage.setText("Access granted. Welcome!");
            new AtmMachine().setVisible(true);
            dispose();
        } else {
            lblMessage.setForeground(Color.RED);
            lblMessage.setText("Invalid username or PIN. Try again.");
            txtPin.setText("");
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AtmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new AtmLogin().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JPanel      jPanel1;
    private javax.swing.JLabel      jLabel1;
    private javax.swing.JLabel      jLabel2;
    private javax.swing.JLabel      jLabel3;
    private javax.swing.JTextField  txtUsername;
    private javax.swing.JPasswordField txtPin;
    private javax.swing.JButton     btnLogin;
    private javax.swing.JButton     btnClear;
    private javax.swing.JLabel      lblMessage;
}
