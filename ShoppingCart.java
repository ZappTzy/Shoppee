import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Product {
    String name;
    double price;
    int quantity;
    String category;

    public Product(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
}

public class ShoppingCart {
    LinkedList<Product> cart = new LinkedList<>();
    Map<String, List<Product>> productCategories = new HashMap<>();

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void updateCart(String productName, int newQuantity) {
        for (Product product : cart) {
            if (product.name.equals(productName)) {
                product.quantity = newQuantity;
                return;
            }
        }
        System.out.println("Product not found in the cart.");
    }

    public void deleteFromCart(String productName) {
        for (Product product : cart) {
            if (product.name.equals(productName)) {
                cart.remove(product);
                return;
            }
        }
        System.out.println("Product not found in the cart.");
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            for (Product product : cart) {
                System.out.println("Product: " + product.name + " - Price: $" + product.price + " - Quantity: " + product.quantity);
            }
        }
    }

    public Product searchCart(String productName) {
        for (Product product : cart) {
            if (product.name.equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public void addProductToCategory(Product product) {
        String category = product.category;
        if (!productCategories.containsKey(category)) {
            productCategories.put(category, new LinkedList<>());
        }
        productCategories.get(category).add(product);
    }

    public List<Product> getProductsInCategory(String category) {
        if (productCategories.containsKey(category)) {
            return productCategories.get(category);
        }
        return new LinkedList<>();
    }

    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nShopping Cart Menu:");
                System.out.println("1. Add to Cart");
                System.out.println("2. Update Cart");
                System.out.println("3. Delete from Cart");
                System.out.println("4. View Cart");
                System.out.println("5. Search Cart");
                System.out.println("6. Add Product to Category");
                System.out.println("7. View Products in Category");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter product price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter product quantity: ");
                        int quantity = scanner.nextInt();
                        System.out.print("Enter product category: ");
                        String category = scanner.nextLine(); // Handle category input with spaces
                        Product product = new Product(name, price, quantity, category);
                        shoppingCart.addToCart(product);
                        shoppingCart.addProductToCategory(product);
                        break;
                    case 2:
                        System.out.print("Enter product name to update: ");
                        String updateName = scanner.nextLine();
                        System.out.print("Enter new quantity: ");
                        int newQuantity = scanner.nextInt();
                        shoppingCart.updateCart(updateName, newQuantity);
                        break;
                    case 3:
                        System.out.print("Enter product name to delete: ");
                        String deleteName = scanner.nextLine();
                        shoppingCart.deleteFromCart(deleteName);
                        break;
                    case 4:
                        shoppingCart.viewCart();
                        break;
                    case 5:
                        System.out.print("Enter product name to search: ");
                        String searchName = scanner.nextLine();
                        Product foundProduct = shoppingCart.searchCart(searchName);
                        if (foundProduct != null) {
                            System.out.println("Product found: " + foundProduct.name);
                        } else {
                            System.out.println("Product not found in the cart.");
                        }
                        break;
                    case 6:
                        System.out.print("Enter product name to categorize: ");
                        String categoryName = scanner.nextLine();
                        System.out.print("Enter category name: ");
                        String productCategory = scanner.nextLine();
                        Product categoryProduct = new Product(categoryName, 0, 0, productCategory);
                        shoppingCart.addProductToCategory(categoryProduct);
                        break;
                    case 7:
                        System.out.print("Enter category name to view products: ");
                        String viewCategory = scanner.nextLine();
                        List<Product> productsInCategory = shoppingCart.getProductsInCategory(viewCategory);
                        if (!productsInCategory.isEmpty()) {
                            System.out.println("Products in Category: " + viewCategory);
                            for (Product p : productsInCategory) {
                                System.out.println("Product: " + p.name + " - Price: $" + p.price + " - Quantity: " + p.quantity);
                            }
                        } else {
                            System.out.println("No products found in the category.");
                        }
                        break;
                    case 8:
                        System.out.println("Thank you for Shopping. Have a nice day!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}