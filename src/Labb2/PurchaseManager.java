package Labb2;

import java.util.Date;

public class PurchaseManager implements IPurchaseManager {

    private PurchaseStore pstore;

    public PurchaseManager(PurchaseStore ps) {
        pstore = ps;
    }

    @Override //sumOfMonth ska beräkna den totala summan för alla köp som gjort under en månad vid ett visst år.
    public float sumOfMonth(int year, int month) {
        Date date1 = new Date(year, month, 1); // Date överstruken p.g.a. gammal "notation", går dock att använda felfritt.
        Date date2 = new Date(year, month, 31);
        Purchase[] purchaseSOM = pstore.getPurchases(date1, date2);
        float sum = 0;

        for (Purchase p : purchaseSOM) {
            if (p.getDate().compareTo(date1) >= 0 && p.getDate().compareTo(date2) <= 0) {
                sum = sum + p.getAmount();
            }
        }
        return sum;
    }

    @Override   //monthlyAverage ska beräkna vad varje månads snittkostnad varit vid ett visst år
    public float[] monthlyAverage(int year) {
        Date date1 = new Date(year, 0, 1);
        Date date2 = new Date(year, 11, 31);
        Purchase[] purchaseMA = pstore.getPurchases(date1, date2);
        float[] avg = new float[12];
        int counter = 0;

        for (int i = 0; i < 12; i++) {
            for (Purchase p : purchaseMA) {
                if (p.getDate().compareTo(date1) >= 0 && p.getDate().compareTo(date2) <= 0 && p.getDate().getMonth() == i) {
                    avg[i] = avg[i] + p.getAmount();
                    counter++;
                }
            }
            if (counter == 0) {
                avg[i] = 0.0f;
            } else {
                avg[i] = avg[i] / counter;
                counter = 0;
            }
        }
        return avg;
    }

    @Override //den genomsnittliga kostnaden för en kategori under ett år
    public float[] yearlyAveragePerCategory(int year) {
        Date date1 = new Date(year, 0, 1);
        Date date2 = new Date(year, 11, 31);
        Purchase[] purchaseYAPC = pstore.getPurchases(date1, date2);
        Category[] categories = pstore.getAllCategories(); //samlar alla kategorier

        float[] avg = new float[categories.length];
        int counter = 0;
        int index = 0;

        for (Category c : categories) {
            for (Purchase p : purchaseYAPC)
                if (p.getDate().compareTo(date1) >= 0 && p.getDate().compareTo(date2) <= 0 && p.getCategoryId() == c.getId()) {
                    avg[index] = avg[index] + p.getAmount();
                    counter++;
                }
            avg[index] = avg[index] / counter;
                index++;

            counter = 0;
        }
        return avg;
    }
}