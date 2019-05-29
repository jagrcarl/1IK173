package LibraryLendingManagement;

import jdk.jshell.execution.LoaderDelegate;

import java.sql.Date;

public class LoanedBooks {

    private String title;
    private int id;
    private Date startDate;
    private Date endDate;
    private int loanID;

    public LoanedBooks() {

    }


    public LoanedBooks(String title, int id, Date startDate, Date endDate, int loanID) {
        this.setId(id);
        this.setTitle(title);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setLoanID(loanID);
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

