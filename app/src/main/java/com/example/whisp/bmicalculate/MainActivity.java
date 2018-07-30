package com.example.whisp.bmicalculate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call Database and Query Name and Password

        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_NAME,new String[]
                {"NAME","PASSWORD"},null,null,null,null,null);

        // Name is either in Database or should be entered in User Data Activity hence always disabled

        EditText userName = (EditText) findViewById(R.id.textUserName);
        userName.setEnabled(false);



        if (cursor.moveToFirst())  {
            String name = cursor.getString(0);
            Password= cursor.getString(1);
            userName.setText(name);
        } else {

            // No user is registered  , disbale all buttons except registeration

            Button pleaseRegister = (Button) findViewById(R.id.btnRegister);
            pleaseRegister.setVisibility(View.VISIBLE);

            Button login = (Button) findViewById(R.id.btnLogin);
            login.setEnabled(false);

            EditText password = (EditText) findViewById(R.id.textPassword);
            password.setEnabled(false);



        }


    }

    public void  onClickEvent(View view) {

        EditText password = (EditText) findViewById(R.id.textPassword);
        String PasswordValue = password.getText().toString();

        if ( PasswordValue.equals(Password) ) {
            Intent intent = new Intent(this,Menu.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Password Incorrect!", Toast.LENGTH_SHORT).show();

        }
    }

    public void  clickRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }


}
