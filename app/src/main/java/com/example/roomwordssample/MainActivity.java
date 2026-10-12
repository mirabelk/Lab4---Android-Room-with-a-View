
package com.example.roomwordssample;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordssample.data.Word;
import com.example.roomwordssample.data.WordViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final WordListAdapter adapter = new WordListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        mWordViewModel = new ViewModelProvider(this)
                .get(WordViewModel.class);

        mWordViewModel.getAllWords().observe(this, words -> {
            adapter.setWords(words);
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            EditText input = new EditText(this);
            input.setHint("Entrer un mot");

            new AlertDialog.Builder(this)
                    .setTitle("Ajouter un mot")
                    .setView(input)
                    .setPositiveButton("Ajouter", (dialog, which) -> {
                        String text = input.getText().toString().trim();

                        if (!text.isEmpty()) {
                            mWordViewModel.insert(new Word(text));
                        } else {
                            Toast.makeText(
                                    this,
                                    "Veuillez entrer un mot",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    })
                    .setNegativeButton("Annuler", null)
                    .show();
        });
    }
}
