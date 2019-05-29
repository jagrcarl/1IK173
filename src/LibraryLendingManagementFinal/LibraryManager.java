package LibraryLendingManagementFinal;

import java.time.LocalDate;
import java.sql.Date;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LibraryManager implements ILibraryManager {

    private Logger logger = LogManager.getLogger(LibraryManager.class.getName());
    private LibraryStore store;

    public LibraryManager(LibraryStore ls) {
        store = ls;
    }

    public LibraryManager() {
    }

    public boolean subscribe(String firstName, String lastName, int personalNumber, String role) {
        Member[] currentMembers = store.getMembers();
        Random rnd = new Random();
        int id = rnd.nextInt(9999 - 1000) + 1000;
        int counter = currentMembers.length;
        for (Member m : currentMembers) {
            if (m.getPNumber() != personalNumber) {
                counter--;
            }
        }
        if (counter == 0) {
            store.addMember(id, firstName, lastName, personalNumber, role);
            logger.info("A new member of the library has been created. The ID is: " + id + " and is a " + role);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Member[] members = store.getMembers();
        for (Member m : members) {
            if (m.getId() == id) {
                store.removeMember(id);
                logger.info("The member with ID " + id + " has been unsubscribed from the library");
                return true;
            }
        }
        return false;
    }

    public boolean suspend(int id) {
        Member[] members = store.getMembers();
        for (Member m : members) {
            if (m.getId() == id && m.getViolations() > 2) {
                store.addSuspension(id);
                logger.info("The member with the id of " + id + " has been suspended for 15 days due to his violations");
                return true;
            }
        }
        return false;
    }

    public boolean lendItem(String title, int id) {
        Member[] members = store.getMembers();
        Book[] books = store.getBooks();

        for (Book b : books) {
            if (title.equalsIgnoreCase(b.getTitle()) && b.isAvailable()) {
                for (Member m : members) {
                    if (m.getId() == id && (!m.isSuspended() && m.getViolations() < 3)) {
                        if (m.getRole().equalsIgnoreCase("Undergraduate")) {
                            if (m.getBorrowedItems() >= 3) {
                                return false;
                            } else if (m.getBorrowedItems() < 3) {
                                if (b.getLibraryItem() > 0) {
                                    store.addLoan(title, id);
                                    logger.info("The book " + title + " has been borrowed by a " + m.getRole() + " with the ID of: " + m.getId());
                                    return true;
                                }
                            }
                        } else if (m.getRole().equalsIgnoreCase("Postgraduate")) {
                            if (m.getBorrowedItems() >= 5) {
                                return false;
                            } else if (m.getBorrowedItems() < 5) {
                                if (b.getLibraryItem() > 0) {
                                    store.addLoan(title, id);
                                    logger.info("The book " + title + " has been borrowed by a " + m.getRole() + " with the ID of: " + m.getId());
                                    return true;
                                }
                            }
                        } else if (m.getRole().equalsIgnoreCase("Phd student")) {
                            if (m.getBorrowedItems() >= 7) {
                                return false;
                            } else if (m.getBorrowedItems() < 7) {
                                if (b.getLibraryItem() > 0) {
                                    store.addLoan(title, id);
                                    logger.info("The book " + title + " has been borrowed by a " + m.getRole() + " with the ID of: " + m.getId());
                                    return true;
                                }
                            }
                        } else if (m.getRole().equalsIgnoreCase("Teacher")) {
                            if (m.getBorrowedItems() >= 11) {
                                return false;
                            } else if (m.getBorrowedItems() < 11) {
                                if (b.getLibraryItem() > 0) {
                                    store.addLoan(title, id);
                                    logger.info("The book " + title + " has been borrowed by a " + m.getRole() + " with the ID of: " + m.getId());
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean returnItem(int id, int loanId) {
        Member[] members = store.getMembers();
        LoanedBooks[] loanedBooks = store.getLoanedBooks();
        Date currentDate = Date.valueOf(LocalDate.now());

        for (LoanedBooks lb : loanedBooks) {
            if (lb.getId() == id && lb.getLoanId() == loanId && lb.getReturnDate().compareTo(currentDate) < 0) {
                String title = lb.getTitle();
                store.removeLoanDelayed(id, loanId, title);
                logger.info("The book " + title + " with loanID: " + loanId + " was returned later than expected, The member with id " + id + " therefore got 1 strike");
                for (Member m : members) {
                    if (m.getStrikes() == 2) {
                        store.addSuspension(id);
                        logger.info("The member with id " + m.getId() + " was given a suspension from the library due to having two strikes");
                    }
                }

                for (Member m : members) {
                    if (m.getId() == id && m.getViolations() == 2) {
                        store.banMember(id);
                        logger.info("The member with the id of " + id + " was banned due to have broken the number of violations possible");
                    }
                }
                return true;
            }

            if (lb.getId() == id && lb.getLoanId() == loanId && lb.getLoanId() == loanId && lb.getReturnDate().compareTo(currentDate) >= 0) {     //när id motsvarar ett loanedbook objekt och loanId motsvarar ett objekts loanid
                String title = lb.getTitle();
                store.removeLoan(id, loanId, title);
                logger.info("The book " + title + " with loanID: " + loanId + " was returned in time by a member with the id of " + id);
                return true;
            }
        }
        store.updateBooks();
        return false;
    }

    public void checkSuspendDate() {
        Date currentDate = Date.valueOf(LocalDate.now());
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