package com.example.assignment;

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
        Button button = findViewById(R.id.sampleButton);

        button.setOnClickListener(new View.OnClickListener() {
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
    }
}
