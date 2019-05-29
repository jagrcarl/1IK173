package LibraryLendingManagement;


import java.util.Scanner;


public class Menu extends LibraryManager {

    public static void main(String[] args) {

        LibraryStore ls = new LibraryStore();
        LibraryManager lm = new LibraryManager(ls);
        Scanner scan = new Scanner(System.in);
        int val;
        String val2;
        String val3;
        int val4;

    do {
        lm.checkSuspendDate();
        System.out.println("\nThe Library Lending System");// do while sats som upprepas sålänge val inte är 0.
        System.out.println("========\n");
        System.out.println("1. Show all current available books");
        System.out.println("2. Show registered members");
        System.out.println("3. Subscribe to the library");
        System.out.println("4. Unsubscribe from the library");
        System.out.println("5. Lend a book");
        System.out.println("6. Suspend a member");
        System.out.println("7. Return a book");
        System.out.println("0. Exit the application\n");
        System.out.print("Make a choice --> ");
        val = scan.nextInt();

        if (val == 1) {

            System.out.println("Books in the Library");
            System.out.println("====================");
            Book[] books = ls.getBooks();
            for (Book b : books) {
                if (b.isAvailable())
                    System.out.println(b.getTitle() + ": ISBN " + b.getISBN() + ": ID " + b.getLibraryItem());
            }

        }
        if (val == 2) {
            System.out.println("Members in the Library DB");
            System.out.println("=========================");
            Member[] members = ls.getMembers();
            for (Member m : members) {
                System.out.println(m.getFName() + " " + m.getLName() + ", ID: " + m.getId());
            }
        }

        if (val == 3) {
            System.out.println("Subscribe new member");
            System.out.println("========\n");
            System.out.println("Write your first name");
            val2 = scan.next();
            System.out.println("Write your last name");
            val3 = scan.next();
            System.out.println("Write your personal number");
            val = scan.nextInt();
            lm.subscribe(val2, val3, val);
        }

        if (val == 4) {
            System.out.println("Unsubscribe from the application");
            System.out.println("=================================");
            System.out.println("Write your identification code");
            val = scan.nextInt();
            lm.delete(val);
        }

        if (val == 5) {
            System.out.println("Lend a book");
            System.out.println("===========");
            System.out.println("Write the title of the book you want to borrow");
            val2 = scan.nextLine();
            lm.lendItem(val2);

        }

        if (val == 6) {
            System.out.println("Suspend a member from the library for 15 days");
            System.out.println("==============================================");
            System.out.println("Enter ID-code for the member you want to suspend");
            val = scan.nextInt();
            lm.suspend(val);
            System.out.println("The member with id: " + val + " has been suspended for 15 days");
        }

        if (val == 7) {
            System.out.println("Return a book");
            System.out.println("=============");
            System.out.println("Write your identification code");
            val = scan.nextInt();
            System.out.println("Write the loan ID for the book you borrowed");
            val4 = scan.nextInt();
            lm.returnItem(val,val4);
        }



    } while (val != 0);

    }
}


