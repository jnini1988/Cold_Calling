package com.example.coldcalling;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Locale;

public class Student implements Serializable {
    private String name;
    private String mImgResId;
    private ArrayList<Instant> callTime = new ArrayList<>();
    private static final String TAG="test";

    public Student(String studentName){
        name=studentName;
        mImgResId=getImgName(name);
    }

    public void setName(String sName) {
        name = sName;
    }
    public String getName(){
        return name;
    }

    public void setImgResId(String imgResId) {
        mImgResId = imgResId;
    }
    public String getImgResId() {
        return mImgResId;
    }

    private String getImgName(String name){
        int space = name.indexOf(" ");
        return (name.substring(0,space)+"_"+name.substring(space+1)).toLowerCase(Locale.ROOT);
    }

    public ArrayList<Instant> getCallTime() { return callTime; }

    public int getFreq(){ return callTime.size();}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean calledOverADay(){
        if(Duration.between(Instant.now(),callTime.get(getFreq()-1)).toHours()>24){
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean calledTwiceFM(){
        long millisMin= (long) (Math.pow(2.4,6)*40);
        if(getFreq()>=2 &&
                Duration.between(callTime.get(getFreq()-2),callTime.get(getFreq()-1)).toMillis()<millisMin){
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addTime(){
        callTime.add(Instant.now());
    }
}
