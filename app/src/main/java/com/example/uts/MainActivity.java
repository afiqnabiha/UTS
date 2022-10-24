package com.example.uts;

import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.BroadcastReceiver;
import java.util.ArrayList;
import android.content.IntentFilter;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<RecyclerData> recyclerDataArrayList;
    TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.idCourseRV);
        total = findViewById(R.id.total);
        recyclerDataArrayList = new ArrayList<>();
        recyclerDataArrayList.add(new RecyclerData("Kaos 1", "70000",
                R.drawable.img));
        recyclerDataArrayList.add(new RecyclerData("Kaos 2", "100000",
                R.drawable.img2));
        recyclerDataArrayList.add(new RecyclerData("Kaos 3", "80000",
                R.drawable.img3));
        recyclerDataArrayList.add(new RecyclerData("Kaos 4", "50000",
                R.drawable.img4));
        recyclerDataArrayList.add(new RecyclerData("Kaos 5", "75000",

                R.drawable.img5));
        recyclerDataArrayList.add(new RecyclerData("Kaos 6", "95000",
                R.drawable.img6));
        RecyclerViewAdapter adapter = new
                RecyclerViewAdapter(recyclerDataArrayList,
                this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String s = intent.getStringExtra("Rhrg");
            total.setText("Total = " + s);
        }
    };
}