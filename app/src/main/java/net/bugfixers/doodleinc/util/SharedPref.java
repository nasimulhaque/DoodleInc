package net.bugfixers.doodleinc.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private static SharedPref sharedPref;
    private final SharedPreferences sharedPreferences;

    private SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences("DGarage", Context.MODE_PRIVATE);
    }

    public static SharedPref getInstance(Context context) {
        if (sharedPref == null) {
            sharedPref = new SharedPref(context);
        }
        return sharedPref;
    }

    public void saveData(String key, String string) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, string);
        editor.apply();
    }

    public String getData(String key) {
        return sharedPreferences.getString(key, null);
    }
}
