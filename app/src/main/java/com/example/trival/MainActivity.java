package com.example.trival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.trival.Data.QuizData;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button next, history;
    public static final String NAME = "inputName";

    private QuizData quizDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDatabase();
        initViews();
        onClickListeners();
    }

    private void createDatabase() {
        quizDataSource = new QuizData(this);
        quizDataSource.open();
    }

    private void onClickListeners() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    Intent intent = new Intent(MainActivity.this, FirstQuestion.class);
                    intent.putExtra(NAME, name.getText().toString());
                    startActivity(intent);
                }
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, History.class);
                startActivity(intent);
            }
        });

    }

    private boolean isValid() {

        boolean allow = true;
        if (!name.getText().toString().matches("^[A-Za-z\\s]+$")) {
            name.setError("Invalid Name");
            allow = false;
        }

        return allow;
    }

    private void initViews() {
        name = findViewById(R.id.name);
        next = findViewById(R.id.next);
        history = findViewById(R.id.history);
    }

    // helps to survive configuration changes

    @Override
    protected void onResume() {
        super.onResume();
        quizDataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        quizDataSource.close();
    }
}
