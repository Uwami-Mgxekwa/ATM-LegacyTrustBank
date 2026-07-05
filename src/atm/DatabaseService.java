package atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

/**
 * Shared database service for the ATM application.
 * Connects to the same SQLite database as the LegacyTrustBank app at:
 * C:\ProgramData\LegacyTrustBank\bankdata.db
 */
public class DatabaseService {

    private static final String DB_DIR = "C:\\ProgramData\\LegacyTrustBank";
    private static final String DB_URL = "jdbc:sqlite:" + DB_DIR + "\\bankdata.db";

    /**
     * Establishes a connection to the shared SQLite database.
     * Creates the ProgramData directory automatically on first run.
     */
    private Connection connect() throws SQLException {
        java.io.File dir = new java.io.File(DB_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Connection conn = DriverManager.getConnection(DB_URL);
        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON");
        }
        return conn;
    }

    /**
     * Creates all required tables if they do not already exist.
     * Safe to call on every startup — uses CREATE TABLE IF NOT EXISTS.
     */
    public void initializeDatabase() {
        String createUsers = """
            CREATE TABLE IF NOT EXISTS users (
                user_id       INTEGER PRIMARY KEY AUTOINCREMENT,
                username      TEXT    NOT NULL UNIQUE,
                email         TEXT    NOT NULL UNIQUE,
                password_hash TEXT    NOT NULL,
                full_name     TEXT    NOT NULL,
                created_at    TEXT    NOT NULL
            )
            """;

        String createAccounts = """
            CREATE TABLE IF NOT EXISTS accounts (
                account_id   INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id      INTEGER NOT NULL,
                account_type TEXT    NOT NULL,
                balance      REAL    NOT NULL DEFAULT 0.0,
                FOREIGN KEY (user_id) REFERENCES users(user_id)
            )
            """;

        String createTransactions = """
            CREATE TABLE IF NOT EXISTS transactions (
                transaction_id   INTEGER PRIMARY KEY AUTOINCREMENT,
                account_id       INTEGER NOT NULL,
                transaction_type TEXT    NOT NULL,
                amount           REAL    NOT NULL,
                description      TEXT,
                transaction_date TEXT    NOT NULL,
                FOREIGN KEY (account_id) REFERENCES accounts(account_id)
            )
            """;

        try (Connection conn = connect(); Statement st = conn.createStatement()) {
            st.execute(createUsers);
            st.execute(createAccounts);
            st.execute(createTransactions);
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    /**
     * Validates user PIN (password) for ATM login.
     * @param username The username on the card.
     * @param pin      The PIN entered at the ATM.
     * @return The user ID if valid, -1 if invalid.
     */
    public int validatePin(String username, String pin) {
        String sql = "SELECT user_id, password_hash FROM users WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                if (pin.equals(storedHash)) {
                    return rs.getInt("user_id");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error validating PIN: " + e.getMessage());
        }

        return -1;
    }

    /**
     * Gets the full name of a user by their ID.
     */
    public String getUserFullName(int userId) {
        String sql = "SELECT full_name FROM users WHERE user_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("full_name");
            }

        } catch (SQLException e) {
            System.out.println("Error getting user full name: " + e.getMessage());
        }

        return "User";
    }

    /**
     * Fetches the balance for a specific account type for the given user.
     * @param userId      The logged-in user's ID.
     * @param accountType "Current" or "Savings"
     */
    public double getAccountBalance(int userId, String accountType) {
        String sql = "SELECT balance FROM accounts WHERE user_id = ? AND account_type = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, accountType);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching balance: " + e.getMessage());
        }

        return 0.0;
    }

    /**
     * Updates the balance of a specific account.
     */
    public void updateAccountBalance(int userId, String accountType, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE user_id = ? AND account_type = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, userId);
            pstmt.setString(3, accountType);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }

    /**
     * Records a transaction in the database.
     */
    public void recordTransaction(int userId, String accountType,
                                  String transactionType, double amount, String description) {
        String findSql  = "SELECT account_id FROM accounts WHERE user_id = ? AND account_type = ?";
        String insertSql = "INSERT INTO transactions "
                + "(account_id, transaction_type, amount, description, transaction_date) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect()) {
            int accountId = -1;

            try (PreparedStatement find = conn.prepareStatement(findSql)) {
                find.setInt(1, userId);
                find.setString(2, accountType);
                ResultSet rs = find.executeQuery();
                if (rs.next()) {
                    accountId = rs.getInt("account_id");
                }
            }

            if (accountId == -1) {
                System.out.println("Account not found for user " + userId);
                return;
            }

            try (PreparedStatement insert = conn.prepareStatement(insertSql)) {
                insert.setInt(1, accountId);
                insert.setString(2, transactionType);
                insert.setDouble(3, amount);
                insert.setString(4, description);
                insert.setString(5, LocalDateTime.now().toString());
                insert.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error recording transaction: " + e.getMessage());
        }
    }

    /**
     * Gets a user's username by their ID.
     */
    public String getUsernameById(int userId) {
        String sql = "SELECT username FROM users WHERE user_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getString("username");
        } catch (SQLException e) {
            System.out.println("Error getting username: " + e.getMessage());
        }
        return "";
    }

    /**
     * Gets a user's ID by their username. Returns -1 if not found.
     */
    public int getUserIdByUsername(String username) {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("user_id");
        } catch (SQLException e) {
            System.out.println("Error getting user ID: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Deposits money into a user's account.
     * Convenience method — updates balance and records the transaction.
     */
    public boolean deposit(int userId, String accountType, double amount) {
        if (amount <= 0) return false;
        double current = getAccountBalance(userId, accountType);
        updateAccountBalance(userId, accountType, current + amount);
        recordTransaction(userId, accountType, "Deposit", amount, "ATM Deposit");
        return true;
    }

    /**
     * Withdraws money from a user's account.
     * Returns false if insufficient funds.
     */
    public boolean withdraw(int userId, String accountType, double amount) {
        if (amount <= 0) return false;
        double current = getAccountBalance(userId, accountType);
        if (amount > current) return false;
        updateAccountBalance(userId, accountType, current - amount);
        recordTransaction(userId, accountType, "Withdrawal", amount, "ATM Withdrawal");
        return true;
    }
}
