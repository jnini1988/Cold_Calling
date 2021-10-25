package com.example.coldcalling;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button mRandomButton,mUncalledButton,mCalledButton;
    private TextView mNameTextView;
    private ImageView mProfileView;
    private ArrayList<Student> called = new ArrayList<>();
    private ArrayList<String> uncalled = new ArrayList<>();
    private TextClock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameTextView = (TextView)findViewById(R.id.name_text_view);
        mRandomButton=(Button)findViewById(R.id.random_button);
        mUncalledButton=(Button)findViewById(R.id.uncalled_button);
        mCalledButton=(Button)findViewById(R.id.called_button);
        mProfileView=(ImageView)findViewById(R.id.profile_view);

        clock=(TextClock)findViewById(R.id.time_view);
        clock.setFormat12Hour(null);
        clock.setFormat24Hour("MMM-dd-yyyy HH:mm");

        String[] nameList = getResources().getStringArray(R.array.name_array);
        int classSize = nameList.length;
        Student[] mStudentList = new Student[classSize];
        for(int i=0;i<classSize;i++){
            mStudentList[i] = new Student(nameList[i]);
        }
        for(String name:nameList){
            uncalled.add(name);
        }

        mRandomButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v){
                Student picked = mStudentList[(int)(Math.random()*classSize)];
                picked.addTime();
                if(picked.calledTwiceFM()){
                    int warnResId=R.string.warn;
                    Toast.makeText(MainActivity.this,warnResId,Toast.LENGTH_SHORT).show();
                }
                else{
                    if(uncalled.contains(picked.getName())){
                        called.add(picked);
                        uncalled.remove(picked.getName());
                    }
                }
                mNameTextView.setText(picked.getName());
                mProfileView.setImageDrawable(getDrawable(picked.getImgResId()));
            }
        });

        mUncalledButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v){
                for(Student s:called) {
                    if (s.calledOverADay()) {
                        called.remove(s);
                        uncalled.add(s.getName());
                    }
                }
                Intent u = Uncalled.newIntent(MainActivity.this,uncalled);
                startActivity(u);
            }
        });

        mCalledButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent c = new Intent(MainActivity.this, Called.class);
                c.putExtra("clist", called);
                startActivity(c);
            }
        });

    }

    public Drawable getDrawable(String name) {
        int resourceId = getResources().getIdentifier(name, "drawable", getPackageName());
        return getDrawable(resourceId);
    }

}