package LibraryLendingManagement;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryManagerTest {

    private LibraryStore store = new LibraryStore();
    private LibraryManager cut = new LibraryManager(store);

    @Test
    void test() {

        Member[] members = store.getMembers();

        for (int i=0;i<members.length;i++) {
            System.out.println(members[i].getFName());
        }

        cut.delete(4);
        Member [] updatedMembers = store.getMembers();
        //får ytterliggare de som finns plus dem nya
        for (int i=0; i<updatedMembers.length;i++) {
            System.out.println(updatedMembers[i].getFName());
        }

    }





    @Test
    void delete_Mockito() {
        Member[] members = store.getMembers();          //hämtar fyra st
        for (int i=0; i < members.length;i++) {
            System.out.println(members[i].getId());     //skriver ut deras id:n
        }
        cut.delete(4);          //kör delete metoden, den kör removeMember. den hämtar getmembers, dvs 4.
        int num = store.getMembers().length;
        System.out.println(num);
    }


    @Test
    void suspend_Mockito() {

    }


    @Test
    void lendItem_Mockito() {
        LibraryStore ls = mock(LibraryStore.class);     //ls når store metoderna
        LibraryManager cut = new LibraryManager(ls);    //cut når manager metoderna

        when(ls.getMembers())
                .thenReturn(new Member[] {
                        new Member(1234,"undergraduate","lars","larsson",950829,false,0, new Date (2020, 11, 21),0, 0)
                });

        when(ls.getBooks())
                .thenReturn(new Book[] {
                        new Book("Narnia",123456,0,true)
                });
        //cut.lendItem(1234,"undergraduate",123456);
    }

    @Test
    void returnItem_Mockito() {

    }
}
