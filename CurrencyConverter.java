import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Project 4: Currency Converter
 * Converts an amount from a base currency to a target currency
 * using predefined exchange rates (relative to USD).
 */
public class Currency_converter {

    // Exchange rates relative to 1 USD
    private static final Map<String, Double> rates = new HashMap<>();

    static {
        rates.put("USD", 1.0);
        rates.put("INR", 83.20);
        rates.put("EUR", 0.92);
        rates.put("GBP", 0.79);
        rates.put("JPY", 156.30);
        rates.put("AUD", 1.51);
        rates.put("CAD", 1.36);
        rates.put("CNY", 7.24);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("           CURRENCY CONVERTER");
        System.out.println("=========================================");
        System.out.println("Available currencies: " + rates.keySet());
        System.out.println();

        // Get base currency
        String baseCurrency = getValidCurrency(sc, "Enter base currency (from): ");

        // Get target currency
        String targetCurrency = getValidCurrency(sc, "Enter target currency (to): ");

        // Get amount
        double amount = getValidAmount(sc);

        // Perform conversion
        double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency);

        // Display result
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
        System.out.println("-----------------------------------------");

        sc.close();
    }

    /**
     * Prompts the user until a valid, supported currency code is entered.
     */
    private static String getValidCurrency(Scanner sc, String prompt) {
        String currency;
        while (true) {
            System.out.print(prompt);
            currency = sc.next().trim().toUpperCase();
            if (rates.containsKey(currency)) {
                return currency;
            }
            System.out.println("Invalid currency code. Please choose from: " + rates.keySet());
        }
    }

    /**
     * Prompts the user until a valid positive amount is entered.
     */
    private static double getValidAmount(Scanner sc) {
        double amount;
        while (true) {
            System.out.print("Enter amount to convert: ");
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                if (amount >= 0) {
                    return amount;
                } else {
                    System.out.println("Amount cannot be negative. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric amount.");
                sc.next(); // discard invalid token
            }
        }
    }

    /**
     * Converts an amount from one currency to another using USD as the pivot.
     */
    private static double convertCurrency(double amount, String from, String to) {
        double amountInUSD = amount / rates.get(from);
        return amountInUSD * rates.get(to);
    }
}