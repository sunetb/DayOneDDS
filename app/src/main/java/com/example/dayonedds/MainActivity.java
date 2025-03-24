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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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
        //hello.setText(Singleton.getInstance().myName);

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String s = readURL("https://www.ruc.dk");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hello.setText(s);
                    }
                });

            }
        });
        myThread.start();


    }
    String readURL(String inputUrl){
        String value = "";
        URL url = null;
        try {
            url = new URL(inputUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            System.out.println(con);
            con.setRequestMethod("GET");
            InputStream i = con.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(i));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                value += inputLine + "\n";
            }
            in.close();
        } catch (ProtocolException ex) {
            throw new RuntimeException(ex);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return value;
    }

}