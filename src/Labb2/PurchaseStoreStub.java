package Labb2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseStoreStub extends PurchaseStore {

    private List<Purchase> entries = null;
    private List<Category> categories = null;

    public PurchaseStoreStub() {
        entries = new ArrayList<Purchase>();
        categories = new ArrayList<Category>();
    }

    public void addPurchaseEntity(int Id, Date Date, Float Amount, String Comment, int CategoryId) {
        Purchase p = new Purchase(Id, Date, Amount, Comment, CategoryId);
        entries.add(p);
    }

    public void addCategoryEntity(int Id, String Description) {
        Category c = new Category(Id, Description);
        categories.add(c);
    }

    @Override
    public Purchase[] getPurchases(Date startDate, Date endDate) {
        Purchase[] arrayP = new Purchase[entries.size()];
        return entries.toArray(arrayP);
    }

    @Override
    public Purchase[] getPurchasesByCategory(Date startDate, Date endDate, int catId) {
        Purchase[] arrayP = new Purchase[entries.size()];
        Purchase[] arrayP2 = new Purchase[arrayP.length];
        int counter = 0;
        for (Purchase p : arrayP) {
            if (startDate.compareTo(p.getDate()) >= 0 && endDate.compareTo(p.getDate()) <= 0 && p.getCategoryId() == catId) {
                arrayP2[counter] = p;
                counter++;
            }
        }
        return entries.toArray(arrayP2);
    }

    @Override
    public Category[] getAllCategories() {
        Category[] arrayC = new Category[categories.size()];
        return categories.toArray(arrayC);
    }
}
