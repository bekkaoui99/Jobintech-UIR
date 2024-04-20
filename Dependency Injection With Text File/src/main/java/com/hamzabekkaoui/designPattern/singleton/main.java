package com.hamzabekkaoui.designPattern.singleton;

public class main {

    public static void main(String[] args) {
        DataBase instance1 = DataBase.createInstance();
        DataBase instance2 = DataBase.createInstance();
        DataBase instance3 = DataBase.createInstance();
        System.out.println(DataBase.getCount());
    }
}
