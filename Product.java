/**
 * CN4001 Software Development - Self-Directed Lab Task
 * Product Class Implementation
 * Student ID:U3084820
 * 
 * This class represents a product in a shop inventory system.
 * It follows the exact UML specification from the assignment.
 */

public class Product {
    
    // ===== PRIVATE ATTRIBUTES =====
    private String name;
    private int stockLevel;
    private double price;
    
    // ===== CONSTRUCTOR =====
    /**
     * Constructor to initialize product with name, stock level, and price
     * @param name Product name
     * @param stockLevel Initial stock quantity
     * @param price Price per unit
     */
    public Product(String name, int stockLevel, double price) {
        this.name = name;
        this.stockLevel = stockLevel;
        this.price = price;
    }
    
    // ===== BUSINESS METHODS =====
    
    /**
     * Adds stock to the product inventory
     * @param quantity Number of items bought
     * @return Total value of purchased stock
     */
    public double buyStock(int quantity) {
        if (quantity > 0) {
            stockLevel += quantity;
            return quantity * price;
        }
        return 0.0;
    }
    
    /**
     * Sells items from inventory if enough stock available
     * @param quantity Number of items to sell
     * @return true if sale successful, false if insufficient stock
     */
    public boolean sell(int quantity) {
        if (quantity > 0 && stockLevel >= quantity) {
            stockLevel -= quantity;
            return true;
        }
        return false;
    }
    
    /**
     * Updates the product price
     * @param newPrice New price per unit
     */
    public void setPrice(double newPrice) {
        if (newPrice > 0) {
            price = newPrice;
        }
    }
    
    // ===== GETTER METHODS =====
    
    /**
     * @return Product name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return Current stock level
     */
    public int getStockLevel() {
        return stockLevel;
    }
    
    /**
     * @return Current price per unit
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Calculates total value of current stock
     * @return Total stock value (stockLevel × price)
     */
    public double getTotalValue() {
        return stockLevel * price;
    }
    
    /**
     * Returns formatted string for display
     */
    @Override
    public String toString() {
        return String.format("%-15s | Stock: %-5d | Price: £%-8.2f | Value: £%-8.2f", 
                           name, stockLevel, price, getTotalValue());
    }
}