package unj.f2d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NonvegActivity extends AppCompatActivity {

    private TextView quantity_nv_1;
    private TextView quantity_nv_2;

    private int quantity1 = 0;
    private int quantity2 = 0;

    // SharedPreferences instance
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonveg);

        quantity_nv_1 = findViewById(R.id.quantity_nv_1);
        quantity_nv_2 = findViewById(R.id.quantity_nv_2);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("Cart", Context.MODE_PRIVATE);

        ImageView plus_nv_1 = findViewById(R.id.plus_nv_1);
        ImageView minus_nv_1 = findViewById(R.id.minus_nv_1);
        ImageView plus_nv_2 = findViewById(R.id.plus_nv_2);
        ImageView minus_nv_2 = findViewById(R.id.minus_nv_2);

        plus_nv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity1++;
                quantity_nv_1.setText(String.valueOf(quantity1));
            }
        });

        minus_nv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity1 > 0) {
                    quantity1--;
                    quantity_nv_1.setText(String.valueOf(quantity1));
                }
            }
        });

        plus_nv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity2++;
                quantity_nv_2.setText(String.valueOf(quantity2));
            }
        });

        minus_nv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity2 > 0) {
                    quantity2--;
                    quantity_nv_2.setText(String.valueOf(quantity2));
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
        editor.putInt("quantity_nv_1", quantity1);
        editor.putInt("quantity_nv_2", quantity2);
        editor.apply();
    }

    private void openCartPage() {
        // Open cart page and pass the prices of the vegetables
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("price_nv_1", 190.0); // Change the value to match the actual price
        intent.putExtra("price_nv_2", 700.0); // Change the value to match the actual price
        startActivity(intent);
    }

}