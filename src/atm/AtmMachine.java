package atm;

import javax.swing.JOptionPane;

public class AtmMachine extends javax.swing.JFrame {

    private final DatabaseService db;
    private final int currentUserId;

    public AtmMachine() {
        initComponents();
        db            = new DatabaseService();
        currentUserId = AtmLogin.getCurrentUserId();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(0, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EXIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, 110, -1));

        jButton3.setBackground(new java.awt.Color(0, 51, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("WITHDRAW");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 110, -1));

        jButton5.setBackground(new java.awt.Color(0, 51, 51));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("SEND");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSendDialog();
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, 110, -1));

        jButton6.setBackground(new java.awt.Color(0, 51, 51));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("E-WALLET");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "E-Wallet coming soon.", "E-Wallet", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 130, -1));

        jButton7.setBackground(new java.awt.Color(0, 51, 51));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("PIN CHANGE");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPinChangeDialog();
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 130, -1));

        jButton10.setBackground(new java.awt.Color(0, 51, 51));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("CHECK BALANCE");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 130, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 21)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PLEASE SELECT YOUR TRANSACTION ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 20, 60));

        jButton9.setBackground(new java.awt.Color(0, 51, 51));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("DEPOSIT");
        jButton9.setMaximumSize(new java.awt.Dimension(93, 23));
        jButton9.setMinimumSize(new java.awt.Dimension(93, 23));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDepositDialog();
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 110, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atm.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        new CheckBalance().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        showWithdrawDialog();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to EXIT ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    // ── Deposit ──────────────────────────────────────────────────────────────
    private void showDepositDialog() {
        String[] accountTypes = {"Current", "Savings"};
        String accountType = (String) JOptionPane.showInputDialog(
                this, "Select account to deposit into:",
                "ATM Deposit", JOptionPane.PLAIN_MESSAGE,
                null, accountTypes, accountTypes[0]);
        if (accountType == null) return;

        String input = JOptionPane.showInputDialog(this,
                "Enter deposit amount (R):", "ATM Deposit", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.isBlank()) return;

        try {
            double amount = Double.parseDouble(input.trim());
            if (db.deposit(currentUserId, accountType, amount)) {
                double newBal = db.getAccountBalance(currentUserId, accountType);
                JOptionPane.showMessageDialog(this,
                        "R" + String.format("%.2f", amount) + " deposited into " + accountType + " account.\n"
                        + "New balance: R" + String.format("%.2f", newBal),
                        "Deposit Complete", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── Withdraw ─────────────────────────────────────────────────────────────
    private void showWithdrawDialog() {
        String[] accountTypes = {"Current", "Savings"};
        String accountType = (String) JOptionPane.showInputDialog(
                this, "Select account to withdraw from:",
                "ATM Withdrawal", JOptionPane.PLAIN_MESSAGE,
                null, accountTypes, accountTypes[0]);
        if (accountType == null) return;

        double balance = db.getAccountBalance(currentUserId, accountType);
        String input = JOptionPane.showInputDialog(this,
                "Available: R" + String.format("%.2f", balance) + "\nEnter withdrawal amount (R):",
                "ATM Withdrawal", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.isBlank()) return;

        try {
            double amount = Double.parseDouble(input.trim());
            if (db.withdraw(currentUserId, accountType, amount)) {
                double newBal = db.getAccountBalance(currentUserId, accountType);
                JOptionPane.showMessageDialog(this,
                        "R" + String.format("%.2f", amount) + " withdrawn from " + accountType + " account.\n"
                        + "Remaining balance: R" + String.format("%.2f", newBal),
                        "Withdrawal Complete", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Insufficient funds or invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── Send Money ───────────────────────────────────────────────────────────
    private void showSendDialog() {
        String recipient = JOptionPane.showInputDialog(this,
                "Enter recipient username:", "ATM Send Money", JOptionPane.PLAIN_MESSAGE);
        if (recipient == null || recipient.isBlank()) return;

        if (recipient.equalsIgnoreCase(db.getUsernameById(currentUserId))) {
            JOptionPane.showMessageDialog(this, "You cannot send money to yourself.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int recipientId = db.getUserIdByUsername(recipient);
        if (recipientId == -1) {
            JOptionPane.showMessageDialog(this, "User \"" + recipient + "\" not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String input = JOptionPane.showInputDialog(this,
                "Enter amount to send (R):", "ATM Send Money", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.isBlank()) return;

        try {
            double amount = Double.parseDouble(input.trim());
            double senderBal = db.getAccountBalance(currentUserId, "Current");

            if (amount <= 0 || amount > senderBal) {
                JOptionPane.showMessageDialog(this,
                        amount <= 0 ? "Enter a positive amount." : "Insufficient funds.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Deduct from sender
            db.updateAccountBalance(currentUserId, "Current", senderBal - amount);
            db.recordTransaction(currentUserId, "Current", "Sent", amount, "ATM Send to " + recipient);

            // Credit recipient
            double recipientBal = db.getAccountBalance(recipientId, "Current");
            db.updateAccountBalance(recipientId, "Current", recipientBal + amount);
            db.recordTransaction(recipientId, "Current", "Received", amount, "ATM Received from " + db.getUsernameById(currentUserId));

            JOptionPane.showMessageDialog(this,
                    "R" + String.format("%.2f", amount) + " sent to " + recipient + " successfully.",
                    "Send Complete", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── PIN Change ───────────────────────────────────────────────────────────
    private void showPinChangeDialog() {
        JOptionPane.showMessageDialog(this,
                "PIN Change is available in the Legacy Trust Bank app under your account settings.",
                "PIN Change", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AtmMachine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtmMachine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtmMachine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtmMachine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Entry point is the login screen, not the machine directly
                new AtmLogin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
