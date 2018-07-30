package com.example.whisp.bmicalculate;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BMICalculate extends AppCompatActivity {

    Context context = this;
    EditText recorddate;
    Calendar recordDateCalendar = Calendar.getInstance();
    String recordDateformat = "dd/mm/yy";
    DatePickerDialog.OnDateSetListener date;
    SimpleDateFormat sdf = new SimpleDateFormat(recordDateformat, Locale.CANADA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculate);

        EditText recorddate = (EditText) findViewById(R.id.recordDate);

        // set calendar date and update editDate

        date = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                recordDateCalendar.set(Calendar.YEAR, year);
                recordDateCalendar.set(Calendar.MONTH, monthOfYear);
                recordDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            } };
        // onclick - popup datepicker
        recorddate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, date, recordDateCalendar
                        .get(Calendar.YEAR), recordDateCalendar.get(Calendar.MONTH),
                        recordDateCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



    }

    public void  CalcBMI(View view) {

        boolean invalidData = false;

        //get the height
        EditText height = (EditText) findViewById(R.id.textHeight);
        String heightvalue = height.getText().toString();



        //get the weight

        EditText weight = (EditText) findViewById(R.id.textWeight);
        String weightValue = weight.getText().toString();



        EditText recordDate = (EditText) findViewById(R.id.recordDate);
        String recordDateValue = recordDate.getText().toString();
        System.out.println("Here is the Date " +recordDateValue);



        if (heightvalue.length()==0 ) {
            height.setError("Height is empty!");
            invalidData = true;     }

        else if ( Double.parseDouble(heightvalue) < 1.0 ) {
            height.setError("Height is too short !");
            invalidData = true; }

        else if ( Double.parseDouble(heightvalue) > 2.2) {
            height.setError("Height is too high !");
            invalidData = true; }  else {}





        if (weightValue.length()==0 ) {
            weight.setError("Weight is empty!");
            invalidData = true;     }

        else if ( Double.parseDouble(weightValue) < 40.0 ) {
            weight.setError("Weight is too small!");
            invalidData = true; }

        else if ( Double.parseDouble(weightValue) > 120.0) {
            weight.setError("Weight is too big!");
            invalidData = true; } else {}



        if (recordDateValue.length()==0 ) {
            recordDate.setError("Date is empty!");
            invalidData = true;     }


        if(recordDateValue.length()>0) {
            Date todayDate = Calendar.getInstance().getTime();

            Date dobdate = recordDateCalendar.getTime() ;


            if ( todayDate.getTime() - dobdate.getTime() <0   )

            {   recordDate.setError("Date of BMI check is in Future!");
                invalidData = true;

            }

        }


        if(!invalidData) {


            Double heightAsint = Double.parseDouble(heightvalue);
            Double weightAsInt = Double.parseDouble(weightValue);

            Double Calc = (weightAsInt / (heightAsint * heightAsint));
            System.out.println("Here is the BMI " + Calc);
            EditText result = (EditText) findViewById(R.id.txtBMI);

            // new DecimalFormat("$#.00").format(Calc);

            result.setText(String.format("Value of BMI: %.2f", Calc));


            InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            helper.StoreBMIRecord(heightAsint, weightAsInt, Calc, recordDateValue);


            TextView Suggestion = (TextView) findViewById(R.id.textSuggestion);
            if (Calc < 18.5) {
                Suggestion.setText("your BMI is considered underweight. Consult Doctor!");
                Suggestion.setTextColor(Color.RED);
                Suggestion.setBackgroundColor(Color.BLACK);
                Suggestion.setTextSize(14);
            } else if (Calc < 24.9) {
                Suggestion.setText("your BMI is considered Healthy. Good job!");
                Suggestion.setTextColor(Color.GREEN);
                Suggestion.setBackgroundColor(Color.BLACK);
                Suggestion.setTextSize(14);
            } else if (Calc < 29.9) {
                Suggestion.setText("your BMI is considered overweight. Please exercise!");
                Suggestion.setTextColor(Color.YELLOW);
                Suggestion.setBackgroundColor(Color.BLACK);
                Suggestion.setTextSize(14);
            } else {
                Suggestion.setText("your BMI is considered obese. Consult Doctor!");
                Suggestion.setTextColor(Color.RED);
                Suggestion.setBackgroundColor(Color.BLACK);
                Suggestion.setTextSize(14);
            }
        }else {
            Toast.makeText(this, "Invalid Data!", Toast.LENGTH_SHORT).show();}

    }

    private void updateDate() {
        EditText dob = (EditText) findViewById(R.id.recordDate);
        dob.setError(null);
        dob.setText(sdf.format(recordDateCalendar.getTime()));
    }

    public void  clickMenuPage(View view) {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }


}
