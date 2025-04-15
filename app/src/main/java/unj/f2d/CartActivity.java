package unj.f2d;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartItemsLayout;
    private TextView totalPriceTextView;

    // SharedPreferences instance
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Inside onCreate method after totalPriceTextView initialization
        Button placeOrderButton = findViewById(R.id.place_order_button);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if address fields are filled
                EditText phoneNumberEditText = findViewById(R.id.phone_number);
                EditText addressStreetEditText = findViewById(R.id.address_street);
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                String addressStreet = addressStreetEditText.getText().toString().trim();

                if (phoneNumber.isEmpty() || addressStreet.isEmpty()) {
                    // Show an alert dialog if any field is empty
                    AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                    builder.setMessage("Please fill all address details.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Close the dialog
                                }
                            });
                    builder.create().show();
                } else if (phoneNumber.length() < 10) {
                    // Show an alert dialog if phone number is less than 10 digits
                    AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                    builder.setMessage("Please enter a valid phone number.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Close the dialog
                                }
                            });
                    builder.create().show();
                } else {
                    // Proceed with placing the order
                    placeOrder(phoneNumber, addressStreet);
                }
            }
        });




        cartItemsLayout = findViewById(R.id.cart_items_layout);
        totalPriceTextView = findViewById(R.id.total_price);

        // Get prices passed from VegetablesActivity
        double priceVeg1 = getIntent().getDoubleExtra("price_veg_1", 0.0);
        double priceVeg2 = getIntent().getDoubleExtra("price_veg_2", 0.0);


        // Get prices passed from FruitsActivity
        double priceFruit1 = getIntent().getDoubleExtra("price_fruit_1", 0.0);
        double priceFruit2 = getIntent().getDoubleExtra("price_fruit_2", 0.0);

        // Get prices passed from GroceryActivity
        double priceGroc1 = getIntent().getDoubleExtra("price_groc_1", 0.0);
        double priceGroc2 = getIntent().getDoubleExtra("price_groc_2", 0.0);

        // Get prices passed from NonVegActivity
        double pricenv1 = getIntent().getDoubleExtra("price_nv_1", 0.0);
        double pricenv2 = getIntent().getDoubleExtra("price_nv_2", 0.0);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("Cart", Context.MODE_PRIVATE);

        displayCartItems(priceVeg1, priceVeg2);
        displayCartItemsFruit(priceFruit1, priceFruit2);
        displayCartItemsGrocery(priceGroc1,priceGroc2);
        displayCartItemsNonveg(pricenv1,pricenv2);

        // Calculate total prices for each category
        double totalVegetablePrice = calculateTotalPriceveg(priceVeg1, priceVeg2);
        double totalFruitPrice = calculateTotalPricefruit(priceFruit1, priceFruit2);
        double totalGroceryPrice = calculateTotalPricegroc(priceGroc1, priceGroc2);
        double totalNonvegPrice = calculateTotalPricenonveg(pricenv1, pricenv2);

        // Calculate total price
        double totalPrice = totalVegetablePrice + totalFruitPrice + totalGroceryPrice + totalNonvegPrice;

        // Display total price
        totalPriceTextView.setText("Total Price: ₹" + totalPrice);


    }

    private void displayCartItems(double priceVeg1, double priceVeg2) {
        // Retrieve cart items from SharedPreferences and display
        Map<String, ?> allEntries = sharedPreferences.getAll();
        double totalPrice = 0.0;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String itemName = null; // Initialize the itemName variable
            double price = 0.0; // Initialize the price variable

            // Determine the itemName and price based on the key
            switch (entry.getKey()) {
                case "quantity_veg_1":
                    if ((int) entry.getValue() > 0) {
                        itemName = "Patato"; // Change to actual item name
                        price = priceVeg1;
                    }
                    break;
                case "quantity_veg_2":
                    if ((int) entry.getValue() > 0) {
                        itemName = "Tomato"; // Change to actual item name
                        price = priceVeg2;
                    }
                    break;
                // Add cases for additional vegetable items if needed
            }

            // If itemName is not null, it means the vegetable was selected
            if (itemName != null) {
                int quantity = (int) entry.getValue();
                totalPrice += price * quantity;

                TextView cartItemTextView = new TextView(this);
                cartItemTextView.setText(itemName + " : ₹" + price + " x " + quantity);
                cartItemsLayout.addView(cartItemTextView);
            }
        }

        totalPriceTextView.setText("Total Price: ₹" + totalPrice);
    }

    private void displayCartItemsFruit(double priceFruit1, double priceFruit2) {
        // Retrieve cart items from SharedPreferences and display
        Map<String, ?> allEntries = sharedPreferences.getAll();
        double totalPrice = 0.0;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String itemName = null; // Initialize the itemName variable
            double price = 0.0; // Initialize the price variable

            // Determine the itemName and price based on the key
            switch (entry.getKey()) {
                case "quantity_fruit_1":
                    if ((int) entry.getValue() > 0) {
                        itemName = "Apple"; // Change to actual item name
                        price = priceFruit1;
                    }
                    break;
                case "quantity_fruit_2":
                    if ((int) entry.getValue() > 0) {
                        itemName = "Orange"; // Change to actual item name
                        price = priceFruit2;
                    }
                    break;
                // Add cases for additional fruit items if needed
            }

            // If itemName is not null, it means the fruit was selected
            if (itemName != null) {
                int quantity = (int) entry.getValue();
                totalPrice += price * quantity;

                TextView cartItemTextView = new TextView(this);
                cartItemTextView.setText(itemName + " : ₹" + price + " x " + quantity);
                cartItemsLayout.addView(cartItemTextView);
            }
        }

        totalPriceTextView.setText("Total Price: ₹" + totalPrice);
    }

    private void displayCartItemsGrocery(double priceGroc1, double priceGroc2) {
        // Retrieve cart items from SharedPreferences and display
        Map<String, ?> allEntries = sharedPreferences.getAll();
        double totalPrice = 0.0;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String itemName = null; // Initialize the itemName variable
            double price = 0.0; // Initialize the price variable

            // Determine the itemName and price based on the key
            switch (entry.getKey()) {
                case "quantity_groc_1":
                    if ((int) entry.getValue() > 0) {
                        itemName = "Oil"; // Change to actual item name
                        price = priceGroc1;
                    }
                    break;
                case "quantity_groc_2":
                    if ((int) entry.getValue() > 0) {
                        itemName = "Masala"; // Change to actual item name
                        price = priceGroc2;
                    }
                    break;
                // Add cases for additional grocery items if needed
            }

            // If itemName is not null, it means the grocery item was selected
            if (itemName != null) {
                int quantity = (int) entry.getValue();
                totalPrice += price * quantity;

                TextView cartItemTextView = new TextView(this);
                cartItemTextView.setText(itemName + " : ₹" + price + " x " + quantity);
                cartItemsLayout.addView(cartItemTextView);
            }
        }

        totalPriceTextView.setText("Total Price: ₹" + totalPrice);
    }

    private void displayCartItemsNonveg(double pricenv1, double pricenv2) {
        // Retrieve cart items from SharedPreferences and display
        Map<String, ?> allEntries = sharedPreferences.getAll();
        double totalPrice = 0.0;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String itemName = null; // Initialize the itemName variable
            double price = 0.0; // Initialize the price variable

            // Determine the itemName and price based on the key
            switch (entry.getKey()) {
                case "quantity_nv_1":
                    if ((int) entry.getValue() > 0) {
                        itemName = "Chicken"; // Change to actual item name
                        price = pricenv1;
                    }
                    break;
                case "quantity_nv_2":
                    if ((int) entry.getValue() > 0) {
                        itemName = "Mutton"; // Change to actual item name
                        price = pricenv2;
                    }
                    break;
                // Add cases for additional non-veg items if needed
            }

            // If itemName is not null, it means the non-veg item was selected
            if (itemName != null) {
                int quantity = (int) entry.getValue();
                totalPrice += price * quantity;

                TextView cartItemTextView = new TextView(this);
                cartItemTextView.setText(itemName + " : ₹" + price + " x " + quantity);
                cartItemsLayout.addView(cartItemTextView);
            }
        }

        totalPriceTextView.setText("Total Price: ₹" + totalPrice);
    }




    // Add a method to display order summary
    private void displayOrderSummary(String phoneNumber, String addressStreet) {
        // Retrieve cart items from SharedPreferences and display
        Map<String, ?> allEntries = sharedPreferences.getAll();
        StringBuilder orderSummary = new StringBuilder("Order Summary:\n");

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String itemName = null;
            int quantity = (int) entry.getValue();

            // Determine the itemName based on the key
            switch (entry.getKey()) {
                case "quantity_veg_1":
                    itemName = "Patato";
                    break;
                case "quantity_veg_2":
                    itemName = "Tomato";
                    break;
                case "quantity_fruit_1":
                    itemName = "Apple";
                    break;
                case "quantity_fruit_2":
                    itemName = "Orange";
                    break;
                case "quantity_groc_1":
                    itemName = "Oil";
                    break;
                case "quantity_groc_2":
                    itemName = "Masala";
                    break;
                case "quantity_nv_1":
                    itemName = "Chicken";
                    break;
                case "quantity_nv_2":
                    itemName = "Mutton";
                    break;

                // Add cases for additional items if needed
            }

            if (itemName != null) {
                orderSummary.append(quantity).append("kg ").append(itemName).append("\n");
            }
        }

        // Show order summary in a dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
        builder.setTitle("Order Placed Successfully!")
                .setMessage(orderSummary.toString() + "\nItems will be delivered to " + addressStreet + " By Tomorrow 8 am.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Close the dialog
                        Intent u = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(u);
                    }
                });
        builder.create().show();
    }

    private double calculateTotalPriceveg(double price1, double price2) {
        double totalPrice = 0.0;
        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            double price = 0.0;

            // Determine the price based on the key
            switch (entry.getKey()) {
                case "quantity_veg_1":
                    price = price1;
                    break;
                case "quantity_veg_2":
                    price = price2;
                    break;
                // Add cases for other categories if needed
            }

            // Add to total price
            totalPrice += price * (int) entry.getValue();
        }

        return totalPrice;
    }

    private double calculateTotalPricenonveg(double price1, double price2) {
        double totalPrice = 0.0;
        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            double price = 0.0;

            // Determine the price based on the key
            switch (entry.getKey()) {
                case "quantity_nv_1":
                    price = price1;
                    break;
                case "quantity_nv_2":
                    price = price2;
                    break;
                // Add cases for other categories if needed
            }

            // Add to total price
            totalPrice += price * (int) entry.getValue();
        }

        return totalPrice;
    }

    private double calculateTotalPricefruit(double price1, double price2) {
        double totalPrice = 0.0;
        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            double price = 0.0;

            // Determine the price based on the key
            switch (entry.getKey()) {
                case "quantity_fruit_1":
                    price = price1;
                    break;
                case "quantity_fruit_2":
                    price = price2;
                    break;
                // Add cases for other categories if needed
            }

            // Add to total price
            totalPrice += price * (int) entry.getValue();
        }

        return totalPrice;
    }

    private double calculateTotalPricegroc(double price1, double price2) {
        double totalPrice = 0.0;
        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            double price = 0.0;

            // Determine the price based on the key
            switch (entry.getKey()) {
                case "quantity_groc_1":
                    price = price1;
                    break;
                case "quantity_groc_2":
                    price = price2;
                    break;
                // Add cases for other categories if needed
            }

            // Add to total price
            totalPrice += price * (int) entry.getValue();
        }

        return totalPrice;
    }



    // Add a method to place the order
    private void placeOrder(String phoneNumber, String addressStreet) {
        // Display order summary
        displayOrderSummary(phoneNumber, addressStreet);

        // Clear cart items after placing the order
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Optionally, you can navigate back to the main activity or perform any other action after placing the order
    }


}

