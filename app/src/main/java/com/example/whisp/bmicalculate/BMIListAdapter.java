package com.example.whisp.bmicalculate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BMIListAdapter extends ArrayAdapter<BMIResult> {

    public BMIListAdapter(Context context, ArrayList<BMIResult> BMIResults){super(context, 0, BMIResults);}




    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //  System.out.println("BMIResults  empty  " +BMIResults.is);
        System.out.println("position is  " +position);
        BMIResult BMIresult = getItem(position);
        if(convertView == null)  {convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout, parent, false);}

        TextView RecordDate = (TextView) convertView.findViewById(R.id.txtDateInList);
        TextView RecordHeight = (TextView) convertView.findViewById(R.id.txtHeightInList);
        TextView RecordWeight = (TextView) convertView.findViewById(R.id.txtweightInList);
        TextView RecordBMI = (TextView) convertView.findViewById(R.id.txtBMIInList);

        RecordDate.setText(BMIresult.getDate());
        RecordHeight.setText(String.valueOf(BMIresult.getHeight()));
        RecordWeight.setText(String.valueOf(BMIresult.getWeight()));
        RecordBMI.setText(String.valueOf(BMIresult.getbmi()));

        return convertView;

    }

}




