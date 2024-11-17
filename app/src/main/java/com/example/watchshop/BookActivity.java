package com.example.watchshop;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> genreList;
    HashMap<String, List<String>> bookData;
    int lastExpandedGroup = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);

        expandableListView = findViewById(R.id.expandableListView);
        setupBookData();
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, genreList, bookData);
        expandableListView.setAdapter(adapter);

        // Toast message on group (genre) click
        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            String genre = genreList.get(groupPosition);
            Toast.makeText(getApplicationContext(), genre, Toast.LENGTH_SHORT).show();
            return false;
        });
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String book = bookData.get(genreList.get(groupPosition)).get(childPosition);
            Toast.makeText(getApplicationContext(), "Selected: " + book, Toast.LENGTH_SHORT).show();
            return false;
        });

        expandableListView.setOnGroupExpandListener(groupPosition -> {
            if (lastExpandedGroup != -1 && lastExpandedGroup != groupPosition) {
                expandableListView.collapseGroup(lastExpandedGroup);
            }
            lastExpandedGroup = groupPosition;
        });
    }

    private void setupBookData() {
        genreList = new ArrayList<>();
        bookData = new HashMap<>();

        // Adding genres
        genreList.add("Fiction");
        genreList.add("Science");
        genreList.add("History");

        List<String> fictionBooks = new ArrayList<>();
        fictionBooks.add("The Great Gatsby");
        fictionBooks.add("To Kill a Mockingbird");
        fictionBooks.add("1984");

        List<String> scienceBooks = new ArrayList<>();
        scienceBooks.add("A Brief History of Time");
        scienceBooks.add("The Selfish Gene");
        scienceBooks.add("The Origin of Species");

        List<String> historyBooks = new ArrayList<>();
        historyBooks.add("Sapiens: A Brief History of Humankind");
        historyBooks.add("The Silk Roads: A New History of the World");
        historyBooks.add("Guns, Germs, and Steel");

        bookData.put(genreList.get(0), fictionBooks);
        bookData.put(genreList.get(1), scienceBooks);
        bookData.put(genreList.get(2), historyBooks);
    }
}
