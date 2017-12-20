package com.appchallenge.ml_app_challenge.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class Transactions {
   private HashMap<Integer, ArrayList<TransactionEvent>> mTransactions;

    public HashMap<Integer, ArrayList<TransactionEvent>> getmTransactions() {
        return mTransactions;
    }
}
