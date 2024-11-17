package com.example.watchshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox rolexCheckBox, omegaCheckBox, tagHeuerCheckBox, casioCheckBox;
    private RadioGroup watchTypeGroup;
    private RadioButton selectedRadioButton;
    private TextView quantityTextView, priceTextView, brandsTextView, ratingTextView, watchTypeTextView;
    private Button incrementButton, decrementButton, orderButton, formButton, bookButton;
    private RatingBar ratingBar;
    private int quantity = 0;
    private final int pricePerWatch = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  
        rolexCheckBox = findViewById(R.id.rolex);
        omegaCheckBox = findViewById(R.id.omega);
        tagHeuerCheckBox = findViewById(R.id.tag_heuer);
        casioCheckBox = findViewById(R.id.casio);
        watchTypeGroup = findViewById(R.id.radioGroup);
        quantityTextView = findViewById(R.id.quantityTextView);
        priceTextView = findViewById(R.id.priceTextView);
        brandsTextView = findViewById(R.id.brands);
        ratingTextView = findViewById(R.id.rating);
        incrementButton = findViewById(R.id.increment);
        decrementButton = findViewById(R.id.decrement);
        orderButton = findViewById(R.id.order_btn);
        formButton = findViewById(R.id.form_btn);
        bookButton = findViewById(R.id.book_btn); // New button for navigating to BookActivity
        ratingBar = findViewById(R.id.ratingBar);

        incrementButton.setOnClickListener(view -> {
            quantity++;
            updateQuantityAndPrice();
        });

        decrementButton.setOnClickListener(view -> {
            if (quantity > 0) {
                quantity--;
            }
            updateQuantityAndPrice();
        });

        watchTypeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            selectedRadioButton = findViewById(checkedId);
            String watchType = selectedRadioButton.getText().toString();
        
        });

        orderButton.setOnClickListener(view -> displaySelectedBrands());

       
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            ratingTextView.setText("Rating: " + rating);
        });

        formButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(intent);
        });

        // Navigate to BookActivity when "Go to Book Activity" button is clicked
        bookButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, BookActivity.class);
            startActivity(intent);
        });
    }

    private void updateQuantityAndPrice() {
        quantityTextView.setText(String.valueOf(quantity));
        int totalPrice = quantity * pricePerWatch;
        priceTextView.setText("BDT " + totalPrice);
    }

    private void displaySelectedBrands() {
        StringBuilder selectedBrands = new StringBuilder("Selected Brands: ");
        if (rolexCheckBox.isChecked()) selectedBrands.append("Rolex ");
        if (omegaCheckBox.isChecked()) selectedBrands.append("Omega ");
        if (tagHeuerCheckBox.isChecked()) selectedBrands.append("Tag Heuer ");
        if (casioCheckBox.isChecked()) selectedBrands.append("Casio ");

        brandsTextView.setText(selectedBrands.toString());
    }
}
