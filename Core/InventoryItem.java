package core;

public abstract class InventoryItem {
    private int id;
    private String name;
    private int quantity;
    private double price;

    public InventoryItem(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name.toLowerCase();
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean purchase(int quantity) {
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
            return true;
        } else {
            return false;
        }
    }

    public abstract String getDetails();
}
