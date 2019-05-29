package LibraryLendingManagementFinal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class LibraryStore implements ILibraryStore {

    private static Logger logger = LogManager.getLogger(LibraryStore.class.getName());
    private List<Member> memberList;
    private List<Book> bookList;
    private List<LoanedBooks> loanedBooksList;

    public LibraryStore() {
        memberList = new ArrayList<>();
        bookList = new ArrayList<>();
        loanedBooksList = new ArrayList<>();
    }

    public void addBook(String Title, int Isbn, int LibraryItem, boolean Available) {
        Book book = new Book(Title, Isbn, LibraryItem, Available);
        bookList.add(book);
    }

    public void addMember(int id, String firstname, String lastname, int personalnum, String role) {
        memberList.clear();
        boolean IsSuspended = false;
        int BorrowedItems = 0;
        int Violations = 0;

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/librarydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root", "121pnv")) {

            PreparedStatement ps = conn.prepareStatement("INSERT INTO member VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, role);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setInt(5, personalnum);
            ps.setBoolean(6, IsSuspended);
            ps.setInt(7, BorrowedItems);
            ps.setDate(8, null);
            ps.setInt(9, Violations);
            ps.setInt(10, 0);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    public void removeMember(int id) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM member WHERE member.id = " + id + ";");
            statement.close();

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    public void removeLoan(int id, int loanId, String title) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {

            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM loanedbooks WHERE loanId = " + loanId + ";");
            statement.executeUpdate("UPDATE book SET libraryCopies = libraryCopies + 1 WHERE title = '" + title + "';");
            statement.executeUpdate("UPDATE member SET BorrowedItems = BorrowedItems - 1 WHERE id = " + id + ";");
            statement.close();

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    public void removeLoanDelayed(int id, int loanId, String title) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM loanedbooks WHERE loanId = " + loanId + ";");
            st.executeUpdate("UPDATE book SET libraryCopies = libraryCopies + 1 WHERE title = '" + title + "';");
            st.executeUpdate("UPDATE member SET BorrowedItems = BorrowedItems - 1, Strikes = Strikes + 1 WHERE id = " + id + ";");
            st.close();

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    public Book[] getBooks() {
        bookList.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to load the drivers to reach getBooks");
        }
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM book");
            while (result.next()) {
                Book book = new Book(result.getString("Title"), result.getInt("isbn"), result.getInt("LibraryCopies"), result.getBoolean("Available"));
                bookList.add(book);
            }

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
        Book[] books = new Book[bookList.size()];
        return bookList.toArray(books);
    }

    public LoanedBooks[] getLoanedBooks() {
        loanedBooksList.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to load the drivers to reach getLoanedBooks");
        }
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM loanedbooks");

            while (result.next()) {
                LoanedBooks loanedBook = new LoanedBooks(result.getString("title"), result.getInt("id"), result.getDate("startDate"), result.getDate("endDate"), result.getInt("loanId"));
                loanedBooksList.add(loanedBook);
            }

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
        LoanedBooks[] loanedBooks = new LoanedBooks[loanedBooksList.size()];
        return loanedBooksList.toArray(loanedBooks);
    }

    public Member[] getMembers() {
        memberList.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to load the drivers to reach getMembers");
        }
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM member");

            while (result.next()) {
                Member member = new Member(result.getInt("id"), result.getString("role"),
                        result.getString("Fname"), result.getString("Ename"),
                        result.getInt("Pnummer"), result.getBoolean("IsSuspended"),
                        result.getInt("BorrowedItems"), result.getDate("suspendDate"), result.getInt("Violations"), result.getInt("Strikes"));
                memberList.add(member);
            }

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
        Member[] members = new Member[memberList.size()];
        return memberList.toArray(members);
    }

    public void addSuspension(int id) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE member SET isSuspended = true WHERE member.id = " + id + ";");
            statement.executeUpdate("UPDATE member SET suspendDate = DATE_ADD(CURRENT_DATE, INTERVAL 15 DAY) WHERE member.id = " + id + ";");
            statement.executeUpdate("UPDATE member SET Strikes = 0 WHERE id = " + id + ";");
            statement.executeUpdate("UPDATE member SET Violations = Violations + 1 WHERE member.id = " + id + ";");
            statement.close();

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    public void addLoan(String title, int id) {
        Random rnd = new Random();
        int loanID = rnd.nextInt(9999 - 1000) + 1000;
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            PreparedStatement ps = conn.prepareStatement("UPDATE member SET BorrowedItems = BorrowedItems + ? WHERE member.id = ?;");
            ps.setInt(1, 1);
            ps.setInt(2, id);
            ps.executeUpdate();

            ps = conn.prepareStatement("UPDATE book SET libraryCopies = libraryCopies ? WHERE book.title = ?;");
            ps.setInt(1, -1);
            ps.setString(2, title);
            ps.executeUpdate();

            ps = conn.prepareStatement("INSERT INTO loanedbooks VALUES (?, ?, CURRENT_DATE , DATE_ADD(CURRENT_DATE, INTERVAL 15 DAY), ?)");
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.setInt(3, loanID);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    public void updateBooks() {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {

            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE book SET Available = 0 WHERE libraryCopies = 0");
            statement.executeUpdate("UPDATE book SET Available = 1 WHERE libraryCopies > 0");

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    public void changeSuspended(int id) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE member SET isSuspended = false WHERE id = " + id + ";");
            statement.executeUpdate("UPDATE member SET suspendDate = null WHERE id = " + id + ";");
            statement.executeUpdate("UPDATE member SET Violations = 0 WHERE id = " + id + ";");
            statement.close();

        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    public void banMember(int id) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT Fname,Ename,Pnummer FROM member WHERE id = " + id + ";");
            while (result.next()) {
                String firstname = result.getString("Fname");
                String lastname = result.getString("Ename");
                int personnumber = result.getInt("Pnummer");

                PreparedStatement ps = conn.prepareStatement("INSERT INTO bannedmembers VALUES (?,?,?)");
                ps.setString(1, firstname);
                ps.setString(2, lastname);
                ps.setInt(3, personnumber);
                ps.executeUpdate();
            }
            result.close();
        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }
}