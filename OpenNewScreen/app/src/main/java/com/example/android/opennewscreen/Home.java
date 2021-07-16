package com.example.android.opennewscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvName = (TextView) findViewById(R.id.tvName);
        Bundle bundle = getIntent().getExtras();
        tvName.setText(bundle.getString("name"));
    }

    public void Close(View view) {
        finish();
    }
}