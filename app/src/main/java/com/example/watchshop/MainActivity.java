package com.example.watchshop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView quantityTextView, priceTextView, ratingTextView;
    private RatingBar ratingBar;
    private int quantity = 0;
    private final int pricePerWatch = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantityTextView = findViewById(R.id.quantityTextView);
        priceTextView = findViewById(R.id.priceTextView);
        ratingTextView = findViewById(R.id.rating);
        ratingBar = findViewById(R.id.ratingBar);

        Button incrementButton = findViewById(R.id.increment);
        Button decrementButton = findViewById(R.id.decrement);
        Button orderButton = findViewById(R.id.order_btn);

        incrementButton.setOnClickListener(view -> updateQuantity(1));
        decrementButton.setOnClickListener(view -> updateQuantity(-1));
        orderButton.setOnClickListener(view -> placeOrder());

        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            ratingTextView.setText("Rating: " + rating);
        });
    }

    private void updateQuantity(int change) {
        quantity += change;
        if (quantity < 0) quantity = 0;
        quantityTextView.setText(String.valueOf(quantity));
        updatePrice();
    }

    private void updatePrice() {
        int totalPrice = quantity * pricePerWatch;
        priceTextView.setText("BDT " + totalPrice);
    }

    private void placeOrder() {
        StringBuilder selectedBrands = new StringBuilder("Selected Brands:\n");
        CheckBox rolex = findViewById(R.id.rolex);
        CheckBox omega = findViewById(R.id.omega);
        CheckBox tagHeuer = findViewById(R.id.tag_heuer);
        CheckBox casio = findViewById(R.id.casio);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        if (rolex.isChecked()) selectedBrands.append("Rolex\n");
        if (omega.isChecked()) selectedBrands.append("Omega\n");
        if (tagHeuer.isChecked()) selectedBrands.append("Tag Heuer\n");
        if (casio.isChecked()) selectedBrands.append("Casio\n");

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String watchType = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "None";

        String message = selectedBrands.toString() +
                "Watch Type: " + watchType + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total Price: BDT " + (quantity * pricePerWatch) + "\n" +
                "Thank you for your order!";

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
