package com.appchallenge.ml_app_challenge.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import timber.log.Timber;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class DataManager {
    private static DataManager mDataManager;
    private ArrayList<TransactionEvent> mCheckingAccountTransactions;
    private ArrayList<TransactionEvent> mSavingsAccountTransactions;
    private ArrayList<TransactionEvent> mTfsaAccountTransactions;
    private ArrayList<HashMap<Integer, ArrayList<TransactionEvent>>> mOverallTransactions;
    private ArrayList<Account> mAccountList;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public DataManager(Context context) {
        //sharedPreferences = context.getSharedPreferences(sharedPreferenceName, 0); I have to complete this later
        initialiseData(context);
    }

    public static DataManager getInstance(Context context) {
        if (mDataManager == null) {
            mDataManager = new DataManager(context);
        }
        return mDataManager;
    }

    private void initialiseData(Context context){
        try {
            mAccountList = fetchList(context, "listOfAccounts.json");
            mCheckingAccountTransactions = fetchList(context, "chequingAccount.json");
            mSavingsAccountTransactions = fetchList(context, "savingsAccount.json");
            mTfsaAccountTransactions = fetchList(context, "TfsaAccount.json");
            mOverallTransactions = fetchList(context, "accountTransactions.json");
        }catch(Exception ex){
            Timber.i("Unable to parse data from file: %s", ex.toString());
        }
    }

    private <T> ArrayList<T> fetchList(Context context, String fileName) {
        Type type =  new TypeToken<ArrayList<T>>(){}.getType();
        return new Gson().fromJson(loadJSONFromAsset(context, fileName), type);
    }

    private String loadJSONFromAsset(Context context, String fileName) {
        String json = "";

        if(context == null){
            return json;
        }

        if(fileName == null){
            return json;
        }
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Timber.i("json output: %s", json);
        return json;
    }
}
