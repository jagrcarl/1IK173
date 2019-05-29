package LibraryLendingManagementFinal;

import java.util.Date;

public class LoanedBooks {

    private String title;
    private int id;
    private Date loanDate;
    private Date returnDate;
    private int loanId;

    public LoanedBooks() {

    }

    public LoanedBooks(String title, int id, Date loanDate, Date returnDate, int loanId) {
        this.setId(id);
        this.setTitle(title);
        this.setLoanDate(loanDate);
        this.setReturnDate(returnDate);
        this.setLoanId(loanId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }
}