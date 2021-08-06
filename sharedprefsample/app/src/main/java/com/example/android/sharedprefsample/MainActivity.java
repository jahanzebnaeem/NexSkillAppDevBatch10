package com.example.android.sharedprefsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedRef sharedRef;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.txtName);
        password = (EditText) findViewById(R.id.txtPassword);

        sharedRef = new SharedRef(this);
    }

    public void buSave(View view) {
        sharedRef.SaveData(userName.getText().toString(), password.getText().toString());
    }

    public void buLoad(View view) {
        String Data = sharedRef.LoadData();
        Toast.makeText(getApplicationContext(), Data, Toast.LENGTH_LONG).show();
    }
}