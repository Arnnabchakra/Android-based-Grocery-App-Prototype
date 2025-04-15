package unj.f2d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitsActivity extends AppCompatActivity {

    private TextView quantity_fruit_1, quantity_fruit_2;

    private int quantity1 = 0;
    private int quantity2 = 0;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);

        quantity_fruit_1 = findViewById(R.id.quantity_fruit_1);
        quantity_fruit_2 = findViewById(R.id.quantity_fruit_2);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("Cart", Context.MODE_PRIVATE);

        ImageView plus_fruit_1 = findViewById(R.id.plus_fruit_1);
        ImageView minus_fruit_1 = findViewById(R.id.minus_fruit_1);
        ImageView plus_fruit_2 = findViewById(R.id.plus_fruit_2);
        ImageView minus_fruit_2 = findViewById(R.id.minus_fruit_2);

        plus_fruit_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity1++;
                quantity_fruit_1.setText(String.valueOf(quantity1));
            }
        });

        minus_fruit_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity1 > 0) {
                    quantity1--;
                    quantity_fruit_1.setText(String.valueOf(quantity1));
                }
            }
        });

        plus_fruit_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity2++;
                quantity_fruit_2.setText(String.valueOf(quantity2));
            }
        });

        minus_fruit_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity2 > 0) {
                    quantity2--;
                    quantity_fruit_2.setText(String.valueOf(quantity2));
                }
            }
        });

        TextView cart = findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
                openCartPage();
            }
        });
    }
    private void addToCart() {
        // Add quantities to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("quantity_fruit_1", quantity1);
        editor.putInt("quantity_fruit_2", quantity2);
        editor.apply();
    }

    private void openCartPage() {
        // Open cart page and pass the prices of the vegetables
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("price_fruit_1", 180.0); // Change the value to match the actual price
        intent.putExtra("price_fruit_2", 140.0); // Change the value to match the actual price
        startActivity(intent);
    }

}