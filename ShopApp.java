/**
 * CN4001 Software Development - Self-Directed Lab Task
 * Shop Application with Menu-Driven Interface
 * Student ID: U3084820
 * 
 * A complete shop management system with 5 products,
 * menu-driven interface, input validation, and all required features.
 */

import java.util.Scanner;

public class ShopApp {
    
    // ===== CONSTANTS =====
    private static final int NUM_PRODUCTS = 5;
    
    // ===== MAIN METHOD =====
    public static void main(String[] args) {
        
        // ===== INITIALIZE PRODUCT ARRAY =====
        Product[] products = new Product[NUM_PRODUCTS];
        products[0] = new Product("Laptop", 15, 899.99);
        products[1] = new Product("Smartphone", 30, 599.99);
        products[2] = new Product("Headphones", 50, 89.99);
        products[3] = new Product("Keyboard", 40, 49.99);
        products[4] = new Product("Monitor", 20, 249.99);
        
        Scanner scanner = new Scanner(System.in);
        
        // ===== DISPLAY HEADER WITH STUDENT ID =====
        displayHeader();
        
        boolean exitProgram = false;
        
        // ===== MAIN PROGRAM LOOP =====
        while (!exitProgram) {
            displayMainMenu();
            int choice = getValidChoice(scanner, 1, 6);
            
            switch (choice) {
                case 1:
                    displayAllProducts(products);
                    break;
                case 2:
                    handleBuyStock(products, scanner);
                    break;
                case 3:
                    handleSellStock(products, scanner);
                    break;
                case 4:
                    handleReprice(products, scanner);
                    break;
                case 5:
                    displayTotalValue(products);
                    break;
                case 6:
                    exitProgram = true;
                    System.out.println("\n========================================");
                    System.out.println("   Thank you for using Shop System!");
                    System.out.println("           Program Terminated.");
                    System.out.println("========================================");
                    break;
            }
            
            if (!exitProgram) {
                pressEnterToContinue(scanner);
            }
        }
        
        scanner.close();
    }
    
    // ===== DISPLAY METHODS =====
    
    /**
     * Displays decorative header with student ID
     */
    private static void displayHeader() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║                SHOP MANAGEMENT SYSTEM              ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  CN4001 - Software Development                     ║");
        System.out.println("║  Student ID:   U3084820                       ║");
        System.out.println("║  Self-Directed Lab Task                            ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    /**
     * Displays the main menu options
     */
    private static void displayMainMenu() {
        System.out.println("\n┌────────────────────────────────────────────┐");
        System.out.println("│                MAIN MENU                   │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("│  1. 📋 Display All Products                │");
        System.out.println("│  2. 🛒 Buy Stock for a Product             │");
        System.out.println("│  3. 💰 Sell Product Stock                  │");
        System.out.println("│  4. 📈 Re-price a Product                  │");
        System.out.println("│  5. 🏪 Display Total Shop Value            │");
        System.out.println("│  6. 🚪 Exit Program                        │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.print("  Enter your choice (1-6): ");
    }
    
    /**
     * Displays all products in a formatted table
     */
    private static void displayAllProducts(Product[] products) {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                          PRODUCT INVENTORY                           │");
        System.out.println("├─────┬─────────────────┬──────────┬────────────┬──────────────────────┤");
        System.out.println("│ No. │ Product Name    │ Stock    │ Price      │ Total Value          │");
        System.out.println("├─────┼─────────────────┼──────────┼────────────┼──────────────────────┤");
        
        for (int i = 0; i < products.length; i++) {
            System.out.printf("│ %-3d │ %-15s │ %-8d │ £%-9.2f │ £%-18.2f │\n",
                i + 1,
                products[i].getName(),
                products[i].getStockLevel(),
                products[i].getPrice(),
                products[i].getTotalValue());
        }
        
        System.out.println("└─────┴─────────────────┴──────────┴────────────┴──────────────────────┘");
    }
    
    /**
     * Displays total value of all products in shop
     */
    private static void displayTotalValue(Product[] products) {
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│                  TOTAL SHOP VALUE                      │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        
        double grandTotal = 0;
        for (int i = 0; i < products.length; i++) {
            double value = products[i].getTotalValue();
            grandTotal += value;
            System.out.printf("│ %-15s: £%-30.2f │\n", 
                products[i].getName(), value);
        }
        
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│ GRAND TOTAL: £%-38.2f │\n", grandTotal);
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }
    
    // ===== BUSINESS LOGIC METHODS =====
    
    /**
     * Handles buying stock for a selected product
     */
    private static void handleBuyStock(Product[] products, Scanner scanner) {
        System.out.println("\n────── BUY STOCK ──────");
        displayProductList(products);
        
        int productIndex = selectProduct(products, scanner);
        if (productIndex == -1) return;
        
        System.out.print("Enter quantity to buy: ");
        int quantity = getPositiveInteger(scanner);
        
        double cost = products[productIndex].buyStock(quantity);
        System.out.println("\n✅ SUCCESS: Stock Purchased!");
        System.out.printf("   Product: %s\n", products[productIndex].getName());
        System.out.printf("   Quantity: %d\n", quantity);
        System.out.printf("   Cost: £%.2f\n", cost);
        System.out.printf("   New Stock Level: %d\n", products[productIndex].getStockLevel());
    }
    
    /**
     * Handles selling stock for a selected product
     */
    private static void handleSellStock(Product[] products, Scanner scanner) {
        System.out.println("\n────── SELL STOCK ──────");
        displayProductList(products);
        
        int productIndex = selectProduct(products, scanner);
        if (productIndex == -1) return;
        
        System.out.print("Enter quantity to sell: ");
        int quantity = getPositiveInteger(scanner);
        
        boolean success = products[productIndex].sell(quantity);
        
        if (success) {
            System.out.println("\n✅ SUCCESS: Stock Sold!");
            System.out.printf("   Product: %s\n", products[productIndex].getName());
            System.out.printf("   Quantity: %d\n", quantity);
            System.out.printf("   New Stock Level: %d\n", products[productIndex].getStockLevel());
        } else {
            System.out.println("\n❌ FAILED: Sale Not Completed!");
            System.out.printf("   Product: %s\n", products[productIndex].getName());
            System.out.printf("   Requested: %d\n", quantity);
            System.out.printf("   Available: %d\n", products[productIndex].getStockLevel());
        }
    }
    
    /**
     * Handles re-pricing a selected product
     */
    private static void handleReprice(Product[] products, Scanner scanner) {
        System.out.println("\n────── RE-PRICE PRODUCT ──────");
        displayProductList(products);
        
        int productIndex = selectProduct(products, scanner);
        if (productIndex == -1) return;
        
        double oldPrice = products[productIndex].getPrice();
        System.out.printf("Current price of %s: £%.2f\n", 
            products[productIndex].getName(), oldPrice);
        
        System.out.print("Enter new price: £");
        double newPrice = getPositiveDouble(scanner);
        
        products[productIndex].setPrice(newPrice);
        System.out.println("\n✅ SUCCESS: Price Updated!");
        System.out.printf("   Product: %s\n", products[productIndex].getName());
        System.out.printf("   Old Price: £%.2f → New Price: £%.2f\n", oldPrice, newPrice);
    }
    
    // ===== HELPER METHODS =====
    
    /**
     * Displays simple product list for selection
     */
    private static void displayProductList(Product[] products) {
        System.out.println("Select a product:");
        for (int i = 0; i < products.length; i++) {
            System.out.printf("  %d. %s (Stock: %d, Price: £%.2f)\n",
                i + 1,
                products[i].getName(),
                products[i].getStockLevel(),
                products[i].getPrice());
        }
        System.out.print("Enter product number (1-5) or 0 to cancel: ");
    }
    
    /**
     * Gets valid product selection from user
     */
    private static int selectProduct(Product[] products, Scanner scanner) {
        while (true) {
            int choice = getValidChoice(scanner, 0, products.length);
            if (choice == 0) {
                System.out.println("Operation cancelled.");
                return -1;
            }
            return choice - 1;
        }
    }
    
    /**
     * Gets valid menu choice with input validation
     */
    private static int getValidChoice(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.printf("Please enter a number between %d and %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }
    
    /**
     * Gets positive integer with validation
     */
    private static int getPositiveInteger(Scanner scanner) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.print("Please enter a positive number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a whole number: ");
            }
        }
    }
    
    /**
     * Gets positive double with validation
     */
    private static double getPositiveDouble(Scanner scanner) {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.print("Please enter a positive amount: £");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid amount: £");
            }
        }
    }
    
    /**
     * Pauses program until Enter is pressed
     */
    private static void pressEnterToContinue(Scanner scanner) {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}