package core;

import java.time.LocalDate;

public class PerishableItem extends InventoryItem {
    private LocalDate expirationDate;

    public PerishableItem(int id, String name, int quantity, double price, LocalDate expirationDate) {
        super(id, name, quantity, price);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public String getDetails() {
        return getName() + " | Qty: " + getQuantity() +
                " | Price: $" + getPrice() +
                " | Expiry: " + expirationDate;
    }
}
