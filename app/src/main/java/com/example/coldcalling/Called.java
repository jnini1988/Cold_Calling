package com.example.coldcalling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class Called extends AppCompatActivity {
    private ArrayList<Student> called;
    private ListView mCalledView;
    private Button backButton;
    private static String cList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called);
        called= (ArrayList<Student>) getIntent().getSerializableExtra("clist");
        mCalledView=(ListView)findViewById(R.id.called_view);
        backButton=(Button)findViewById(R.id.back_button);

        listAdapter customAdapter = new listAdapter(this, R.layout.called_row, called);
        mCalledView.setAdapter(customAdapter);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }

}