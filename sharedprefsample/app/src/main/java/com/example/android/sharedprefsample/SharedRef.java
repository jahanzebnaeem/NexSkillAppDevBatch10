package com.example.android.sharedprefsample;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedRef {
    SharedPreferences sharedPreferences;

    public SharedRef(Context context) {
        sharedPreferences = context.getSharedPreferences("myRef", Context.MODE_PRIVATE);
    }

    public void SaveData(String UserName, String Password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserName", UserName);
        editor.putString("Password", Password);
        editor.commit();
    }

    public String LoadData() {
        String FileContent = "UserName: " + sharedPreferences.getString("UserName", "No name");
        FileContent += ", Password: " + sharedPreferences.getString("Password", "No password");
        return FileContent;
    }
}
