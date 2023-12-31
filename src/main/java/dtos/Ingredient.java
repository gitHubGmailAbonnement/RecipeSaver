package dtos;

public class Ingredient {
    private String quantity;
    private String unity;
    private String ingreident;
    private String notes;

    public Ingredient(String quantity, String unity, String ingreident)
    {
        this.ingreident = ingreident;
        this.notes = "NA";
        this.quantity = quantity;
        this.unity = unity;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getIngreident() {
        return ingreident;
    }

    public void setIngreident(String ingreident) {
        this.ingreident = ingreident;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
