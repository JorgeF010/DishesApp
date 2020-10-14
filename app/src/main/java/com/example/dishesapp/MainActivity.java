package com.example.dishesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // all the required objects for RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter myAdapter;
    // Data
    private List<String> names;
    // data operations
    private FloatingActionButton addDish;
    private FloatingActionButton removeDish;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // data operations
        this.names = new ArrayList<>();
        this.names = new ArrayList<>();
        // some default data
        this.names.add("Pasta");
        this.names.add("Pizza");
        this.addDish = findViewById(R.id.floatingActionButton);
        this.removeDish = findViewById(R.id.floatingActionButton2);

        this.recyclerView = findViewById(R.id.recyclerView);
        // Adapter with data needed
        this.myAdapter = new MyAdapter(this, names);
        this.recyclerView.setAdapter(myAdapter);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);

        this.addDish.setOnClickListener(new View.OnClickListener() {
            EditText input = findViewById(R.id.nameOfDishInput);
            @Override
            public void onClick(View v) {
                input.setVisibility(View.VISIBLE);
                input.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            String content = input.getText().toString();
                            addName(content);
                            input.setVisibility(View.INVISIBLE);
                            input.setText(" ");
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

        this.removeDish.setOnClickListener(new View.OnClickListener() {
            EditText input = findViewById(R.id.nameOfDishInput);
            @Override
            public void onClick(View v) {
                input.setVisibility(View.VISIBLE);
                input.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            String content = input.getText().toString();
                            content = content.replaceAll("\\s", "");
                            remove(Integer.parseInt(content));
                            input.setVisibility(View.INVISIBLE);
                            input.setText(" ");
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
    }

    /**
     * Adds a new dish
     * @param nameOfDish - String
     */
    public void addName(String nameOfDish) {
        this.names.add(nameOfDish);
        // performance boost
        this.myAdapter.notifyItemInserted(names.size() - 1);
    }

    /**
     * Removes a dish based on their position on the list
     * @param index - their position
     */
    public void remove(int index) {
        // subtracting one for their index
        this.names.remove(index - 1);
        this.myAdapter.notifyItemRemoved(index - 1);
    }
}