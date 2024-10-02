package com.example.watchshop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class FormActivity extends AppCompatActivity {

    private EditText nameEditText, idEditText, emailEditText, phoneEditText;
    private TextInputEditText passwordEditText;
    private Spinner deptSpinner;
    private Button submitButton, clearButton, editButton;
    private LinearLayout inputLayout, outputLayout;
    private TextView outputText;

    private String name, id, email, phone, password, dept;
    private Pattern namePattern = Pattern.compile("[a-zA-Z ]+");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nameEditText = findViewById(R.id.name);
        idEditText = findViewById(R.id.sId);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.num);
        passwordEditText = findViewById(R.id.pass);
        deptSpinner = findViewById(R.id.spinner);
        submitButton = findViewById(R.id.submit);
        clearButton = findViewById(R.id.clearButton);
        editButton = findViewById(R.id.editButton);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);

        String[] departments = new String[]{"Select Department", "CSE", "EEE", "ME", "CE", "LAW","THM","CIVIL","IS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        deptSpinner.setAdapter(adapter);

        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dept = deptSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        submitButton.setOnClickListener(v -> {
            name = nameEditText.getText().toString();
            id = idEditText.getText().toString();
            email = emailEditText.getText().toString();
            phone = phoneEditText.getText().toString();
            password = passwordEditText.getText().toString();

            if (validateForm()) {

                inputLayout.setVisibility(View.GONE);
                outputLayout.setVisibility(View.VISIBLE);
                String output = "Name: " + name + "\nID: " + id + "\nEmail: " + email + "\nPhone: " + phone + "\nPassword: " + password + "\nDepartment: " + dept;
                outputText.setText(output);
            }
        });


        editButton.setOnClickListener(v -> {

            inputLayout.setVisibility(View.VISIBLE);
            outputLayout.setVisibility(View.GONE);

            nameEditText.setText(name);
            idEditText.setText(id);
            emailEditText.setText(email);
            phoneEditText.setText(phone);
            passwordEditText.setText(password);

            int spinnerPosition = adapter.getPosition(dept);
            deptSpinner.setSelection(spinnerPosition);
        });

        clearButton.setOnClickListener(v -> {
            resetForm();
        });
    }


    private boolean validateForm() {
        if (name.isEmpty()) {
            nameEditText.setError("Please enter your name");
            return false;
        } else if (!namePattern.matcher(name).matches()) {
            nameEditText.setError("Name can only contain letters and spaces");
            return false;
        } else if (id.isEmpty()) {
            idEditText.setError("Please enter your ID");
            return false;
        } else if (email.isEmpty()) {
            emailEditText.setError("Please enter your email");
            return false;
        } else if (phone.isEmpty()) {
            phoneEditText.setError("Please enter your phone number");
            return false;
        } else if (password.isEmpty()) {
            passwordEditText.setError("Please enter your password");
            return false;
        } else if (dept.equals("Select Department")) {
            Toast.makeText(getApplicationContext(), "Please select a department", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void resetForm() {
        nameEditText.setText("");
        idEditText.setText("");
        emailEditText.setText("");
        phoneEditText.setText("");
        passwordEditText.setText("");
        deptSpinner.setSelection(0);

        inputLayout.setVisibility(View.VISIBLE);
        outputLayout.setVisibility(View.GONE);
    }
}