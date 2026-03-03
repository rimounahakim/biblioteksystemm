package org.example.dao;

import org.example.DatabaseConnection;
import org.example.model.Bok;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BokDAO {

    // 1. Visa alla böcker
    public List<Bok> hamtaAllaBocker() {

        List<Bok> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY title";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                books.add(new Bok(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }


    // 2. Sök bok
    public List<Bok> sokBok(String keyword) {

        List<Bok> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? ORDER BY title";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(new Bok(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    // 3. Låna bok + skapa
    public void lanaBok(int bookId, int userId) {

        String checkSql = "SELECT available FROM books WHERE id = ?";
        String updateSql = "UPDATE books SET available = false WHERE id = ?";
        String insertLoan = "INSERT INTO loans (user_id, book_id, loan_date, return_date) " +
                "VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY))";

        try (Connection conn = DatabaseConnection.getConnection()) {

            // Check availability
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getBoolean("available")) {

                // Update book
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                // Insert loan
                PreparedStatement loanStmt = conn.prepareStatement(insertLoan);
                loanStmt.setInt(1, userId);
                loanStmt.setInt(2, bookId);
                loanStmt.executeUpdate();

                System.out.println("Boken är nu utlånad.");

            } else {
                System.out.println("Boken är inte tillgänglig.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Mina lån
    public void visaMinaLan(int userId) {

        String sql = """
                SELECT b.title, b.author, l.loan_date, l.return_date
                FROM loans l
                JOIN books b ON l.book_id = b.id
                WHERE l.user_id = ?
                ORDER BY l.loan_date DESC
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n=== Mina lån ===");

            while (rs.next()) {
                System.out.println(
                        rs.getString("title") + " | "
                                + rs.getString("author") + " | "
                                + "Loaned: " + rs.getDate("loan_date") + " | "
                                + "Return: " + rs.getDate("return_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}