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
    private final String SHARED_PREFERENCES_KEY = "com.appchallenge.ml_app_challenge.sharedPreferences";
    private final String SHOW_WELCOME = "com.appchallenge.ml_app_challenge.showwelcome";
    private static DataManager mDataManager;
    private ArrayList<TransactionEventModel> mCheckingAccountTransactions;
    private ArrayList<TransactionEventModel> mSavingsAccountTransactions;
    private ArrayList<TransactionEventModel> mTfsaAccountTransactions;
    private ArrayList<HashMap<Integer, ArrayList<TransactionEventModel>>> mOverallTransactions;
    private ArrayList<AccountModel> mAccountModelList;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private DataManager(Context context) {
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
            mAccountModelList = fetchList(context, "listOfAccounts.json",
                    new TypeToken<ArrayList<AccountModel>>(){}.getType());
            mCheckingAccountTransactions = fetchList(context, "chequingAccount.json",
                    new TypeToken<ArrayList<TransactionEventModel>>(){}.getType());
            mSavingsAccountTransactions = fetchList(context, "savingsAccount.json",
                    new TypeToken<ArrayList<TransactionEventModel>>(){}.getType());
            mTfsaAccountTransactions = fetchList(context, "TfsaAccount.json",
                    new TypeToken<ArrayList<TransactionEventModel>>(){}.getType());
            mOverallTransactions = fetchList(context, "accountTransactions.json",
                    new TypeToken<ArrayList<HashMap<Integer, ArrayList<TransactionEventModel>>>>(){}.getType());
        }catch(Exception ex){
            Timber.e("Unable to parse data from file: %s", ex.toString());
        }
    }

    public <T> ArrayList<T> fetchList(Context context, String fileName, Type type) {
        return new Gson().fromJson(loadJSONFromAsset(context, fileName), type);
    }

    public boolean saveShowWelcome(Context context, boolean initialSetup) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, 0);
        return sharedPreferences.edit().putBoolean(SHOW_WELCOME, initialSetup).commit();
    }

    public boolean loadShowWelcome(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, 0);
        return sharedPreferences.getBoolean(SHOW_WELCOME, true);
    }

    public String loadJSONFromAsset(Context context, String fileName) {
        String json = "";

        if(context == null || fileName == null){
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

    public ArrayList<TransactionEventModel> getmCheckingAccountTransactions() {
        return mCheckingAccountTransactions;
    }

    public ArrayList<TransactionEventModel> getmSavingsAccountTransactions() {
        return mSavingsAccountTransactions;
    }

    public ArrayList<TransactionEventModel> getmTfsaAccountTransactions() {
        return mTfsaAccountTransactions;
    }

    public ArrayList<HashMap<Integer, ArrayList<TransactionEventModel>>> getmOverallTransactions() {
        return mOverallTransactions;
    }

    public ArrayList<AccountModel> getmAccountModelList() {
        return mAccountModelList;
    }
}
