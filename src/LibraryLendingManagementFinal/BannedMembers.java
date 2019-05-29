package LibraryLendingManagementFinal;

public class BannedMembers {
    private String firstname;
    private String lastname;
    private int personnumber;


    public BannedMembers() {

    }

    public BannedMembers(String firstname, String lastname, int personnumber) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPersonnumber(personnumber);
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPersonnumber() {
        return personnumber;
    }

    public void setPersonnumber(int personnumber) {
        this.personnumber = personnumber;
    }
}
