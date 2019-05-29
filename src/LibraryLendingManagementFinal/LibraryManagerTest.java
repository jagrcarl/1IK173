package LibraryLendingManagementFinal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibraryManagerTest {

    private LibraryStore ls = mock(LibraryStore.class);
    private LibraryManager cut = new LibraryManager(ls);

    @Test
    void lendItem_Mockito() {
        when(ls.getMembers())
                .thenReturn(new Member[]{
                        new Member(1, "Undergraduate", "Olle", "Karlsson", 950601, false, 0, null, 0, 0)
                });

        when(ls.getBooks())
                .thenReturn(new Book[]{
                        new Book("UML", 876421, 10, true)
                });

        assertTrue(cut.lendItem("UML", 1));

        verify(ls, Mockito.times(1)).addLoan("UML", 1);
    }

    @Test
    void returnItem_Mockito() {
        LocalDate date = LocalDate.now();
        LocalDate date1 = date.plusDays(15);
        Date loanDate = java.sql.Date.valueOf(date);
        Date returnDate = java.sql.Date.valueOf(date1);

        when(ls.getMembers())
                .thenReturn(new Member[]{
                        new Member(1, "Undergraduate", "Olle", "Karlsson", 950601, false, 0, null, 0, 0)
                });

        when(ls.getLoanedBooks())
                .thenReturn(new LoanedBooks[]{
                        new LoanedBooks("UML", 1, loanDate, returnDate, 1)
                });

        assertTrue(cut.returnItem(1, 1));
        verify(ls, Mockito.times(1)).removeLoan(1, 1, "UML");
    }

    @Test
    void subscribe_Mockito() {
        when(ls.getMembers())
                .thenReturn(new Member[]{
                        new Member(1, "Undergraduate", "Olle", "Karlsson", 950601, false, 0, null, 0, 0),

                });

        assertTrue(cut.subscribe("Rasmus", "Olsson", 910220, "Undergraduate"));
        //går inte att verifiera att ls.addmember läggs till då de unika id som skapas är slumpmässigt mellan 1000-9999
    }

    @Test
    void delete_Mockito() {
        when(ls.getMembers())
                .thenReturn(new Member[]{
                        new Member(1, "Undergraduate", "Olle", "Karlsson", 950601, false, 0, null, 0, 0)
                });

        assertTrue(cut.delete(1));
        verify(ls).removeMember(1);
    }

    @Test
    void suspend_Mockito() {
        when(ls.getMembers())
                .thenReturn(new Member[]{
                        new Member(1, "Undergraduate", "Olle", "Karlsson", 950601, false, 0, null, 3, 0)
                });

        assertTrue(cut.suspend(1));
        verify(ls).addSuspension(1);
    }
}