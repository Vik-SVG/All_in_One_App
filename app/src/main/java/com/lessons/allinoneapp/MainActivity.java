package com.lessons.allinoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.lessons.allinoneapp.MyAdapters.MyAdapter;
import com.lessons.allinoneapp.MyData.SocialData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        ArrayList<SocialData> data = new ArrayList<>();

        data.add(new SocialData(R.drawable.banana, "https://www.facebook.com"));
        data.add(new SocialData(R.drawable.broccoli, "https://www.youtube.com"));
        data.add(new SocialData(R.drawable.banana, "https://www.google.com/"));
        data.add(new SocialData(R.drawable.broccoli, "https://www.instagram.com/"));

        GridLayoutManager gd = new GridLayoutManager(this, 3);
        adapter = new MyAdapter(data, getApplicationContext());
        recyclerView.setLayoutManager(gd);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}