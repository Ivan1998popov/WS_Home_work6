package ru.myproject.ws_home_work6.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {


   public enum  Keys{
        Login,Token, RefreshToken
    }

    private static SharedPreferences settings=null;


    private static SharedPreferences.Editor editor=null;

    @SuppressLint("CommitPrefEdits")
    public static void init (Context context){
            settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            editor = settings.edit();
    }

    public static SharedPreferences get(){
      return settings;
    }

    static SharedPreferences.Editor edit(){
        return editor;
    }





}
