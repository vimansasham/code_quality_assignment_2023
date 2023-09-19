import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShopCalculator {
    private static Map<String, Double> discnts = new HashMap<>();
    private static Map<String, Double> taxes = new HashMap<>();
    private static Map<String, Double> prmtns = new HashMap<>();
    private static Map<String, Double> curr_rates = new HashMap<>();
    private static Map<String, Integer> invntry = new HashMap<>();

    private static void ldDiscnts(String file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                discnts.put(parts[0].toUpperCase(), Double.parseDouble(parts[1]));
            }
        }
    }

    private static void ldTaxes(String file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                taxes.put(parts[0].toUpperCase(), Double.parseDouble(parts[1]));
            }
        }
    }

    private static void ldPrmtns(String file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                prmtns.put(parts[0], Double.parseDouble(parts[1]));
            }
        }
    }

    private static void ldCurrRates(String file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                curr_rates.put(parts[0].toUpperCase(), Double.parseDouble(parts[1]));
            }
        }
    }

    private static void ldInvntry(String file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                invntry.put(parts[0].toUpperCase(), Integer.parseInt(parts[1]));
            }
        }
    }

    public static void main(String[] args) {
        try {
            ldDiscnts("data/discounts.csv");
            ldTaxes("data/tax_rates.csv");
            ldPrmtns("data/promotions.csv");
            ldCurrRates("data/currency_rates.csv");
            ldInvntry("data/inventory.csv");

            Map<String, Integer> cart = new HashMap<>();
            Map<String, Double> crtDisPrices = new HashMap<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("shopping_cart.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    cart.put(parts[0], Integer.parseInt(parts[1]));
                }
            }

            double total_price_usd = 0;

            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                String itm = entry.getKey();
                int qty = entry.getValue();

                if (invntry.getOrDefault(itm.toUpperCase(), 0) < qty) {
                    System.out.println("Cannot proceed, insufficient inventory for " + itm);
                    return;
                }

                Double promo = prmtns.get(itm.toUpperCase());
                if (promo != null) {
                    qty = qty - (qty / promo.intValue());
                }
                Double dscnt = discnts.getOrDefault(itm.toUpperCase(), 0.0);
                double price = 1.0;
                double dscntd_price = price - (price * dscnt);
                double item_price = dscntd_price * qty;
                crtDisPrices.put(itm.toUpperCase(), dscntd_price);
                total_price_usd += item_price;
                invntry.put(itm.toUpperCase(), invntry.get(itm.toUpperCase()) - qty);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] available_states = taxes.keySet().toArray(new String[0]);
            System.out.print("Enter state (" + String.join(", ", available_states) + "): ");
            String state = reader.readLine();

            double tax_rate = taxes.getOrDefault(state, 0.0);
            double tax_amt = total_price_usd * tax_rate;

            double final_price_usd = total_price_usd + tax_amt;

            System.out.println("-------- SHOPPING CART SUMMARY --------");
            System.out.println("Item   | Qty | Price");
            System.out.println("-----------------------------");
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                String itm = entry.getKey();
                int qty = entry.getValue();
                double dscntd_price = crtDisPrices.get(itm.toUpperCase());
                double item_price = dscntd_price * qty;
                System.out.printf("%-6s | %3d | USD %.2f\n", itm, qty, item_price);
            }
            System.out.println("-----------------------------");
            System.out.printf("Subtotal: USD %.2f\n", total_price_usd);
            System.out.printf("Tax (%s): USD %.2f\n", state, tax_amt);
            System.out.printf("Total: USD %.2f\n", final_price_usd);

            String[] available_currencies = curr_rates.keySet().toArray(new String[0]);
            System.out.print("Select currency for payment (" + String.join(", ", available_currencies) + "): ");
            String selected_currency = reader.readLine();

            double final_price_currency = final_price_usd * curr_rates.get(selected_currency);

            System.out.printf("Total in %s: %.2f\n", selected_currency, final_price_currency);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
