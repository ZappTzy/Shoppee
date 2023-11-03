import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

public class ShoppingCart {

    static class Product {
        String name;
        double price;
        int quantity;

        public Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> cart = new LinkedList<>();

        int choice;
        do {
            System.out.println("\nShopping Cart");
            System.out.println("1. Add to cart");
            System.out.println("2. Update cart");
            System.out.println("3. Delete cart");
            System.out.println("4. View cart");
            System.out.println("5. Search cart");
            System.out.println("6. Exit");
            System.out.print("Select your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addToCart(scanner, cart);
                    break;
                case 2:
                    updateCart(scanner, cart);
                    break;
                case 3:
                    deleteCart(scanner, cart);
                    break;
                case 4:
                    viewCart(cart);
                    break;
                case 5:
                    searchCart(scanner, cart);
                    break;
                case 6:
                    System.out.println("Thank You for shopping, Have a nice day!.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private static void addToCart(Scanner scanner, List<Product> cart) {
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();

        cart.add(new Product(name, price, quantity));
        System.out.println("Product added to cart.");
    }

    private static void updateCart(Scanner scanner, List<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.print("Enter product name to update: ");
        String name = scanner.next();

        boolean isUpdated = false;
        for (Product product : cart) {
            if (product.name.equalsIgnoreCase(name)) {
                System.out.print("Enter new product price: ");
                double price = scanner.nextDouble();
                System.out.print("Enter new product quantity: ");
                int quantity = scanner.nextInt();
                product.price = price;
                product.quantity = quantity;
                isUpdated = true;
                break;
            }
        }

        if (isUpdated) {
            System.out.println("Product updated in cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    private static void deleteCart(Scanner scanner, List<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.print("Enter product name to delete: ");
        String name = scanner.next();

        boolean isDeleted = cart.removeIf(product -> product.name.equalsIgnoreCase(name));

        if (isDeleted) {
            System.out.println("Product deleted from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    private static void viewCart(List<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\nProduct Name - Price - Quantity");
        double total = 0;
        for (Product product : cart) {
            System.out.println(product.name + " - " + product.price + " - " + product.quantity);
            total += product.price * product.quantity;
        }

        System.out.println("Total Price: " + total);
    }

    private static void searchCart(Scanner scanner, List<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.print("Enter product name to search: ");
        String name = scanner.next();

        boolean isFound = false;
        for (Product product : cart) {
            if (product.name.equalsIgnoreCase(name)) {
                System.out.println("Product found in cart.");
                System.out.println("Product Name - Price - Quantity");
                System.out.println(product.name + " - " + product.price + " - " + product.quantity);
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            System.out.println("Product not found in cart.");
        }
    }
}
