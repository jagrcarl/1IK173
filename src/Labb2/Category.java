package Labb2;

public class Category extends Purchase {

    private int Id;
    private String Description;

    public Category(int Id, String Description) {

        this.setId(Id);
        this.setDescription(Description);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
