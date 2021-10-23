package com.example.coldcalling;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

public class listAdapter extends ArrayAdapter<Student> {

    private int resourceLayout;
    private Context mContext;

    public listAdapter(Context context, int resource, List<Student> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }
        Student s = getItem(position);
        TextView tt1 = (TextView) v.findViewById(R.id.nameView);
        TextView tt2 = (TextView) v.findViewById(R.id.freqView);
        TextView tt3 = (TextView) v.findViewById(R.id.recentView);
        tt1.setText(s.getName());
        tt2.setText("Called times: "+s.getFreq());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm:ss")
                .withZone(ZoneId.of("US/Eastern"));
        tt3.setText("Last called on "+formatter.format(s.getCallTime().get(s.getFreq()-1)));
        return v;
    }

}