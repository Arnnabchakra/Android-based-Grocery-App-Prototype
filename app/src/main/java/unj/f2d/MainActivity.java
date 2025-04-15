package unj.f2d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout vegetables, fruits, grocery, Nonveg;
    CardView apple, tamato, chicken, oil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vegetables = findViewById(R.id.vegetables);
        fruits = findViewById(R.id.fruits);
        grocery = findViewById(R.id.grocery);
        Nonveg = findViewById(R.id.Nonveg);

        apple = findViewById(R.id.apple);
        tamato = findViewById(R.id.tamato);
        chicken = findViewById(R.id.chicken);
        oil = findViewById(R.id.oil);

        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VegetablesActivity.class);
                startActivity(i);
            }
        });
        tamato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VegetablesActivity.class);
                startActivity(i);
            }
        });

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FruitsActivity.class);
                startActivity(i);
            }
        });
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FruitsActivity.class);
                startActivity(i);
            }
        });

        grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GroceryActivity.class);
                startActivity(i);
            }
        });
        oil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GroceryActivity.class);
                startActivity(i);
            }
        });

        Nonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NonvegActivity.class);
                startActivity(i);
            }
        });
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NonvegActivity.class);
                startActivity(i);
            }
        });

    }
}