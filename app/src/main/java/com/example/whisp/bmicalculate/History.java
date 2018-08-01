package com.example.whisp.bmicalculate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.whisp.bmicalculate.InClassDatabaseHelper.Tbl_BMIRecords;

public class History extends AppCompatActivity {

    ArrayList<BMIResult> results = new ArrayList<BMIResult>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(Tbl_BMIRecords, new String[]
                {"height", "weight", "bmi", "DATE"}, null, null, null, null, null);


        try {
            while (cursor.moveToNext()) {
                String heightvalue = cursor.getString(0);
                Double heightAsint = Double.parseDouble(heightvalue);

                System.out.println("Here is the height " +heightAsint);

                String weightvalue = cursor.getString(1);
                Double weightAsint = Double.parseDouble(weightvalue);
                String bmivalue = cursor.getString(2);
                Double bmiAsint = Double.parseDouble(bmivalue);

                String datevalue = cursor.getString(3);
                Log.d("Result", datevalue);
                results.add(new BMIResult(heightAsint,weightAsint,bmiAsint,datevalue));


            }
        } finally {

        }


        Log.d("Results empty", ""+results.isEmpty() );
        BMIListAdapter adapter = new BMIListAdapter(this, results);
        ListView listView = (ListView) findViewById(R.id.layoutBMIList);
        listView.setAdapter(adapter);


    }

}
