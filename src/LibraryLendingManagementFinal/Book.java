package LibraryLendingManagementFinal;

public class Book {
    private String Title;
    private int ISBN;
    private int LibraryItem;
    private boolean available;

    public Book() {         //default constructor
    }

    public Book(String Title, int Isbn, int LibraryItem, boolean Available) {
        this.setTitle(Title);
        this.setISBN(Isbn);
        this.setLibraryItem(LibraryItem);
        this.setAvailable(Available);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int isbn) {
        this.ISBN = isbn;
    }

    public int getLibraryItem() {
        return LibraryItem;
    }

    public void setLibraryItem(int libraryItem) {
        LibraryItem = libraryItem;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}