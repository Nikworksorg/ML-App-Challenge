package com.appchallenge.ml_app_challenge.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class TransactionsModel {
   private HashMap<Integer, ArrayList<TransactionEventModel>> mTransactions;

    public HashMap<Integer, ArrayList<TransactionEventModel>> getmTransactions() {
        return mTransactions;
    }
}
