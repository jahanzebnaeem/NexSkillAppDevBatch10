package com.example.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etAge;
    TextView tvYourAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAge = (EditText) findViewById(R.id.etAge);
        tvYourAge = (TextView) findViewById(R.id.tvYourAge);
    }

    public void Calculate(View view) {
        int dob = Integer.parseInt(etAge.getText().toString());
        Calendar cal = Calendar.getInstance();
        int age = cal.get(Calendar.YEAR) - dob;
        String message = "You age is : " + age ;
        tvYourAge.setText(message);
    }
}