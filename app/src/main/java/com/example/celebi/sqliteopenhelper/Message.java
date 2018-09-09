package com.example.celebi.sqliteopenhelper;

import android.content.Context;
import android.widget.Toast;

public class Message{
    public static void message(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
