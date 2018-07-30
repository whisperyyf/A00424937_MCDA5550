package com.example.whisp.bmicalculate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void  clickBMIRecordlist(View view) {
        Intent intent = new Intent(this,History.class);
        startActivity(intent);
    }

    public void  clickLogout(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void  clickCalcBMI(View view) {
        Intent intent = new Intent(this,BMICalculate.class);
        startActivity(intent);
    }

    public void  clickEditUserData(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

}