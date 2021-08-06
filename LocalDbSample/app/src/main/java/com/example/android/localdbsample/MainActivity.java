package com.example.android.localdbsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DBManager dbManager;
    EditText etUserName;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = (EditText) findViewById(R.id.txtName);
        etPassword = (EditText) findViewById(R.id.txtPassword);

        dbManager = new DBManager(this);
    }


    public void buLoad(View view) {
    }

    public void buSave(View view) {
    }

    public void buUpdate(View view) {
    }
}