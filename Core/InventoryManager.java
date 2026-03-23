package core;

import java.util.ArrayList;

public class InventoryManager {

    private ArrayList<InventoryItem> inventory;
    private double totalRevenue;

    public InventoryManager() {
        inventory = new ArrayList<>();
        totalRevenue = 0.0;
    }

    public void addItem(InventoryItem item) {
        for (InventoryItem existingItem : inventory) {
            if (existingItem.getId() == item.getId()) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        inventory.add(item);
    }

    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }

    public void removeItem(int id) {
        inventory.removeIf(item -> item.getId() == id);
    }

    public void buyItem(String name, int quantity) {
        for (InventoryItem item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                if (item.purchase(quantity)) {
                    totalRevenue += item.getPrice() * quantity;
                    System.out.println("Purchase successful!");
                } else {
                    System.out.println("Not enough stock.");
                }
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public ArrayList<InventoryItem> getLowStockItems(int threshold) {
        ArrayList<InventoryItem> lowStock = new ArrayList<>();

        for (InventoryItem item : inventory) {
            if (item.getQuantity() < threshold) {
                lowStock.add(item);
            }
        }

        return lowStock;
    }

    public ArrayList<PerishableItem> getExpiredItems() {
        ArrayList<PerishableItem> expired = new ArrayList<>();

        for (InventoryItem item : inventory) {
            if (item instanceof PerishableItem) {
                PerishableItem p = (PerishableItem) item;
                if (p.isExpired()) {
                    expired.add(p);
                }
            }
        }

        return expired;
    }
}
