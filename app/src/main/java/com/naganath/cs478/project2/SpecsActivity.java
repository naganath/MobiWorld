package com.naganath.cs478.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SpecsActivity extends AppCompatActivity {

    private static String[] keyArray = {"RAM", "Display", "Memory", "Dual-Sim", "Model"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specs);

        Intent intent = getIntent();
        int specsKey = intent.getExtras().getInt(MainActivity.SPECS);
        String [] specsArray = getResources().getStringArray(specsKey);
        // fill in Array Pair

        ListView keyList = findViewById(R.id.keyList);
        keyList.setAdapter(new ArrayAdapter<>(this,R.layout.spec_item, keyArray ));

        //fill in value List1
        ListView valueList = findViewById(R.id.valueList);
        valueList.setAdapter(new ArrayAdapter<>(this,R.layout.spec_item, specsArray ));

    }
}
