package com.example.android.layoutinflaterandview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buShow(View view) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View viewLayout = layoutInflater.inflate(R.layout.login_inflater, null);
        EditText editText = (EditText) viewLayout.findViewById(R.id.etMsg);
        editText.setText("Welcome");

        Toast toast = new Toast(getApplicationContext());
        toast.setView(viewLayout);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }
}