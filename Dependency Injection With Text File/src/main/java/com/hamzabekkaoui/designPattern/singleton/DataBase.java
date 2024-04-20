package com.hamzabekkaoui.designPattern.singleton;

public class DataBase {

    private static DataBase instance;

    private static int count = 0;
    private DataBase(){
        count++;
    }

    public static synchronized DataBase createInstance(){
        if(instance == null){
             instance = new DataBase();
        }
        return instance;
    }

    public static int getCount(){
        return count;
    }

}
