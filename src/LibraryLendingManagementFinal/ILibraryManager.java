package LibraryLendingManagementFinal;

public interface ILibraryManager {

    boolean lendItem(String title, int id);

    boolean returnItem(int id, int loanId);

    boolean subscribe(String firstName, String lastName, int personalNumber, String role);

    boolean delete(int id);

    boolean suspend(int id);
}