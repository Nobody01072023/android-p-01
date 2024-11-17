package com.example.watchshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    boolean isImageChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.sampleImage);
        TextView textView = findViewById(R.id.sampleText);
        Button changeImageButton = findViewById(R.id.sampleButton);
        Button feedbackButton = findViewById(R.id.feedbackButton);
        Button formButton = findViewById(R.id.formButton);
        Button bookButton = findViewById(R.id.bookButton);

        changeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Displaying new page", Toast.LENGTH_SHORT).show();
                if (!isImageChanged) {
                    imageView.setImageResource(R.drawable.download);
                    isImageChanged = true;
                } else {
                    imageView.setImageResource(R.drawable.ss);
                    isImageChanged = false;
                }
            }
        });

        feedbackButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FeedbackActivity.class);
            startActivity(intent);
        });

        formButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(intent);
        });

        bookButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, BookActivity.class);
            startActivity(intent);
        });
    }
}
