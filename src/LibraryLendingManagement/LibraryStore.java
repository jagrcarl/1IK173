package LibraryLendingManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class LibraryStore implements ILibraryStore {


    private List<Member> memberList = null;
    private List<Book> bookList = null;
    private List<LoanedBooks> loanedBooksList = null;
    private List<BannedMembers> bannedMemberList;
    private Scanner scan = new Scanner(System.in);

    public LibraryStore() {
        memberList = new ArrayList<Member>();
        bookList = new ArrayList<Book>();
        loanedBooksList = new ArrayList<LoanedBooks>();
        bannedMemberList = new ArrayList<BannedMembers>();

    }

   /* public void addBook(String Title, int Isbn, int LibraryItem, boolean Available) {
        Book book = new Book(Title, Isbn, LibraryItem, Available);
        bookList.add(book);
    }*/

    public void addMember(int id, String firstname, String lastname, int personalnum) {
        memberList.clear();
        boolean IsSuspended = false;
        int BorrowedItems = 0;
        int Violations = 0;
        System.out.println("Enter Role:");
        String role = scan.nextLine();


        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/librarydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root", "121pnv")) {
            System.out.println("Connected");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO member VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, role);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setInt(5, personalnum);
            ps.setBoolean(6, IsSuspended);
            ps.setInt(7, BorrowedItems);
            ps.setDate(8, null);
            ps.setInt(9, Violations);
            ps.executeUpdate();


        } catch (SQLException ex) {
            System.out.println("Något gick fel");
        }
    }


    public void removeMember(int id) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");

            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM member WHERE member.id = " + id + ";");
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book[] getBooks() {
        bookList.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the drivers.");
        }
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM book");

            while (result.next()) {
                Book book = new Book(result.getString("Title"), result.getInt("isbn"), result.getInt("LibraryCopies"), result.getBoolean("Available"));
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Book[] books = new Book[bookList.size()];
        return bookList.toArray(books);
    }


    public Member[] getMembers() {

        memberList.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the drivers.");
        }
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM member");

            while (result.next()) {
                Member member = new Member(result.getInt("id"), result.getString("role"),
                        result.getString("Fname"), result.getString("Ename"),
                        result.getInt("Pnummer"), result.getBoolean("IsSuspended"),
                        result.getInt("BorrowedItems"), result.getDate("suspendDate"), result.getInt("Violations"), result.getInt("Suspensions"));
                memberList.add(member);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Member[] members = new Member[memberList.size()];

        return memberList.toArray(members);
    }

    public void addSuspension(int id)  {
        loanedBooksList.clear();
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");

            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE member SET isSuspended = TRUE WHERE member.id = " + id + ";");
            statement.executeUpdate("UPDATE member SET suspendDate = DATE_ADD(CURRENT_DATE, INTERVAL 15 DAY) WHERE member.id = " + id + ";");
            statement.executeUpdate("UPDATE member SET Violations = Violations + 1 WHERE member.id = " + id + ";");
            statement.executeUpdate("UPDATE member SET Suspensions = 0 WHERE member.id = " + id + ";");
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLoan(String Title, int id) {
        Random rnd = new Random();
        int loanID = rnd.nextInt(9999) - 1;
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");
            //funkar hur bra som helst
            PreparedStatement ps = conn.prepareStatement("UPDATE member SET BorrowedItems = BorrowedItems + ? WHERE member.id = ?;");
            ps.setInt(1, 1);
            ps.setInt(2, id);
            ps.executeUpdate();


            //funkar hur bra som helst
            ps = conn.prepareStatement("UPDATE  book SET libraryCopies = libraryCopies ? WHERE book.title = ?;");
            ps.setInt(1, -1);
            ps.setString(2, Title);
            ps.executeUpdate();


            ps = conn.prepareStatement("INSERT INTO loanedbooks VALUES (?, ?, CURRENT_DATE , DATE_ADD(CURRENT_DATE, INTERVAL 15 DAY), ?)");
            ps.setString(1, Title);
            ps.setInt(2, id);
            ps.setInt(3, loanID);
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLoan(int id, int loanId, String title) {
        loanedBooksList.clear();
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM loanedbooks WHERE loanId = " + loanId + ";");
            statement.executeUpdate("UPDATE book SET libraryCopies = libraryCopies + 1 WHERE title = '"+ title + "';");
            statement.executeUpdate("UPDATE member SET BorrowedItems = BorrowedItems - 1 WHERE id = " + id + ";");
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLoanDelayed(int id, int loanId, String title) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM loanedbooks WHERE loanId = " + loanId + ";");
            st.executeUpdate("UPDATE book SET libraryCopies = libraryCopies + 1 WHERE title = '"+ title + "';");
            st.executeUpdate("UPDATE member SET BorrowedItems = BorrowedItems - 1 WHERE id = " + id + ";");
            st.executeUpdate("UPDATE member SET Suspensions = Suspensions + 1 WHERE id = " + id + ";");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBooks(){
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");
            Statement statement = conn.createStatement();

            statement.executeUpdate("UPDATE book SET Available = 0 WHERE libraryCopies = 0");
            statement.executeUpdate("UPDATE book SET Available = 1 WHERE libraryCopies > 0");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LoanedBooks[] getLoanedBooks() {
        loanedBooksList.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded");
        } catch (ClassNotFoundException e) {

            System.out.println("Failed to load the drivers.");
        }
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM loanedbooks");

            while (result.next()) {
                LoanedBooks loanedBook = new LoanedBooks(result.getString("Title"), result.getInt("id"), result.getDate("startDate"), result.getDate("endDate"), result.getInt("loanId"));
                loanedBooksList.add(loanedBook);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        LoanedBooks[] loanedBooks = new LoanedBooks[loanedBooksList.size()];
        return loanedBooksList.toArray(loanedBooks);
    }

    public void changeSuspended(int id) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");

            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE member SET isSuspended = false WHERE id = " + id + ";");
            statement.executeUpdate("UPDATE member SET suspendDate = null WHERE id = " + id + ";");
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void banMember(int id) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/librarydb?useUnicode=trueCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "121pnv"
                )) {
            System.out.println("Connected to the database\n");

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT Fname,Ename,Pnummer FROM member WHERE id = " + id + ";");
            while (result.next()){
                String firstname = result.getString("Fname");
                String lastname = result.getString("Ename");
                int personnumber = result.getInt("Pnummer");


                statement.executeUpdate("DELETE FROM member WHERE id = " + id + ";");

                PreparedStatement ps = conn.prepareStatement("INSERT INTO bannedmembers VALUES (?,?,?)");
                ps.setString(1,firstname);
                ps.setString(2,lastname);
                ps.setInt(3,personnumber);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    }

