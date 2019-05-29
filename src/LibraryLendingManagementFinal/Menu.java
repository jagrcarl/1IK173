package LibraryLendingManagementFinal;

import java.util.Scanner;

public class Menu extends LibraryManager {
    public static void main(String[] args) {

        LibraryStore ls = new LibraryStore();
        LibraryManager lm = new LibraryManager(ls);
        Scanner scan = new Scanner(System.in);
        int val;
        int val4;
        String val2;
        String val3;
        String val5;

        do {
            lm.checkSuspendDate();
            System.out.println("\nThe Library Lending System");
            System.out.println("===========================\n");
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
                        System.out.println(b.getTitle() + ": ISBN " + b.getISBN() + ": In stock: " + b.getLibraryItem());
                }
            }
            if (val == 2) {
                System.out.println("Members in the Library");
                System.out.println("======================");
                Member[] members = ls.getMembers();
                for (Member m : members) {
                    System.out.println(m.getFName() + " " + m.getLName() + ", ID: " + m.getId());
                }
            }

            if (val == 3) {
                System.out.println("Subscribe new member");
                System.out.println("====================\n");
                System.out.print("Write your first name: ");
                val2 = scan.next();
                System.out.print("Write your last name: ");
                val3 = scan.next();
                System.out.print("Write your personal number: ");
                val = scan.nextInt();
                scan.nextLine();
                System.out.print("Write your role: ");
                val5 = scan.nextLine();

                if (lm.subscribe(val2, val3, val, val5)) {
                    System.out.println("You are now subscribed");
                } else if (!lm.subscribe(val2, val3, val, val5)) {
                    System.out.println("Your subscription did not go through");
                }
            }

            if (val == 4) {
                System.out.println("Unsubscribe from the application");
                System.out.println("================================");
                System.out.print("Write your identification code: ");
                val = scan.nextInt();

                if (lm.delete(val)) {
                    System.out.println("You are now unsubscribed from the library");
                } else if (!lm.delete(val)) {
                    System.out.println("Your removal didn't go through due to invalid identification code");
                }
            }

            if (val == 5) {
                System.out.println("Lend a book");
                System.out.println("===========");
                scan.nextLine();
                System.out.print("Write the title of the book you want to borrow: ");
                val2 = scan.nextLine();
                System.out.print("Write your identification code: ");
                val = scan.nextInt();
                if (lm.lendItem(val2, val)) {
                    System.out.println("You have borrowed the book " + val2);
                } else if (!lm.lendItem(val2, val)) {
                    System.out.println("Your lending of the book " + val2 + " didn't go through due to your limitation");
                }
            }

            if (val == 6) {
                System.out.println("Suspend a member from the library for 15 days");
                System.out.println("=============================================");
                System.out.print("Enter ID-code for the member you want to suspend: ");
                val = scan.nextInt();
                if (lm.suspend(val)) {
                    System.out.println("The member with id: " + val + " has been suspended for 15 days");
                } else if (!lm.suspend(val)) {
                    System.out.println("The action of suspending the member with id: " + val + " didn't go through");
                }
            }

            if (val == 7) {
                System.out.println("Return a book");
                System.out.println("=============");
                System.out.print("Write your identification code: ");
                val = scan.nextInt();
                System.out.print("Write the loan ID for the book you borrowed: ");
                val4 = scan.nextInt();
                if (lm.returnItem(val, val4)) {
                    System.out.println("The action of returning the book was successful");
                } else if (!lm.returnItem(val, val4)) {
                    System.out.println("The action of returning the book didn't go through due to wrong loan ID input");
                }
            }
        } while (val != 0);
    }
}