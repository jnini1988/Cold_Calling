package com.example.coldcalling;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class Uncalled extends AppCompatActivity {
    private ListView mUnCalledView;
    private static String uList;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncalled);
        mUnCalledView=(ListView)findViewById(R.id.uncalled_view);
        backButton=(Button)findViewById(R.id.back_button);

        ArrayList<String> uncalled=getIntent().getStringArrayListExtra(uList);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.uncalled_row,R.id.rowTextView,uncalled);
        mUnCalledView.setAdapter(arrayAdapter);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }

    public static Intent newIntent(Context packageContext, ArrayList<String> list){
        Intent i = new Intent(packageContext,Uncalled.class);
        i.putExtra(uList,list);
        return i;
    }
}