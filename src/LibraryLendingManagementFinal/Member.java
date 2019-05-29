package LibraryLendingManagementFinal;

import java.util.Date;

public class Member {

    private int Id;
    private String Role;
    private String FName;
    private String LName;
    private int PNumber;
    private boolean IsSuspended;
    private int BorrowedItems;
    private Date suspendDate;
    private int Violations;
    private int Strikes;

    public Member() {
    }

    public Member(int Id, String Role, String FName, String LName, int PNumber, boolean IsSuspended, int BorrowedItems, Date suspendDate, int Violations, int Strikes) {
        this.setId(Id);
        this.setRole(Role);
        this.setFName(FName);
        this.setLName(LName);
        this.setPNumber(PNumber);
        this.setIsSuspended(IsSuspended);
        this.setBorrowedItems(BorrowedItems);
        this.setSuspendDate(suspendDate);
        this.setViolations(Violations);
        this.setStrikes(Strikes);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public int getPNumber() {
        return PNumber;
    }

    public void setPNumber(int PNumber) {
        this.PNumber = PNumber;
    }

    public boolean isSuspended() {
        return IsSuspended;
    }

    public void setIsSuspended(boolean suspended) {
        IsSuspended = suspended;
    }

    public int getBorrowedItems() {
        return BorrowedItems;
    }

    public void setBorrowedItems(int borrowedItems) {
        BorrowedItems = borrowedItems;
    }

    public Date getSuspendDate() {
        return suspendDate;
    }

    public void setSuspendDate(Date suspendDate) {
        this.suspendDate = suspendDate;
    }

    public int getViolations() {
        return Violations;
    }

    public void setViolations(int violations) {
        Violations = violations;
    }

    public int getStrikes() {
        return Strikes;
    }

    public void setStrikes(int Strikes) {
        this.Strikes = Strikes;
    }
}
