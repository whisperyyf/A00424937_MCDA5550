package com.example.whisp.bmicalculate;

public class BMIResult {
    private double height =1;
    private double weight =1;
    private String date = "1/1/2018";
    private double bmi = 1;

    public BMIResult(double height, double weight, double bmi, String date){
        this.height = height;
        this.weight = weight;
        this.date = date;
        this.bmi = bmi ;

    }





    public double getHeight(){return height;}
    public void setHeight(double height){this.height = height;}
    public double getbmi(){return bmi;}
    public void setbmi(double bmi){this.bmi = bmi;}
    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}
    public double getWeight(){return weight;}
    public void setWeight(double weight){this.weight = weight;}
    public double getResult(){return weight/(height*height);}

    public String toString(){return String.valueOf(getResult());}


}
