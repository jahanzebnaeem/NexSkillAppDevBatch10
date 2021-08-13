package com.example.android.localdbsample;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DBManager dbManager;
    EditText etUserName;
    EditText etPassword;

    int recordId;

    ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
    MyCustomAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = (EditText) findViewById(R.id.txtName);
        etPassword = (EditText) findViewById(R.id.txtPassword);

        dbManager = new DBManager(this);
    }

    public void buSave(View view) {
        ContentValues values = new ContentValues();
        values.put(DBManager.ColUserName, etUserName.getText().toString());
        values.put(DBManager.ColPassWord, etPassword.getText().toString());

        long id = dbManager.Insert(values);
        if (id>0) {
            Toast.makeText(getApplicationContext(), "Data is added and user id: " + id, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Can not insert", Toast.LENGTH_LONG).show();
        }
    }

    public void buUpdate(View view) {
        ContentValues values = new ContentValues();
        values.put(DBManager.ColUserName, etUserName.getText().toString());
        values.put(DBManager.ColPassWord, etPassword.getText().toString());
        values.put(DBManager.ColID, recordId);

        String[] selectionArgs = {String.valueOf(recordId)};

        dbManager.Update(values, "ID = ?", selectionArgs);
    }

    public void buLoad(View view) {
        LoadElement();
    }

    void LoadElement() {
        ListView lvStudents = (ListView) findViewById(R.id.lvStudents);

        listnewsData.clear();

//        String[] projection = {"UserName", "Password"};
        String[] selectionArgs = {"%" + etUserName.getText().toString() + "%"};
//        Cursor cursor = dbManager.query(null, null,null, DBManager.ColUserName);
        Cursor cursor = dbManager.query(null, "UserName like ?",selectionArgs, DBManager.ColUserName);

        if (cursor.moveToFirst()) {
            String tableData = "";

            do {
//                tableData += cursor.getColumnIndex(DBManager.ColUserName) + ", " +
//                        cursor.getColumnIndex(DBManager.ColPassWord) + "::";
//                tableData += cursor.getString(cursor.getColumnIndex(DBManager.ColUserName)) + ", " +
//                cursor.getString(cursor.getColumnIndex(DBManager.ColPassWord)) + "::";
                //add data and view it
                listnewsData.add(new AdapterItems(cursor.getInt(cursor.getColumnIndex(DBManager.ColID)), cursor.getString(cursor.getColumnIndex(DBManager.ColUserName)), cursor.getString(cursor.getColumnIndex(DBManager.ColPassWord))));
            } while(cursor.moveToNext());

//            Toast.makeText(getApplicationContext(), tableData, Toast.LENGTH_LONG).show();
        }

        myadapter=new MyCustomAdapter(listnewsData);

        lvStudents.setAdapter(myadapter);
    }

    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdpater ;

        public MyCustomAdapter(ArrayList<AdapterItems> listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.user_layout, null);

            final AdapterItems s = listnewsDataAdpater.get(position);

            TextView tvID = (TextView) myView.findViewById(R.id.tvID);
            tvID.setText(String.valueOf(s.ID));

            TextView tvUserName = (TextView) myView.findViewById(R.id.tvUserName);
            tvUserName.setText(s.UserName);

            TextView tvPassword = (TextView) myView.findViewById(R.id.tvPassword);
            tvPassword.setText(s.Password);

            Button btnUpdate = (Button) myView.findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etUserName.setText(s.UserName);
                    etPassword.setText(s.Password);
                    recordId = s.ID;
                }
            });

            Button btnDelete = (Button) myView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] selectionArgs = {String.valueOf(s.ID)};
                    int count = dbManager.Delete("ID=?", selectionArgs);
                    if (count>0) {
                        LoadElement();
                    }
                }
            });

            return myView;
        }

    }
}
