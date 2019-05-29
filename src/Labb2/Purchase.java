package Labb2;

import java.util.Date;

public class Purchase {
    private int Id;
    private Date Date;
    private float Amount;
    private String Comment;
    private int CategoryId;

    public Purchase() {
    }

    public Purchase(int Id, Date Date, float Amount, String Comment, int CategoryId) {
        this.setId(Id);
        this.setDate(Date);
        this.setAmount(Amount);
        this.setComment(Comment);
        this.setCategoryId(CategoryId);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        this.Date = date;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        this.Amount = amount;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        this.Comment = comment;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        this.CategoryId = categoryId;
    }
}
