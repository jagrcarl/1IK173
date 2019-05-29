package Labb2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class PurchaseManagerTest {

    private PurchaseStoreStub stub = new PurchaseStoreStub();
    private PurchaseManager cut = new PurchaseManager(stub);

    @DisplayName("sumOfMonth stub")
    @Test
    void sumOfMonth() {
        stub.addPurchaseEntity(3, new Date(2010, 1, 1), 3f, "Shirt", 1);
        stub.addPurchaseEntity(2, new Date(2010, 2, 2), 3f, "Jeans", 2);
        stub.addPurchaseEntity(1, new Date(2010, 3, 3), 2f, "Jacket", 3);

        assertEquals(3f, cut.sumOfMonth(2010, 2));
    }

    @DisplayName("monthlyAverage with stub")
    @Test
    void monthlyAverage() {
        stub.addPurchaseEntity(10, new Date(2019, 9, 11), 50f, "monthlyAverage", 1);
        stub.addPurchaseEntity(9, new Date(2019, 8, 1), 50f, "monthlyAverage", 1);
        stub.addPurchaseEntity(8, new Date(2019, 7, 11), 50f, "monthlyAverage", 1);
        stub.addPurchaseEntity(7, new Date(2019, 6, 1), 100f, "monthlyAverage", 2);
        stub.addPurchaseEntity(6, new Date(2019, 5, 11), 100f, "monthlyAverage", 2);
        stub.addPurchaseEntity(5, new Date(2019, 4, 1), 100f, "monthlyAverage", 2);
        stub.addPurchaseEntity(4, new Date(2019, 3, 11), 100f, "monthlyAverage", 2);
        stub.addPurchaseEntity(3, new Date(2019, 2, 1), 300f, "monthlyAverage", 3);
        stub.addPurchaseEntity(2, new Date(2019, 1, 11), 300f, "monthlyAverage", 3);
        stub.addPurchaseEntity(1, new Date(2019, 0, 1), 300f, "monthlyAverage", 3);

        float[] arrayMA = {300, 300, 300, 100, 100, 100, 100, 50, 50, 50, 0, 0}; //lagrar månadskostnaden för ett visst år i en array

        assertArrayEquals(arrayMA, cut.monthlyAverage(2019));
    }

    @DisplayName("yearlyAveragePerCategory with stub")
    @Test
    void yearlyAveragePerCategory() {

        stub.addCategoryEntity(1, "Shirt");
        stub.addCategoryEntity(2, "Jeans");
        stub.addCategoryEntity(3, "Jacket");


        stub.addPurchaseEntity(1, new Date(2019, 3, 3), 300f, "Jacket", 3);
        stub.addPurchaseEntity(1, new Date(2019, 2, 2), 100f, "Jeans", 2);
        stub.addPurchaseEntity(1, new Date(2019, 1, 1), 50f, "Shirt", 1);
        stub.addPurchaseEntity(1, new Date(2019, 3, 3), 300f, "Jacket", 3);
        stub.addPurchaseEntity(1, new Date(2019, 2, 2), 100f, "Jeans", 2);
        stub.addPurchaseEntity(1, new Date(2019, 1, 1), 50f, "Shirt", 1);

        float[] arrayYAPC = {50, 100, 300}; //lagrar medelvärdet för kategori 1,2 o 3

        assertArrayEquals(arrayYAPC, cut.yearlyAveragePerCategory(2019));
    }

    @DisplayName("sumOfMonth with Mockito")
    @Test
    void sumOfMonth_Mockito() {
        PurchaseStore ps = mock(PurchaseStore.class);
        PurchaseManager cut = new PurchaseManager(ps);
        Date date1 = new Date(2010, 1, 1);
        Date date2 = new Date(2010, 1, 31);

        when(ps.getPurchases(date1, date2))
                .thenReturn(new Purchase[]{
                        new Purchase(3, new Date(2010, 1, 1), 3f, "Shirt", 1),
                        new Purchase(2, new Date(2010, 1, 2), 3f, "Jeans", 2),
                        new Purchase(1, new Date(2010, 1, 3), 2f, "Jacket", 3) });

        assertEquals(8f, cut.sumOfMonth(2010, 1));
    }

    @DisplayName("monthlyAverage with Mockito")
    @Test
    void monthlyAverage_Mockito() {
        PurchaseStore ps = mock(PurchaseStore.class);
        PurchaseManager cut = new PurchaseManager(ps);
        Date date1 = new Date(2019, 0, 1);
        Date date2 = new Date(2019, 11, 31);

        when(ps.getPurchases(date1, date2))
                .thenReturn(new Purchase[]{
                        new Purchase(3, new Date(2019, 3, 3), 30f, "monthlyAverage", 3),
                        new Purchase(2, new Date(2019, 2, 2), 20f, "monthlyAverage", 2),
                        new Purchase(1, new Date(2019, 1, 1), 10f, "monthlyAverage", 1)});

        float[] arrayMAMock = {0, 10, 20, 30, 0, 0, 0, 0, 0, 0, 0, 0};

        assertArrayEquals(arrayMAMock, cut.monthlyAverage(2019));
    }

    @DisplayName("yearlyAveragePerCategory with Mockito")
    @Test
    void yearlyAveragePerCategory_Mockito() {
        PurchaseStore ps = mock(PurchaseStore.class);
        PurchaseManager cut = new PurchaseManager(ps);
        Date date1 = new Date(2019,0,1);
        Date date2 = new Date(2019,11,31);

        when(ps.getPurchases(date1,date2))
                .thenReturn(new Purchase[] {
                        new Purchase(3, new Date(2019,3,3), 30f, "PurchaseDay", 3),
                        new Purchase(2, new Date(2019,2,2), 20f, "PurchaseDay", 2),
                        new Purchase(1, new Date(2019,1,1), 10f, "PurchaseDay", 1)
                });

        when(ps.getAllCategories())
                .thenReturn(new Category[]{
                        new Category(1,"Shirt"),
                        new Category(2,"Jeans"),
                        new Category(3,"Jacket")
                });

        float [] arrayYAPCMock = {10,20,30};

        assertArrayEquals(arrayYAPCMock,cut.yearlyAveragePerCategory(2019));
    }
}