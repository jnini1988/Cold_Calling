package com.example.coldcalling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Uncalled extends AppCompatActivity {
    private ListView mUnCalledView;
    private static String uList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncalled);
        mUnCalledView=(ListView)findViewById(R.id.uncalled_view);

        ArrayList<String> uncalled=getIntent().getStringArrayListExtra(uList);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.uncalled_row,R.id.rowTextView,uncalled);
        mUnCalledView.setAdapter(arrayAdapter);
    }

    public static Intent newIntent(Context packageContext, ArrayList<String> list){
        Intent i = new Intent(packageContext,Uncalled.class);
        i.putExtra(uList,list);
        return i;
    }
}