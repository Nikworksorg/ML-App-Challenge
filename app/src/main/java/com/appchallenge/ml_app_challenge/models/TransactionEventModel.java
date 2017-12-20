package com.appchallenge.ml_app_challenge.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class TransactionEventModel implements Serializable {
    @SerializedName("date")
    String mDate;

    @SerializedName("activity")
    ArrayList<TransactionActivityModel> mTransactionActivities;


    public String getmDate() {
        return mDate;
    }

    public ArrayList<TransactionActivityModel> getmTransactionActivities() {
        return mTransactionActivities;
    }
}
