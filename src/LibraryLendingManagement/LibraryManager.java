package LibraryLendingManagement;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;


public class LibraryManager implements ILibraryManager {

    private LibraryStore store;

    public LibraryManager(LibraryStore ls) {
        store = ls;
    }

    public LibraryManager() {
    }

    public void subscribe(String firstName, String lastName, int personalNumber) {

        Member[] currentMembers = store.getMembers();
        List list = Arrays.asList(currentMembers);
        Random rnd = new Random();
        int id = rnd.nextInt(9999 - 1000) + 1000;

        if (list.contains(personalNumber)) {
            System.out.println("This account is already registered ");
        } else if (!list.contains(personalNumber) && !list.contains(id)) {
            store.addMember(id, firstName, lastName, personalNumber);
        } else {
            id = rnd.nextInt(9999) + 1;
            store.addMember(id, firstName, lastName, personalNumber);
        }
    }

    public void delete(int id) {

        Member[] members = store.getMembers();
        for (Member m : members) {
            if (m.getId() == id) {
                store.removeMember(id);
            } else {
                System.out.println("The id you wrote doesn't exist in this library");
            }
        }
    }

    public void suspend(int id) {

        Member[] members = store.getMembers();
        for (Member m : members) {
            if (m.getId() == id && m.getViolations() <= 2) {
                    store.addSuspension(id);
            } else if (m.getId() == id && m.getViolations() == 3) {
                System.out.println("This member is permanently banned.");
            }
        }
    }

    public void lendItem(String title) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write your identification code");
        int id = scan.nextInt();
        String undergraduate = "Undergraduate";
        String postgraduate = "Postgraduate";
        String phd = "Phd student";
        String teacher = "Teacher";
        Member[] members = store.getMembers();
        Book[] books = store.getBooks();

        for (Book b : books) {      //iterera 9 gånger
            if (title.equalsIgnoreCase(b.getTitle()) && b.isAvailable()) {  // om titel motsvarar varje bok i booklist,dvs bara en
                for (Member m : members) {      //för varje bok, gå igenom alla members
                    if (m.getId() == id && (m.isSuspended() == false && m.getViolations() < 3)) {      //om members id finns med och ej avstängd.. gå vidare härifrån och upp borde störa ut,
                        if (m.getRole().equalsIgnoreCase(undergraduate)) {      //om rollen motsvarar undergraduate
                            if (m.getBorrowedItems() > 3) {         //om borrowed items på personen är över tre
                                System.out.println("You have borrowed to many books according to your role");       //funkar
                            } else if (m.getBorrowedItems() < 3) {     // om det är under eller lika med tre.// om titel är lika med inparameter
                                if (b.getLibraryItem() > 0) {           // om libraryitem inte är noll
                                    store.addLoan(title, id);
                                }
                            }
                        } else if (m.getRole().equalsIgnoreCase(postgraduate)) {
                            if (m.getBorrowedItems() > 5) {
                                System.out.println("You have borrowed to many books according to your role");       //funkar
                            } else if (m.getBorrowedItems() < 5) {
                                if (b.getLibraryItem() > 0) {
                                    store.addLoan(title, id);
                                }
                            }
                        } else if (m.getRole().equalsIgnoreCase(phd)) {
                            if (m.getBorrowedItems() > 7) {
                                System.out.println("You have borrowed to many books according to your role");       //funkar
                            } else if (m.getBorrowedItems() < 7) {
                                if (b.getLibraryItem() > 0) {
                                    store.addLoan(title, id);
                                }
                            }
                        } else if (m.getRole().equalsIgnoreCase(teacher)) {
                            if (m.getBorrowedItems() > 10) {
                                System.out.println("You have borrowed to many books according to your role");       //funkar
                            } else if (m.getBorrowedItems() < 10) {
                                if (b.getLibraryItem() > 0) {
                                    store.addLoan(title, id);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void returnItem(int id, int loanId) {

        Member [] members = store.getMembers();

        LoanedBooks [] loanedBooks = store.getLoanedBooks();
        Date currentDate  =  Date.valueOf(LocalDate.now());

        for (LoanedBooks lb: loanedBooks) {
            if (lb.getId() == id && lb.getLoanID() == loanId && lb.getEndDate().compareTo(currentDate) < 0) {
                String title = lb.getTitle();
                store.removeLoanDelayed(id,loanId,title);
                for (Member m: members) {
                    if (m.getId() == id && m.getViolations() == 2) {
                        store.banMember(id);
                    }
                }
            }

            for (Member m : members) {
                if (m.getSuspensions() == 2) {
                    store.addSuspension(id);
                }
            }

            if (lb.getId() == id && lb.getLoanID() == loanId && lb.getLoanID() == loanId && lb.getEndDate().compareTo(currentDate) >= 0) {     //när id motsvarar ett loanedbook objekt och loanId motsvarar ett objekts loanid
                String title = lb.getTitle();
                store.removeLoan(id, loanId, title);
            }
        }

        for (Member m: members) {
            if (m.getId() == id && m.getViolations() > 2) {
                suspend(id);
            }
        }
        store.updateBooks();
    }

    public void checkSuspendDate() {
        Date currentDate  = Date.valueOf(LocalDate.now());

        Member[] members = store.getMembers();
        for (Member m : members) {
            if (m.getSuspendDate() != null && m.getSuspendDate().compareTo((currentDate)) < 0) {
                System.out.println(m.getFName() + " " + m.getLName() + " with Id: " + m.getId() + " suspended date has passed.");
                int id = m.getId();
                    store.changeSuspended(id);
                }
            }
        }
    }

