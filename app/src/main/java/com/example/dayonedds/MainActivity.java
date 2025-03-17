package com.example.dayonedds;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button click;
    TextView hello;
    EditText nameInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.hello);
        hello.setText(Singleton.getInstance().myName);
        nameInputField = findViewById(R.id.editTextText);
        click = findViewById(R.id.button);
        click.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //System.out.println("Click: " + hello.getText().toString());
        //Log.d("my label", "my message"); //Alternative to System.out.println
        Singleton.getInstance().myName = "Welcome" + nameInputField.getText().toString();
        hello.setText(Singleton.getInstance().myName);


    }
}