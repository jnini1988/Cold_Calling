package com.example.coldcalling;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Called extends AppCompatActivity {
    private ListView mCalledView;
    private static String cList;
    private ArrayList<Student> called;
    ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called);
        mCalledView=(ListView)findViewById(R.id.called_view);

        called = (ArrayList<Student>) getIntent().getSerializableExtra("clist");
        ArrayList<String> name = new ArrayList<>();
        for(Student s:called){
            name.add(s.getName());
        }

        //using uncalled row xml temporary
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.uncalled_row,R.id.rowTextView,name);
        mCalledView.setAdapter(arrayAdapter);
    }

}