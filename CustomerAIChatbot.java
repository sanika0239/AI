import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerAIChatbot {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Knowledge Base using HashMap
        HashMap<String, Integer> products = new HashMap<>();

        products.put("laptop", 55000);
        products.put("mobile", 25000);
        products.put("headphones", 2000);
        products.put("smartwatch", 5000);
        products.put("tablet", 30000);

        System.out.println("====================================");
        System.out.println("      AI Shopping Assistant");
        System.out.println("====================================");
        System.out.println("Available products:");
        System.out.println("\nType 'exit' to end chat.\n");

        while (true) {

            System.out.print("You: ");
            String input = sc.nextLine().toLowerCase();

            // Exit
            if (input.equals("exit")) {
                System.out.println("Bot: Thank you for visiting our store!");
                break;
            }

            // Greetings
            else if (input.contains("hello") || input.contains("hi")) {
                System.out.println("Bot: Hello! How can I help you today?");
            }

            // Product list
            else if (input.contains("product")) {

                System.out.println("Bot: We have the following products:");

                for (String product : products.keySet()) {
                    System.out.println("- " + product);
                }
            }

            // Price Inquiry
            else if (input.contains("price")) {

                boolean found = false;

                for (Map.Entry<String, Integer> entry : products.entrySet()) {

                    if (input.contains(entry.getKey())) {

                        System.out.println("Bot: Price of " +
                                entry.getKey() + " is ₹" +
                                entry.getValue());

                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("Bot: Please mention the product name.");
                    System.out.println("Bot: Example - price of laptop");
                }
            }

            // Delivery information
            else if (input.contains("delivery")) {
                System.out.println("Bot: Delivery takes 3 to 5 working days.");
            }

            // Payment information
            else if (input.contains("payment")) {
                System.out.println("Bot: We accept UPI, Debit Card, Credit Card, and Net Banking.");
            }

            // Order support
            else if (input.contains("order")) {
                System.out.println("Bot: make your order from our store.");
                System.out.println("if already order ask to tract ex- track myorder");
            }
            // Thanks response
            else if (input.contains("thank")) {
                System.out.println("Bot: You're welcome!");
            }

            else if (input.contains("track")) {
                System.out.println("Bot: Please share your Order_id for tracking.");
            }

            else if (input.contains("order_id")) {
                System.out.println("Bot: We let you to know about this after some time.");
            }
            // Unknown query
            else {
                System.out.println("Bot: Sorry, I couldn't understand.");
                System.out.println("Bot: You can ask about products, prices, delivery, or payment.");
            }
        }

        sc.close();
    }
}