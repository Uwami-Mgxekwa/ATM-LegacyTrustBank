package atm;

import javax.swing.JOptionPane;

/**
 * ATM Check Balance screen.
 * Reads live balance from the shared SQLite database.
 */
public class CheckBalance extends javax.swing.JFrame {

    private final DatabaseService db;
    private final int currentUserId;

    public CheckBalance() {
        initComponents();
        db            = new DatabaseService();
        currentUserId = AtmLogin.getCurrentUserId();
        loadAndDisplayBalance();
    }

    private void loadAndDisplayBalance() {
        double balance = db.getAccountBalance(currentUserId, "Current");
        balanace.setText(String.format("%.2f", balance));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jButton10 = new javax.swing.JButton();
        jButton6  = new javax.swing.JButton();
        jButton7  = new javax.swing.JButton();
        jButton3  = new javax.swing.JButton();
        jButton9  = new javax.swing.JButton();
        jButton5  = new javax.swing.JButton();
        jButton2  = new javax.swing.JButton();
        balanace  = new javax.swing.JLabel();
        jLabel2   = new javax.swing.JLabel();
        jLabel3   = new javax.swing.JLabel();
        jLabel4   = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setBackground(new java.awt.Color(0, 51, 51));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("CHECK BALANCE");
        jButton10.addActionListener(evt -> {
            new CheckBalance().setVisible(true);
            dispose();
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 130, -1));

        jButton6.setBackground(new java.awt.Color(0, 51, 51));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("E-WALLET");
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 130, -1));

        jButton7.setBackground(new java.awt.Color(0, 51, 51));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("PIN CHANGE");
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 130, -1));

        jButton3.setBackground(new java.awt.Color(0, 51, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("WITHDRAW");
        jButton3.addActionListener(evt -> showWithdrawDialog());
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 110, -1));

        jButton9.setBackground(new java.awt.Color(0, 51, 51));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("DEPOSIT");
        jButton9.addActionListener(evt -> showDepositDialog());
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 110, -1));

        jButton5.setBackground(new java.awt.Color(0, 51, 51));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("SEND");
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, 110, -1));

        jButton2.setBackground(new java.awt.Color(0, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EXIT");
        jButton2.addActionListener(evt -> {
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to EXIT?", "Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) System.exit(0);
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, 110, -1));

        balanace.setFont(new java.awt.Font("Segoe UI Semibold", 1, 21));
        balanace.setForeground(new java.awt.Color(255, 255, 255));
        balanace.setText("0.00");
        getContentPane().add(balanace, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 180, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 21));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BALANCE : R ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 36));
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 20, 60));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atm.png")));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 700));

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Withdraw dialog — deducts from Current account, updates DB.
     */
    private void showWithdrawDialog() {
        double balance = db.getAccountBalance(currentUserId, "Current");
        String input = JOptionPane.showInputDialog(this,
                "Available: R" + String.format("%.2f", balance) + "\nEnter withdrawal amount (R):",
                "ATM Withdrawal", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.isBlank()) return;

        try {
            double amount = Double.parseDouble(input.trim());
            if (db.withdraw(currentUserId, "Current", amount)) {
                loadAndDisplayBalance();
                JOptionPane.showMessageDialog(this,
                        "R" + String.format("%.2f", amount) + " withdrawn successfully.\n"
                        + "Remaining balance: R" + String.format("%.2f", db.getAccountBalance(currentUserId, "Current")),
                        "Withdrawal Complete", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Insufficient funds or invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Deposit dialog — asks for amount, updates balance in the shared DB.
     */
    private void showDepositDialog() {        String input = JOptionPane.showInputDialog(this,
                "Enter deposit amount (R):", "ATM Deposit",
                JOptionPane.PLAIN_MESSAGE);

        if (input == null || input.isBlank()) return;

        try {
            double amount = Double.parseDouble(input.trim());
            if (db.deposit(currentUserId, "Current", amount)) {
                JOptionPane.showMessageDialog(this,
                        "R" + String.format("%.2f", amount) + " deposited successfully.",
                        "Deposit Complete", JOptionPane.INFORMATION_MESSAGE);
                loadAndDisplayBalance(); // refresh the displayed balance
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(CheckBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new AtmLogin().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JLabel      balanace;
    private javax.swing.JButton     jButton10;
    private javax.swing.JButton     jButton2;
    private javax.swing.JButton     jButton3;
    private javax.swing.JButton     jButton5;
    private javax.swing.JButton     jButton6;
    private javax.swing.JButton     jButton7;
    private javax.swing.JButton     jButton9;
    private javax.swing.JLabel      jLabel2;
    private javax.swing.JLabel      jLabel3;
    private javax.swing.JLabel      jLabel4;
}
