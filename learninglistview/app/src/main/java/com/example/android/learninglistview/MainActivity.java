package com.example.android.learninglistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<JobDesc> listnewsData = new ArrayList<JobDesc>();
    MyCustomAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvlist = (ListView) findViewById(R.id.lvlist);

        //add data and view it
        listnewsData.add(new JobDesc(1,"developer"," develop apps"));
        listnewsData.add(new JobDesc(2,"tester"," tester apps"));
        listnewsData.add(new JobDesc(3,"admin"," admin apps"));
        listnewsData.add(new JobDesc(4,"developer"," develop apps"));
        listnewsData.add(new JobDesc(5,"tester"," tester apps"));
        listnewsData.add(new JobDesc(6,"admin"," admin apps"));
        myadapter=new MyCustomAdapter(listnewsData);

        lvlist.setAdapter(myadapter);
    }

    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<JobDesc>  listnewsDataAdpater ;

        public MyCustomAdapter(ArrayList<JobDesc> listnewsDataAdpater) {
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
            View myView = mInflater.inflate(R.layout.job_des, null);

            final JobDesc s = listnewsDataAdpater.get(position);

            TextView tvID=( TextView)myView.findViewById(R.id.tvID);
            tvID.setText("ID: " + s.ID);

            TextView tvTitle=( TextView)myView.findViewById(R.id.tvTitle);
            tvTitle.setText(s.JobTitle);

            TextView tvDesc=( TextView)myView.findViewById(R.id.tvDesc);
            tvDesc.setText(s.Description);

            return myView;
        }

    }
}