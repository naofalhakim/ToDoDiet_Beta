package com.motionlaboratory.tododiet.Config;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.motionlaboratory.tododiet.LoginActivity;

import java.util.HashMap;

/**
 * Created by naofal on 7/25/2017.
 */

public class SessionPatient {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "PatientSession";

    public static final String KEY_ID = "id";

    public SessionPatient(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void createPatientSession(int id){
        editor.clear();
        editor.commit();
        // Storing login value as TRUE
        editor.putInt(KEY_ID, id);
        editor.commit();
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getPatientId(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_ID, String.valueOf(pref.getInt(KEY_ID,0)));
        // return user
        return user;
    }
}
