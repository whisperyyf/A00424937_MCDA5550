package com.example.whisp.bmicalculate;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.whisp.bmicalculate.InClassDatabaseHelper.TABLE_NAME;

public class Register extends AppCompatActivity {

    Context context = this;
    EditText dob;
    Calendar dobCalendar = Calendar.getInstance();
    String dobformat = "dd/MM/yy";
    DatePickerDialog.OnDateSetListener date;
    SimpleDateFormat sdf = new SimpleDateFormat(dobformat, Locale.CANADA);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText dob = (EditText) findViewById(R.id.txtDateOfBirth);

        // set calendar date and update editDate

        date = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                dobCalendar.set(Calendar.YEAR, year);
                dobCalendar.set(Calendar.MONTH, monthOfYear);
                dobCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            } };
        // onclick - popup datepicker
        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, date, dobCalendar
                        .get(Calendar.YEAR), dobCalendar.get(Calendar.MONTH),
                        dobCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]
                {"NAME","EMAIL","PASSWORD","HEALTH_CARD_NUMB","DOB"},null,null,null,null,null);



        //     if (cursor.moveToFirst())  {

        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                EditText Username =(EditText) findViewById(R.id.txtName);
                Username.setText(name);

                String email = cursor.getString(1);
                EditText UserEmail =(EditText) findViewById(R.id.txtEmail);
                UserEmail.setText(email);

                String password = cursor.getString(2);
                EditText userPassword =(EditText) findViewById(R.id.textUserPassword);
                userPassword.setText(password);

                //HCN is HEALTH_CARD_NUMB
                String HCN = cursor.getString(3);
                EditText userHCN =(EditText) findViewById(R.id.txtHCN);
                userHCN.setText(HCN);

                //DOB is Date Of Birth
                String DOB = cursor.getString(4);
                EditText userDOB =(EditText) findViewById(R.id.txtDateOfBirth);
                userDOB.setText(DOB);

                Button menuPage = (Button) findViewById(R.id.btnMenu);
                menuPage.setVisibility(View.VISIBLE);


            }} finally {}
    }

    public void  clickLogout(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    public void  clickConfirmUserData(View view) {
        boolean invalidData = false;

        EditText name = (EditText) findViewById(R.id.txtName);
        String nameValue = name.getText().toString();

        EditText email = (EditText) findViewById(R.id.txtEmail);
        String emailValue = email.getText().toString();

        EditText dob = (EditText) findViewById(R.id.txtDateOfBirth);
        String dobValue = dob.getText().toString();

        EditText hcn = (EditText) findViewById(R.id.txtHCN);
        String hcnValue = hcn.getText().toString();

        EditText password = (EditText) findViewById(R.id.textUserPassword);
        String passwordValue = password.getText().toString();


        if (nameValue.length()==0 ) {
            name.setError("Name is empty!");
            invalidData = true;     }

        if (emailValue.length()==0 ) {
            email.setError("Email is empty!");
            invalidData = true;     }

        if( emailValue.length()>0 && (!emailValue.contains(".") || !emailValue.contains("@")))  {
            email.setError("Invalid Email format!");
            invalidData = true;     }

        if (hcnValue.length()==0 ) {
            hcn.setError("Health Card Number is empty!");
            invalidData = true;     }

        if (passwordValue.length()==0 ) {
            password.setError("Password is empty!");
            invalidData = true;     }

        if (passwordValue.length() > 0  && passwordValue.length() < 6 ) {
            password.setError("Password is too short!");
            invalidData = true;     }

        // Date check
        if (dobValue.length()==0 ) {
            dob.setError("Date of Birth is empty!");
            invalidData = true;     }

        if(dobValue.length()>0) {
            Date todayDate = Calendar.getInstance().getTime();

            Date dobdate = dobCalendar.getTime() ;


            if ( todayDate.getTime() - dobdate.getTime() <0   )

            {   dob.setError("Date of Birth is in Future!");
                invalidData = true;

            }

        }


        if(!invalidData){

            Date today = new Date();
            ContentValues personValues = new ContentValues();
            personValues.put("NAME",nameValue);
            personValues.put("EMAIL",emailValue);
            personValues.put("PASSWORD",passwordValue);
            personValues.put("HEALTH_CARD_NUMB",hcnValue);
            personValues.put("DOB", dobValue);

            InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            db.execSQL("delete from "+ TABLE_NAME);
            db.insert(TABLE_NAME,null,personValues);

            Toast.makeText(this, "User Data Saved", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(this,Menu.class);
            startActivity(intent); } else {
            Toast.makeText(this, "Invalid Data!", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateDate() {
        EditText dob = (EditText) findViewById(R.id.txtDateOfBirth);
        dob.setError(null);
        dob.setText(sdf.format(dobCalendar.getTime()));
    }

    public void  clickGoMenuPage(View view) {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }

}
