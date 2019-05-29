package LibraryLendingManagement;

public interface ILibraryManager {

    void lendItem(String title);

    void returnItem(int id, int loanId);

    void subscribe(String firstName, String lastName, int personalNumber);

    void delete(int id);

    void suspend(int id);
}
